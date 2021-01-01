package com.tenth.scu_score_system.Repository;

import com.tenth.scu_score_system.model.Teaching;
import com.tenth.scu_score_system.model.TeachingPriKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TeachingRepository extends JpaRepository<Teaching, TeachingPriKey> {
    @Query(value = "select * from teaching", nativeQuery = true)
    List<Teaching> findAll();

    @Query(value = "select * from teaching where(t_cid=?1 and t_corder=?2)", nativeQuery = true)
    Teaching findTeachingById(Integer cid, Integer corder);

    @Query(value = "select * from teaching where(t_cid=?1 and t_tid=?2)", nativeQuery = true)
    Teaching findTeachingByCidAndTid(Integer cid, Integer tid);

    @Query(value = "select * from teaching where(t_tid=?1)", nativeQuery = true)
    List<Teaching> findTeachingsByTid(Integer tid);

}
