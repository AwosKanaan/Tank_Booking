package com.example.tankbookingsystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
public class Vehicle {

	@Id
	private final String id;
	private final String vehicleId;
	private final String model;
	private final boolean availability;
}
