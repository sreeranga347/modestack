package com.example.modestack_assignment.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.modestack_assignment.dto.UserCreateDto;
import com.example.modestack_assignment.entity.User;

public interface UserService extends UserDetailsService {

	UserCreateDto createUser(UserCreateDto createDto);
}
