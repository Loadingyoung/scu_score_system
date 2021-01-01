package com.tenth.scu_score_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SCPriKey implements Serializable {
    @JsonIgnore
    @ManyToOne(targetEntity = Student.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "sc_sid", referencedColumnName = "sid")
    private Student student;

    @JsonIgnore
    @ManyToOne(targetEntity = Teaching.class,cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "sc_cid", referencedColumnName = "t_cid"),
            @JoinColumn(name = "sc_tid", referencedColumnName = "t_tid")
    })
    private Teaching teaching;

    public SCPriKey() {
    }

    public SCPriKey(Student student, Teaching teaching) {
        this.student = student;
        this.teaching = teaching;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Teaching getTeaching() {
        return teaching;
    }

    public void setTeaching(Teaching teaching) {
        this.teaching = teaching;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SCPriKey)) return false;
        SCPriKey scPriKey = (SCPriKey) o;
        return Objects.equals(getStudent(), scPriKey.getStudent()) && Objects.equals(getTeaching(), scPriKey.getTeaching());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getTeaching());
    }

    @Override
    public String toString() {
        return "SCPriKey{" +
                "student=" + student +
                ", teaching=" + teaching +
                '}';
    }
}
