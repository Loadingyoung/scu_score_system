package com.tenth.scu_score_system.controller;

import com.tenth.scu_score_system.model.*;
import com.tenth.scu_score_system.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private TitleService titleService;
    @Autowired
    private MajorService majorService;
    @Autowired
    private SelectCourseService selectCourseService;
    @Autowired
    private TeachingService teachingService;

    @PostMapping(value = "/admin/login")
    public String login(@RequestParam("admin") Integer adminId,
                        @RequestParam("adminPasswd") String passwd,
                        Map<String, Object> map,
                        HttpSession session) {
        String pswd = administratorService.findAdminPswdById(adminId);
        String name = administratorService.findAdminNameById(adminId);
        if (pswd != null && pswd.trim().equals(passwd)) {
            session.setAttribute("loginAdminId", adminId);
            session.setAttribute("loginAdminName", name);
            return "redirect:/admin/main.html";
        } else {
            map.put("msg", "账号密码错误！");
            return "administrator/admin_login";
        }
    }

    @GetMapping(value = "/admin/main.html")
    public String index(Model model) {
        Integer totalAdmin = administratorService.countAll();
        Integer totalStu = studentService.countAll();
        Integer totalCrs = courseService.countAll();
        Integer totalTcr = teacherService.countAll();
        model.addAttribute("totalAdmins", totalAdmin.toString());
        model.addAttribute("totalStus", totalStu.toString());
        model.addAttribute("totalCrses", totalCrs.toString());
        model.addAttribute("totalTcrs", totalTcr.toString());
        return "administrator/index_adm";
    }

    @GetMapping(value = "/admin/manage-teacher.html")
    public String teacherList(Model model) {
        List<Teacher> allTeachers = teacherService.findAllTeachers();
        model.addAttribute("teachers", allTeachers);
        return "administrator/table_teachers";
    }

    @GetMapping(value = "/admin/manage-teacher/add")
    public String toAddTeacherPage(Model model) {
        List<Department> allDept = departmentService.findAllDept();
        List<Title> allTitle = titleService.findAllTitle();
        model.addAttribute("depts", allDept);
        model.addAttribute("titles", allTitle);
        return "administrator/manage_teacher/add";
    }

    @PostMapping(value = "/admin/manage-teacher/add")
    public String addTeacher(Teacher teacher) {
        teacherService.addTeacher(teacher);
        return "redirect:/admin/manage-teacher.html";
    }

    @GetMapping(value = "/admin/manage-teacher/edit/{tid}")
    public String toEditTeacherPage(@PathVariable("tid") Integer tid, Model model) {
        List<Department> allDept = departmentService.findAllDept();
        List<Title> allTitle = titleService.findAllTitle();
        model.addAttribute("depts", allDept);
        model.addAttribute("titles", allTitle);
        Teacher teacher = teacherService.findTcrById(tid);
        model.addAttribute("tcr", teacher);
        return "administrator/manage_teacher/edit";
    }

    @PutMapping(value = "/admin/manage-teacher/edit")
    public String editTeacher(Teacher teacher) {
        teacherService.editTeacher(teacher);
        return "redirect:/admin/manage-teacher.html";
    }

    @DeleteMapping(value = "/admin/manage-teacher/delete/{tid}")
    public String deleteTeacher(@PathVariable("tid") Integer tid) {
        teacherService.deleteTeacher(teacherService.findTcrById(tid));
        return "redirect:/admin/manage-teacher.html";
    }

    @GetMapping(value = "/admin/manage-student.html")
    public String studentList(Model model) {
        List<Student> allStudents = studentService.findAllStudents();
        List<Department> allDept = departmentService.findAllDept();
        List<Major> allMajor = majorService.findAllMajor();
        model.addAttribute("students", allStudents);
        model.addAttribute("depts", allDept);
        model.addAttribute("majors", allMajor);
        return "administrator/table_students";
    }

    @GetMapping(value = "/admin/manage-student/add")
    public String toAddStudentPage(Model model) {
        List<Department> allDept = departmentService.findAllDept();
        List<Major> allMajor = majorService.findAllMajor();
        model.addAttribute("depts", allDept);
        model.addAttribute("majors", allMajor);
        return "administrator/manage_student/add";
    }

    @PostMapping(value = "/admin/manage-student/add")
    public String addStudent(Student student) {
        studentService.addStudent(student);
        return "redirect:/admin/manage-student.html";
    }

    @GetMapping(value = "/admin/manage-student/edit/{sid}")
    public String toEditStudentPage(@PathVariable("sid") Long sid, Model model) {
        List<Department> allDept = departmentService.findAllDept();
        List<Major> allMajor = majorService.findAllMajor();
        model.addAttribute("depts", allDept);
        model.addAttribute("majors", allMajor);
        Student student = studentService.findStuById(sid);
        List<Major> majorByDeptId = majorService.findMajorByDeptId(departmentService.findDeptIdByName(student.getStudentDepartment()));
        model.addAttribute("stu", student);
        model.addAttribute("majors2StuDept", majorByDeptId);
        return "administrator/manage_student/edit";
    }

    @PutMapping(value = "/admin/manage-student/edit")
    public String editStudent(Student student) {
        studentService.editStudent(student);
        return "redirect:/admin/manage-student.html";
    }

    @DeleteMapping(value = "/admin/manage-student/delete/{sid}")
    public String deleteStduent(@PathVariable("sid") Long sid) {
        studentService.deleteStudent(studentService.findStuById(sid));
        return "redirect:/admin/manage-student.html";
    }

    @ResponseBody
    @GetMapping(value = "/dept2major")
    public List<Major> getMajor(String dept) {
        return majorService.findMajorByDeptId(departmentService.findDeptIdByName(dept));
    }

    @GetMapping(value = "/admin/manage-course.html")
    public String courseList(Model model) {
        List<Course> allCourse = courseService.findAllCourse();
        model.addAttribute("courses", allCourse);
        return "administrator/table_courses";
    }

    @GetMapping(value = "/admin/manage-course/add")
    public String toAddCoursePage(Model model) {
        List<Department> allDept = departmentService.findAllDept();
        model.addAttribute("depts", allDept);
        return "administrator/manage_course/add_course";
    }

    @PostMapping(value = "/admin/manage-course/add")
    public String addCourse(Course course) {
        courseService.addCourse(course);
        return "redirect:/admin/manage-course.html";
    }

    @GetMapping(value = "/admin/manage-course/edit/{cid}")
    public String toEditCoursePage(@PathVariable("cid") Integer cid, Model model) {
        List<Department> allDept = departmentService.findAllDept();
        model.addAttribute("depts", allDept);
        Course course = courseService.findCourseById(cid);
        model.addAttribute("crs", course);
        return "administrator/manage_course/edit_course";
    }

    @PutMapping(value = "/admin/manage-course/edit")
    public String editCourse(Course course) {
        courseService.editCourse(course);
        return "redirect:/admin/manage-course.html";
    }

    @DeleteMapping(value = "/admin/manage-course/delete/{cid}")
    public String deleteCourse(@PathVariable("cid") Integer cid) {
        courseService.deleteCourse(courseService.findCourseById(cid));
        return "redirect:/admin/manage-course.html";
    }

    @GetMapping(value = "/admin/manage-select-course.html")
    public String selectList(Model model) {
        List<SelectCourse> allSelectCourse = selectCourseService.findAllSelectCourse();
        int size = allSelectCourse.size();
        List<List> lists = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<String> strings = new ArrayList<>();
            Student stuById = studentService.findStuById(allSelectCourse.get(i).getScId().getStudent().getStudentId());
            Course courseById = courseService.findCourseById(allSelectCourse.get(i).getScId().getTeaching().getTeachingId().getCourse().getCourseId());
            strings.add(courseById.getCourseId().toString());
            strings.add(courseById.getCourseName());
            strings.add(allSelectCourse.get(i).getCourseOrder().toString());
            strings.add(stuById.getStudentId().toString());
            strings.add(stuById.getStudentName());
            lists.add(strings);
        }
        model.addAttribute("selectCourses", lists);
        return "administrator/table_select";
    }

    @GetMapping(value = "/admin/manage-select-course/add")
    public String toAddSelectPage(Model model) {
        List<Department> allDept = departmentService.findAllDept();
        List<Title> allTitle = titleService.findAllTitle();
        model.addAttribute("depts", allDept);
        model.addAttribute("titles", allTitle);
        return "administrator/manage_course/add_sc";
    }

    @PostMapping(value = "/admin/manage-select-course/add")
    public String addSelect(Teacher teacher) {
        System.out.println(teacher.toString());
        teacherService.addTeacher(teacher);
        return "redirect:/admin/manage-select-course.html";
    }

    @GetMapping(value = "/admin/manage-select-course/edit/{tid}")
    public String toEditSelectPage(@PathVariable("tid") Integer tid, Model model) {
        List<Department> allDept = departmentService.findAllDept();
        List<Title> allTitle = titleService.findAllTitle();
        model.addAttribute("depts", allDept);
        model.addAttribute("titles", allTitle);
        Teacher teacher = teacherService.findTcrById(tid);
        model.addAttribute("tcr", teacher);
        return "administrator/manage_course/edit_sc";
    }

    @PutMapping(value = "/admin/manage-select-course/edit")
    public String editSelect(Teacher teacher) {
        teacherService.editTeacher(teacher);
        return "redirect:/admin/manage-select-course.html";
    }

    @DeleteMapping(value = "/admin/manage-select-course/delete/{tid}")
    public String deleteSelect(@PathVariable("tid") Integer tid) {
        teacherService.deleteTeacher(teacherService.findTcrById(tid));
        return "redirect:/admin/manage-select-course.html";
    }

    @GetMapping(value = "/admin/manage-teaching-course.html")
    public String teachingList(Model model) {
        List<Teaching> allTeaching = teachingService.findAllTeaching();
        model.addAttribute("teachings", allTeaching);
        return "administrator/table_teaching";
    }

    @GetMapping(value = "/admin/manage-teaching-course/add")
    public String toAddTeachingPage(Model model) {
        List<Teacher> allTeachers = teacherService.findAllTeachers();
        List<Course> allCourse = courseService.findAllCourse();
        model.addAttribute("teachers", allTeachers);
        model.addAttribute("courses", allCourse);
        return "administrator/manage_course/add_tching";
    }

    @ResponseBody
    @GetMapping(value = "/getBuilding")
    public List<Building> getBuilding(Integer campus) {
        return teachingService.findBuildingByCid(campus);
    }

    @ResponseBody
    @GetMapping(value = "/getRoom")
    public List<Room> getRoom(Integer campus, String building) {
        return teachingService.findRoomByCampusIdAndBuildingId(campus, teachingService.findBuildingIdByCampusIdAndName(campus, building));
    }

    @ResponseBody
    @GetMapping(value = "/checkTching")
    public List<Boolean> checkTching(Integer crsId, Integer tchId, Integer crsOd) {
        List<Boolean> list = new ArrayList<>();
        Teaching teachingByCidAndTid = teachingService.findTeachingByCidAndTid(crsId, tchId);
        Teaching teachingById = teachingService.findTeachingById(crsId, crsOd);
        boolean res = true;
        if (teachingByCidAndTid != null) {
            res = false;
        }
        list.add(res);
        res = true;
        if (teachingById != null) {
            res = false;
        }
        list.add(res);
        return list;
    }

    @PostMapping(value = "/admin/manage-teaching-course/add")
    public String addTeaching(Teaching teaching) {
        teachingService.addTeaching(teaching);
        return "redirect:/admin/manage-teaching-course.html";
    }

    @GetMapping(value = "/admin/manage-teaching-course/edit/{cid}&{tid}")
    public String toEditTeachingPage(@PathVariable("cid") Integer cid,
                                     @PathVariable("tid") Integer tid,
                                     Model model) {
        List<Teacher> allTeachers = teacherService.findAllTeachers();
        List<Course> allCourse = courseService.findAllCourse();
        model.addAttribute("teachers", allTeachers);
        model.addAttribute("courses", allCourse);
        Teaching teaching = teachingService.findTeachingByCidAndTid(cid, tid);
        List<String> weekTo = teaching.getWeekTo();
        List<String> sectionTo = teaching.getSectionTo();
        String s = teaching.getAddress().get(0);
        Integer campusIdByName = teachingService.findCampusIdByName(s);
        List<Building> buildings = teachingService.findBuildingByCid(campusIdByName);
        String s1 = teaching.getAddress().get(1);
        Integer buildingIdByCampusIdAndName = teachingService.findBuildingIdByCampusIdAndName(campusIdByName, s1);
        List<Room> rooms = teachingService.findRoomByCampusIdAndBuildingId(campusIdByName, buildingIdByCampusIdAndName);
        model.addAttribute("tching", teaching);
        model.addAttribute("buildings", buildings);
        model.addAttribute("rooms", rooms);
        model.addAttribute("week", weekTo);
        model.addAttribute("section", sectionTo);
        return "administrator/manage_course/edit_tching";
    }

    @PutMapping(value = "/admin/manage-teaching-course/edit")
    public String editTeaching(Teaching teaching) {
        teachingService.addTeaching(teaching);
        return "redirect:/admin/manage-teaching-course.html";
    }

    @DeleteMapping(value = "/admin/manage-teaching-course/delete/{cid}&{tid}")
    public String deleteTeaching(@PathVariable("cid") Integer cid,
                                 @PathVariable("tid") Integer tid) {
        teachingService.deleteTeaching(teachingService.findTeachingByCidAndTid(cid,tid));
        return "redirect:/admin/manage-teaching-course.html";
    }

    @ResponseBody
    @GetMapping(value = "/getAdminPswd")
    public boolean getAdminPswd(Integer aid, String pswd) {
        return administratorService.confirmPasswd(aid, pswd);
    }

    @PostMapping(value = "/admin/update-passwd")
    public String updatePasswd(Administrator administrator) {
        administratorService.updatePasswd(administrator);
        return "redirect:/admin/login";
    }

    @GetMapping(value = "/admin/login")
    public String toLogin() {
        return "administrator/admin_login";
    }
}
