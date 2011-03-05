package com.shekhar.usermanagement.profileService.ws.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.shekhar.usermanagement.profile.UserProfileCreateRequest;
import com.shekhar.usermanagement.profile.UserProfileCreateResponse;

@Endpoint
public class UserProfileEndpoint {

	private UserService service;

	@Autowired
	public UserProfileEndpoint(UserService service) {
		this.service = service;
	}

	@PayloadRoot(localPart = "UserProfileCreateRequest", namespace = "http://shekhar.com/usermanagement/schemas")
	@ResponsePayload
	public UserProfileCreateResponse create(@RequestPayload UserProfileCreateRequest request) {
		String message = service.createUser(request.getUserProfile());
		UserProfileCreateResponse response = new UserProfileCreateResponse();
		response.setMessage(message);
		return response;

	}

}
