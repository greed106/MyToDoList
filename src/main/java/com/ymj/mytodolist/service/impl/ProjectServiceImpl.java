package com.ymj.mytodolist.service.impl;

import com.ymj.mytodolist.exception.NotFoundException;
import com.ymj.mytodolist.exception.NullArgumentException;
import com.ymj.mytodolist.mapper.ProjectMapper;
import com.ymj.mytodolist.pojo.Project;
import com.ymj.mytodolist.pojo.Task;
import com.ymj.mytodolist.pojo.User;
import com.ymj.mytodolist.service.ProjectService;
import com.ymj.mytodolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private UserService userService;
    /**
     * 获取指定用户的所有项目
     *
     * @param user 用户信息
     * @return 返回一个包含所有项目的列表
     */
    @Override
    public List<Project> getProjectsByUsername(User user) {
        User valid = userService.getUserByUsername(user);
        List<Project> projects = projectMapper.getProjectsByUuid(valid);
        return projects;
    }

    /**
     * 通过任务id获取项目id
     *
     * @param task 一个包含任务信息的任务类
     * @return 返回一个项目id
     */
    @Override
    public Integer getProjectIdByTaskId(Task task) {
        Integer valid = projectMapper.getProjectIdByTaskId(task);
        if(valid == null){
            throw new NotFoundException("该项目不存在");
        }
        return valid;
    }

    /**
     * 添加新项目
     *
     * @param project 一个包含项目信息的项目类
     */
    @Override
    @Transactional
    public void addProject(Project project) {
        if(project == null){
            throw new NullArgumentException("项目信息不能为空");
        }
        projectMapper.insertProject(project);
    }

    /**
     * 更新项目信息
     *
     * @param project 一个包含项目信息的项目类
     */
    @Override
    @Transactional
    public void updateProject(Project project) {
        Project valid = projectMapper.getProjectByProjectId(project);
        if(valid == null){
            throw new NotFoundException("该项目不存在，更新失败");
        }
        projectMapper.updateProject(project);
    }

    /**
     * 删除项目
     *
     * @param project 一个包含项目信息的项目类
     */
    @Override
    @Transactional
    public void deleteProject(Project project) {
        Project valid = projectMapper.getProjectByProjectId(project);
        if(valid == null){
            throw new NotFoundException("该项目不存在，删除失败");
        }
        projectMapper.deleteProjectTasks(project);
        projectMapper.deleteProject(project);
    }
}
