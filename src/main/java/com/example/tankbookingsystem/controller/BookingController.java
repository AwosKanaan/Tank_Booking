package com.example.tankbookingsystem.controller;

import com.example.tankbookingsystem.model.Booking;
import com.example.tankbookingsystem.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

	private final BookingService bookingService;
	private final ObjectMapper mapper;

	public BookingController(
			final BookingService bookingService,
			final ObjectMapper mapper
	) {
		this.bookingService = bookingService;
		this.mapper = mapper;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Booking> getBookingById(@PathVariable String id) {
		Optional<Booking> booking;
		try {
			booking = bookingService.getBookingById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return booking.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/save")
	public ResponseEntity<Booking> createBooking(@RequestBody Booking newBooking) {
		Booking booking;
		try {
			booking = bookingService.createBooking(newBooking);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(booking, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Booking> updateBooking(@PathVariable String id, @RequestParam String updatedBooking) {
		Optional<Booking> booking;
		try {
			Booking booking_new = mapper.readValue(updatedBooking, Booking.class);
			booking = bookingService.patchBooking(id, booking_new);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return booking.map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBooking(@PathVariable String id) {
		try {
			bookingService.deleteBooking(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
