package com.devloveops.zeus.configuration;

import com.devloveops.zeus.domain.system.SystemUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author RockyWu
 * @date 2019-07-19 14:47
 * hasAuthority('ROLE_ADMIN') means the the same as hasRole('ADMIN')
 * https://stackoverflow.com/questions/19525380/difference-between-role-and-grantedauthority-in-spring-security
 */
public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private List<String> roles;
    private Set<String> permissions;

    CustomUserDetails(SystemUser systemUser) {
        this.username = systemUser.getUserId();
        this.password = systemUser.getPassword();
        this.roles = systemUser.getRoles();
        this.permissions = systemUser.getPermissions();


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();

        if (roles != null) {
            for (String role: roles) {
                authorityList.add(new SimpleGrantedAuthority(role));
            }
        }

        if (permissions != null) {
            for (String perm: permissions) {
                authorityList.add(new SimpleGrantedAuthority(perm));
            }
        }
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账户是否未过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账户是否未锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 密码是否未过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 账户是否激活
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
