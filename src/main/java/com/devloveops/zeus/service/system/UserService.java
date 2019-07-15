package com.devloveops.zeus.service.system;

import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.domain.system.SystemUserExample;
import com.devloveops.zeus.mapper.system.SystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RockyWu
 * @date 2019-07-15 20:58
 */
@Service
public class UserService {
    @Autowired
    private SystemUserMapper systemUserMapper;

    public List<SystemUser> getUserList(){
        SystemUserExample systemUserExample = new SystemUserExample();
        return systemUserMapper.selectByExample(systemUserExample);
    }

}
