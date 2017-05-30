package com.spring.student.util;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.spring.student.model.Marks;

@Component
public class RestConnector {
	@Autowired
	private LoadBalancerClient client;
	
    public Marks getMarks(int id) {
        //String baseUrl = "http://localhost:8084";
    	ServiceInstance instance = client.choose("student");
    	String baseUrl = instance.getUri().toString();
    	System.out.println(" base url of results-service : "+ baseUrl);
    	String resultsUrl = baseUrl + "/results/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Integer> marks = (Map) restTemplate.getForObject(resultsUrl, Object.class);
        return new Marks(marks.get("language"), marks.get("math"), marks.get("art"));
    }
}
