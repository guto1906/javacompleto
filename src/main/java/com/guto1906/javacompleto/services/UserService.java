package com.guto1906.javacompleto.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.guto1906.javacompleto.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		
		try {
			return (UserSS)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			
		} catch (Exception e) {
			return null;
		}
	}

}
