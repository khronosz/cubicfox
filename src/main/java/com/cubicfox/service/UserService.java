package com.cubicfox.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubicfox.mapper.UserMapper;
import com.cubicfox.model.domain.User;
import com.cubicfox.model.dto.UserDto;
import com.cubicfox.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
public class UserService {

	private final UserRepository userRepository;

	private final UserMapper userMapper;

	@Transactional
	public void saveAll(List<UserDto> userDtoList) {
		log.info("Start saving the data...");
		List<User> users = userDtoList.stream().map(userMapper::toEntity).collect(Collectors.toList());
		userRepository.saveAll(users);
		log.info("Save successfully completed!");
	}
}
