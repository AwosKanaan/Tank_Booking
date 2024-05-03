package com.example.tankbookingsystem.service;

import com.example.tankbookingsystem.dao.BookingDAO;
import com.example.tankbookingsystem.model.Booking;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

	private final BookingDAO bookingDAO;

	public BookingServiceImpl(final BookingDAO bookingDAO) {
		this.bookingDAO = bookingDAO;
	}

	@Override
	public List<Booking> getAllBookings() {
		return bookingDAO.findAll();
	}

	@Override
	public Optional<Booking> getBookingById(String bookingId) throws Exception {
		Optional<Booking> booking = bookingDAO.findById(bookingId);
		if (booking.isPresent()) {
			return booking;
		} else {
			throw new Exception("Booking with id: " + bookingId + " is not found.");
		}
	}

	@Override
	public Booking createBooking(Booking booking) {
		return bookingDAO.save(booking);
	}

	public Booking patchBooking(String bookingId, Map<String, Object> updates) {
		return bookingDAO.findById(bookingId)
				.map(booking -> {
					updates.forEach((key, value) -> {
						Field field = null;
						try {
							field = Booking.class.getDeclaredField(key);
							field.setAccessible(true);
							field.set(booking, value);
						} catch (NoSuchFieldException | IllegalAccessException e) {
							assert field != null;
							throw new RuntimeException(field.getName() + " does not exist.", e);
						}
					});
					return bookingDAO.save(booking);
				})
				.orElseThrow(() -> new RuntimeException("Booking not found with id " + bookingId));
	}

	@Override
	public void deleteBooking(String bookingId) throws Exception {
		Optional<Booking> booking = bookingDAO.findById(bookingId);
		if (booking.isPresent()) {
			bookingDAO.deleteById(bookingId);
		} else {
			throw new Exception("Booking with id : " + bookingId + " is not found.");
		}
	}
}
