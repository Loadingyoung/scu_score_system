package com.tenth.scu_score_system.Repository;

import com.tenth.scu_score_system.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building,Integer> {
    @Query(value = "select * from building where (cid = ?1)",nativeQuery = true)
    List<Building> findBuildingByCampusId(Integer cid);

    @Query(value = "select bid from building where (cid = ?1 and bd = ?2)",nativeQuery = true)
    Integer findBuildingIdByCampusIdAndName(Integer cid,String bd);

}
