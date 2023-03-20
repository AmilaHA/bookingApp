package com.encora.hotel.bookingApp.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "Booking")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String booking_Id;
    private String guest_Name;
    private Date booking_Date;
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_Id", referencedColumnName = "room_Id", nullable = false)
    private Room room_Id;

    @Override
    public String toString() {
        return "Booking{" +
                "booking_Id='" + booking_Id + '\'' +
                ", guest_Name='" + guest_Name + '\'' +
                ", booking_Date=" + booking_Date +
                ", room_Id=" + room_Id +
                '}';
    }
}
