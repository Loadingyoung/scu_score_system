package com.tenth.scu_score_system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(name ="id")
    private Integer id;
    @Column(name ="cid")
    private Integer campusId;
    @Column(name = "bid")
    private  Integer buldingId;
    @Column(name = "rid")
    private Integer roomId;
    @Column(name = "room")
    private String room;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCampusId() {
        return campusId;
    }

    public void setCampusId(Integer campusId) {
        this.campusId = campusId;
    }

    public Integer getBuldingId() {
        return buldingId;
    }

    public void setBuldingId(Integer buldingId) {
        this.buldingId = buldingId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
