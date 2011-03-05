package com.shekhar.usermanagement.profileService.ws.client;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ws.client.core.WebServiceTemplate;

public class Client {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"com/shekhar/usermanagement/profileService/ws/client/profile-service-client.xml");
		WebServiceTemplate template = applicationContext
				.getBean(WebServiceTemplate.class);
		ProfileServiceClient client = new ProfileServiceClient();
		client.webServiceTemplate = template;
		client.invokeProfileServiceAndGetASuccessResponse();

	}

}
