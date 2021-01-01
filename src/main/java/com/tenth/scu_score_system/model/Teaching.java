package com.tenth.scu_score_system.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name = "teaching")
public class Teaching {
    @EmbeddedId
    private TeachingPriKey teachingId;
    @Column(name = "t_corder")
    private Integer courseOrder;
    @Column(name = "ttime")
    private String teachingTime;
    @Column(name = "taddress")
    private String teachingAddress;
    @Column(name = "tannounce")
    private String teacherAnnounce;
    @Column(name = "tnumber")
    private Integer teachingNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "scId.teaching",cascade = CascadeType.ALL)
    private Set<SelectCourse> selectCourses = new HashSet<>();


    public Set<SelectCourse> getSelectCourses() {
        return selectCourses;
    }

    public void setSelectCourses(Set<SelectCourse> selectCourses) {
        this.selectCourses = selectCourses;
    }

    public TeachingPriKey getTeachingId() {
        return teachingId;
    }

    public void setTeachingId(TeachingPriKey teachingId) {
        this.teachingId = teachingId;
    }

    public String getTeachingTime() {
        return teachingTime;
    }

    public void setTeachingTime(String teachingTime) {
        this.teachingTime = teachingTime;
    }

    public String getTeachingAddress() {
        return teachingAddress;
    }

    public void setTeachingAddress(String teachingAddress) {
        this.teachingAddress = teachingAddress;
    }

    public String getTeacherAnnounce() {
        return teacherAnnounce;
    }

    public void setTeacherAnnounce(String teacherAnnounce) {
        this.teacherAnnounce = teacherAnnounce;
    }

    public Integer getTeachingNumber() {
        return teachingNumber;
    }

    public void setTeachingNumber(Integer teachingNumber) {
        this.teachingNumber = teachingNumber;
    }

    public Integer getCourseOrder() {
        return courseOrder;
    }

    public void setCourseOrder(Integer courseOrder) {
        this.courseOrder = courseOrder;
    }

    public List<String> getTime() {
        String[] split = teachingTime.split("-|周|节|星期| ");
        List<String> time = new ArrayList();
        for (int i = 0; i < split.length; i++) {
            if (split[i] != "") {
                time.add(split[i]);
            }
        }
        return time;
    }

    public List<String> getAddress() {
        String[] split = teachingAddress.split(" ");
        List<String> addr = new ArrayList();
        for (int i = 0; i < split.length; i++) {
            if (split[i] != "") {
                addr.add(split[i]);
            }
        }
        return addr;
    }

    public List<String> getWeekTo() {
        List<String> time = getTime();
        List<String> timeTo = new ArrayList();
        for (int i = Integer.valueOf(time.get(1)); i <= 20; i++) {
            timeTo.add(String.valueOf(i));
        }
        return timeTo;
    }

    public List<String> getSectionTo() {
        List<String> time = getTime();
        List<String> sectionTo = new ArrayList();
        for (int i = Integer.valueOf(time.get(4)); i <= 12; i++) {
            sectionTo.add(String.valueOf(i));
        }
        return sectionTo;
    }

    @Override
    public String toString() {
        return "Teaching{" +
                "teachingId=" + teachingId +
                ", courseOrder=" + courseOrder +
                ", teachingTime='" + teachingTime + '\'' +
                ", teachingAddress='" + teachingAddress + '\'' +
                ", teacherAnnounce='" + teacherAnnounce + '\'' +
                ", teachingNumber=" + teachingNumber +
                ", selectCourses=" + selectCourses +
                '}';
    }
}
