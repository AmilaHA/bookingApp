package com.encora.hotel.bookingApp.service;

import com.encora.hotel.bookingApp.domain.Booking;
import com.encora.hotel.bookingApp.domain.Room;
import com.encora.hotel.bookingApp.exception.ResourceNotFoundException;
import com.encora.hotel.bookingApp.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public List<Booking> findBookingByCustomer(String guestName) {
        return bookingRepository.findBookingByGuest_Name(guestName);
    }

    public Booking findBookingById(String id) throws ResourceNotFoundException{
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if(!optionalBooking.isPresent()) {
            throw new ResourceNotFoundException("Booking not Found...!");
        } else {
            return optionalBooking.get();
        }
    }

    public List<Booking> findAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Room> findRoomsByDate(Date date) {
        return bookingRepository.findAvailableRooms(date);
    }

    public Booking updateBooking(Booking booking) throws ResourceNotFoundException {
        Optional<Booking> bookingOptional = bookingRepository.findById(booking.getBooking_Id());
        if (!bookingOptional.isPresent()) {
            throw new ResourceNotFoundException("Booking not found...!");
        } else {
            Booking booking1 = bookingOptional.get();
            booking1.setBooking_Date(booking.getBooking_Date());
            booking1.setGuest_Name(booking.getGuest_Name());
            booking1.setRoom_Id(booking.getRoom_Id());
            return booking1;
        }
    }
}
