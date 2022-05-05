package com.cubicfox.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cubicfox.model.domain.User;
import com.cubicfox.repository.UserRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public void saveAll(List<User> users) {
		userRepository.saveAll(users);
	}
}
