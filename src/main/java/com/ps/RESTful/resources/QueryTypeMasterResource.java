package com.ps.RESTful.resources;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ps.RESTful.dto.request.QueryTypeMasterRequestDTO;
import com.ps.RESTful.dto.request.QueryTypeMasterRequestDTONew;
import com.ps.RESTful.dto.request.QueryTypeQueAnsMappingMasterRequestDTO;
import com.ps.RESTful.dto.request.SubQueryTypeMasterRequestDTO;
import com.ps.RESTful.dto.request.SubQueryTypeQueAnsMappingMasterRequestDTO;
import com.ps.RESTful.resources.response.handler.Response;
public interface QueryTypeMasterResource {

public static final String RESOURCE_PATH = "/QueryMaster";

public static final String SUBQUERY_PATH = "/SubQuery";

public static final String ADDINGALL_PATH ="/AddingAll";

public static final String SUBADDING_PATH = "/SubAdding";

public static final String UPDATE_PATH ="/UpdateData";

public static final String Add_PATH ="/AddnewData";

public static final String GET_PATH="/getNew/{queryTypeMasterId}";
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getAll();
	
	/*
	 * @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces =
	 * MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Response>
	 * add(@RequestBody QueryTypeMasterRequestDTO requestDTO);
	 */
	@PostMapping(path=SUBQUERY_PATH,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> addSubQuery(@RequestBody SubQueryTypeMasterRequestDTO requestDTO);
	
	@GetMapping(path = SUBQUERY_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getAllSubQuery();

	@PostMapping(path=ADDINGALL_PATH,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> addAll(@RequestBody List<QueryTypeQueAnsMappingMasterRequestDTO> requestDTOList);

	@PostMapping(path=SUBADDING_PATH,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> SubaddAll(@RequestBody List<SubQueryTypeQueAnsMappingMasterRequestDTO> requestDTOList);

// 6/4/20 update code started	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateList(@RequestBody QueryTypeMasterRequestDTO requestDTO);

	//08/04/2021 started add new Code
	@PostMapping(path=Add_PATH,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> AddNew(@RequestBody QueryTypeMasterRequestDTO requestDTO);

	//12/04/21
	@GetMapping(path=GET_PATH,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> getAllByQueryTypeMasterId(@PathVariable("queryTypeMasterId") int queryTypeMasterId);
	
}