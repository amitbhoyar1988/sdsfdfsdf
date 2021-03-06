package com.ps.RESTful.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ps.RESTful.resources.response.handler.Response;

public interface AbstractResource {
	String DEFAULT_PATH = "";
	String DEFAULT_DELETE_PATH = "/{deptCode}";
	String DEFAULT_GET_PATH = "/{deptCode}";

	@GetMapping(path = DEFAULT_GET_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> get(@PathVariable("deptCode") String resourceId);

	@DeleteMapping(path = DEFAULT_DELETE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> delete(@PathVariable("deptCode") String resourceId);
}// interface Close
