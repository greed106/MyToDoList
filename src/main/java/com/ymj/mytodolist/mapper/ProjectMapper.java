package com.ymj.mytodolist.mapper;

import com.ymj.mytodolist.pojo.Project;
import com.ymj.mytodolist.pojo.Task;
import com.ymj.mytodolist.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProjectMapper {

    @Select("select * from mydatabase.project_info where project_id=#{projectId}")
    Project getProjectByProjectId(Project project);

    @Select("select * from mydatabase.project_info where title=#{title}")
    Project getProjectByTittle(Project project);

    @Select("select * from mydatabase.user_project where uuid=#{uuid}")
    List<Project> getProjectsByUuid(User user);

    @Select("select project_id from mydatabase.project_task where task_id=#{taskId}")
    Integer getProjectIdByTaskId(Task task);
    @Insert("insert into mydatabase.project_info(title,description,created_time,updated_time)" +
            " values(#{title},#{description},#{createdTime},#{updatedTime})")
    void insertProject(Project project);

    @Delete("delete from mydatabase.project_info where project_id=#{projectId}")
    void deleteProject(Project project);

    @Delete("delete from mydatabase.project_task where project_id=#{projectId}")
    void deleteProjectTasks(Project project);

    void updateProject(Project project);
}
