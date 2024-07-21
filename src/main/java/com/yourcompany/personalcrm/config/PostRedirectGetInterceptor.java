package com.yourcompany.personalcrm.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

@Slf4j
@ControllerAdvice
public class PostRedirectGetInterceptor implements HandlerInterceptor
{
    @Override
    public boolean preHandle(@NonNull final HttpServletRequest request, @NonNull final HttpServletResponse response, Object handler)
    {
        if (handler instanceof final HandlerMethod handlerMethod) 
        {
            final boolean handled = handle(handlerMethod.getMethod(), request, response);
            log.info("{} {} {} {}", (handled ? "Handled" : "Ignored"), (response.isCommitted() ? "Committed" : "Uncommitted"), request.getMethod(), request.getRequestURI());
        }
        return true;
    }

    private boolean handle(@NonNull final Method method, @NonNull final HttpServletRequest request, @NonNull final HttpServletResponse response)
    {
        if (!"POST".equalsIgnoreCase(request.getMethod()) || !method.isAnnotationPresent(PostRedirectGet.class))
            return false;
        
        final PostRedirectGet annotation = method.getAnnotation(PostRedirectGet.class);
        String redirectUrl = annotation.value();
        
        if (redirectUrl.isEmpty()) 
            redirectUrl = request.getRequestURL().toString();
        
        response.setStatus(HttpServletResponse.SC_SEE_OTHER);
        response.setHeader("Location", redirectUrl);
        return true;
    }
}