package com.ps.RESTful.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.ps.RESTful.resources.response.handler.Response;

public interface StandardKeywordMasterResource {
	
	public static final String RESOURCE_PATH = "/StandardKeyword";

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getAll();

}
