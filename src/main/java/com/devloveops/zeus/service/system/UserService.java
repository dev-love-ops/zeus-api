package com.devloveops.zeus.service.system;

import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.domain.system.SystemUserExample;
import com.devloveops.zeus.mapper.system.ExSystemUserMapper;
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
    private ExSystemUserMapper exSystemUserMapper;

    public PageInfo<SystemUser>  getUserList(QuerySystemUser queryCondition){
        //分页
        PageHelper.startPage(queryCondition.getPageNo(), queryCondition.getPageSize());
        //从数据库中查询的数据
        List<SystemUser> systemUsers = exSystemUserMapper.selectByQueryCondition(queryCondition);
        return new PageInfo<>(systemUsers);
    }

    public void createUser(SystemUser systemUser) throws ExistsException {
        //判断是否已经存在
        SystemUserExample systemUserExample = new SystemUserExample();
        systemUserExample.createCriteria().andUserIdEqualTo(systemUser.getUserId());

        List<SystemUser> systemUsers =  exSystemUserMapper.selectByExample(systemUserExample);

        if (systemUsers.size() != 0){
            throw new ExistsException("用户已存在");
        }
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        systemUser.setPassword(encoder.encode(systemUser.getPassword().trim()));
        exSystemUserMapper.insertSelective(systemUser);
    }

    public void modifyUser(SystemUser systemUser){

        SystemUserExample systemUserExample = new SystemUserExample();
        systemUserExample.createCriteria().andUserIdEqualTo(systemUser.getUserId());
        //根据systemUserExample修改systemUser不为null的值
        exSystemUserMapper.updateByExampleSelective(systemUser, systemUserExample);
    }

    public void deleteUser(SystemUser systemUser){

        SystemUserExample systemUserExample = new SystemUserExample();
        systemUserExample.createCriteria().andUserIdEqualTo(systemUser.getUserId());

        exSystemUserMapper.deleteByExample(systemUserExample);
    }

}
