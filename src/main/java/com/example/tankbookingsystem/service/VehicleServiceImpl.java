package com.example.tankbookingsystem.service;

import com.example.tankbookingsystem.dao.VehicleDAO;
import com.example.tankbookingsystem.model.Booking;
import com.example.tankbookingsystem.model.Vehicle;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

	private final VehicleDAO vehicleDAO;

	public VehicleServiceImpl(final VehicleDAO vehicleDAO) {
		this.vehicleDAO = vehicleDAO;
	}

	@Override
	public List<Vehicle> getAllVehicles() {
		return vehicleDAO.findAll();
	}

	@Override
	public Optional<Vehicle> getVehicleById(String vehicleId) throws Exception {
		Optional<Vehicle> vehicle = vehicleDAO.findById(vehicleId);
		if (vehicle.isPresent()) {
			return vehicle;
		} else {
			throw new Exception("Vehicle with id: " + vehicleId + " is not found.");
		}
	}

	@Override
	public Vehicle createVehicle(Vehicle vehicle) {
		return vehicleDAO.save(vehicle);
	}

	public Vehicle patchVehicle(String vehicleId, Map<String, Object> updates) {
		return vehicleDAO.findById(vehicleId)
				.map(vehicle -> {
					updates.forEach((key, value) -> {
						Field field = null;
						try {
							field = Vehicle.class.getDeclaredField(key);
							field.setAccessible(true);
							field.set(vehicle, value);
						} catch (NoSuchFieldException | IllegalAccessException e) {
							assert field != null;
							throw new RuntimeException(field.getName() + " does not exist.", e);
						}
					});
					return vehicleDAO.save(vehicle);
				})
				.orElseThrow(() -> new RuntimeException("Vehicle not found with id " + vehicleId));
	}

	@Override
	public void deleteVehicle(String vehicleId) throws Exception {
		Optional<Vehicle> vehicle = vehicleDAO.findById(vehicleId);
		if (vehicle.isPresent()) {
			vehicleDAO.deleteById(vehicleId);
		} else {
			throw new Exception("vehicle with id : " + vehicleId + " is not found.");
		}
	}
}
