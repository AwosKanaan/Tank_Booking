package com.example.tankbookingsystem.controller;

import com.example.tankbookingsystem.model.Booking;
import com.example.tankbookingsystem.service.BookingService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
	public ResponseEntity<Map<String,Object>> getBookingById(@PathVariable String id) {
		Map<String, Object> result = new HashMap<>();
		Optional<Booking> booking;
		try {
			booking = bookingService.getBookingById(id);
			result.put("Booking", booking);
		} catch (Exception e) {
			result.put("message", "booking with id " + id + " is not found");
		}
		return ResponseEntity.ok(result);
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
	public ResponseEntity<Map<String, Object>> updateBooking(@PathVariable String id, @RequestParam String updatedBooking) {
		Booking booking;
		Map<String, Object> currenMap, result = new HashMap<>();
		try {
			currenMap = mapper.readValue(updatedBooking, new TypeReference<>() {});
			booking = bookingService.patchBooking(id, currenMap);
			result.put("updated booking", booking);
		} catch (Exception e) {
			result.put("Message", e.getMessage());
		}
		return ResponseEntity.ok(result);
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
