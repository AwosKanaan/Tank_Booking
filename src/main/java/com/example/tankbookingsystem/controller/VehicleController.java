package com.example.tankbookingsystem.controller;

import com.example.tankbookingsystem.model.Vehicle;
import com.example.tankbookingsystem.service.VehicleService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

	private final VehicleService vehicleService;
	private final ObjectMapper mapper;

	public VehicleController(final VehicleService vehicleService, final ObjectMapper mapper) {
		this.vehicleService = vehicleService;
		this.mapper = mapper;
	}

	@GetMapping("getAll")
	public ResponseEntity<Map<String, Object>> getAllVehicles() {
		Map<String, Object> result = new HashMap<>();
		result.put("vehicles", vehicleService.getAllVehicles());
		return ResponseEntity.ok(result);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getVehicleById(@PathVariable String id) {
		Map<String, Object> result = new HashMap<>();
		Optional<Vehicle> vehicle;
		try {
			vehicle = vehicleService.getVehicleById(id);
			result.put("Vehicle", vehicle);
		} catch (Exception e) {
			result.put("message", "vehicle with id " + id + " is not found");
		}
		return ResponseEntity.ok(result);
	}

	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> createVehicle(@RequestBody Vehicle newVehicle) {
		Map<String, Object> result = new HashMap<>();
		Vehicle vehicle;
		try {
			vehicle = vehicleService.createVehicle(newVehicle);
			result.put("Vehicle", vehicle);
		} catch (Exception e) {
			result.put("Message", "Failed to create vehicle due to " + e.getMessage());
		}
		return ResponseEntity.ok(result);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Map<String, Object>> updateVehicle(@PathVariable String id, @RequestParam String updatedVehicle) {
		Vehicle vehicle;
		Map<String, Object> currenMap, result = new HashMap<>();
		try {
			currenMap = mapper.readValue(updatedVehicle, new TypeReference<>() {});
			vehicle = vehicleService.patchVehicle(id, currenMap);
			result.put("updated vehicle", vehicle);
		} catch (Exception e) {
			result.put("Message", e.getMessage());
		}
		return ResponseEntity.ok(result);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> deleteVehicle(@PathVariable String id) {
		Map<String, Object> result = new HashMap<>();
		try{
			vehicleService.deleteVehicle(id);
			result.put("Deleted vehicle with id", id);
		} catch (Exception e) {
			result.put("Message", e.getMessage());
		}
		return ResponseEntity.ok(result);
	}
}
