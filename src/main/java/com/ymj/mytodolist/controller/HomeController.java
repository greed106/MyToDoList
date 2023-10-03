package com.ymj.mytodolist.controller;


import com.ymj.mytodolist.pojo.DTO.AddProjectRequest;
import com.ymj.mytodolist.pojo.DTO.AddTaskRequest;
import com.ymj.mytodolist.pojo.DTO.TodayTasksRequest;
import com.ymj.mytodolist.pojo.DTO.UserInfoRequest;
import com.ymj.mytodolist.pojo.Project;
import com.ymj.mytodolist.pojo.Result;
import com.ymj.mytodolist.pojo.Task;
import com.ymj.mytodolist.pojo.User;
import com.ymj.mytodolist.service.ProjectService;
import com.ymj.mytodolist.service.TaskService;
import com.ymj.mytodolist.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Slf4j
public class HomeController {
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private TaskService taskService;

    @PostMapping("/todolist/userinfo")
    public Result getUserInfo(@RequestBody User user){
        log.info("获取用户信息:{}", user.getUsername());
        return Result.success(userService.getUserByUsername(user));
    }
    @PostMapping("/todolist/todaytask")
    public Result getTodayTasks(@RequestBody TodayTasksRequest request){
        log.info("获取用户{}的今日任务", request.getUsername());
        User user = request.getUser();
        List<Task> tasks = taskService.getTasksByUsernameAndTime(user, request.getTime());
        return Result.success(tasks);
    }
    @PostMapping("/todolist/addtask")
    public Result addTask(@RequestBody AddTaskRequest request){
        Task task = request.getTask();
        task.setUuid(userService.getUuidByUsername(User.getUser(request.getUsername())));
        log.info("添加任务:{}", task);
        taskService.addTask(task);
        return Result.success();
    }
    @PostMapping("todolist/addproject")
    public Result addProject(@RequestBody AddProjectRequest request){
        Project project = request.getProject();
        project.setUuid(userService.getUuidByUsername(User.getUser(request.getUsername())));
        log.info("添加项目:{}", project);
        projectService.addProject(project);
        return Result.success();
    }
}
