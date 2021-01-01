package com.tenth.scu_score_system.service.impl;

import com.tenth.scu_score_system.Repository.StudentRepository;
import com.tenth.scu_score_system.model.*;
import com.tenth.scu_score_system.service.CourseService;
import com.tenth.scu_score_system.service.SelectCourseService;
import com.tenth.scu_score_system.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.RollbackException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service(value = "StudentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseService courseService;
    @Autowired
    private SelectCourseService selectCourseService;

    @Override
    public Integer countAll() {
        return studentRepository.countAll();
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAllByStudentId();
    }

    @Override
    public void addStudent(Student student) {
        studentRepository.saveAndFlush(student);
    }

    @Override
    public void editStudent(Student student) {
        studentRepository.saveAndFlush(student);
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public Student findStuById(Long id) {
        return studentRepository.findStuByStudentId(id);
    }

    @Override
    public String findStuPswdById(Long id) {
        return studentRepository.findStuPswdById(id);
    }

    @Override
    public String findStuNameById(Long id) {
        return studentRepository.findStuNameById(id);
    }

    @Override
    public List<Integer> countCoursesAndCreditsOfStuById(Long id) {
        Student student = studentRepository.findStuByStudentId(id);
        int len = student.getSelectCourses().size();
        Object[] objects = student.getSelectCourses().toArray();
        Integer courses = 0;
        Integer credits = 0;
        List<Integer> coursesAndCredits = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            SelectCourse sc = (SelectCourse) objects[i];
            TeachingPriKey tpk = sc.getScId().getTeaching().getTeachingId();
            Course course = courseService.findCourseById(tpk.getCourse().getCourseId());
            Integer score = selectCourseService.findScoreByStuIdAndCourseIdAndOrder(id, tpk.getCourse().getCourseId(), sc.getScId().getTeaching().getCourseOrder());
            if (score != null) {
                courses += 1;
                credits += course.getCourseCredit();
            }
        }
        coursesAndCredits.add(courses);
        coursesAndCredits.add(credits);
        return coursesAndCredits;
    }

    private double getGPA(Integer score) {
        double gpa = 0;
        if (score < 60) {
            gpa = 0;
        } else if (score <= 60) {
            gpa = 1;
        } else if (score <= 62) {
            gpa = 1.3;
        } else if (score <= 65) {
            gpa = 1.7;
        } else if (score <= 70) {
            gpa = 2;
        } else if (score <= 72) {
            gpa = 2.3;
        } else if (score <= 75) {
            gpa = 2.7;
        } else if (score < 80) {
            gpa = 3;
        } else if (score <= 84) {
            gpa = 3.3;
        } else if (score < 90) {
            gpa = 3.7;
        } else {
            gpa = 4;
        }
        return gpa;
    }

    @Override
    public List<Double> calcuAvgScoreAndGPAOfStuById(Long id) {

        Student student = studentRepository.findStuByStudentId(id);
        int len = student.getSelectCourses().size();
        Object[] objects = student.getSelectCourses().toArray();
        double avg = 0;
        double avg_must = 0;
        double gpa = 0;
        double gpa_must = 0;
        Integer credits = 0;
        Integer credits_must = 0;
        List<Double> avgAndGPA = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            SelectCourse sc = (SelectCourse) objects[i];
            TeachingPriKey tpk = sc.getScId().getTeaching().getTeachingId();
            Course course = courseService.findCourseById(tpk.getCourse().getCourseId());
            Integer score = selectCourseService.findScoreByStuIdAndCourseIdAndOrder(id, tpk.getCourse().getCourseId(), sc.getScId().getTeaching().getCourseOrder());
            if (score != null) {
                avg += score * course.getCourseCredit();
                gpa += getGPA(score) * course.getCourseCredit();
                credits += course.getCourseCredit();
                if ("必修".equals(course.getCourseType())) {
                    avg_must += score * course.getCourseCredit();
                    gpa_must += getGPA(score) * course.getCourseCredit();
                    credits_must += course.getCourseCredit();
                }
            }
        }
        DecimalFormat avgf = new DecimalFormat("###.00");
        DecimalFormat gpaf = new DecimalFormat("#.000");
        if (avg != 0) {
            avg = avg / credits.doubleValue();
            avg_must = avg_must / credits_must.doubleValue();
            gpa = gpa / credits.doubleValue();
            gpa_must = gpa_must / credits_must.doubleValue();
        }
        avgAndGPA.add(Double.valueOf(avgf.format(avg)));
        avgAndGPA.add(Double.valueOf(avgf.format(avg_must)));
        avgAndGPA.add(Double.valueOf(gpaf.format(gpa)));
        avgAndGPA.add(Double.valueOf(gpaf.format(gpa_must)));
        return avgAndGPA;
    }

    @Override
    public List<Course> findUnfinishedCoursesOfStuById(Long id) {
        Student student = studentRepository.findStuByStudentId(id);
        int len = student.getSelectCourses().size();
        Object[] objects = student.getSelectCourses().toArray();
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            SelectCourse sc = (SelectCourse) objects[i];
            TeachingPriKey tpk = sc.getScId().getTeaching().getTeachingId();
            Course course = courseService.findCourseById(tpk.getCourse().getCourseId());
            Integer score = selectCourseService.findScoreByStuIdAndCourseIdAndOrder(id, tpk.getCourse().getCourseId(), sc.getScId().getTeaching().getCourseOrder());
            if (score == null) {
                courses.add(course);
            }
        }
        return courses;
    }

    @Override
    public List<List> findCourseAnnounceOfStuById(Long id) {
        Student student = studentRepository.findStuByStudentId(id);
        int len = student.getSelectCourses().size();
        Object[] objects = student.getSelectCourses().toArray();
        List<List> announces = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            SelectCourse sc = (SelectCourse) objects[i];
            TeachingPriKey tpk = sc.getScId().getTeaching().getTeachingId();
            Course course = courseService.findCourseById(tpk.getCourse().getCourseId());
            if (sc.getScId().getTeaching().getTeacherAnnounce() != null) {
                List<String> strings = new ArrayList<>();
                strings.add(course.getCourseName());
                strings.add(sc.getScId().getTeaching().getTeacherAnnounce());
                announces.add(strings);
            }
        }
        return announces;
    }

    @Override
    public boolean confirmPasswd(Long sid, String pswd) {
        if (pswd.equals(findStuPswdById(sid))) {
            return true;
        } else {
            return false;
        }
    }
}
