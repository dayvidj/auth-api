package com.dayvid.authentication_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dayvid.authentication_api.domain.user.User;
import com.dayvid.authentication_api.domain.user.UserDataDTO;
import com.dayvid.authentication_api.domain.user.UserService;
import com.dayvid.authentication_api.security.TokenJWT;
import com.dayvid.authentication_api.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserService service;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid UserDataDTO data) {
		var authenticationToken = new UsernamePasswordAuthenticationToken(data.username(), data.password());
		var authentication = manager.authenticate(authenticationToken);
		var tokenJWT = tokenService.tokenGenerate((User) authentication.getPrincipal());
		
		return ResponseEntity.ok(new TokenJWT(tokenJWT));
	}
	
	@PostMapping("/register") 
	public ResponseEntity register(@RequestBody UserDataDTO data) {
		service.saveUser(data);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/foo-bar")
	public ResponseEntity getFooBar() {
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	} 
	
}
