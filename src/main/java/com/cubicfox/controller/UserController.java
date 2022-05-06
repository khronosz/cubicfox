package com.cubicfox.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cubicfox.exception.BadRequestException;
import com.cubicfox.exception.IncorrectUserResponseException;
import com.cubicfox.mapper.UserMapper;
import com.cubicfox.model.domain.User;
import com.cubicfox.model.dto.UserDto;
import com.cubicfox.service.UserService;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping
@Log4j2
public class UserController {

	private static final String USER_API = "https://jsonplaceholder.typicode.com/users";

	private final UserService userService;

	private final UserMapper userMapper;

	private final RestTemplate restTemplate;

	public UserController(final UserService userService, final UserMapper userMapper, final RestTemplateBuilder builder) {
		this.userService = userService;
		this.userMapper = userMapper;
		this.restTemplate = builder.build();
	}

	@PostMapping(value = "/save")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		log.info("Start receiving the data from the URL: {}", USER_API);
		User[] users = Objects.requireNonNull(restTemplate.getForObject(USER_API, User[].class));
		List<UserDto> userDtoList = new ArrayList<>();
		log.info("Start mapping the data to dto...");
		for (User user : users) {
			userDtoList.add(userMapper.toDto(user));
		}
		log.info("Mapping successfully completed!");
		if (userDtoList.size() != 10) {
			log.error("Incorrect response! User data are not valid! The size of the data received: {}", userDtoList.size());
			throw new IncorrectUserResponseException("Incorrect response! User data are not valid!");
		}
		log.info("Data received successfully!");
		ResponseEntity<List<UserDto>> responseEntity = new ResponseEntity<>(userDtoList, HttpStatus.OK);
		if (responseEntity.getStatusCode() != HttpStatus.OK) {
			log.error("Bad Request! HTTP Status is not correct! {}", responseEntity.getStatusCode());
			throw new BadRequestException("Bad Request! HTTP Status is not correct!");
		}
		log.info("HTTP status: {}", responseEntity.getStatusCode());
		userService.saveAll(userDtoList);
		return responseEntity;
	}
}
