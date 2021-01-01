package com.tenth.scu_score_system.model;

import javax.persistence.*;

@Entity
@Table(name = "selectcourse")
public class SelectCourse {

    @EmbeddedId
    private SCPriKey scId;
    @Column(name = "sc_corder")
    private Integer courseOrder;
    @Column(name = "attend")
    private Integer attend;
    @Column(name = "homework")
    private Integer homework;
    @Column(name = "midterm")
    private Integer midterm;
    @Column(name = "endterm")
    private Integer endterm;
    @Column(name = "score")
    private Integer score;

    public SCPriKey getScId() {
        return scId;
    }

    public void setScId(SCPriKey scId) {
        this.scId = scId;
    }

    public Integer getCourseOrder() {
        return courseOrder;
    }

    public void setCourseOrder(Integer courseOrder) {
        this.courseOrder = courseOrder;
    }

    public Integer getAttend() {
        return attend;
    }

    public void setAttend(Integer attend) {
        this.attend = attend;
    }

    public Integer getHomework() {
        return homework;
    }

    public void setHomework(Integer homework) {
        this.homework = homework;
    }

    public Integer getMidterm() {
        return midterm;
    }

    public void setMidterm(Integer midterm) {
        this.midterm = midterm;
    }

    public Integer getEndterm() {
        return endterm;
    }

    public void setEndterm(Integer endterm) {
        this.endterm = endterm;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
