package com.tenth.scu_score_system.service;

import com.tenth.scu_score_system.model.SelectCourse;

import java.util.List;

public interface SelectCourseService {
    List<SelectCourse> findAllSelectCourse();

    Integer countCoursesByStuId(Long id);

    Integer findScoreByStuIdAndCourseIdAndOrder(Long sid, Integer cid, Integer order);
    SelectCourse findSCByStuIdAndCourseIdAndOrder(Long sid, Integer cid, Integer order);
    SelectCourse findSCByStuIdAndCourseId(Long sid, Integer cid);
    SelectCourse findSCById(Long sid, Integer cid, Integer tid);
    List<List> findAllTeachingTimeByStuId(Long sid);
    void saveSelect(SelectCourse selectCourse);
    void deleteSC(SelectCourse selectCourse);
    List<SelectCourse> findSCByStuId(Long sid);
    List<List> findFinishedSCByStuId(Long stuId);
    List<SelectCourse> findSCByTcrIdAndCourseId(Integer tid, Integer cid);
}
