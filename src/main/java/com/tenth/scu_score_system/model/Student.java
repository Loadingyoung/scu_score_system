package com.tenth.scu_score_system.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "sid")
    private Long studentId;
    @Column(name = "sname")
    private String studentName;
    @Column(name = "sgender")
    private String studentGender;
    @Column(name = "sdepartment")
    private String studentDepartment;
    @Column(name = "smajor")
    private String studentMajor;
    @Column(name = "stelephone")
    private Long studentTelephone;
    @Column(name = "semail")
    private String studentEmail;
    @Column(name = "spasswd")
    private String studentPasswd;

    @JsonIgnore
    @OneToMany(mappedBy = "scId.student",cascade = CascadeType.ALL)
    private Set<SelectCourse> selectCourses = new HashSet<>();

    public Set<SelectCourse> getSelectCourses() {
        return selectCourses;
    }

    public void setSelectCourses(Set<SelectCourse> selectCourses) {
        this.selectCourses = selectCourses;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }

    public String getStudentDepartment() {
        return studentDepartment;
    }

    public void setStudentDepartment(String studentDepartment) {
        this.studentDepartment = studentDepartment;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getStudentPasswd() {
        return studentPasswd;
    }

    public void setStudentPasswd(String studentPasswd) {
        this.studentPasswd = studentPasswd;
    }

    public Long getStudentTelephone() {
        return studentTelephone;
    }

    public void setStudentTelephone(Long studentTelephone) {
        this.studentTelephone = studentTelephone;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName='" + studentName + '\'' +
                ", studentGender='" + studentGender + '\'' +
                ", studentDepartment='" + studentDepartment + '\'' +
                ", studentMajor='" + studentMajor + '\'' +
                ", studentTelephone='" + studentTelephone + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentPasswd='" + studentPasswd + '\'' +
                '}';
    }
}
