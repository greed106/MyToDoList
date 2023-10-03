package com.ymj.mytodolist.service.impl;

import com.ymj.mytodolist.exception.DuplicateEmailException;
import com.ymj.mytodolist.exception.DuplicateUsernameException;
import com.ymj.mytodolist.exception.WrongCaptchaException;
import com.ymj.mytodolist.mapper.UserMapper;
import com.ymj.mytodolist.pojo.User;
import com.ymj.mytodolist.service.RegisterService;
import com.ymj.mytodolist.utils.EmailVerificationUtils;
import com.ymj.mytodolist.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private EmailVerificationUtils emailVerificationUtils;
    @Autowired
    private UserMapper userMapper;

    /**
     * 检测用户名是否合法（重名）
     *
     * @param user 一个包含用户名信息的用户类
     * @return 返回是否合法
     */
    @Override
    public boolean isValidUsername(User user) {
        User valid = userMapper.getUserByUsername(user);
        if(valid != null){
            throw new DuplicateUsernameException("该用户名已被注册");
        }
        return true;
    }

    /**
     * 检测邮箱是否合法（验证码解析jwt令牌）
     *
     * @param user 包含邮箱信息的用户类
     * @return 返回是否合法
     */
    @Override
    public boolean isValidCode(User user, String jwt) {
        try{
            Claims claims = JwtUtils.parseJWT(jwt);
            String email = (String)claims.get("email");
            String username = (String)claims.get("username");
            if(!(email.equals(user.getEmail()) && username.equals(user.getUsername()))){
                throw new WrongCaptchaException("用户名和邮箱不匹配");
            }
        }catch (WrongCaptchaException e){
            throw e;
        }catch (Exception e){
            throw new WrongCaptchaException("无效的验证令牌");
        }
        return true;
    }

    /**
     * 检测邮箱是否合法（邮箱是否已经被注册）
     *
     * @param user 包含邮箱信息的用户类
     * @return 返回是否合法
     */
    @Override
    public boolean isValidEmail(User user) {
        User valid = userMapper.getUserByEmail(user);
        if(valid != null){
            throw new DuplicateEmailException("该邮箱已被注册");
        }
        return true;
    }

    /**
     * 注册用户，将用户信息存入数据库
     *
     * @param user 一个包含账号密码信息的用户类
     */
    @Override
    public void register(User user) {
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(user.getCreatedTime());
        userMapper.insertUser(user);
    }
}
