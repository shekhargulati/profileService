package com.shekhar.usermanagement.profileService.ws.server;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shekhar.usermanagement.profile.UserProfile;

@Service
public class UserServiceImpl implements UserService {

	private static final Map<String, UserProfile> USERS = new HashMap<String, UserProfile>();

	public String createUser(UserProfile userProfile) {
		USERS.put(userProfile.getFirstName(), userProfile);
		return "user created successfully";
	}

}
