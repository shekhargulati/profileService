package com.shekhar.usermanagement.profileService.ws.client;

import java.math.BigInteger;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.shekhar.usermanagement.profile.UserProfile;
import com.shekhar.usermanagement.profile.UserProfileCreateRequest;
import com.shekhar.usermanagement.profile.UserProfileCreateResponse;

public class ProfileServiceClient {

	private WebServiceTemplate webServiceTemplate;

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"com/shekhar/usermanagement/profileService/ws/client/profile-service-client.xml");
		WebServiceTemplate template = applicationContext
				.getBean(WebServiceTemplate.class);
		ProfileServiceClient client = new ProfileServiceClient();
		client.webServiceTemplate = template;
		client.invokeProfileServiceAndGetASuccessResponse();

	}

	public void invokeProfileServiceAndGetASuccessResponse() {
		UserProfileCreateRequest request = new UserProfileCreateRequest();
		UserProfile userProfile = new UserProfile();
		userProfile.setAge(BigInteger.valueOf(27));
		userProfile.setFirstName("Shekhar");
		userProfile.setLastName("Gulati");
		request.setUserProfile(userProfile);

		UserProfileCreateResponse response = (UserProfileCreateResponse) webServiceTemplate
				.marshalSendAndReceive(request);
		System.out.println(response.getMessage());
	}
}
