package com.ymj.mytodolist.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    private Integer uuid;
    private Integer projectId;
    private String title;
    private String description;
    private List<Task> tasks;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}
