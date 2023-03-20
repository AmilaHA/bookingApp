package com.encora.hotel.bookingApp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Room")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String room_Id;
    private int room_No;
    private String room_Name;
    private String view;
    private String description;
    private double price;

    @Override
    public String toString() {
        return "Room{" +
                "room_Id='" + room_Id + '\'' +
                ", room_Name='" + room_Name + '\'' +
                ", view='" + view + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
