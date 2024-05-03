package com.example.tankbookingsystem.dao;

import com.example.tankbookingsystem.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingDAO extends MongoRepository<Booking, String> {
}
