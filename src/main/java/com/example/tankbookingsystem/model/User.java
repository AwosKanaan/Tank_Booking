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
	private String userName;
	private String password;
	private String email;
	private String phoneNumber;
}
