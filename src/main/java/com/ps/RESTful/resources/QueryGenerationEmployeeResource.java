package com.ps.RESTful.resources;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ps.RESTful.dto.request.QueryGenerationEmployeeRequestDTO;
import com.ps.RESTful.resources.response.handler.Response;

public interface QueryGenerationEmployeeResource {

	public static final String RESOURCE_PATH = "/QueryGeneration";
	public static final String RESOURCE_ID_PATH = "/{id}";	
	public static final String RESOURCE_UPDATE_PATH = "/update";	

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getAll();
	
	@GetMapping(path = RESOURCE_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getById(@PathVariable("id") int id);

	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> add(@RequestParam String queryGenerationEmployeeData,
			@RequestParam MultipartFile[] queryDocs);
	
	@PutMapping(path = RESOURCE_UPDATE_PATH, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateNew(@RequestParam String queryGenerationEmployeeData,
			@RequestParam MultipartFile[] queryDocs);
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> update(@RequestBody QueryGenerationEmployeeRequestDTO requestDTO);
	
	@DeleteMapping(path = RESOURCE_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> delete(@PathVariable("id") int id);

}
