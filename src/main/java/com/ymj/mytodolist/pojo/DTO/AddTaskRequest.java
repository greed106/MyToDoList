package com.ymj.mytodolist.pojo.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ymj.mytodolist.pojo.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddTaskRequest {
    private Integer uuid;
    private Integer taskId;
    private Integer projectId;
    private String title;
    private String description;
    private Boolean completed;
    private Boolean overdue;
    private LocalDateTime endTime;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String username;
    public Task getTask() {
        return new Task(uuid, taskId, projectId, title, description, completed, overdue, endTime, createdTime, updatedTime);
    }
}
