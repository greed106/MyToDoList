package com.ymj.mytodolist.service;

import com.ymj.mytodolist.pojo.User;

public interface UserService {
    /**
     * 通过用户名获取用户
     * @param user 一个包含用户名信息的用户类
     * @return 返回一个包含用户个人信息（不含任务信息）的用户类
     */
    User getUserByUsername(User user);
    /**
     * 通过用户名获取用户的uuid
     * @param user 一个包含用户名信息的用户类
     * @return 返回一个uuid
     */
    Integer getUuidByUsername(User user);
    /**
     * 更新用户的个人信息（主要是密码信息，其他的信息不可修改）
     * @param user 一个包含用户名信息的用户类
     */
    void updateUser(User user);
    /**
     * 删除用户
     * @param user 一个包含用户名信息的用户类
     */
    void deleteUser(User user);
    /**
     * 添加用户（应当只有超级管理员有这个方法的权限）
     * @param user 一个包含用户名信息的用户类
     */
    void addUser(User user);
}
