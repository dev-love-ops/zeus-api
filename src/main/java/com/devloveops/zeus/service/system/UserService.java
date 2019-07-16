package com.devloveops.zeus.service.system;

import com.devloveops.zeus.domain.system.ExSystemUser;
import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.domain.system.SystemUserExample;
import com.devloveops.zeus.mapper.system.ExSystemUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @author RockyWu
 * @date 2019-07-15 20:58
 */
@Service
public class UserService {
    @Autowired
    private ExSystemUserMapper exSystemUserMapper;

    public List<ExSystemUser> getUserList(){
        SystemUserExample systemUserExample = new SystemUserExample();

        List<ExSystemUser> exSystemUsers = new LinkedList<>();
        List<SystemUser> systemUsers = exSystemUserMapper.selectByExample(systemUserExample);

        for (SystemUser systemUser: systemUsers) {
            ExSystemUser exSystemUser = new ExSystemUser();

            exSystemUser.setUserId(systemUser.getUserId());
            exSystemUser.setUsername(systemUser.getUsername());
            exSystemUser.setEmail(systemUser.getEmail());
            exSystemUser.setMobile(systemUser.getMobile());

            exSystemUsers.add(exSystemUser);

        }
        return exSystemUsers;
    }

}
