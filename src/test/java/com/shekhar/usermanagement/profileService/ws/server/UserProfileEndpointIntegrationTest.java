package com.shekhar.usermanagement.profileService.ws.server;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.ws.test.server.RequestCreators;
import org.springframework.ws.test.server.ResponseMatchers;
import org.springframework.xml.transform.StringSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("spring-ws-servlet-test.xml")
public class UserProfileEndpointIntegrationTest {

	@Autowired
	private ApplicationContext applicationContext;

	private MockWebServiceClient mockClient;

	@Before
	public void createClient() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}

	@Test
	public void testCreate() {
		String request = "<ns2:UserProfileCreateRequest xmlns:ns2='http://shekhar.com/usermanagement/schemas'> 	<ns2:UserProfile>		<ns2:firstName>Shekhar</ns2:firstName>		<ns2:lastName>Gulati</ns2:lastName>		<ns2:age>27</ns2:age>	</ns2:UserProfile></ns2:UserProfileCreateRequest>";
		String response = "<ns2:UserProfileCreateResponse xmlns:ns2='http://shekhar.com/usermanagement/schemas'>  <ns2:message>user created successfully</ns2:message>  </ns2:UserProfileCreateResponse>";

		Assert.assertNotNull(applicationContext);
		mockClient
				.sendRequest(
						RequestCreators.withPayload(new StringSource(request)))
				.andExpect(ResponseMatchers.payload(new StringSource(response)));
	}

}
