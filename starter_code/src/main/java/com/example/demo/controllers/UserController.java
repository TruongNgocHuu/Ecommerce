package com.example.demo.controllers;
import com.splunk.logging.*;
import org.apache.http.client.utils.DateUtils;
import org.apache.logging.log4j.*;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Objects;

@RestController
@RequestMapping("/api/user")
public class UserController {
	Logger log = org.apache.logging.log4j.LogManager.getLogger(UserController.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/id/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		return ResponseEntity.of(userRepository.findById(id));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUserName(@PathVariable String username) {
		User user = userRepository.findByUsername(username);
		if (Objects.isNull(user)){
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(user);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
		log.info("connect to create User api");
		User user = new User();
		String userName = createUserRequest.getUsername();
		if(Objects.isNull(userRepository.findByUsername(userName))){
			if (createUserRequest.getPassword().length()<5 ||
					!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())){
				log.info("Weak password is the cause of error create user failed" + LocalDateTime.now());
				return ResponseEntity.badRequest().build();
			}
		}else {
			log.error("User exists is the cause of error create user failed");
			throw new RuntimeException("Username Already Exists");
		}

		user.setUsername(userName);
		user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
		Cart cart = new Cart();
		cartRepository.save(cart);
		user.setCart(cart);
		userRepository.save(user);
		log.info("Create User Success " + user.getUsername());

		return ResponseEntity.ok(user);
	}
	
}
