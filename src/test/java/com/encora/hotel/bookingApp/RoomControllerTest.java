package com.encora.hotel.bookingApp;

import static org.junit.Assert.assertNotNull;

import com.encora.hotel.bookingApp.domain.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookingAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoomControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllRooms() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/rooms",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetRoomByID() {
        Room room = restTemplate.getForObject(getRootUrl() + "/rooms/1", Room.class);
        System.out.println(room.getRoom_Name());
        assertNotNull(room);
    }

    @Test
    public void testSaveRoom() {
        Room room = new Room();
        room.setPrice(1200.89);
        room.setRoom_Name("Delux");
        room.setRoom_No(200);
        room.setView("sea");
        room.setDescription("awesome");
        ResponseEntity<Room> postResponse = restTemplate.postForEntity(getRootUrl() + "/rooms", room, Room.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

}
