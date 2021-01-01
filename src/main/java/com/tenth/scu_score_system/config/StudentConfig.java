package com.tenth.scu_score_system.config;

import com.tenth.scu_score_system.component.StudentLoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StudentConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/student").setViewName("student/student_login");
        registry.addViewController("/student/").setViewName("student/student_login");
        registry.addViewController("/student/main.html").setViewName("student/index_stu");
        registry.addViewController("/student/profile.html").setViewName("student/stu_info");
        registry.addViewController("/student/select-course.html").setViewName("student/manage_course/select");
        registry.addViewController("/student/drop-course.html").setViewName("student/manage_course/drop");
        registry.addViewController("/student/my-courses.html").setViewName("student/my_courses");
        registry.addViewController("/student/my-score.html").setViewName("student/my_score");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new StudentLoginHandlerInterceptor()).addPathPatterns("/student/**").excludePathPatterns("/student", "/student/", "/student/login");
    }
}
