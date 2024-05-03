package com.example.tankbookingsystem.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {

	@Id
	private final String id;
	private final String userId;
	private final String userName;
	private final String password;
	private final String email;
	private final String phoneNumber;
}
