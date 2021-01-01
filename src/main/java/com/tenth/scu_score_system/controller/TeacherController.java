package com.tenth.scu_score_system.controller;

import com.tenth.scu_score_system.model.*;
import com.tenth.scu_score_system.service.CourseService;
import com.tenth.scu_score_system.service.SelectCourseService;
import com.tenth.scu_score_system.service.TeacherService;
import com.tenth.scu_score_system.service.TeachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.*;

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    TeachingService teachingService;
    @Autowired
    CourseService courseService;

    @Autowired
    SelectCourseService selectCourseService;

    @PostMapping(value = "/teacher/login")
    public String login(@RequestParam("teacher") Integer tchrId,
                        @RequestParam("tchrPasswd") String passwd,
                        Map<String, Object> map,
                        HttpSession session) {
        String pswd = teacherService.findTcrPswdById(tchrId);
        String name = teacherService.findTcrNameById(tchrId);
        if (pswd != null && pswd.trim().equals(passwd)) {
            session.setAttribute("loginTcrId", tchrId);
            session.setAttribute("loginTcrName", name);
            return "redirect:/teacher/main.html";
        } else {
            map.put("msg", "账号密码错误！");
            return "teacher/teacher_login";
        }
    }

    @GetMapping(value = "/teacher/main.html")
    public String index() {
        return "teacher/index_tcr";
    }

    @ResponseBody
    @GetMapping(value = "/mycrses")
    public List<List> mycrses(Integer tcrId) {
        List<Teaching> allTeachingOfTcrById = teachingService.findAllTeachingOfTcrById(tcrId);
        List<List> lists = new ArrayList<>();
        int size = allTeachingOfTcrById.size();
        for (int i = 0; i < size; i++) {
            List<String> strings = new ArrayList<>();
            Integer courseId = allTeachingOfTcrById.get(i).getTeachingId().getCourse().getCourseId();
            Course courseById = courseService.findCourseById(courseId);
            strings.add(courseById.getCourseDept());
            strings.add(courseById.getCourseId().toString());
            strings.add(allTeachingOfTcrById.get(i).getCourseOrder().toString());
            strings.add(courseById.getCourseName());
            strings.add(courseById.getCourseType());
            strings.add(courseById.getCourseCredit().toString());
            strings.add(courseById.getCourseExam());
            strings.add(allTeachingOfTcrById.get(i).getTeachingTime());
            strings.add(allTeachingOfTcrById.get(i).getTeachingAddress());
            lists.add(strings);
        }
        return lists;
    }

    @GetMapping(value = "/teacher/my-courses.html")
    public String mycrss() {
        return "teacher/my_courses";
    }

    @GetMapping(value = "/teacher/send-announce.html")
    public String toSendA() {
        return "teacher/send_announce";
    }

    @PostMapping(value = "/teacher/send-announce/update/{tid}&{cid}")
    public String sendA(@PathVariable(name = "tid") Integer tid,
                        @PathVariable(name = "cid") Integer cid,
                        @RequestParam(name = "courseAnnounce") String announce) {
        Teaching teachingByCidAndTid = teachingService.findTeachingByCidAndTid(cid, tid);
        teachingByCidAndTid.setTeacherAnnounce(announce);
        teachingService.addTeaching(teachingByCidAndTid);
        return "redirect:/teacher/send-announce.html";
    }

    @GetMapping(value = "/teacher/manage-score.html")
    public String toManageScore() {
        return "teacher/manage_score";
    }

    @GetMapping(value = "/teacher/profile.html")
    public String toProfile() {
        return "teacher/tcr_info";
    }

    @ResponseBody
    @GetMapping(value = "/getStus")
    public List<List> getStus(Integer tcrId, Integer crsId) {
        List<List> lists = new ArrayList<>();
        Teaching teachingByCidAndTid = teachingService.findTeachingByCidAndTid(crsId, tcrId);
        Set<SelectCourse> selectCourses = teachingByCidAndTid.getSelectCourses();
        int size = selectCourses.size();
        Object[] objects = selectCourses.toArray();
        for (int i = 0; i < size; i++) {
            List<String> strings = new ArrayList<>();
            SelectCourse sc = (SelectCourse) objects[i];
            strings.add(sc.getScId().getStudent().getStudentId().toString());
            strings.add(sc.getScId().getStudent().getStudentName());
            if (sc.getAttend() != null) {
                strings.add(sc.getAttend().toString());
            } else {
                strings.add("");
            }
            if (sc.getHomework() != null) {
                strings.add(sc.getHomework().toString());
            } else {
                strings.add("");
            }
            if (sc.getMidterm() != null) {
                strings.add(sc.getMidterm().toString());
            } else {
                strings.add("");
            }
            if (sc.getEndterm() != null) {
                strings.add(sc.getEndterm().toString());
            } else {
                strings.add("");
            }
            if (sc.getScore() != null) {
                strings.add(sc.getScore().toString());
            } else {
                strings.add("");
            }
            lists.add(strings);
        }
        return lists;
    }

    @ResponseBody
    @PostMapping(value = "/score")
    public void saveScore(Long stuId, Integer tcrId, Integer crsId,
                          @RequestParam(value = "scores[]") Integer[] scores) {
        SelectCourse scById = selectCourseService.findSCById(stuId, crsId, tcrId);
        scById.setAttend(scores[0]);
        scById.setHomework(scores[1]);
        scById.setMidterm(scores[2]);
        scById.setEndterm(scores[3]);
        scById.setScore(scores[4]);
        selectCourseService.saveSelect(scById);
    }

    @ResponseBody
    @GetMapping(value = "/getTcr")
    public Teacher getTcr(Integer tid) {
        return teacherService.findTcrById(tid);
    }

    @ResponseBody
    @GetMapping(value = "/getTcrPswd")
    public boolean confTcrPswd(Integer tid, String pswd) {
        return teacherService.confirmPasswd(tid, pswd);
    }

    @PostMapping(value = "/teacher/update-info")
    public String updateInfo(Teacher teacher) {
        Teacher tcrById = teacherService.findTcrById(teacher.getTeacherId());
        tcrById.setTeacherTelephone(teacher.getTeacherTelephone());
        tcrById.setTeacherEmail(teacher.getTeacherEmail());
        teacherService.editTeacher(tcrById);
        return "redirect:/teacher/profile.html";
    }

    @PostMapping(value = "/teacher/update-passwd")
    public String updatePasswd(Teacher teacher) {
        Teacher tcrById = teacherService.findTcrById(teacher.getTeacherId());
        tcrById.setTeacherTelephone(teacher.getTeacherTelephone());
        tcrById.setTeacherEmail(teacher.getTeacherEmail());
        tcrById.setTeacherPasswd(teacher.getTeacherPasswd());
        teacherService.editTeacher(tcrById);
        return "redirect:/teacher/login";
    }

    @GetMapping(value = "/teacher/login")
    public String toLogin() {
        return "teacher/teacher_login";
    }
}
