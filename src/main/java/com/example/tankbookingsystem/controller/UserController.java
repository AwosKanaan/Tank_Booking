package com.example.tankbookingsystem.controller;

import com.example.tankbookingsystem.model.User;
import com.example.tankbookingsystem.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService userService;
	private final ObjectMapper mapper;

	public UserController(final UserService userService, final ObjectMapper mapper) {
		this.userService = userService;
		this.mapper = mapper;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> getUserById(@PathVariable String id) {
		Map<String, Object> result = new HashMap<>();
				Optional<User> user;
				try {
					user = userService.getUserById(id);
					result.put("User", user);
				} catch (Exception e) {
					result.put("message", "user with id " + id + " is not found");
				}
				return ResponseEntity.ok(result);
	}

	@PostMapping("/save")
	public ResponseEntity<Map<String, Object>> createUser(@RequestBody User newUser) {
		Map<String, Object> result = new HashMap<>();
		User user;
				try {
					user = userService.createUser(newUser);
					result.put("User", user);
				} catch (Exception e) {
					result.put("Message", "Failed to create user due to " + e.getMessage());
				}
				return ResponseEntity.ok(result);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> updateUser(@PathVariable String id, @RequestParam String updatedUser) {
		User user;
				Map<String, Object> currenMap, result = new HashMap<>();
				try {
					currenMap = mapper.readValue(updatedUser, new TypeReference<>() {});
					user = userService.patchUser(id, currenMap);
					result.put("updated user", user);
				} catch (Exception e) {
					result.put("Message", e.getMessage());
				}
				return ResponseEntity.ok(result);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable String id) {
		Map<String, Object> result = new HashMap<>();
		try{
			userService.deleteUser(id);
			result.put("Deleted user with id", id);
		} catch (Exception e) {
			result.put("Message", e.getMessage());
		}
		return ResponseEntity.ok(result);
	}
}
