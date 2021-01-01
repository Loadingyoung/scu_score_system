package com.tenth.scu_score_system.service;

import com.tenth.scu_score_system.model.Course;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface CourseService {
    Integer countAll();
    List<Course> findAllCourse();
    void addCourse(Course course);
    Course findCourseById(Integer cid);
    void editCourse(Course course);
    void deleteCourse(Course course);
    Integer findCourseCreditByCourseId(Integer cid);
}
