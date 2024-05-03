package com.example.tankbookingsystem.service;

import com.example.tankbookingsystem.model.Booking;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookingService {

	List<Booking> getAllBookings();

	Optional<Booking> getBookingById(String bookingId) throws Exception;

	Booking createBooking(Booking booking);

	Booking patchBooking(String bookingId, Map<String, Object> updates);

	void deleteBooking(String bookingId) throws Exception;
}
