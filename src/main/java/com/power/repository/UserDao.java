package com.power.repository;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import com.power.model.User;
import com.power.model.UserSession;


public interface UserDao extends JpaRepository<User, Integer>{

	//to get user using mobile number
	Optional<User> findByMobileNumber( String mobileNumber);

	
	

}
