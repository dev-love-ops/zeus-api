package me.rockywu.zeus.config;

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
 * @date 2019-01-20 16:53
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null){
            System.out.println(authentication.getName());
            System.out.println(request.getRequestURI());
        }

        response.sendError(HttpServletResponse.SC_FORBIDDEN, "forbidden for no authorization lalala!");
    }
}

