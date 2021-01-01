package com.tenth.scu_score_system.service;

import com.tenth.scu_score_system.model.Course;
import com.tenth.scu_score_system.model.Student;
import com.tenth.scu_score_system.model.Teacher;

import java.util.List;

public interface StudentService {
    Integer countAll();
    List<Student> findAllStudents();
    void addStudent(Student student);
    void editStudent(Student student);
    void deleteStudent(Student student);
    Student findStuById(Long id);
    String findStuPswdById(Long id);
    String findStuNameById(Long id);
    List<Integer> countCoursesAndCreditsOfStuById(Long id);
    List<Double> calcuAvgScoreAndGPAOfStuById(Long id);
    List<Course> findUnfinishedCoursesOfStuById(Long id);
    List<List> findCourseAnnounceOfStuById(Long id);
    boolean confirmPasswd(Long sid,String pswd);
}
