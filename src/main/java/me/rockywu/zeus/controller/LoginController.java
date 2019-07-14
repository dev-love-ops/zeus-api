package me.rockywu.zeus.controller;

import me.rockywu.zeus.common.JsonResult;
import me.rockywu.zeus.config.JwtUserDetailsService;
import me.rockywu.zeus.entity.LoginEntity;
import me.rockywu.zeus.enums.ErrorCode;
import me.rockywu.zeus.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * AuthenticationManager的bean定义在了CustomWebSecurityConfig Class中(authenticationManagerBean)
 * @author rocky
 * @date 2019/01/19
 */
@RestController
@RequestMapping("/v1/login")
@CrossOrigin(value = "*")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public JsonResult login(@RequestBody LoginEntity loginEntity){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginEntity.getUsername(),
                loginEntity.getPassword()
        );
        logger.info(loginEntity.toString());
        try {
            final Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);


            return JsonResult.success("登陆成功",
                    jwtTokenUtil.generateToken(jwtUserDetailsService.loadUserByUsername(loginEntity.getUsername())));
        } catch (BadCredentialsException e) {
            return JsonResult.error(ErrorCode.LOGIN_PASSWORD_WRONG.getCode(), ErrorCode.LOGIN_PASSWORD_WRONG.getDesc());
        }

    }
}
