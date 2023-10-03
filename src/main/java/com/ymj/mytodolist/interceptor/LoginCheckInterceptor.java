package com.ymj.mytodolist.interceptor;



import com.ymj.mytodolist.exception.NotLoggedInException;
import com.ymj.mytodolist.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component  //成为一个bean,交给IOC容器管理
@Slf4j      //输出日志
public class LoginCheckInterceptor implements HandlerInterceptor {

    // 在目标方法运行前运行
    // 返回true：放行
    // 返回false：不放行
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        Enumeration<String> headers = request.getHeaderNames();
//        while (headers.hasMoreElements()) {
//            String header = headers.nextElement();
//            System.out.println(header + ": " + request.getHeader(header));
//        }
        if(request.getMethod().equals("OPTIONS")){
            log.info("请求方法为OPTIONS，拦截但返回200");
            response.setStatus(HttpStatus.OK.value());
            return true;
        }




        //获取请求的url
        String url = request.getRequestURL().toString();
        log.info("请求的url:"+url);

        //获取请求头中的令牌(Authorization)
        String jwt = request.getHeader("Authorization");

        //判断令牌是否存在，如果不存在，返回错误结果（未登录）
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头Authorization为空，返回未登录信息");
            //使用全局异常处理器机制处理抛出的异常
            throw new NotLoggedInException("用户尚未登陆");
        }

        //解析JWT令牌，判断是否合法
        //如果解析失败，返回错误结果（未登录）
        try{
            JwtUtils.parseJWT(jwt);
        }catch(Exception e){
            e.printStackTrace();
            log.info("令牌解析失败，返回未登录的错误信息");
            throw new NotLoggedInException("令牌解析错误");
        }
        log.info("令牌解析成功，放行");
        //令牌验证通过，放行
        return true;
    }
}
