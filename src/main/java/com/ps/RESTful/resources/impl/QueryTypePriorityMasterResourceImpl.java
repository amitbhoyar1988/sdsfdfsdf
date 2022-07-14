package com.ps.RESTful.resources.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.jboss.logging.Logger;
import com.ps.RESTful.dto.mapper.QueryTypePriorityMasterDTOMapper;
import com.ps.RESTful.dto.request.QueryTypeMasterRequestDTO;
import com.ps.RESTful.dto.request.QueryTypePriorityMasterRequestDTO;
import com.ps.RESTful.dto.request.QuestionAnswerMasterRequestDTO;
import com.ps.RESTful.dto.response.QueryTypePriorityMasterResponseDTO;
import com.ps.RESTful.dto.response.QuestionAnswerMasterResponseDTO;
import com.ps.RESTful.resources.QueryTypePriorityMasterResource;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.RESTful.response.enums.StatusEnum;
import com.ps.RESTful.response.enums.SuccessCode;
import com.ps.entities.master.QueryTypePriorityMaster;
import com.ps.entities.master.QuestionAnswerMaster;
import com.ps.services.QueryTypePriorityMasterService;
import com.ps.util.MethodValidationUtils;


@RestController
@RequestMapping(path =QueryTypePriorityMasterResource.RESOURCE_PATH)
public class QueryTypePriorityMasterResourceImpl implements QueryTypePriorityMasterResource {

	Logger logger = Logger.getLogger(QueryTypePriorityMasterResourceImpl.class);
	
	@Autowired
	QueryTypePriorityMasterDTOMapper queryTypePriorityMasterDTOMapper;
	
	@Autowired
	QueryTypePriorityMasterService queryTypePriorityMasterService;
	
	//get All List
	@Override
	public ResponseEntity<Response>getAll() {
		
		if(logger.isDebugEnabled())
			logger.debug("In Get All Query Type Priority Master List resource");
		
		//calling getAll method of company master Service
		if (logger.isDebugEnabled())
			logger.debug("calling Query Type Priority Master List Service getAll");
		
		List<QueryTypePriorityMaster> entityList = queryTypePriorityMasterService.getAll();
	
		if(entityList !=null && !entityList.isEmpty()) {
			List<QueryTypePriorityMasterResponseDTO>listResponseDTO = queryTypePriorityMasterDTOMapper.entityListToDtoList(entityList);
			if(logger.isDebugEnabled())
				logger.debug("Returning response From Query Type Priority Master recourse for all Query Type Priority Master");
			
			return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder().results(listResponseDTO)
					.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Retrieved results Successfully")
					.build());
		}else {
			logger.error("Query type Priority Master list is empty");
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(),
							SuccessCode.NO_CONTENT.getCode(), "Query type Priority  list is empty")
							.results(entityList).build());
			
		}
	
	}
	
	@Override
	public ResponseEntity<Response> add(QueryTypePriorityMasterRequestDTO requestDTO) {
		
		if (logger.isDebugEnabled())
			logger.debug("In add Query Type Priority Master add resource");						
		
		// Check-Object list-is-not-null
	    MethodValidationUtils.checkIfObjectIsNotNULL(requestDTO, QueryTypePriorityMaster.class.getSimpleName());
		
		//mapping DTO to entity 
		if (logger.isDebugEnabled())
			logger.debug("mapping dtoList to entity list");
		QueryTypePriorityMaster QAInfo = queryTypePriorityMasterDTOMapper
				.dtoToEntity(requestDTO);

		//calling Question Answer Master service add method
		if (logger.isDebugEnabled())
			logger.debug("calling Query Type Priority Master service add method");
		QueryTypePriorityMaster result = queryTypePriorityMasterService.add(QAInfo);
									
		// converting entity to DTO
		QueryTypePriorityMasterResponseDTO responseDTO = queryTypePriorityMasterDTOMapper.entityToDto(result);
			
		if (logger.isDebugEnabled())
			logger.debug("Query Type Priority Master Details saved, Returning response from Query Type Priority Master resource for " + QAInfo);
					
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
			.status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(), "Query Type Priority Master Details saved Successfully")
			.result(responseDTO).build());
		
	}
	
	
}
