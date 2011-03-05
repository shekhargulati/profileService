package com.shekhar.usermanagement.profileService.ws.client;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.shekhar.usermanagement.profile.UserProfile;
import com.shekhar.usermanagement.profile.UserProfileCreateRequest;
import com.shekhar.usermanagement.profile.UserProfileCreateResponse;

@Component
public class ProfileServiceClient {
	
	@Autowired
	WebServiceTemplate webServiceTemplate;

	public String invokeProfileServiceAndGetASuccessResponse() {
		UserProfileCreateRequest request = new UserProfileCreateRequest();
		UserProfile userProfile = new UserProfile();
		userProfile.setAge(BigInteger.valueOf(27));
		userProfile.setFirstName("Shekhar");
		userProfile.setLastName("Gulati");
		request.setUserProfile(userProfile);

		UserProfileCreateResponse response = (UserProfileCreateResponse) webServiceTemplate
				.marshalSendAndReceive(request);
		System.out.println(response.getMessage());
		return response.getMessage();
	}
}
