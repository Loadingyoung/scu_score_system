package com.tenth.scu_score_system.service;

import com.tenth.scu_score_system.model.Teacher;

import java.util.List;

public interface TeacherService {
    Integer countAll();
    List<Teacher> findAllTeachers();
    void addTeacher(Teacher teacher);
    void editTeacher(Teacher teacher);
    void deleteTeacher(Teacher teacher);
    Teacher findTcrById(Integer id);
    String findTcrPswdById(Integer id);
    String findTcrNameById(Integer id);
    boolean confirmPasswd(Integer tid,String pswd);
}
