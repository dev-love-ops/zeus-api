package com.devloveops.zeus.controller.system;

import com.devloveops.zeus.configuration.CustomUserDetailsService;
import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.enums.ErrorCode;
import com.devloveops.zeus.service.system.UserService;
import com.devloveops.zeus.support.CommonVo;
import com.devloveops.zeus.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RockyWu
 * @date 2019-07-15 20:48
 */
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private UserService userService;

    @PostMapping
    public CommonVo login(@RequestBody SystemUser systemUser) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(systemUser.getUsername(), systemUser.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String generatedToken = jwtTokenUtil.generateToken(customUserDetailsService.loadUserByUsername(systemUser.getUsername()));
            Map<String, Object> data = new HashMap<>(2);
            data.put("token", generatedToken);
            data.put("userInfo", userService.getUserDetailByUserId(systemUser.getUsername()));
            return CommonVo.success("登陆成功", data);

        } catch (BadCredentialsException e) {
            return CommonVo.fail("密码错误");
        }
    }
}
