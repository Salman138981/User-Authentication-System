package com.masai.otpenabledapp.service;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.masai.otpenabledapp.model.UserPojo;
import com.masai.otpenabledapp.repository.UserRepository;


@Service
public class UsersService implements UserDetailsService {
	
  @Autowired
  private UserRepository userRepository;
  
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		UserPojo userPojo = userRepository.findByUsername(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(userPojo.getRole());
		UserDetails userDetails = (UserDetails) new User(userPojo.getUsername(),
		userPojo.getPassword(), Arrays.asList(authority));
		
return userDetails;
}
}
