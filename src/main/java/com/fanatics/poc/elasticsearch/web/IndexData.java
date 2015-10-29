package com.fanatics.poc.elasticsearch.web;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilteredQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
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
	
	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes={"application/json"})
	public String searchDocument(@RequestBody Employee employee) throws JsonProcessingException {
	    
		QueryBuilder bool_qb = null;
		BoolQueryBuilder builder = QueryBuilders.boolQuery();
		if(!StringUtils.isBlank(employee.getAbout())) {
			builder.must(QueryBuilders.matchQuery("about", "picnic basket"));
		}
		if(employee.getInterests() != null) {
			for(String s : employee.getInterests()) {
				builder.must(QueryBuilders.matchQuery("interests", s));
			}
		}
		if(!StringUtils.isBlank(employee.getNonInterest())) {
			builder.mustNot(QueryBuilders.matchQuery("interests", employee.getNonInterest()));
		}
		if(!StringUtils.isBlank(employee.getName().getFirst_name())) {
			builder.should(QueryBuilders.matchQuery("first_name", employee.getName().getFirst_name()));
		}		        

		//bool_qb = builder;
	    FilterBuilder filter_query = new org.elasticsearch.index.query.RangeFilterBuilder("age")
	    			  .gt(29L).lte(employee.getAge());
	    FilteredQueryBuilder filter_qb = new FilteredQueryBuilder(builder, filter_query);
	    
	    //bool_qb = filter_qb;

		Client client = ESClient.getClient();
		SearchResponse response = client.prepareSearch("fanatics").setQuery(filter_qb).execute().actionGet();

		return response.toString();
	}
}
