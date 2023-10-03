package com.ymj.mytodolist.pojo.DTO;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymj.mytodolist.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodayTasksRequest {
    private String username;
    private LocalDateTime time;
    public User getUser(){
        return new User(username, null, null, null, null, null);
    }
}
