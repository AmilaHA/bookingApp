package com.encora.hotel.bookingApp.service;

import com.encora.hotel.bookingApp.domain.Room;
import com.encora.hotel.bookingApp.exception.ResourceNotFoundException;
import com.encora.hotel.bookingApp.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public Room saveRoom(Room room){
        return roomRepository.save(room);
    }

    public List<Room> getRooms(){
        return roomRepository.findAll();
    }

    public String getRoomName(String id){
        return roomRepository.getRoomNameById(id);
    }

    public Room searchRoom(String id) throws ResourceNotFoundException {
        Optional<Room> roomOptional = roomRepository.findById(id);
        if (!roomOptional.isPresent()) {
            throw new ResourceNotFoundException("Room not found...!");
        } else {
            return roomOptional.get();
        }
    }

    public Room updateRoom(Room room) throws ResourceNotFoundException{
        Optional<Room> optionalRoom = roomRepository.findById(room.getRoom_Id());
        if (!optionalRoom.isPresent()) {
            throw new ResourceNotFoundException("Room not found...!");
        } else {
            Room room1 = optionalRoom.get();
            room1.setRoom_Name(room.getRoom_Name());
            room1.setDescription(room.getDescription());
            room1.setView(room.getView());
            room1.setPrice(room.getPrice());
            return room1;
        }
    }
}
