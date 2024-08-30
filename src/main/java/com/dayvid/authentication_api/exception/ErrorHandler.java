package com.dayvid.authentication_api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.auth0.jwt.exceptions.JWTVerificationException;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> tratarErroBadCredentials() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
	}

	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> tratarErroAuthentication() {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> tratarErroAcessoNegado() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
	}

	@ExceptionHandler(InvalidTokenException.class)
	public ResponseEntity<String> tratarErroTokenInvalido(InvalidTokenException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
	}

	@ExceptionHandler(JWTVerificationException.class)
	public ResponseEntity<String> tratarErroJWTVerification(JWTVerificationException ex) {
		InvalidTokenException invalidTokenException = new InvalidTokenException("Invalid or expired JWT token");
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(invalidTokenException.getMessage());
	}

}
