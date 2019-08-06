package com.devloveops.zeus.configuration;

/**
 * JWT工具类
 * @author RockyWu
 * @date 2019-01-20 09:44
 */

import com.devloveops.zeus.domain.system.SystemUser;
import com.devloveops.zeus.service.system.UserService;
import com.devloveops.zeus.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = getTokenFromHeader(request);

        if (token != null && jwtTokenUtil.validateToken(token)){

            //把认证通过的用户信息设置到spring上下文中, 经过测试, 只要是个userDetails对象就行, 不用密码就算通过了, 如果需要权限信息的话可以查询一下数据库然后把真实的权限信息加上去.
            //把认证通过的用户和该用户的权限设置到spring上下文中
            String username = jwtTokenUtil.getUsernameFromToken(token);
            SystemUser systemUser = userService.getUserDetailByUserId(username);
            CustomUserDetails customUserDetails = new CustomUserDetails(systemUser);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customUserDetails.getUsername(), customUserDetails.getPassword(), customUserDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }

    private String getTokenFromHeader(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith(tokenHead)){
            return authHeader.substring(tokenHead.length());
        }
        return null;

    }
}
