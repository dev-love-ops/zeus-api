package com.devloveops.zeus.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 这个类是用来配置SpringSecurity的
 * @author RockyWu
 * @date 2019-07-18 17:11
 */
@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;
    @Autowired
    private CustomUnauthorizedHandler unauthorizedHandler;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        //默认添加spring-security依赖后会对所有的接口自动开启CSRF保护, 这里我们会先关闭它.
        httpSecurity.csrf().disable()
                //用户访问无权限资源时的异常
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).and()
                //用户未认证的异常处理
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                //关闭session认证, 用不到
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
//                .antMatchers("/api/**").permitAll()
                .antMatchers("/api/login").permitAll()
                //其他的请求, 需要认证才能访问
                .anyRequest().authenticated();

        // 添加JWT认证过滤
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);

        // 禁用缓存
//        httpSecurity.headers().cacheControl();
    }

    /**
     * 重载configure方法来配置认证的方式, 可以配置内存用户, 数据库, LDAP等
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationTokenFilter();
    }

    /**
     * 定义密码加密的方式为Bcrypt
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * SpringBoot2以后AuthenticationManager不是默认的bean了, 这是手工把它变成bean, 用于用户认证阶段
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}
