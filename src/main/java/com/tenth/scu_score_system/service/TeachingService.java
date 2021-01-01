package com.tenth.scu_score_system.service;

import com.tenth.scu_score_system.model.Building;
import com.tenth.scu_score_system.model.Room;
import com.tenth.scu_score_system.model.Teaching;

import java.util.List;

public interface TeachingService {
    List<Teaching> findAllTeaching();
    void addTeaching(Teaching teaching);
    Teaching findTeachingById(Integer cid,Integer corder);
    Teaching findTeachingByCidAndTid(Integer cid,Integer tid);
    void deleteTeaching(Teaching teaching);
    List<Building> findBuildingByCid(Integer cid);
    List<Room> findRoomByCampusIdAndBuildingId(Integer cid, Integer bid);
    Integer findBuildingIdByCampusIdAndName(Integer cid,String bd);
    Integer findCampusIdByName(String cp);
    List<List> findAllTeachingWithSpecificInfo();
    List<Teaching> findAllTeachingOfTcrById(Integer tid);
}
