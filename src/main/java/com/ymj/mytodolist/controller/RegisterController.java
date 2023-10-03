package com.ymj.mytodolist.controller;

import com.ymj.mytodolist.pojo.DTO.UserRegistrationRequest;
import com.ymj.mytodolist.pojo.Result;
import com.ymj.mytodolist.pojo.User;
import com.ymj.mytodolist.service.RegisterService;
import com.ymj.mytodolist.utils.EmailVerificationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@Slf4j
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @Autowired
    private EmailVerificationUtils emailVerificationUtils;

    @PostMapping("/todolist/register/valid")
    public Result validUsername(@RequestBody User user){
        log.info("检测用户名是否合法："+user.getUsername());
        registerService.isValidUsername(user);
        log.info("用户名合法");
        log.info("检测邮箱是否被注册："+user.getEmail());
        registerService.isValidEmail(user);
        log.info("邮箱未被注册，发送验证令牌");
        emailVerificationUtils.sendVerificationCode(user);
        log.info("验证令牌已发送");
        return Result.success();
    }
    @PostMapping("/todolist/register/code")
    public Result validEmail(@RequestBody UserRegistrationRequest userRequest){
        User user = userRequest.getUser();
        String jwt = userRequest.getJwt();
        log.info("检测邮箱用户名是否匹配："+user.getEmail());
        if(registerService.isValidCode(user,jwt)){
            log.info("{}邮箱验证成功",user.getEmail());
            //验证成功后，将用户加入数据库
            registerService.register(user);
        }

        return Result.success();
    }



}
