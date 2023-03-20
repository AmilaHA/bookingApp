package com.encora.hotel.bookingApp.repository;

import com.encora.hotel.bookingApp.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {

    @Query("select r.room_Name from Room r where r.room_Id = ?1")
    String getRoomNameById(String roomId);
}
