package com.devloveops.zeus.service.system;

import com.devloveops.zeus.domain.system.SystemRole;
import com.devloveops.zeus.domain.system.SystemRoleExample;
import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.domain.system.SystemUserExample;
import com.devloveops.zeus.mapper.system.SystemRoleMapper;
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

        SystemRoleExample systemRoleExample = new SystemRoleExample();
        systemRoleExample.createCriteria().andNameEqualTo(systemRole.getRoleName());
        //根据systemRoleExample修改systemRole不为null的值
        systemRoleMapper.updateByExampleSelective(systemRole, systemRoleExample);
    }

    public void deleteRole(SystemRole systemRole){
        systemRoleMapper.deleteByRoleId(systemRole);
    }

}
