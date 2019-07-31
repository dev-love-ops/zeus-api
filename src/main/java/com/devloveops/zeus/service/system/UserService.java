package com.devloveops.zeus.service.system;

import com.devloveops.zeus.domain.system.SystemRole;
import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.domain.system.SystemUserExample;
import com.devloveops.zeus.domain.system.SystemUserRole;
import com.devloveops.zeus.mapper.system.ExSystemUserMapper;
import com.devloveops.zeus.mapper.system.SystemUserMapper;
import com.devloveops.zeus.mapper.system.SystemUserRoleMapper;
import com.devloveops.zeus.support.exception.system.ExistsException;
import com.devloveops.zeus.support.query.QuerySystemUser;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RockyWu
 * @date 2019-07-15 20:58
 */
@Service
public class UserService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SystemUserMapper systemUserMapper;
    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    public PageInfo<SystemUser>  getUserList(QuerySystemUser queryCondition){
        //分页
        PageHelper.startPage(queryCondition.getPageNo(), queryCondition.getPageSize());
        //从数据库中查询的数据
        List<SystemUser> systemUsers = systemUserMapper.selectByQueryCondition(queryCondition);
        return new PageInfo<>(systemUsers);
    }

    public void createUser(SystemUser systemUser) throws ExistsException {
        //判断是否已经存在
        SystemUserExample systemUserExample = new SystemUserExample();
        systemUserExample.createCriteria().andUserIdEqualTo(systemUser.getUserId());

        List<SystemUser> systemUsers =  systemUserMapper.selectByExample(systemUserExample);

        if (systemUsers.size() != 0){
            throw new ExistsException("用户已存在");
        }
        //使用Bcrypt加密密码后存储
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        systemUser.setPassword(encoder.encode(systemUser.getPassword().trim()));
        systemUserMapper.insertSelective(systemUser);
        //如果选择了角色后给用户授权角色
        if (systemUser.getRoles().size() != 0){
            for (String roleId: systemUser.getRoles()) {
                SystemUserRole systemUserRole = new SystemUserRole();
                systemUserRole.setUserId(systemUser.getUserId());
                systemUserRole.setRoleId(roleId);
                systemUserRoleMapper.insert(systemUserRole);
            }
        }
    }

    public void modifyUser(SystemUser systemUser){

        SystemUserExample systemUserExample = new SystemUserExample();
        systemUserExample.createCriteria().andUserIdEqualTo(systemUser.getUserId());
        //根据systemUserExample修改systemUser不为null的值
        systemUserMapper.updateByExampleSelective(systemUser, systemUserExample);
        //修改用户的角色
        List<String> newRoles = systemUser.getRoles();
        List<String> existedRoles = systemUserRoleMapper.selectByUserId(systemUser.getUserId());
        if (newRoles.size() == 0){
            if (existedRoles.size() != 0){
                systemUserRoleMapper.deleteByUserId(systemUser.getUserId());
            }
        } else {
            for (String nRole : newRoles) {
                if (!existedRoles.contains(nRole)){
                    SystemUserRole systemUserRole = new SystemUserRole();
                    systemUserRole.setRoleId(nRole);
                    systemUserRole.setUserId(systemUser.getUserId());
                    systemUserRoleMapper.insert(systemUserRole);
                }
                if (existedRoles.size() != 0){
                    for (String eRole : existedRoles) {
                        if (!newRoles.contains(eRole)){
                            SystemUserRole systemUserRole = new SystemUserRole();
                            systemUserRole.setRoleId(eRole);
                            systemUserRole.setUserId(systemUser.getUserId());
                            systemUserRoleMapper.deleteByUserRole(systemUserRole);
                        }
                    }
                }
            }
        }

    }

    public void deleteUser(SystemUser systemUser){

        SystemUserExample systemUserExample = new SystemUserExample();
        systemUserExample.createCriteria().andUserIdEqualTo(systemUser.getUserId());

        systemUserMapper.deleteByExample(systemUserExample);

        systemUserRoleMapper.deleteByUserId(systemUser.getUserId());
    }

    public SystemUser getUserDetailByUserId(String userId){
        return systemUserMapper.getUserDetailByUserId(userId);
    }

}
