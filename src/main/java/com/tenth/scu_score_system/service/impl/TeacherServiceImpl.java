package com.tenth.scu_score_system.service.impl;

import com.tenth.scu_score_system.Repository.TeacherRepository;
import com.tenth.scu_score_system.model.Teacher;
import com.tenth.scu_score_system.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "TeacherService")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;


    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAllByTeacherId();
    }

    @Override
    public Integer countAll() {
        return teacherRepository.countAll();
    }

    @Override
    public String findTcrPswdById(Integer id) {
        return teacherRepository.findTeacherByTeacherId(id).getTeacherPasswd();
    }

    @Override
    public String findTcrNameById(Integer id) {
        return teacherRepository.findTeacherByTeacherId(id).getTeacherName();
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public void editTeacher(Teacher teacher) {
        teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public void deleteTeacher(Teacher teacher) {
        teacherRepository.delete(teacher);
    }

    @Override
    public Teacher findTcrById(Integer id) {
        return teacherRepository.findTeacherByTeacherId(id);
    }
    @Override
    public boolean confirmPasswd(Integer tid, String pswd) {
        if (pswd.equals(findTcrPswdById(tid))) {
            return true;
        } else {
            return false;
        }
    }
}
