package com.devloveops.zeus.configuration;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author RockyWu
 * @date 2019-07-19 20:38
 */
@Component
public class CustomUnauthorizedHandler implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        //用户访问受保护的接口未提供认证信息的时候触发
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "未认证");
    }
}
