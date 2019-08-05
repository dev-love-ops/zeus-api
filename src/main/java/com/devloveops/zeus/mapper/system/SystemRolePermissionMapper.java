package com.devloveops.zeus.mapper.system;

import com.devloveops.zeus.domain.system.SystemUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author RockyWu
 * @date 2019-07-27 14:49
 */
public interface SystemRolePermissionMapper {
    void insert(SystemUserRole systemUserRole);
    void deleteByUserId(@Param("userId") String userId);
    void deleteByUserRole(SystemUserRole systemUserRole);
    List<String> selectByRoleId(@Param("roleId") String roleId);
}
