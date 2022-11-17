package com.power.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.power.exception.LoginException;
import com.power.exception.UserException;
import com.power.model.LoginUser;
import com.power.model.PowerIn;
import com.power.model.PowerRange;
import com.power.model.PowerUsage;
import com.power.model.UsageDate;
import com.power.model.User;
import com.power.model.UserSession;
import com.power.service.PowerUsageServiceImpl;
import com.power.service.UserServiceImpl;
import com.power.service.UserSessionService;
import com.power.service.UserSessionServiceImpl;

@RestController

@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserSessionService userSessionService;

	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Autowired
	private PowerUsageServiceImpl puserviceImpl;
	
     //add user
	@PostMapping("/register")
	public ResponseEntity<User> saveUser(@RequestBody User user) throws UserException {

		return new ResponseEntity<User>(userServiceImpl.createUser(user),HttpStatus.CREATED);
	}
	
	// for user login
	@PostMapping("/userlogin")
	public ResponseEntity<String> logInUser(@RequestBody LoginUser user) throws LoginException{
		return new ResponseEntity<String>(userSessionService.logIntoAccount(user),HttpStatus.ACCEPTED);
	}

	// for user logout
	@DeleteMapping("/userlogout")
	public ResponseEntity<String>  logOutUser(@RequestParam(required = false) String key) throws LoginException {
		return new ResponseEntity<String> (userSessionService.logOutAccount(key),HttpStatus.ACCEPTED);
	}

	//get all current users
	@GetMapping("getallcurretuser")
	public ResponseEntity<List<UserSession>> getUsers() throws LoginException{
		return new ResponseEntity<List<UserSession>> (userSessionService.getAllCurrentUsers(),HttpStatus.OK);
	}
	
	//add powerusage
	@PostMapping("/addpowerusage")
	public ResponseEntity<PowerUsage> addPowerUsage(@RequestBody PowerIn pu, @RequestParam String key) throws Exception {
		return new ResponseEntity<PowerUsage>(puserviceImpl.addPowerUsage(pu,key),HttpStatus.CREATED);
	}
	
	//get power usage list
	@PatchMapping(value="/getlistpowerusage")
	public ResponseEntity<List<PowerUsage>> getListPowerUsage(@RequestBody UsageDate us, @RequestParam String key) throws Exception {
		return new ResponseEntity<List<PowerUsage>>(puserviceImpl.getList(us,key),HttpStatus.OK);
	}
	
	//period usage data
	@PatchMapping("getdaywiseusage")
	public ResponseEntity<PowerRange> getPowerRange(@RequestBody UsageDate us, @RequestParam String key) throws Exception {
		return new ResponseEntity<PowerRange>(puserviceImpl.getPowerRange(us, key),HttpStatus.OK);
	}
	
	
}
