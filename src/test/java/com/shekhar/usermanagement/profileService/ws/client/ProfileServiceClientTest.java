package com.shekhar.usermanagement.profileService.ws.client;

import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.test.client.MockWebServiceServer;
import org.springframework.ws.test.client.RequestMatchers;
import org.springframework.ws.test.client.ResponseCreators;
import org.springframework.xml.transform.StringSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("profile-service-client.xml")
public class ProfileServiceClientTest {

	private String request = "<ns2:UserProfileCreateRequest xmlns:ns2='http://shekhar.com/usermanagement/schemas'> 	<ns2:UserProfile>		<ns2:firstName>Shekhar</ns2:firstName>		<ns2:lastName>Gulati</ns2:lastName>		<ns2:age>27</ns2:age>	</ns2:UserProfile></ns2:UserProfileCreateRequest>";
	private String response = "<ns2:UserProfileCreateResponse xmlns:ns2='http://shekhar.com/usermanagement/schemas'>  <ns2:message>user created successfully</ns2:message>  </ns2:UserProfileCreateResponse>";

	@Autowired
	private WebServiceTemplate webServiceTemplate;
	private MockWebServiceServer server;
	@Autowired
	private ProfileServiceClient client;

	@Before
	public void setup() {
		server = MockWebServiceServer.createServer(webServiceTemplate);
	}

	@Test
	public void testInvokeProfileServiceAndGetASuccessResponse() {
		server.expect(RequestMatchers.payload(new StringSource(request)))
				.andRespond(
						ResponseCreators
								.withPayload(new StringSource(response)));
		Assert.assertEquals("user created successfully",
				client.invokeProfileServiceAndGetASuccessResponse());
		server.verify();
	}

	@Test
	public void shouldInvokeProfileServiceAndGetASuccessResponse()
			throws Exception {
		Resource schema = new FileSystemResource(
				"src/main/webapp/WEB-INF/userManagement.xsd");
		server.expect(RequestMatchers.payload(new StringSource(request)))
				.andExpect(RequestMatchers.validPayload(schema))
				.andRespond(
						ResponseCreators
								.withPayload(new StringSource(response)));
		Assert.assertEquals("user created successfully",
				client.invokeProfileServiceAndGetASuccessResponse());
		server.verify();
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowRuntimeException() {
		server.expect(RequestMatchers.payload(new StringSource(request)))
				.andRespond(
						ResponseCreators.withException(new RuntimeException()));
		client.invokeProfileServiceAndGetASuccessResponse();
		server.verify();
	}

	@Test(expected = SoapFaultClientException.class)
	public void shouldThrowSoapFault() {
		server.expect(RequestMatchers.payload(new StringSource(request)))
				.andRespond(
						ResponseCreators.withServerOrReceiverFault(
								"Soap Fault Exception", Locale.US));
		client.invokeProfileServiceAndGetASuccessResponse();
		server.verify();
	}
}
