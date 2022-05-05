package com.cubicfox.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cubicfox.model.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
