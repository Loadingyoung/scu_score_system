package com.tenth.scu_score_system.Repository;

import com.tenth.scu_score_system.model.SelectCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectCourseRepository extends JpaRepository<SelectCourse, Long> {
    @Query(value = "select * from selectcourse", nativeQuery = true)
    List<SelectCourse> findAll();

    @Query(value = "select count (*) from selectcourse where(sc_sid = ?1)", nativeQuery = true)
    Integer countSelectCoursesByStuId(Long stuId);

    @Query(value = "select score from selectcourse where(sc_sid=?1 and sc_cid =?2 and sc_corder=?3)", nativeQuery = true)
    Integer findScore(Long sid, Integer cid, Integer order);

    @Query(value = "select * from selectcourse where(sc_sid=?1 and sc_cid =?2 and sc_corder=?3)", nativeQuery = true)
    SelectCourse findSelectCourseById(Long sid, Integer cid, Integer order);

    @Query(value = "select * from selectcourse where(sc_sid=?1)", nativeQuery = true)
    List<SelectCourse> findSelectCoursesByStuId(Long sid);

    @Query(value = "select * from selectcourse where(sc_sid=?1 and sc_cid =?2 and sc_tid=?3)", nativeQuery = true)
    SelectCourse findSelectCourseByIds(Long sid, Integer cid, Integer tid);

    @Query(value = "select * from selectcourse where(sc_sid=?1 and sc_cid =?2)", nativeQuery = true)
    SelectCourse findSelectCourseBysidandcid(Long sid, Integer cid);

    @Query(value = "select * from selectcourse where(sc_tid=?1 and sc_cid =?2)", nativeQuery = true)
    List<SelectCourse> findSelectCoursesByTidAndCid(Integer tid, Integer cid);


}
