package com.voronov.service;

import com.voronov.service.serviceInterfaces.LoginService;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
	@Override
	public boolean validateUser(String user, String password) {
		return user.equalsIgnoreCase("admin") && password.equals("12345");
	}

}