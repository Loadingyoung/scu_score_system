package com.tenth.scu_score_system.Repository;

import com.tenth.scu_score_system.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query(value = "select count(*) from course",nativeQuery = true)
    Integer countAll();

    @Query(value = "select * from course",nativeQuery = true)
    List<Course> findAllByCourseId();

    @Query(value = "select * from course where (cid = ?1)",nativeQuery = true)
    Course findCourseByCourseId(Integer cid);

    @Query(value = "select ccredit from course where (cid = ?1)",nativeQuery = true)
    Integer findCourseCreditByCourseId(Integer cid);
}
