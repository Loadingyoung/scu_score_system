package com.tenth.scu_score_system.Repository;

import com.tenth.scu_score_system.model.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major,Integer> {
    @Query(value = "select * from major",nativeQuery = true)
    List<Major> findAllById();

    List<Major> findMajorByDeptId(Integer deptId);

}
