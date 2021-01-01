package com.tenth.scu_score_system.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentLoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object stu = request.getSession().getAttribute("loginStuId");
        if (stu == null) {
            request.setAttribute("msg", "请登录后再访问！");
            request.getRequestDispatcher("/student").forward(request, response);
            return false;
        } else {
            request.setAttribute("loginStuName",request.getSession().getAttribute("loginStuName"));
            request.setAttribute("loginStuId",stu);
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
