package com.ymj.mytodolist.service;

import com.ymj.mytodolist.pojo.Project;
import com.ymj.mytodolist.pojo.Task;
import com.ymj.mytodolist.pojo.User;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskService {
    /**
     * 获取指定时间（天）的所有任务
     * @param user 用户信息
     * @param time 指定时间
     * @return 返回一个包含所有任务的列表
     */
    List<Task> getTasksByUsernameAndTime(User user, LocalDateTime time);
    /**
     * 获取指定时间段内的所有任务
     * @param user 用户信息
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return 返回一个包含截止时间在start和end之间的所有任务的列表
     */
    List<Task> getTasksByUsernameAndTime(User user, LocalDateTime startTime, LocalDateTime endTime);

    /**
     * 添加新任务
     * @param task 一个包含任务信息的任务类
     */
    void addTask(Task task);
    /**
     * 更新任务信息
     * @param task 一个包含任务信息的任务类
     */
    void updateTask(Task task);
    /**
     * 删除任务
     * @param task 一个包含任务信息的任务类
     */
    void deleteTask(Task task);
}
