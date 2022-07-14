package com.ps.RESTful.resources.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.mapping.Array;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ps.RESTful.dto.mapper.QuestionAnswerMasterDTOMapper;
import com.ps.RESTful.dto.request.QuestionAnswerMasterRequestDTO;
import com.ps.RESTful.dto.response.QuestionAnswerMasterResponseDTO;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.resources.QuestionAnswerMasterResource;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.RESTful.response.enums.StatusEnum;
import com.ps.RESTful.response.enums.SuccessCode;
import com.ps.entities.master.QuestionAnswerMaster;
import com.ps.services.QuestionAnswerMasterService;
import com.ps.util.MethodValidationUtils;
import com.ps.util.ObjectMapperUtils;
import com.ps.util.StringUtils;

@RestController
@RequestMapping(path = QuestionAnswerMasterResource.RESOURCE_PATH)
public class QuestionAnswerMasterResourceImpl implements QuestionAnswerMasterResource{

Logger logger = Logger.getLogger(QuestionAnswerMasterResourceImpl.class);
	
	@Autowired
	QuestionAnswerMasterDTOMapper questionAnswerMasterDTOMapper;
		
	@Autowired
	QuestionAnswerMasterService questionAnswerMasterService;
	
	//get all company list
		@Override
		public ResponseEntity<Response> getAll() {
			
			if (logger.isDebugEnabled())
				logger.debug("In Get All Question Answer Master list resource ");
			
			//calling getAll method of company master Service
			if (logger.isDebugEnabled())
				logger.debug("calling Question Answer Master Service getAll");
			
			List<QuestionAnswerMaster> entityList = questionAnswerMasterService.getAll();
			
			if (entityList != null && !entityList.isEmpty()) {
				//converting entityList to DtoList
				List<QuestionAnswerMasterResponseDTO> listResponseDto = questionAnswerMasterDTOMapper.entityListToDtoList(entityList);
				
				if (logger.isDebugEnabled())
					logger.debug("Returning response from Question Answer Master resource for all Question Answer Master ");

				return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder().results(listResponseDto)
						.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Retrieved results Successfully")
						.build());
			}else {
				logger.error("Question Answer Master list is empty");
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(),
								SuccessCode.NO_CONTENT.getCode(), "Question Answer Master list is empty")
								.results(entityList).build());
			}			
		}
		
		@Override
		public ResponseEntity<Response> add(QuestionAnswerMasterRequestDTO requestDTO) {
			
			if (logger.isDebugEnabled())
				logger.debug("In add Question Answer Master add resource");						
			
			// Check-Object list-is-not-null
		    MethodValidationUtils.checkIfObjectIsNotNULL(requestDTO, QuestionAnswerMaster.class.getSimpleName());
			
			//mapping DTO to entity 
			if (logger.isDebugEnabled())
				logger.debug("mapping dtoList to entity list");
			QuestionAnswerMaster QAInfo = questionAnswerMasterDTOMapper
					.dtoToEntity(requestDTO);

			//calling Question Answer Master service add method
			if (logger.isDebugEnabled())
				logger.debug("calling Question Answer Master service add method");
			List<QuestionAnswerMaster>  resultList = new ArrayList<>();
			resultList = questionAnswerMasterService.add(QAInfo);
										
			// converting entity to DTO
			List<QuestionAnswerMasterResponseDTO> responseDTO = questionAnswerMasterDTOMapper.entityListToDtoList(resultList);
				
			if (logger.isDebugEnabled())
				logger.debug("Question Answer Master Details saved, Returning response from Question Answer Master resource for " + QAInfo);
						
			return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
				.status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(), "Question Answer Master Details saved Successfully")
				.result(responseDTO).build());
			
		}
		
		@Override
		public ResponseEntity<Response> update(QuestionAnswerMasterRequestDTO requestDTO) {
			
			if (logger.isDebugEnabled())
			      logger.debug("Modifying QuestionAnswerMaster for Id: " + requestDTO.getQueAnsMasterId());
			
			// Check_Id_is_Not_Null/Zero
		    MethodValidationUtils.checkIfIdIsZero(requestDTO.getQueAnsMasterId(), "QuestionAnswerMasterId");
		    
			if (logger.isDebugEnabled())
				logger.debug("Mapping dto to entity");
			QuestionAnswerMaster master = questionAnswerMasterDTOMapper.dtoToEntity(requestDTO);
			
			if (logger.isDebugEnabled())
				logger.debug("calling service mtd in QuestionAnswerMaster resource: " + master);
			
			List<QuestionAnswerMaster>  resultList = new ArrayList<>();
			// implementing service method for update 
			resultList = questionAnswerMasterService.update(master);
			
			// converting entity to DTO  to returning response
			List<QuestionAnswerMasterResponseDTO> responseDTO = questionAnswerMasterDTOMapper.entityListToDtoList(resultList);
			
			if (logger.isDebugEnabled())
				logger.debug("Updated QuestionAnswerMaster Successfully");
			return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
					.status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(), "Updated QuestionAnswerMaster Successfully")
					.result(responseDTO).build());
		}
		
		@Override
		public ResponseEntity<Response> delete(int id) {
			
			if (logger.isDebugEnabled())
				logger.debug("In delete QuestionAnswerMaster resource, Id " + id);
			
			// Check_Id_Is_Not_Null/Zero
		    MethodValidationUtils.checkIfIdIsZero(id, "QuestionAnswerMaster ID");

		    logger.debug("Performing Soft Delete by updating Active Status to 0 / false");

		    //delete method from service
		    questionAnswerMasterService.delete(id);		
			
			if (logger.isDebugEnabled())
				logger.debug("QuestionAnswerMaster deleted Returning response from QuestionAnswerMaster resource for " + id);
			
			return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder()
					.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Deleted QuestionAnswerMaster Successfully").build());
		}
		
		@Override
		public ResponseEntity<Response> deleteByCode(int code) {
			
			if (logger.isDebugEnabled())
				logger.debug("In delete QuestionAnswerMaster resource, Id " + code);
			
			// Check_Id_Is_Not_Null/Zero
		    MethodValidationUtils.checkIfIdIsZero(code, "QuestionAnswerMaster Code");

		    logger.debug("Performing Soft Delete by updating Active Status to 0 / false");

		    //delete method from service
		    questionAnswerMasterService.deleteByCode(code);		
			
			if (logger.isDebugEnabled())
				logger.debug("QuestionAnswerMaster deleted Returning response from QuestionAnswerMaster resource for " + code);
			
			return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder()
					.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Deleted QuestionAnswerMaster Successfully").build());
		}
	
}
