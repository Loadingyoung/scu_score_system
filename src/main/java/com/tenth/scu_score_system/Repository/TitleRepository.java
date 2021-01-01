package com.tenth.scu_score_system.Repository;

import com.tenth.scu_score_system.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TitleRepository extends JpaRepository<Title,String> {
    @Query(value = "select * from title",nativeQuery = true)
    List<Title> findAllById();
}
