package com.jasperproject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jasperproject.Models.User;
import com.jasperproject.UserRepository.UserRepository;
import java.util.*;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public String saveUser(List<User> users) {
				
		userRepository.saveAll(users);
		
		return "All Users saved successfully !!";
	}
	
	public List<User> allusers(){
		
		List<User> userList = userRepository.findAll();
		
		return userList;
	}
}
