package com.tenth.scu_score_system.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teacher")
public class Teacher {
    @Id
    @Column(name = "tid")
    private Integer teacherId;
    @Column(name = "tname")
    private String teacherName;
    @Column(name = "tgender")
    private String teacherGender;
    @Column(name = "tdepartment")
    private String teacherDepartment;
    @Column(name = "ttitle")
    private String teacherTitle;
    @Column(name = "ttelephone")
    private Long teacherTelephone;
    @Column(name = "temail")
    private String teacherEmail;
    @Column(name = "tpasswd")
    private String teacherPasswd;

    @JsonIgnore
    @OneToMany(mappedBy = "teachingId.teacher",cascade = CascadeType.ALL)
    private Set<Teaching> teachings = new HashSet<>();

    public Set<Teaching> getTeachings() {
        return teachings;
    }

    public void setTeachings(Set<Teaching> teachings) {
        this.teachings = teachings;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherGender() {
        return teacherGender;
    }

    public void setTeacherGender(String teacherGender) {
        this.teacherGender = teacherGender;
    }

    public String getTeacherDepartment() {
        return teacherDepartment;
    }

    public void setTeacherDepartment(String teacherDepartment) {
        this.teacherDepartment = teacherDepartment;
    }

    public String getTeacherTitle() {
        return teacherTitle;
    }

    public void setTeacherTitle(String teacherTitle) {
        this.teacherTitle = teacherTitle;
    }

    public Long getTeacherTelephone() {
        return teacherTelephone;
    }

    public void setTeacherTelephone(Long teacherTelephone) {
        this.teacherTelephone = teacherTelephone;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherPasswd() {
        return teacherPasswd;
    }

    public void setTeacherPasswd(String teacherPasswd) {
        this.teacherPasswd = teacherPasswd;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherGender='" + teacherGender + '\'' +
                ", teacherDepartment='" + teacherDepartment + '\'' +
                ", teacherTitle='" + teacherTitle + '\'' +
                ", teacherTelephone=" + teacherTelephone +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", teacherPasswd='" + teacherPasswd + '\'' +
                '}';
    }

}
