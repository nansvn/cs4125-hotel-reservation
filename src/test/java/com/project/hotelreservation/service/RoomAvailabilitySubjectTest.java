package com.project.hotelreservation.service;

import com.project.hotelreservation.model.entity.Room;
import com.project.hotelreservation.service.serviceImpl.RoomAvailabilitySubject;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class RoomAvailabilitySubjectTest {

    @Mock
    private RoomAvailabilityObserver observer1;

    @Mock
    private RoomAvailabilityObserver observer2;

    @Test
    public void testNotifyObservers() {
        // Create a subject
        RoomAvailabilitySubject subject = new RoomAvailabilitySubject(Arrays.asList(observer1, observer2));

        // Set a room on the subject
        Room room = new Room(); // Create a sample room object
        subject.setRoom(room);

        // Verify that both observers were updated
        Mockito.verify(observer1).update(room);
        Mockito.verify(observer2).update(room);
    }
}

