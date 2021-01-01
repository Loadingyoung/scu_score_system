package com.tenth.scu_score_system.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminLoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object admin = request.getSession().getAttribute("loginAdminId");
        if (admin == null) {
            request.setAttribute("msg", "请登录后再访问！");
            request.getRequestDispatcher("/admin").forward(request, response);
            return false;
        } else {
            request.setAttribute("loginAdminName",request.getSession().getAttribute("loginAdminName"));
            request.setAttribute("loginAdminId",admin);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
