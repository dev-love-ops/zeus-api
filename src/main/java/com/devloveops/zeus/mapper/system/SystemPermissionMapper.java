package com.devloveops.zeus.mapper.system;

import com.devloveops.zeus.domain.system.SystemPermission;
import com.devloveops.zeus.domain.system.SystemRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author RockyWu
 * @date 2019-08-01 14:55
 */
public interface SystemPermissionMapper {
    int insert(SystemPermission systemPermission);
    int deleteByPrimaryId(@Param("id") Integer id);
    int updateByPrimaryKey(SystemPermission systemPermission);
    List<SystemPermission> selectByLevel(@Param("level") Integer level);
    List<SystemPermission> selectByParentId(@Param("parentId") Integer parentId);
}
