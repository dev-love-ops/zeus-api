package com.devloveops.zeus.config;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RockyWu
 * @date 2019-01-20 10:10
 */
final class JwtUserFactory {

    static JwtUser create(User user) {
        return new JwtUser(
                user.getUsername(),
                user.getPassword(),
                mapToGrantedAuthorities(user.getRoles().stream().map(Role::getRoleName).collect(Collectors.toList()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}

