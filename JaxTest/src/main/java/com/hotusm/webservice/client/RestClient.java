package com.hotusm.webservice.client;

import org.springframework.web.client.RestTemplate;

/***
 * 
 * @author Hotusm
 *
 */
public class RestClient {
	
	private RestTemplate restTemplate;
	
	
	public String test(){
		String message=restTemplate.postForObject("http://localhost:8080/JaxTest/jaxrs?_wadl", null, String.class);
		System.out.println("message:"+message);
		return null;
	}

	public RestTemplate getRestTemplate() {
		return restTemplate;
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	
}
