package com.example.modestack_assignment.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.modestack_assignment.model.request.UserRequestModel;
import com.example.modestack_assignment.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	public final AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager=authenticationManager;
	}
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		try {
		UserRequestModel requestModel=new ObjectMapper().readValue(request.getInputStream(), UserRequestModel.class);
		
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						requestModel.getUserName(), requestModel.getPassword(),new ArrayList<>()));
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String userName=((User)authResult.getPrincipal()).getUsername();
		String token=Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis()+Constants.EXP_TIME))
				.signWith(SignatureAlgorithm.HS512, Constants.SECRET)
				.compact();
				
		response.addHeader(Constants.HEADER_STRING, Constants.TOKEN_PRIFIX+token);
		response.getWriter().append("Success");
		
		
		
	}

	
}
