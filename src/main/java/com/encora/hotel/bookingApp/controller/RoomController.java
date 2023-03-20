package com.encora.hotel.bookingApp.controller;

import com.encora.hotel.bookingApp.domain.Room;
import com.encora.hotel.bookingApp.exception.ResourceNotFoundException;
import com.encora.hotel.bookingApp.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/rooms")
    public ResponseEntity<Room> saveRoom(@RequestBody Room room){
        return new ResponseEntity<Room>(roomService.saveRoom(room), HttpStatus.CREATED);
    }

    @GetMapping("/rooms")
    public List<Room> getAllRooms() {
        return roomService.getRooms();
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> searchRoom(@PathVariable String id) throws ResourceNotFoundException {
        return new ResponseEntity<Room>(roomService.searchRoom(id), HttpStatus.OK);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, @PathVariable String id) throws ResourceNotFoundException {
        return new ResponseEntity<Room>(roomService.updateRoom(room), HttpStatus.OK);
    }
}
