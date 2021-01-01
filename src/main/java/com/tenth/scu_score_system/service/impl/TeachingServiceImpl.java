package com.tenth.scu_score_system.service.impl;

import com.tenth.scu_score_system.Repository.*;
import com.tenth.scu_score_system.model.*;
import com.tenth.scu_score_system.service.TeachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "TeachingService")
public class TeachingServiceImpl implements TeachingService {
    @Autowired
    TeachingRepository teachingRepository;
    @Autowired
    BuildingRepository buildingRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teaching> findAllTeaching() {
        return teachingRepository.findAll();
    }

    @Override
    public void addTeaching(Teaching teaching) {
        teachingRepository.saveAndFlush(teaching);
    }

    @Override
    public Teaching findTeachingById(Integer cid, Integer corder) {
        if(cid==null||corder==null){
            return null;
        }
        else {
            return teachingRepository.findTeachingById(cid, corder);
        }
    }

    @Override
    public void deleteTeaching(Teaching teaching) {
        teachingRepository.delete(teaching);
    }

    @Override
    public List<Building> findBuildingByCid(Integer cid) {
        return buildingRepository.findBuildingByCampusId(cid);
    }

    @Override
    public List<Room> findRoomByCampusIdAndBuildingId(Integer cid, Integer bid) {
        return roomRepository.findRoomByCampusIdAndBuildingId(cid, bid);
    }

    @Override
    public Integer findBuildingIdByCampusIdAndName(Integer cid, String bd) {
        return buildingRepository.findBuildingIdByCampusIdAndName(cid, bd);
    }

    @Override
    public Integer findCampusIdByName(String cp) {
        if (cp.equals("望江")) {
            return 1;
        } else if (cp.equals("华西")) {
            return 2;
        } else if (cp.equals("江安")) {
            return 3;
        } else {
            return null;
        }
    }

    @Override
    public List<List> findAllTeachingWithSpecificInfo() {
        List<Teaching> all = teachingRepository.findAll();
        int size = all.size();
        List<List> lists = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            List<String> strings = new ArrayList<>();
            Integer teacherId = all.get(i).getTeachingId().getTeacher().getTeacherId();
            Integer courseId = all.get(i).getTeachingId().getCourse().getCourseId();
            Course courseByCourseId = courseRepository.findCourseByCourseId(courseId);
            Teacher teacherByTeacherId = teacherRepository.findTeacherByTeacherId(teacherId);
            strings.add(courseByCourseId.getCourseDept());
            strings.add(courseByCourseId.getCourseId().toString());
            strings.add(all.get(i).getCourseOrder().toString());
            strings.add(courseByCourseId.getCourseName());
            strings.add(courseByCourseId.getCourseType());
            strings.add(courseByCourseId.getCourseCredit().toString());
            strings.add(courseByCourseId.getCourseExam());
            strings.add(teacherByTeacherId.getTeacherName());
            strings.add(courseByCourseId.getCourseRestrict());
            strings.add(all.get(i).getTeachingNumber().toString());
            strings.add(all.get(i).getTeachingTime());
            strings.add(all.get(i).getTeachingAddress());
            strings.add(teacherId.toString());
            lists.add(strings);
        }
        return lists;
    }

    @Override
    public Teaching findTeachingByCidAndTid(Integer cid, Integer tid) {
        if(cid==null||tid==null){
            return null;
        }
        else {
            return teachingRepository.findTeachingByCidAndTid(cid, tid);
        }
    }

    @Override
    public List<Teaching> findAllTeachingOfTcrById(Integer tid) {
        return teachingRepository.findTeachingsByTid(tid);
    }
}
