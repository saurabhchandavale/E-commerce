package com.example.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.config.JwtProvider;
import com.example.demo.exception.UserException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userrRepository;
	@Autowired
	private JwtProvider jwtProvider;
	

	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User> user = userrRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		throw new UserException("User not found with Id " + userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email = jwtProvider.generateEmailFromToken(jwt);
		User user = userrRepository.findByEmail(email);
		
		if(user == null) {
			throw new UserException("User not found with email " + email);
		}
		return user;
		
	}

}
