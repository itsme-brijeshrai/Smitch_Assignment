package com.power.service;

import java.util.Optional;

import javax.sound.sampled.UnsupportedAudioFileException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

import com.power.exception.UserException;
import com.power.model.User;
import com.power.model.UserSession;
import com.power.repository.UserDao;
import com.power.repository.UserSessionDao;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserSessionDao userSessionDao;
	
	@Override
	public User createUser(User user) throws UserException {
       Optional<User> opt= userDao.findByMobileNumber(user.getMobileNumber());
		
		if(opt.isPresent()) {
			throw new UserException("User already exist");
		  }
		return userDao.save(user);
		
	}

	@Override
	public User updateUser(User user, String key) throws UserException {
		 Optional<UserSession> optCurrUser= userSessionDao.findByUuid(key);
			
			if(!optCurrUser.isPresent()) {
				
				throw new  UserException("Unauthorised access");
			}
			
			return userDao.save(user);
			
			
	}

}
