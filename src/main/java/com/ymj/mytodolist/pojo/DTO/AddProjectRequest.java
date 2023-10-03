package com.ymj.mytodolist.pojo.DTO;

import com.ymj.mytodolist.pojo.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddProjectRequest {
    private Integer uuid;
    private String username;
    private Integer projectId;
    private String title;
    private String description;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    public Project getProject(){
        return new Project(uuid, projectId, title, description, null, createdTime, updatedTime);
    }
}
