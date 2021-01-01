package com.tenth.scu_score_system.Repository;

import com.tenth.scu_score_system.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,String> {
    @Query(value = "select * from department",nativeQuery = true)
    List<Department> findAllById();

    @Query(value = "select id from department where dept = ?1",nativeQuery = true)
    Integer findDepartmentIdByName(String dept);
}
