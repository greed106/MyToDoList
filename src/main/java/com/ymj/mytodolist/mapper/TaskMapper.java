package com.ymj.mytodolist.mapper;

import com.ymj.mytodolist.pojo.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskMapper {

    @Select("select * from mydatabase.task_info where task_id=#{taskId}")
    Task getTaskByTaskId(Task task);

    @Select("select * from mydatabase.task_info where title=#{title}")
    Task getTaskByTitle(Task task);

    @Select("select task_id from mydatabase.user_task where uuid=#{uuid}")
    List<Integer> getTasksIdByUuid(Integer uuid);

    @Select("select * from mydatabase.project_task where project_id=#{projectId}")
    List<Task> getTasksByProjectId(Integer projectId);

    @Insert("insert into mydatabase.task_info(title,description,created_time,updated_time,completed,end_time,overdue)" +
            " values(#{title},#{description},#{createdTime},#{updatedTime},#{completed},#{endTime},#{overdue})")
    @Options(useGeneratedKeys = true, keyProperty = "taskId")
    void insertTask(Task task);

    @Insert("insert into mydatabase.user_task(uuid, task_id) VALUES (#{uuid},#{taskId})")
    void insertTaskInUser(Task task);

    @Insert("insert into mydatabase.project_task(project_id, task_id) VALUES (#{projectId},#{taskId})")
    void insertTaskInProject(Task task);

    @Delete("delete from mydatabase.task_info where task_id=#{taskId}")
    void deleteTask(Task task);

    @Delete("delete from mydatabase.user_task where task_id=#{taskId}")
    void deleteTaskInUser(Task task);

    @Delete("delete from mydatabase.project_task where task_id=#{taskId}")
    void deleteTaskInProject(Task task);

    void updateTask(Task task);
}
