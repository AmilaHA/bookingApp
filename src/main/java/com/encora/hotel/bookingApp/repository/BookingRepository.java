package com.encora.hotel.bookingApp.repository;

import com.encora.hotel.bookingApp.domain.Booking;
import com.encora.hotel.bookingApp.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    @Query("select b from Booking b where b.guest_Name = ?1")
    List<Booking> findBookingByGuest_Name(String name);

    @Query("select b.room_Id from Booking b where b.booking_Date = :date")
    List<Room> findAvailableRooms(@Param("date") Date date);
}
