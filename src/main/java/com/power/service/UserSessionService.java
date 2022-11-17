package com.power.service;

import java.util.List;

import com.power.exception.LoginException;
import com.power.model.LoginUser;
import com.power.model.User;
import com.power.model.UserSession;

public interface UserSessionService {
	public String logIntoAccount(LoginUser user) throws LoginException;
	
	public String logOutAccount(String key) throws LoginException;
	
	public List<UserSession> getAllCurrentUsers() throws LoginException;

}
