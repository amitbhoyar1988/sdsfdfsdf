package com.ps.RESTful.resources.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.RESTful.dto.mapper.StandardKeywordMasterDTOMapper;
import com.ps.RESTful.dto.response.StandardKeywordMasterResponseDTO;
import com.ps.RESTful.resources.StandardKeywordMasterResource;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.RESTful.response.enums.StatusEnum;
import com.ps.RESTful.response.enums.SuccessCode;
import com.ps.entities.tenant.StandardKeywordMaster;
import com.ps.services.StandardKeywordMasterService;

@RestController
@RequestMapping(path = StandardKeywordMasterResource.RESOURCE_PATH)
public class StandardKeywordMasterResourceImpl implements StandardKeywordMasterResource{

Logger logger = Logger.getLogger(QuestionAnswerMasterResourceImpl.class);
	
	@Autowired
	StandardKeywordMasterDTOMapper standardKeywordMasterDTOMapper;
		
	@Autowired
	StandardKeywordMasterService standardKeywordMasterService;
	
	//get all company list
			@Override
			public ResponseEntity<Response> getAll() {
				
				if (logger.isDebugEnabled())
					logger.debug("In Get All Standard Keyword Master list resource ");
				
				//calling getAll method of company master Service
				if (logger.isDebugEnabled())
					logger.debug("calling Standard Keyword Master Service getAll");
				
				List<StandardKeywordMaster> entityList = standardKeywordMasterService.getAll();
				
				if (entityList != null && !entityList.isEmpty()) {
					//converting entityList to DtoList
					List<StandardKeywordMasterResponseDTO> listResponseDto = standardKeywordMasterDTOMapper.entityListToDtoList(entityList);
					
					if (logger.isDebugEnabled())
						logger.debug("Returning response from Standard Keyword Master resource for all Question Answer Master ");

					return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder().results(listResponseDto)
							.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Retrieved results Successfully")
							.build());
				}else {
					logger.error("Standard Keyword Master list is empty");
					return ResponseEntity.status(HttpStatus.OK)
							.body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(),
									SuccessCode.NO_CONTENT.getCode(), "Standard Keyword Master list is empty")
									.results(entityList).build());
				}			
			}
	
}
