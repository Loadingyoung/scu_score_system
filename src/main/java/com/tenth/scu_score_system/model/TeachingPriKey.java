package com.tenth.scu_score_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TeachingPriKey implements Serializable {
    @JsonIgnore
    @ManyToOne(targetEntity = Course.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "t_cid",referencedColumnName = "cid")
    private Course course;

    @JsonIgnore
    @ManyToOne(targetEntity = Teacher.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "t_tid",referencedColumnName = "tid")
    private Teacher teacher;

    public TeachingPriKey() {
    }

    public TeachingPriKey(Course course, Teacher teacher) {
        this.course = course;
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeachingPriKey)) return false;
        TeachingPriKey that = (TeachingPriKey) o;
        return Objects.equals(getCourse(), that.getCourse()) && Objects.equals(getTeacher(), that.getTeacher());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCourse(), getTeacher());
    }

    @Override
    public String toString() {
        return "TeachingPriKey{" +
                "course=" + course +
                ", teacher=" + teacher +
                '}';
    }
}