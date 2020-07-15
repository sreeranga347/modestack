package com.example.modestack_assignment.service.impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.modestack_assignment.dto.UserCreateDto;
import com.example.modestack_assignment.entity.User;
import com.example.modestack_assignment.repository.UserRepository;
import com.example.modestack_assignment.service.UserService;

@Service
public class UserServieImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public UserCreateDto createUser(UserCreateDto createDto) {
		User user=new User();
		BeanUtils.copyProperties(createDto, user);
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return createDto;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userRepository.findByUserName(username);
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), new ArrayList<>());
	}

}
