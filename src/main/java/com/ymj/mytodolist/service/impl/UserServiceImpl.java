package com.ymj.mytodolist.service.impl;

import com.ymj.mytodolist.exception.DuplicateUsernameException;
import com.ymj.mytodolist.exception.NotFoundException;
import com.ymj.mytodolist.exception.NullArgumentException;
import com.ymj.mytodolist.mapper.UserMapper;
import com.ymj.mytodolist.pojo.User;
import com.ymj.mytodolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 通过用户名获取用户
     *
     * @param user 一个包含用户名信息的用户类
     * @return 返回一个包含用户个人信息（不含任务信息）的用户类
     */
    @Override
    public User getUserByUsername(User user) {
        User valid = userMapper.getUserByUsername(user);
        if(valid == null){
            throw new NotFoundException("该用户不存在");
        }
        //用户信息脱敏
        valid.setPassword(null);
        return valid;
    }

    /**
     * 通过用户名获取用户的uuid
     *
     * @param user 一个包含用户名信息的用户类
     * @return 返回一个uuid
     */
    @Override
    public Integer getUuidByUsername(User user) {
        Integer uuid = userMapper.getUuidByUsername(user);
        if(uuid == null){
            throw new NotFoundException("该用户不存在");
        }
        return uuid;
    }

    /**
     * 更新用户的个人信息（主要是密码信息，其他的信息不可修改）
     *
     * @param user 一个包含用户名信息的用户类
     */
    @Override
    @Transactional
    public void updateUser(User user) {
        User valid = userMapper.getUserByUsername(user);
        if(valid == null){
            throw new NotFoundException("该用户不存在，更新失败");
        }
        user.setUpdatedTime(LocalDateTime.now());
        userMapper.updateUser(user);
    }

    /**
     * 删除用户
     *
     * @param user 一个包含用户名信息的用户类
     */
    @Override
    @Transactional
    public void deleteUser(User user) {
        User valid = userMapper.getUserByUsername(user);
        if(valid == null){
            throw new NotFoundException("该用户不存在，删除失败");
        }
        userMapper.deleteUser(user);
        userMapper.deleteUserTasks(user);
        userMapper.deleteUserProjects(user);
    }

    /**
     * 添加用户（应当只有超级管理员有这个方法的权限）
     *
     * @param user 一个包含用户名信息的用户类
     */
    @Override
    @Transactional
    public void addUser(User user) {
        if(user == null){
            throw new NullArgumentException("用户信息不能为空");
        }
        User valid = userMapper.getUserByUsername(user);
        if(valid != null){
            throw new DuplicateUsernameException("该用户已存在，添加失败");
        }
        user.setCreatedTime(LocalDateTime.now());
        userMapper.insertUser(user);
    }
}
