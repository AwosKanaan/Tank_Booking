package com.example.tankbookingsystem.service;

import com.example.tankbookingsystem.model.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface VehicleService {

	List<Vehicle> getAllVehicles();

	Optional<Vehicle> getVehicleById(String vehicleId) throws Exception;

	Vehicle createVehicle(Vehicle vehicle);

	Vehicle patchVehicle(String vehicleId, Map<String, Object> updates);

	void deleteVehicle(String vehicleId) throws Exception;
}
