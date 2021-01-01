package com.tenth.scu_score_system.service.impl;

import com.tenth.scu_score_system.Repository.CourseRepository;
import com.tenth.scu_score_system.Repository.SelectCourseRepository;
import com.tenth.scu_score_system.Repository.TeachingRepository;
import com.tenth.scu_score_system.model.Course;
import com.tenth.scu_score_system.model.SelectCourse;
import com.tenth.scu_score_system.model.Teaching;
import com.tenth.scu_score_system.service.SelectCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "SelectCourseService")
public class SelectCourseServiceImpl implements SelectCourseService {
    @Autowired
    SelectCourseRepository selectCourseRepository;
    @Autowired
    TeachingRepository teachingRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public List<SelectCourse> findAllSelectCourse() {
        return selectCourseRepository.findAll();
    }

    @Override
    public Integer countCoursesByStuId(Long id) {
        return selectCourseRepository.countSelectCoursesByStuId(id);
    }

    @Override
    public Integer findScoreByStuIdAndCourseIdAndOrder(Long sid, Integer cid, Integer order) {
        return selectCourseRepository.findScore(sid, cid, order);
    }

    @Override
    public SelectCourse findSCByStuIdAndCourseIdAndOrder(Long sid, Integer cid, Integer order) {
        return selectCourseRepository.findSelectCourseById(sid, cid, order);
    }

    @Override
    public List<List> findAllTeachingTimeByStuId(Long sid) {
        List<SelectCourse> selectCourses = selectCourseRepository.findSelectCoursesByStuId(sid);
        List<List> times = new ArrayList<>();
        for (int i = 0; i < selectCourses.size(); i++) {
            Teaching teaching = teachingRepository.findTeachingById(selectCourses.get(i).getScId().getTeaching().getTeachingId().getCourse().getCourseId(), selectCourses.get(i).getCourseOrder());
            times.add(teaching.getTime());
        }
        return times;
    }

    @Override
    public void saveSelect(SelectCourse selectCourse) {
        selectCourseRepository.saveAndFlush(selectCourse);
    }

    @Override
    public SelectCourse findSCById(Long sid, Integer cid, Integer tid) {
        return selectCourseRepository.findSelectCourseByIds(sid, cid, tid);
    }

    @Override
    public void deleteSC(SelectCourse selectCourse) {
        selectCourseRepository.delete(selectCourse);
    }

    @Override
    public List<SelectCourse> findSCByStuId(Long sid) {
        return selectCourseRepository.findSelectCoursesByStuId(sid);
    }

    private double getGPA(Integer score) {
        double gpa = 0;
        if (score < 60) {
            gpa = 0;
        } else if (score <= 60) {
            gpa = 1;
        } else if (score <= 62) {
            gpa = 1.3;
        } else if (score <= 65) {
            gpa = 1.7;
        } else if (score <= 70) {
            gpa = 2;
        } else if (score <= 72) {
            gpa = 2.3;
        } else if (score <= 75) {
            gpa = 2.7;
        } else if (score < 80) {
            gpa = 3;
        } else if (score <= 84) {
            gpa = 3.3;
        } else if (score < 90) {
            gpa = 3.7;
        } else {
            gpa = 4;
        }
        return gpa;
    }

    @Override
    public List<List> findFinishedSCByStuId(Long stuId) {
        List<SelectCourse> selectCoursesByStuId = selectCourseRepository.findSelectCoursesByStuId(stuId);
        List<List> lists = new ArrayList<>();
        int size = selectCoursesByStuId.size();
        for (int i = 0; i < size; i++) {
            Integer score = selectCoursesByStuId.get(i).getScore();
            if (score != null) {
                List<String> strings = new ArrayList<>();
                Integer courseId = selectCoursesByStuId.get(i).getScId().getTeaching().getTeachingId().getCourse().getCourseId();
                Course courseByCourseId = courseRepository.findCourseByCourseId(courseId);
                strings.add(courseId.toString());
                strings.add(courseByCourseId.getCourseName());
                strings.add(courseByCourseId.getCourseType());
                strings.add(courseByCourseId.getCourseCredit().toString());
                strings.add(score.toString());
                strings.add(String.valueOf(getGPA(score)));
                lists.add(strings);
            }
        }
        return lists;
    }

    @Override
    public SelectCourse findSCByStuIdAndCourseId(Long sid, Integer cid) {
        return selectCourseRepository.findSelectCourseBysidandcid(sid, cid);
    }

    @Override
    public List<SelectCourse> findSCByTcrIdAndCourseId(Integer tid, Integer cid) {
        return null;
    }
}