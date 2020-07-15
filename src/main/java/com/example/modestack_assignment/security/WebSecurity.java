package com.example.modestack_assignment.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.modestack_assignment.service.UserService;
import com.example.modestack_assignment.utils.Constants;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{
   	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;


	public WebSecurity(UserService detailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = detailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST,Constants.SIGN_UP_URL)
		.permitAll()
		.anyRequest().authenticated()
		
		.and().addFilter(new AuthenticationFilter(authenticationManager()))
		.addFilter(new AuthorizationFilter(authenticationManager()));
	
	}

}
