package com.security.services.config.oauth;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import com.security.services.service.special.CustomPermissionEvaluator;


public class GlobalPermissionEvaluator implements HandlerInterceptor {

    private ApplicationContext ctx;
    private CustomPermissionEvaluator permissionEvaluator;

    public GlobalPermissionEvaluator(ApplicationContext context) {
        this.ctx = context;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String servletPath = request.getRequestURL().toString();

        if (servletPath.contains("/api") && !servletPath.contains("/login")) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (Objects.nonNull(authentication)) {
                String endpoint = request.getRequestURL().substring(request.getRequestURL().lastIndexOf("/"));
                permissionEvaluator = ctx.getBean(CustomPermissionEvaluator.class);
                if (!permissionEvaluator.hasPermission(authentication, null, endpoint)) {
                    throw new AccessDeniedException("You are not authorized to access this");
                }
            }
        }
        return true;
    }
}
