package com.example.tankbookingsystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Booking {

	@Id
	private final String id;
	private final String bookingId;
	private final String userId;
	private final String vehicleId;
}
