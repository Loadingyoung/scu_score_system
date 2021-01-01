package com.tenth.scu_score_system.config;

import com.tenth.scu_score_system.component.TeacherLoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class TeacherConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/teacher").setViewName("teacher/teacher_login");
        registry.addViewController("/teacher/").setViewName("teacher/teacher_login");
        registry.addViewController("/teacher/main.html").setViewName("teacher/index_tcr");
        registry.addViewController("/teacher/profile.html").setViewName("teacher/tcr_info");
        registry.addViewController("/teacher/manage-score.html").setViewName("teacher/manage_score");
        registry.addViewController("/teacher/send-announce.html").setViewName("teacher/send_announce");
        registry.addViewController("/teacher/my-courses.html").setViewName("teacher/my_courses");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TeacherLoginHandlerInterceptor()).addPathPatterns("/teacher/**").excludePathPatterns("/teacher", "/teacher/", "/teacher/login");
    }
}
