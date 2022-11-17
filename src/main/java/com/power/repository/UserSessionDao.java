package com.power.repository;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;

import com.power.model.User;
import com.power.model.UserSession;

public interface UserSessionDao extends JpaRepository<UserSession, Integer>{

	//to get usersession for deleting while logout
	Optional<UserSession> findByUuid(String key);

	//to get usersession using userid to check whether user already  logged in or not

	Optional<UserSession> findByuserId(Integer userId);

}
