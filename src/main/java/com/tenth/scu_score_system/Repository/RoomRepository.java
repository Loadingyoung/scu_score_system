package com.tenth.scu_score_system.Repository;

import com.tenth.scu_score_system.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query(value = "select * from room where(cid = ?1 and bid = ?2)", nativeQuery = true)
    List<Room> findRoomByCampusIdAndBuildingId(Integer cid, Integer bid);
}
