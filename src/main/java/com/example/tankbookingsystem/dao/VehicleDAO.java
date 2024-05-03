package com.example.tankbookingsystem.dao;

import com.example.tankbookingsystem.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleDAO extends MongoRepository<Vehicle, String> {
}
