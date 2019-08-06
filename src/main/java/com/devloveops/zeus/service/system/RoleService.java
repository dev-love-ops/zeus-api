package com.devloveops.zeus.service.system;

import com.devloveops.zeus.domain.system.*;
import com.devloveops.zeus.mapper.system.SystemPermissionMapper;
import com.devloveops.zeus.mapper.system.SystemRoleMapper;
import com.devloveops.zeus.mapper.system.SystemRolePermissionMapper;
import com.devloveops.zeus.support.exception.system.ExistsException;
import com.devloveops.zeus.support.query.QuerySystemRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author RockyWu
 * @date 2019-07-15 20:58
 */
@Service
public class RoleService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Autowired
    private SystemRolePermissionMapper systemRolePermissionMapper;

    public PageInfo<SystemRole>  getRoleList(QuerySystemRole queryCondition){
        //分页
        PageHelper.startPage(queryCondition.getPageNo(), queryCondition.getPageSize());
        //从数据库中查询的数据
        List<SystemRole> systemRoles = systemRoleMapper.selectByQueryCondition(queryCondition);
        return new PageInfo<>(systemRoles);
    }

    public void createRole(SystemRole systemRole) throws ExistsException {
        //判断是否已经存在

        SystemRole systemRoleExist =  systemRoleMapper.selectByRoleId(systemRole);

        if (systemRoleExist != null){
            throw new ExistsException("角色已存在");
        }
        Date now = new Date();
        systemRole.setCreateTime(now);
        systemRoleMapper.insertSelective(systemRole);
    }

    public void modifyRole(SystemRole systemRole){
        systemRoleMapper.updateByPrimaryKeySelective(systemRole);
    }

    public void modifyRolePerm(SystemRole systemRole){

        List<Integer> newPermissions = systemRole.getPermissions();
        List<Integer> existedPermissions = systemRolePermissionMapper.selectByRoleId(systemRole.getId());

        if (newPermissions.size() == 0){
            if (existedPermissions.size() != 0){
                systemRolePermissionMapper.deleteByRoleId(systemRole.getId());
            }
        } else {
            for (Integer nPerm : newPermissions) {
                if (!existedPermissions.contains(nPerm)){
                    systemRolePermissionMapper.insert(systemRole.getId(), nPerm);
                }
            }

            if (existedPermissions.size() != 0){
                for (Integer ePermId: existedPermissions) {
                    if (!newPermissions.contains(ePermId)){
                        systemRolePermissionMapper.deleteByRolePerm(systemRole.getId(), ePermId);
                    }
                }
            }
        }


    }

    public void deleteRole(SystemRole systemRole){
        systemRoleMapper.deleteByRoleId(systemRole);
    }

}
