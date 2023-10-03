package com.ymj.mytodolist.service.impl;

import com.ymj.mytodolist.exception.NullArgumentException;
import com.ymj.mytodolist.mapper.TaskMapper;
import com.ymj.mytodolist.pojo.Task;
import com.ymj.mytodolist.pojo.User;
import com.ymj.mytodolist.service.ProjectService;
import com.ymj.mytodolist.service.TaskService;
import com.ymj.mytodolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;

    /**
     * 获取指定时间（天）的所有任务
     *
     * @param user 用户信息
     * @param time 指定时间
     * @return 返回一个以time所在日为截止日期的任务的列表
     */
    @Override
    public List<Task> getTasksByUsernameAndTime(User user, LocalDateTime time) {
        Integer uuid = userService.getUuidByUsername(user);
        List<Integer> tasksId = taskMapper.getTasksIdByUuid(uuid);
        String today = time.toLocalDate().toString();
        List<Task> tasks = new ArrayList<>();
        for(Integer taskId : tasksId){
            Task task = taskMapper.getTaskByTaskId(new Task(taskId));
            if(dateEqual(task.getEndTime(), today)){
                task.setUuid(uuid);
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * 获取指定时间段内的所有任务
     *
     * @param user      用户信息
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 返回一个包含截止时间在start和end之间的所有任务的列表
     */
    @Override
    public List<Task> getTasksByUsernameAndTime(User user, LocalDateTime startTime, LocalDateTime endTime) {
        Integer uuid = userService.getUuidByUsername(user);
        List<Integer> tasksId = taskMapper.getTasksIdByUuid(uuid);
        List<Task> tasks = new ArrayList<>();
        for(Integer taskId : tasksId){
            Task task = taskMapper.getTaskByTaskId(new Task(taskId));
            if(task.getEndTime().isAfter(startTime) && task.getEndTime().isBefore(endTime)){
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * 添加新任务
     *
     * @param task 一个包含任务信息的任务类
     */
    @Override
    @Transactional
    public void addTask(Task task) {
        if(task == null){
            throw new NullArgumentException("任务信息为空，添加失败");
        }
        taskMapper.insertTask(task);
        if(task.getUuid() != null)
            taskMapper.insertTaskInUser(task);
        if(task.getProjectId() != null)
            taskMapper.insertTaskInProject(task);
    }

    /**
     * 更新任务信息
     *
     * @param task 一个包含任务信息的任务类
     */
    @Override
    public void updateTask(Task task) {
        if(task == null){
            throw new NullArgumentException("任务信息为空，更新失败");
        }
        taskMapper.updateTask(task);
    }

    /**
     * 删除任务
     *
     * @param task 一个包含任务信息的任务类
     */
    @Override
    public void deleteTask(Task task) {
        if(task == null){
            throw new NullArgumentException("任务信息为空，删除失败");
        }
        taskMapper.deleteTask(task);
    }
    private boolean dateEqual(LocalDateTime time1, LocalDateTime time2){
        return time1.toLocalDate().toString().equals(time2.toLocalDate().toString());
    }
    private boolean dateEqual(LocalDateTime time1, String time2){
        return time1.toLocalDate().toString().equals(time2);
    }
}
