package com.tenth.scu_score_system.service.impl;

import com.tenth.scu_score_system.Repository.CourseRepository;
import com.tenth.scu_score_system.model.Course;
import com.tenth.scu_score_system.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "CourseService")
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Integer countAll() {
        return courseRepository.countAll();
    }

    @Override
    public List<Course> findAllCourse() {
        return courseRepository.findAllByCourseId();
    }

    @Override
    public Course findCourseById(Integer cid) {
        return courseRepository.findCourseByCourseId(cid);
    }

    @Override
    public void addCourse(Course course) {
        courseRepository.saveAndFlush(course);
    }

    @Override
    public void editCourse(Course course) {
        courseRepository.saveAndFlush(course);
    }

    @Override
    public void deleteCourse(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public Integer findCourseCreditByCourseId(Integer cid) {
        return courseRepository.findCourseCreditByCourseId(cid);
    }
}
