package com.ps.RESTful.resources;

import java.util.List;

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

import com.ps.RESTful.dto.request.QuestionAnswerMasterRequestDTO;
import com.ps.RESTful.resources.response.handler.Response;

public interface QuestionAnswerMasterResource {

	public static final String RESOURCE_PATH = "/QuestionAnswer";
	public static final String RESOURCE_DELETE_PATH = "/{id}";
	public static final String RESOURCE_DELETE_BY_CODE = "/deleteByCode/{code}";

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getAll();

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> add(@RequestBody QuestionAnswerMasterRequestDTO requestDTO);
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> update(@RequestBody QuestionAnswerMasterRequestDTO requestDTO);

	// deleting specific language question
	@DeleteMapping(path = RESOURCE_DELETE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> delete(@PathVariable("id") int id);

	// deleting specific language question
	@DeleteMapping(path = RESOURCE_DELETE_BY_CODE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteByCode(@PathVariable("code") int code);

}
