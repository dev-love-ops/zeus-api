package com.devloveops.zeus.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author RockyWu
 * @date 2019-07-19 20:36
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            System.out.println(authentication.getAuthorities());
            logger.warn("用户: [{}]鉴权失败, 请求路径: [{}]", authentication.getName(), request.getRequestURI());
        }

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "无权限");
    }
}
