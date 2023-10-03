package com.ymj.mytodolist.service;

import com.ymj.mytodolist.pojo.User;


public interface LoginService {
    /**
     * @param user 一个包含账号密码信息的用户类
     * @return 返回得到的实体user
     */
    User validLogin(User user);

    /**
     * @param user 一个包含账号密码信息的用户类
     * @return 返回得到的jwt
     */
    String getJwt(User user);
}
