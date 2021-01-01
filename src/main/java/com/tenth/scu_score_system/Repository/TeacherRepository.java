package com.tenth.scu_score_system.Repository;

import com.tenth.scu_score_system.model.Teacher;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Integer> {
    @Query(value = "select count(*) from teacher",nativeQuery = true)
    Integer countAll();

    @Query(value = "select * from teacher",nativeQuery = true)
    List<Teacher> findAllByTeacherId();

    @Query(value = "select * from teacher where tid = ?1",nativeQuery = true)
    Teacher findTeacherByTeacherId(Integer tid);
}
