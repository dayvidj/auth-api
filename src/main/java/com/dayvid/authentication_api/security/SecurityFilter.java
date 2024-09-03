package com.dayvid.authentication_api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dayvid.authentication_api.domain.user.UserRepository;
import com.dayvid.authentication_api.exception.InvalidTokenException;
import com.dayvid.authentication_api.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserRepository userRepository;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String tokenJWT = extractToken(request);
		
		try {
			if (tokenJWT != null) {
				authenticateUser(tokenJWT);
			}
		} catch (InvalidTokenException ex) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
			response.setContentType("application/json");
			response.getWriter().write("{\"error\": \"" + ex.getMessage() + "\"}");
			return;
		}

		filterChain.doFilter(request, response);
	}

	private String extractToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}
		return null;
	}

	private void authenticateUser(String token) {
		String subject = tokenService.getSubject(token);
		UserDetails user = userRepository.findByUsername(subject);

		if (user == null) {
			throw new InvalidTokenException("Invalid token");
		}

		var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
