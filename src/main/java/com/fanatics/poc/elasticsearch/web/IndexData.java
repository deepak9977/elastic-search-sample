package com.fanatics.poc.elasticsearch.web;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/es")
public class IndexData {
	
	/*private static final String EMPLOYEE_INDEX_DOC = "{\n" + 
			"	    \"name\" : {\n" + 
			"	    \"first_name\" : \"scooby\",\n" + 
			"	    \"last_name\" :  \"doo\"\n" + 
			"	    },\n" + 
			"	    \"age\" :        31,\n" + 
			"	    \"about\" :      \"I love to eat out of picnic baskets\",\n" + 
			"	    \"interests\": [ \"sports\", \"music\", \"sleeping\" ],\n" + 
			"	    \"publish_date\" : \"2015-10-23T16:26:04-0400\"\n" + 
			"	}";*/

	@RequestMapping(value = "/index", method = RequestMethod.POST, consumes={"application/json"})
	public String indexDocument(@RequestBody String doc) throws JsonProcessingException {
		Client client = ESClient.getClient();
		//ObjectMapper mapper = new ObjectMapper();
		//byte[] arr = mapper.writeValueAsBytes(doc);
		IndexResponse response = client.prepareIndex("fanatics","employee").setSource(doc).get();
		String id = response.getId();
		GetResponse getResponse = client.prepareGet("fanatics", "employee", id).get();
		//getResponse.getSourceAsString()
		return getResponse.getSourceAsString();
	}
}
