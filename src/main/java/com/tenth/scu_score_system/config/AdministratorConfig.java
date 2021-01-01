package com.tenth.scu_score_system.config;

import com.tenth.scu_score_system.component.AdminLoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AdministratorConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin").setViewName("administrator/admin_login");
        registry.addViewController("/admin/").setViewName("administrator/admin_login");
        registry.addViewController("/admin/main.html").setViewName("administrator/index_adm");
        registry.addViewController("/admin/profile.html").setViewName("administrator/admin_info");
        registry.addViewController("/admin/manage-teacher.html").setViewName("administrator/table_teachers");
        registry.addViewController("/admin/manage-course.html").setViewName("administrator/table_courses");
        registry.addViewController("/admin/manage-student.html").setViewName("administrator/table_students");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminLoginHandlerInterceptor()).addPathPatterns("/admin/**").excludePathPatterns("/admin", "/admin/", "/admin/login");
    }

}