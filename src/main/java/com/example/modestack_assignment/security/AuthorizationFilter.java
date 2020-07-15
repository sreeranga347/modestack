package com.example.modestack_assignment.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.modestack_assignment.utils.Constants;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter {

	public AuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		String header=request.getHeader(Constants.HEADER_STRING);
		if(header==null || !header.startsWith(Constants.TOKEN_PRIFIX)) {
			chain.doFilter(request, response);
			return;
		}
		
		UsernamePasswordAuthenticationToken authenticationToken=getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		chain.doFilter(request, response);
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token=request.getHeader(Constants.HEADER_STRING);
		if(token!=null) {
			token=token.replace(Constants.TOKEN_PRIFIX, "");
			String user=Jwts.parser().setSigningKey(Constants.SECRET)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
			if(user!=null) {
				return new UsernamePasswordAuthenticationToken(user, null,new ArrayList<>());
			}
			return null;
		}
		return null;
	}

}
