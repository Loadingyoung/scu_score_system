package com.tenth.scu_score_system.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.sql.Array;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "cid")
    private Integer courseId;
    @Column(name = "cname")
    private String courseName;
    @Column(name = "ccredit")
    private Integer courseCredit;
    @Column(name = "ctype")
    private String courseType;
    @Column(name = "cexam")
    private String courseExam;
    @Column(name = "cdept")
    private String courseDept;
    @Column(name = "crestrict")
    private String courseRestrict;

    @JsonIgnore
    @OneToMany(mappedBy = "teachingId.course",cascade = CascadeType.ALL)
    private Set<Teaching> teachings = new HashSet<>();

    public Set<Teaching> getTeachings() {
        return teachings;
    }

    public void setTeachings(Set<Teaching> teachings) {
        this.teachings = teachings;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(Integer courseCredit) {
        this.courseCredit = courseCredit;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public String getCourseExam() {
        return courseExam;
    }

    public void setCourseExam(String courseExam) {
        this.courseExam = courseExam;
    }

    public String getCourseDept() {
        return courseDept;
    }

    public void setCourseDept(String courseDept) {
        this.courseDept = courseDept;
    }

    public String getCourseRestrict() {
        return courseRestrict;
    }

    public boolean getCourseRestrictIn(String res) {
        if (this.getCourseRestrict() == null) {
            return false;
        } else {
            String[] split = courseRestrict.split(";|允许院系专业 ");
            List<String> strings = Arrays.asList(split);
            return strings.contains(res);
        }
    }

    public void setCourseRestrict(String courseRestrict) {
        this.courseRestrict = courseRestrict;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseCredit=" + courseCredit +
                ", courseType='" + courseType + '\'' +
                ", courseExam='" + courseExam + '\'' +
                ", courseDept='" + courseDept + '\'' +
                ", courseRestrict='" + courseRestrict + '\'' +
                '}';
    }
}
