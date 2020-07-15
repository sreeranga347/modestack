package com.example.modestack_assignment.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.modestack_assignment.dto.UserCreateDto;
import com.example.modestack_assignment.model.request.UserCreateRequestModel;
import com.example.modestack_assignment.model.request.UserRequestModel;
import com.example.modestack_assignment.service.UserService;

@RestController
//@RequestMapping("/")
public class UserController {
	@Autowired
	private UserService userService;

	
//	@PostMapping("/login")
//	public String login(@RequestBody UserRequestModel  requestModel) {
//		System.out.print(requestModel.getUserName());
//		return "Login Successfully";
//	}
	
	@PostMapping("/register")
	public String createUser(@RequestBody UserCreateRequestModel createRequestModel ) {
		UserCreateDto createDto=new UserCreateDto();
		BeanUtils.copyProperties(createRequestModel, createDto);
		userService.createUser(createDto);
		return "New User Created";
	}
}
