package com.power.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.power.exception.LoginException;
import com.power.model.LoginUser;
import com.power.model.User;
import com.power.model.UserSession;
import com.power.repository.UserDao;
import com.power.repository.UserSessionDao;

import net.bytebuddy.utility.RandomString;

@Service
public class UserSessionServiceImpl implements UserSessionService{
	
	@Autowired
	private UserDao udao;
	
	@Autowired
	private UserSessionDao usessiondao;

	@Override
	public String logIntoAccount(LoginUser user) throws LoginException {
	         
		
			Optional<User> opt=udao.findByMobileNumber(user.getMobileNumber());
			  if(!opt.isPresent())
				  return "Enter Registered Mobile Number";
			  
			  Integer userId = opt.get().getId();
			Optional<UserSession>  currUser= usessiondao.findByuserId(userId);
				
				if(currUser.isPresent()) {
					return "User already logged in with this number.";
				}
				
				if(user.getPassword().equals(opt.get().getPassword())) {
					
					String key = RandomString.make(6);
					UserSession currentUserSession = new UserSession(userId,opt.get().getUsername(), key, LocalDateTime.now());
					
					usessiondao.save(currentUserSession);
					
			     return currentUserSession.toString();
			  
		        }
				else
			  return "Please enter valid password";
		
		
	}

	@Override
	public String logOutAccount(String key) throws LoginException{
Optional<UserSession> currUserOpt=usessiondao.findByUuid(key);
		
		if(currUserOpt.isPresent()) {
			usessiondao.delete(currUserOpt.get());
			return "User logged out successfully.";
		}
		throw new LoginException("User does not exist, Enter correct uuid");
	}

	@Override
	public List<UserSession> getAllCurrentUsers() throws LoginException {
		List<UserSession> users=usessiondao.findAll();
		if(users.isEmpty())
			throw new LoginException("No data");
		
		return users;
	}



}
