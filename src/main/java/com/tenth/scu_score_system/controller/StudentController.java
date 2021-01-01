package com.tenth.scu_score_system.controller;

import com.tenth.scu_score_system.model.*;
import com.tenth.scu_score_system.service.*;
import net.bytebuddy.matcher.FilterableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;
import javax.transaction.RollbackException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    StudentService studentService;
    @Autowired
    CourseService courseService;
    @Autowired
    TeachingService teachingService;
    @Autowired
    SelectCourseService selectCourseService;
    @Autowired
    TeacherService teacherService;
    Course course = new Course();

    @PostMapping(value = "/student/login")
    public String login(@RequestParam("student") Long stuId,
                        @RequestParam("stuPasswd") String passwd,
                        Map<String, Object> map,
                        HttpSession session) {
        String pswd = studentService.findStuPswdById(stuId);
        String name = studentService.findStuNameById(stuId);
        if (pswd != null && pswd.trim().equals(passwd)) {
            session.setAttribute("loginStuId", stuId);
            session.setAttribute("loginStuName", name);
            return "redirect:/student/main.html";
        } else {
            map.put("msg", "账号密码错误！");
            return "student/student_login";
        }
    }

    @ResponseBody
    @GetMapping(value = "/stuIndex")
    public List<List> index(Long stuId) {
        List<Integer> crsAndcrd = studentService.countCoursesAndCreditsOfStuById(stuId);
        List<Double> avgAndgpa = studentService.calcuAvgScoreAndGPAOfStuById(stuId);
        List<List> courseAnnounce = studentService.findCourseAnnounceOfStuById(stuId);
        List<Course> unfinishedCourses = studentService.findUnfinishedCoursesOfStuById(stuId);
        List<List> lists = new ArrayList<>();
        lists.add(crsAndcrd);
        lists.add(avgAndgpa);
        lists.add(unfinishedCourses);
        lists.add(courseAnnounce);
        return lists;
    }

    @ResponseBody
    @GetMapping(value = "/stuCanSelectCourse")
    public List<List> canSelect(Long stuId) {
        List<List> allTeaching = teachingService.findAllTeachingWithSpecificInfo();
        int size = allTeaching.size();
        List<List> l = new ArrayList<>();
        List<List> ll = new ArrayList<>();
        for (int i = size - 1; i >= 0; i--) {
            List<Boolean> lists = new ArrayList<>();
            List list = allTeaching.get(i);
            SelectCourse sc = selectCourseService.findSCByStuIdAndCourseId(stuId, Integer.parseInt((String) list.get(1)));
            if (sc == null) {
                Teaching teaching = teachingService.findTeachingById(Integer.parseInt((String) list.get(1)), Integer.parseInt((String) list.get(2)));
                List<String> time = teaching.getTime();
                String weekfrom = time.get(0);
                String weekto = time.get(1);
                String week = time.get(2);
                String dayfrom = time.get(3);
                String dayto = time.get(4);
                String courseRestrict = courseService.findCourseById(Integer.parseInt((String) list.get(1))).getCourseRestrict();
                boolean courseRestrictIn = courseService.findCourseById(Integer.parseInt((String) list.get(1))).getCourseRestrictIn(studentService.findStuById(stuId).getStudentDepartment()) || courseRestrict.equals("");
                List<List> allTeachingTimes = selectCourseService.findAllTeachingTimeByStuId(stuId);
                boolean t = courseRestrictIn;
                for (int j = 0; j < allTeachingTimes.size() && t; j++) {
                    String wfrom = (String) allTeachingTimes.get(j).get(0);
                    String wto = (String) allTeachingTimes.get(j).get(1);
                    String wk = (String) allTeachingTimes.get(j).get(2);
                    String dfrom = (String) allTeachingTimes.get(j).get(3);
                    String dto = (String) allTeachingTimes.get(j).get(4);
                    if (Math.max(Integer.parseInt(weekto), Integer.parseInt(wto)) - Math.min(Integer.parseInt(weekfrom), Integer.parseInt(wfrom)) <= (Integer.parseInt(weekto) - Integer.parseInt(weekfrom)) + (Integer.parseInt(wto) - Integer.parseInt(wfrom)) && week.equals(wk) && Math.max(Integer.parseInt(dayto), Integer.parseInt(dto)) - Math.min(Integer.parseInt(dayfrom), Integer.parseInt(dfrom)) <= (Integer.parseInt(dayto) - Integer.parseInt(dayfrom)) + (Integer.parseInt(dto) - Integer.parseInt(dfrom))) {
                        t = false;
                        break;
                    }
                }
                lists.add(t);
                ll.add(lists);
            } else {
                allTeaching.remove(i);
            }
        }
        l.add(ll);
        l.add(allTeaching);
        return l;
    }

    @GetMapping(value = "/student/select-course.html")
    public String selectCourse() {
        return "student/manage_course/select";
    }

    @ResponseBody
    @GetMapping(value = "/SelectCourse")
    public boolean selectDone(Long stuId, String chk) {
        String[] s = chk.split("_");
        int length = s.length;
        for (int i = 0; i < length / 3; i++) {
            SelectCourse sc = new SelectCourse();
            SCPriKey scp = new SCPriKey();
            Student stuById = studentService.findStuById(stuId);
            scp.setStudent(stuById);
            sc.setCourseOrder(Integer.parseInt(s[i * 3 + 1]));
            Teaching teachingByCidAndTid = teachingService.findTeachingByCidAndTid(Integer.parseInt(s[i * 3]), Integer.parseInt(s[i * 3 + 2]));
            teachingByCidAndTid.setTeachingNumber(teachingByCidAndTid.getTeachingNumber()-1);
            scp.setTeaching(teachingByCidAndTid);
            sc.setScId(scp);
            teachingService.addTeaching(teachingByCidAndTid);
            selectCourseService.saveSelect(sc);
        }
        return true;
    }

    @ResponseBody
    @GetMapping(value = "/mycrs")
    public List<List> mycrs(Long stuId) {
        List<SelectCourse> scByStuId = selectCourseService.findSCByStuId(stuId);
        List<List> lists = new ArrayList<>();
        int size = scByStuId.size();
        for (int i = 0; i < size; i++) {
            List<String> strings = new ArrayList<>();
            Integer courseId = scByStuId.get(i).getScId().getTeaching().getTeachingId().getCourse().getCourseId();
            Integer teacherId = scByStuId.get(i).getScId().getTeaching().getTeachingId().getTeacher().getTeacherId();
            Course courseById = courseService.findCourseById(courseId);
            Teacher tcrById = teacherService.findTcrById(teacherId);
            Teaching teachingByCidAndTid = teachingService.findTeachingByCidAndTid(courseId, teacherId);
            strings.add(courseById.getCourseDept());
            strings.add(courseById.getCourseId().toString());
            strings.add(teachingByCidAndTid.getCourseOrder().toString());
            strings.add(courseById.getCourseName());
            strings.add(courseById.getCourseType());
            strings.add(courseById.getCourseCredit().toString());
            strings.add(courseById.getCourseExam());
            strings.add(tcrById.getTeacherName());
            strings.add(teachingByCidAndTid.getTeachingTime());
            strings.add(teachingByCidAndTid.getTeachingAddress());
            strings.add(teacherId.toString());
            lists.add(strings);
        }
        return lists;
    }

    @GetMapping(value = "/student/drop-course.html")
    public String dropCourse() {
        return "student/manage_course/drop";
    }

    @DeleteMapping(value = "/student/drop-course/{sid}&{cid}&{tid}")
    public String delete(@PathVariable("sid") Long stuId,
                         @PathVariable("cid") Integer crsId,
                         @PathVariable("tid") Integer tcrId
    ) {
        selectCourseService.deleteSC(selectCourseService.findSCById(stuId, crsId, tcrId));
        return "redirect:/student/drop-course.html";
    }

    @ResponseBody
    @GetMapping(value = "/myscore")
    public List<List> score(Long stuId){
        List<List> finishedSCByStuId = selectCourseService.findFinishedSCByStuId(stuId);
        return finishedSCByStuId;
    }
    @GetMapping(value = "/student/my-courses.html")
    public String myCourses(Model model) {
        return "student/my_courses";
    }

    @GetMapping(value = "/student/my-score.html")
    public String myScore(Model model) {
        return "student/my_score";
    }

    @GetMapping(value = "/student/profile.html")
    public String profile() {
        return "student/stu_info";
    }

    @ResponseBody
    @GetMapping(value = "/getStuPswd")
    public boolean getStuPswd(Long sid, String pswd) {
        return studentService.confirmPasswd(sid, pswd);
    }

    @ResponseBody
    @GetMapping(value = "/getStu")
    public Student getStu(Long sid) {
        return studentService.findStuById(sid);
    }


    @PostMapping(value = "/student/update-passwd")
    public String updatePasswd(Student student) {
        Student stuById = studentService.findStuById(student.getStudentId());
        stuById.setStudentTelephone(student.getStudentTelephone());
        stuById.setStudentEmail(student.getStudentEmail());
        stuById.setStudentPasswd(student.getStudentPasswd());
        studentService.editStudent(stuById);
        return "redirect:/student/login";
    }

    @GetMapping(value = "/student/login")
    public String toLogin() {
        return "student/student_login";
    }

    @PostMapping(value = "/student/update-info")
    public String updateInfo(Student student) {
        Student stuById = studentService.findStuById(student.getStudentId());
        stuById.setStudentTelephone(student.getStudentTelephone());
        stuById.setStudentEmail(student.getStudentEmail());
        studentService.editStudent(stuById);
        return "redirect:/student/profile.html";
    }
}
