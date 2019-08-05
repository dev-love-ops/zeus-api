package com.devloveops.zeus.service.system;

import com.devloveops.zeus.domain.system.SystemPermission;
import com.devloveops.zeus.domain.system.SystemRole;
import com.devloveops.zeus.domain.system.SystemRoleExample;
import com.devloveops.zeus.mapper.system.SystemPermissionMapper;
import com.devloveops.zeus.mapper.system.SystemRoleMapper;
import com.devloveops.zeus.support.constant.SystemConstant;
import com.devloveops.zeus.support.exception.system.ExistsException;
import com.devloveops.zeus.support.query.QuerySystemRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author RockyWu
 * @date 2019-07-15 20:58
 */
@Service
public class PermissionService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemPermissionMapper systemPermissionMapper;

    public List<SystemPermission>  getAllPermission(){
        //一级菜单列表
        List<SystemPermission> level0PermList = systemPermissionMapper.selectByLevel(SystemConstant.LEVEL_ZERO);
        for (SystemPermission sp0: level0PermList) {
            List<SystemPermission> level1PermList = systemPermissionMapper.selectByParentId(sp0.getId());
            sp0.setChildren(level1PermList);
            for (SystemPermission sp1: level1PermList) {
                List<SystemPermission> level2PermList = systemPermissionMapper.selectByParentId(sp1.getId());
                sp1.setChildren(level2PermList);
            }
        }
        return level0PermList;

    }

    public void updateByPrimaryKey(SystemPermission systemPermission){

        systemPermissionMapper.updateByPrimaryKey(systemPermission);

    }

    public void insert(SystemPermission systemPermission){

        systemPermissionMapper.insert(systemPermission);

    }

    public void batchDeleteById(List<Integer> ids){

        for (Integer id: ids) {
            systemPermissionMapper.deleteByPrimaryId(id);
        }

    }


}
