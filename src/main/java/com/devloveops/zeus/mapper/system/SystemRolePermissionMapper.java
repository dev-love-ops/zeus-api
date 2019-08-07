package com.devloveops.zeus.mapper.system;

import com.devloveops.zeus.domain.system.SystemPermission;
import com.devloveops.zeus.domain.system.SystemUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author RockyWu
 * @date 2019-07-27 14:49
 */
public interface SystemRolePermissionMapper {
    void insert(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
    void deleteByRoleId(@Param("roleId") Integer roleId);
    void deleteByRolePerm(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
    List<Integer> selectByRoleId(@Param("roleId") Integer roleId);

    /**
     * @param roleId 比如ROLE_ADMIN
     * @return
     */
    List<String> selectPermissionIdByRoleId(@Param("roleId") String roleId);
    List<SystemPermission> selectPermissionByRoleId(@Param("roleId") String roleId);
}
