package me.rockywu.zeus.mapper;

import me.rockywu.zeus.domain.Role;
import me.rockywu.zeus.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author RockyWu
 * @date 2019-01-19 23:38
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO user(username, password, mobile) VALUES(#{username}, #{password}, #{mobile})")
    int createUser(User user);


    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "mobile", column = "mobile"),
            @Result(property = "password", column = "password"),
            //下面的column是userId, 是传递给select后面那个方法的参数
            @Result(property = "roles", column = "id", many = @Many(select = "getRoleByUserId"))
    })
    @Select("SELECT * from user where username=#{username}")
    User getUserByName(@Param("username") String username);

    @Select("SELECT username FROM user")
    List<String> getAllUserNames();

    @Select("SELECT * FROM user")
    List<User> getAllUsers();

    /**
     * 通过角色ID查找用户列表
     *
     * @param userId
     * @return
     */
    @Select("select * from role where id in(select role_id from user_role where user_id=#{user_id})")
    List<Role> getRoleByUserId(@Param("user_id") int userId);

}
