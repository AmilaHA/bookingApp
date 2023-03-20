package com.encora.hotel.bookingApp.controller;

import com.encora.hotel.bookingApp.domain.Booking;
import com.encora.hotel.bookingApp.domain.Room;
import com.encora.hotel.bookingApp.exception.ResourceNotFoundException;
import com.encora.hotel.bookingApp.repository.RoomRepository;
import com.encora.hotel.bookingApp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private RoomRepository roomRepository;

    @PostMapping("/booking/{roomID}")
    public ResponseEntity<Booking> saveBooking(@RequestBody Booking booking, @PathVariable(value = "roomID") String roomID) {
        try {
            roomRepository.findById(roomID).map(room -> {
                booking.setRoom_Id(room);
                return bookingService.saveBooking(booking);
            }).orElseThrow(() -> new ResourceNotFoundException("Not found room with id = " + roomID));
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

    @GetMapping("/booking/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("id") String id) throws ResourceNotFoundException{
        return new ResponseEntity<Booking>(bookingService.findBookingById(id), HttpStatus.FOUND);
    }

    @GetMapping("/booking/byName/{name}")
    public List<Booking> getBookingByName(@PathVariable("name") String name){
        return bookingService.findBookingByCustomer(name);
    }

    @GetMapping("/booking")
    public List<Booking> getAllBookings(){
        return bookingService.findAllBookings();
    }

    @GetMapping("/booking/byDate/{Date}")
    public List<Room> getRoomsByDate(@PathVariable(value = "Date") Date date){
        return bookingService.findRoomsByDate(date);
    }

    @PutMapping("/booking/{id}")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking booking, @PathVariable("id") String id) throws ResourceNotFoundException{
        booking.setBooking_Id(id);
        return new ResponseEntity<Booking>(bookingService.updateBooking(booking), HttpStatus.OK);
    }

}
