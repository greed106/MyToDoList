package com.ymj.mytodolist.service;

import com.ymj.mytodolist.pojo.Project;
import com.ymj.mytodolist.pojo.Task;
import com.ymj.mytodolist.pojo.User;

import java.util.List;

public interface ProjectService {
    /**
     * 获取指定用户的所有项目
     * @param user 用户信息
     * @return 返回一个包含所有项目的列表
     */
    List<Project> getProjectsByUsername(User user);
    /**
     * 通过任务id获取项目id
     * @param task 一个包含任务信息的任务类
     * @return 返回一个项目id
     */
    Integer getProjectIdByTaskId(Task task);
    /**
     * 添加新项目
     * @param project 一个包含项目信息的项目类
     */
    void addProject(Project project);
    /**
     * 更新项目信息
     * @param project 一个包含项目信息的项目类
     */
    void updateProject(Project project);
    /**
     * 删除项目
     * @param project 一个包含项目信息的项目类
     */
    void deleteProject(Project project);
}
