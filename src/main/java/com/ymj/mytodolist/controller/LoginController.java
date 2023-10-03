package com.ymj.mytodolist.controller;

import com.ymj.mytodolist.pojo.Result;
import com.ymj.mytodolist.pojo.User;
import com.ymj.mytodolist.service.LoginService;
import com.ymj.mytodolist.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin("*")
@Slf4j
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/todolist/login")
    public Result validLogin(@RequestBody User user){
        log.info("用户登录请求："+user);
        User valid = loginService.validLogin(user);
        String jwt = loginService.getJwt(valid);
        log.info("用户{}登录成功，下发jwt",user.getUsername());
        return Result.success(jwt);
    }
}
