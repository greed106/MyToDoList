package com.ymj.mytodolist.service.impl;

import com.ymj.mytodolist.exception.WrongUsernameOrPasswordException;
import com.ymj.mytodolist.mapper.UserMapper;
import com.ymj.mytodolist.pojo.User;
import com.ymj.mytodolist.service.LoginService;
import com.ymj.mytodolist.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;

    /**
     * @param user 一个包含账号密码信息的用户类
     * @return 判断账号密码是否有效的boolean
     */
    @Override
    public User validLogin(User user) {
        User checkValid = userMapper.getUserByUsernameAndPassword(user);
        if(checkValid == null){
            throw new WrongUsernameOrPasswordException("用户名或密码错误");
        }
        checkValid.setLastLoginTime(LocalDateTime.now());
        userMapper.updateLastLoginTime(checkValid);
        return checkValid;
    }

    @Override
    public String getJwt(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());

        Long expire = 3*60*60*1000L; //三个小时
        return JwtUtils.generateJwt(claims, expire);
    }
}
