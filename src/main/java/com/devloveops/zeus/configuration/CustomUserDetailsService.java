package com.devloveops.zeus.configuration;

import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.domain.system.SystemUserExample;
import com.devloveops.zeus.mapper.system.SystemUserMapper;
import com.devloveops.zeus.service.system.UserService;
import com.devloveops.zeus.support.query.QuerySystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 从数据库获取用户相关的数据
 * @author rocky
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        SystemUser systemUser = userService.getUserDetailByUserId(username);
        System.out.println(systemUser);

        if (systemUser == null) {
            throw new UsernameNotFoundException("用户不存在");
        } else {
            return new CustomUserDetails(systemUser);
        }
    }
}
