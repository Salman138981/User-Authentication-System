package com.masai.otpenabledapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.otpenabledapp.model.UserPojo;

@Repository
public interface UserRepository extends JpaRepository<UserPojo, String>{
   UserPojo findByUsername(String username);
} 
