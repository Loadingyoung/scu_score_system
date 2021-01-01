package com.tenth.scu_score_system.Repository;

import com.tenth.scu_score_system.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Integer> {
    @Query(value = "select count(*) from administrator",nativeQuery = true)
    Integer countAll();

    @Query(value = "select apasswd from administrator where aid = ?1",nativeQuery = true)
    String findAdministratorByAdminId(Integer id);

    @Query(value = "select aname from administrator where aid = ?1",nativeQuery = true)
    String findAdminNameById(Integer id);
}