package com.power.service;

import com.power.exception.UserException;
import com.power.model.User;

public interface UserService {
	
    public User createUser(User user) throws UserException;
	
	public User updateUser(User user, String key) throws UserException;

}
