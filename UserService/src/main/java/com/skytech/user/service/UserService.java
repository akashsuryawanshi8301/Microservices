package com.skytech.user.service;

import java.util.List;

import com.skytech.user.entities.User;

public interface UserService {
	
	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUser(String userId); 

}
