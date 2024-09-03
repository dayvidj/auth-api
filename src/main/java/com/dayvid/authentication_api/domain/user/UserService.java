package com.dayvid.authentication_api.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayvid.authentication_api.exception.DuplicateEntityException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	@Transactional
	public void saveUser(UserDataDTO data) {
		var encryptedPassword = new BCryptPasswordEncoder().encode(data.password()); 
		if(repository.findByUsername(data.username()) != null) {
			throw new DuplicateEntityException("Already registered user");
		}
		repository.save(new User(data.username(), encryptedPassword));		
	}
	
}
