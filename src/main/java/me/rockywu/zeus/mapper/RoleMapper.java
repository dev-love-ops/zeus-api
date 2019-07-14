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
public interface RoleMapper {

    /**
     * 通过角色名称获取角色对象
     * @param rolename 角色名称
     * @return
     */
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            //下面的column是roleId, 是传递给select后面那个方法的参数
            @Result(property = "users", column = "id", many = @Many(select = "getUserByRoleId"))

    })
    @Select("select * from role where name=#{rolename}")
    Role getRoleByName(@Param("rolename") String rolename);

    /**
     * 通过用户名查找该用户的角色列表
     * @param roleId 角色ID
     * @return
     */
    @Select("select * from role where rolename in(select rolename from user_role where username=#{username})")
    List<User> getUserByRoleId(@Param("role_id") String roleId);

}
