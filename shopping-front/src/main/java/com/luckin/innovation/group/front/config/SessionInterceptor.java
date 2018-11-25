package com.luckin.innovation.group.front.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
public class SessionInterceptor implements HandlerInterceptor {
    private static final Set<String> REQUEST_URIS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("/captcha/getCaptcha.jpg", "/login")));

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        //登录不做拦截
        if(REQUEST_URIS.contains(request.getRequestURI()))
        {
            return true;
        }
        //验证session是否存在
        Object obj = request.getSession().getAttribute("user");
        if(obj == null)
        {
            request.getSession().setAttribute("return", request.getRequestURI());
            response.sendRedirect("/index");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
