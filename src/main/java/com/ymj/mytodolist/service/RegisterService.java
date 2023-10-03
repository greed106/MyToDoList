package com.ymj.mytodolist.service;

import com.ymj.mytodolist.pojo.User;

public interface RegisterService {
    /**
     * 检测用户名是否合法（重名）
     * @param user 一个包含用户名信息的用户类
     * @return 返回是否合法
     */
    boolean isValidUsername(User user);

    /**
     * 检测验证码是否合法（验证码解析jwt令牌）
     * @param user 包含邮箱信息的用户类
     * @param jwt 验证码
     * @return 返回是否合法
     */
    boolean isValidCode(User user, String jwt);

    /**
     * 检测邮箱是否合法（邮箱是否已经被注册）
     * @param user 包含邮箱信息的用户类
     * @return 返回是否合法
     */
    boolean isValidEmail(User user);

    /**
     * 注册用户，将用户信息存入数据库
     * @param user 一个包含账号密码信息的用户类
     */
    void register(User user);
}
