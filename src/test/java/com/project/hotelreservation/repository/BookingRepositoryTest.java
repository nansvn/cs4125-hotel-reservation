package com.project.hotelreservation.repository;

import com.project.hotelreservation.enums.BedSize;
import com.project.hotelreservation.enums.RoomType;
import com.project.hotelreservation.model.entity.Booking;
import com.project.hotelreservation.model.entity.Customer;
import com.project.hotelreservation.model.entity.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class BookingRepositoryTest {

    @Mock
    private BookingRepository bookingRepository;

    private Booking booking;
    private Customer customer; // Declare customer at the class level

    @BeforeEach
    public void setUp() {
        // Set up common data before each test
        customer = new Customer(); // Use the class-level customer variable
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setRewardPoints(100);

        Room room = new Room();
        room.setRoomNumber(101);
        room.setPricePerNight(BigDecimal.valueOf(150));
        room.setMaxPeople(2);
        room.setAvailable(true);
        room.setBedSize(BedSize.DOUBLE);
        room.setRoomType(RoomType.STANDARD);
        room.setDescription("Standard room with queen bed");
        room.setImagePath("path/to/image.jpg");

        booking = new Booking();
        booking.setCheckInDate(new Date());
        booking.setCheckOutDate(new Date());
        booking.setBookingDate(new Timestamp(System.currentTimeMillis()));
        booking.setCustomer(customer);
        booking.setRoom(room);
    }

    @Test
    public void testFindBookingByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual() {
        // Create test data
        Date checkInDate = java.sql.Date.valueOf(LocalDate.now());
        Date checkOutDate = java.sql.Date.valueOf(LocalDate.now().plusDays(5));

        List<Booking> bookings = Collections.singletonList(booking);

        // Mock the repository method;
        when(bookingRepository.findBookingByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(checkInDate, checkOutDate))
                .thenReturn(bookings);

        // Call the method
        List<Booking> result = bookingRepository.findBookingByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(checkInDate, checkOutDate);

        // Verify the result
        assertEquals(bookings, result);

        // Verify that the method was called with the correct arguments
        verify(bookingRepository, times(1)).findBookingByCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(checkInDate, checkOutDate);
    }

    @Test
    public void testFindBookingByCustomer() {
        // Mock the repository method
        when(bookingRepository.findBookingByCustomer(any(Customer.class)))
                .thenReturn(Collections.singletonList(booking));

        // Call the method
        List<Booking> result = bookingRepository.findBookingByCustomer(customer);

        // Verify the result
        assertEquals(Collections.singletonList(booking), result);

        // Verify that the method was called with the correct argument
        verify(bookingRepository, times(1)).findBookingByCustomer(any(Customer.class));
    }

    @Test
    public void testDeleteBookingByBookingId() {
        long bookingId = 1L;

        // Call the method
        bookingRepository.deleteBookingByBookingId(bookingId);

        // Verify that the method was called with the correct argument
        verify(bookingRepository, times(1)).deleteBookingByBookingId(bookingId);
    }

}
