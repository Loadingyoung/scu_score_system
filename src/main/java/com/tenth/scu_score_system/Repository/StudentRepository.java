package com.tenth.scu_score_system.Repository;

import com.tenth.scu_score_system.model.Student;
import com.tenth.scu_score_system.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(value = "select count(*) from student",nativeQuery = true)
    Integer countAll();

    @Query(value = "select * from student",nativeQuery = true)
    List<Student> findAllByStudentId();

    @Query(value = "select * from student where sid = ?1",nativeQuery = true)
    Student findStuByStudentId(Long sid);

    @Query(value = "select sname from student where(sid = ?1)",nativeQuery = true)
    String findStuNameById(Long sid);

    @Query(value = "select spasswd from student where(sid = ?1)",nativeQuery = true)
    String findStuPswdById(Long sid);

}
