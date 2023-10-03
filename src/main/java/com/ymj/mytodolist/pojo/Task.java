package com.ymj.mytodolist.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {
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

    public Task(Integer taskId) {
        this.taskId = taskId;
    }
}
