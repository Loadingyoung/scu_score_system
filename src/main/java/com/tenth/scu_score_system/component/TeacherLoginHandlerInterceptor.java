package com.tenth.scu_score_system.component;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TeacherLoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object tcr = request.getSession().getAttribute("loginTcrId");
        if (tcr == null) {
            request.setAttribute("msg", "请登录后再访问！");
            request.getRequestDispatcher("/teacher").forward(request, response);
            return false;
        } else {
            request.setAttribute("loginTcrName",request.getSession().getAttribute("loginTcrName"));
            request.setAttribute("loginTcrId",tcr);
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
