package com.ymj.mytodolist.mapper;


import com.ymj.mytodolist.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Select("select * from mydatabase.user_info where username=#{username}")
    User getUserByUsername(User user);

    @Select("select uuid from mydatabase.user_info where username=#{username}")
    Integer getUuidByUsername(User user);

    @Select("select * from mydatabase.user_info where username=#{username} and password=#{password}")
    User getUserByUsernameAndPassword(User user);

    @Select("select * from mydatabase.user_info where email=#{email}")
    User getUserByEmail(User user);

    @Insert("insert into mydatabase.user_info(username,password,created_time,updated_time,email) values(#{username},#{password},#{createdTime},#{updatedTime},#{email})")
    void insertUser(User user);

    @Delete("delete from mydatabase.user_info where username=#{username}")
    void deleteUser(User user);

    @Delete("delete from mydatabase.user_task where uuid=#{uuid}")
    void deleteUserTasks(User user);

    @Delete("delete from mydatabase.user_project where uuid=#{uuid}")
    void deleteUserProjects(User user);

    void updateUser(User user);

    @Update("update mydatabase.user_info set last_login_time=#{lastLoginTime} where username=#{username}")
    void updateLastLoginTime(User user);
}
