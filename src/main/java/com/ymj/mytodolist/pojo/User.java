package com.ymj.mytodolist.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private LocalDateTime lastLoginTime;
    static public User getUser(String username){
        return new User(username, null, null, null, null, null);
    }
}
