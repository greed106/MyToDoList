package com.ymj.mytodolist;

import com.ymj.mytodolist.mapper.UserMapper;
import com.ymj.mytodolist.pojo.User;
import com.ymj.mytodolist.utils.EmailVerificationUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class MyToDoListApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    void contextLoads() {
        User user = new User();
        user.setUsername("ymj");
        user.setEmail("yangmingjia@bupt.edu.cn");
        user.setPassword("123");
        user.setCreatedTime(LocalDateTime.now());
        user.setUpdatedTime(user.getCreatedTime());
        userMapper.insertUser(user);
    }

}
