package com.ps.RESTful.resources.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.RESTful.dto.mapper.QueryTypeMasterDTOMapper;
import com.ps.RESTful.dto.mapper.QueryTypePriorityMasterDTOMapper;
import com.ps.RESTful.dto.mapper.QueryTypeQueAnsMappingMasterDTOMapper;
import com.ps.RESTful.dto.mapper.SubQueryTypeMasterDTOMapper;
import com.ps.RESTful.dto.mapper.SubQueryTypeQueAnsMappingMasterDTOMapper;
import com.ps.RESTful.dto.request.QueryTypeMasterRequestDTO;
import com.ps.RESTful.dto.request.QueryTypeMasterRequestDTONew;
import com.ps.RESTful.dto.request.QueryTypeQueAnsMappingMasterRequestDTO;
import com.ps.RESTful.dto.request.SubQueryTypeMasterRequestDTO;
import com.ps.RESTful.dto.request.SubQueryTypeQueAnsMappingMasterRequestDTO;
import com.ps.RESTful.dto.response.QueryTypeMasterResponseDTO;
import com.ps.RESTful.dto.response.QueryTypeQueAnsMappingMasterResponseDTO;
import com.ps.RESTful.dto.response.SubQueryTypeMasterResponseDTO;
import com.ps.RESTful.dto.response.SubQueryTypeQueAnsMappingMasterResponseDTO;
import com.ps.RESTful.resources.QueryTypeMasterResource;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.RESTful.response.enums.StatusEnum;
import com.ps.RESTful.response.enums.SuccessCode;
import com.ps.entities.master.QueryTypeMaster;
import com.ps.entities.master.QueryTypePriorityMaster;
import com.ps.entities.master.QueryTypeQueAnsMappingMaster;
import com.ps.entities.master.SubQueryTypeMaster;
import com.ps.entities.master.SubQueryTypeQueAnsMappingMaster;
import com.ps.services.QueryTypeMasterService;
import com.ps.services.QueryTypePriorityMasterService;
import com.ps.services.QueryTypeQueAnsMappingMasterService;
import com.ps.services.SubQueryTypeMasterService;
import com.ps.services.SubQueryTypeQueAnsMappingMasterService;
import com.ps.util.MethodValidationUtils;


@RestController
@RequestMapping(path =QueryTypeMasterResource.RESOURCE_PATH)
public class QueryTypeMasterResourceImpl implements QueryTypeMasterResource{

Logger logger = Logger.getLogger(QueryTypeMasterResourceImpl.class);
	
	@Autowired
	QueryTypeMasterDTOMapper queryTypeMasterDTOMapper;
	
	@Autowired
	QueryTypeMasterService queryTypeMasterService;
	
	@Autowired
	SubQueryTypeMasterService subQueryTypeMasterService;
	
	@Autowired
	QueryTypePriorityMasterService queryTypePriorityMasterService;
	
	@Autowired
	SubQueryTypeMasterDTOMapper subQueryTypeMasterDTOMapper;
	
	@Autowired
	QueryTypeQueAnsMappingMasterDTOMapper queryTypeQueAnsMappingMasterDTOMapper;
	
	@Autowired
	QueryTypePriorityMasterDTOMapper queryTypePriorityMasterDTOMapper;
	
	@Autowired
	QueryTypeQueAnsMappingMasterService queryTypeQueAnsMappingMasterService;
	
	@Autowired
	SubQueryTypeQueAnsMappingMasterDTOMapper subQueryTypeQueAnsMappingMasterDTOMapper;
	
	@Autowired
	SubQueryTypeQueAnsMappingMasterService subQueryTypeQueAnsMappingMasterService;
	
	
	//get All List
	@Override
	public ResponseEntity<Response>getAll() {
		
		if(logger.isDebugEnabled())
			logger.debug("In Get All Query Type  Master List resource");
		
		//calling getAll method of company master Service
		if (logger.isDebugEnabled())
			logger.debug("calling Query Type  Master List Service getAll");
		
		List<QueryTypeMaster> entityList = queryTypeMasterService.getAll();
	
		if(entityList !=null && !entityList.isEmpty()) {
			List<QueryTypeMasterResponseDTO>listResponseDTO = queryTypeMasterDTOMapper.entityListToDtoList(entityList);
			if(logger.isDebugEnabled())
				logger.debug("Returning response From Query Type  Master recourse for all Query Type Priority Master");
			
			return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder().results(listResponseDTO)
					.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Retrieved results Successfully")
					.build());
		}else {
			logger.error("Query type  Master list is empty");
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(),
							SuccessCode.NO_CONTENT.getCode(), "Query Type Master list is empty")
							.results(entityList).build());
			
		}
	
	}
	
	/*
	 * @Override public ResponseEntity<Response> add(QueryTypeMasterRequestDTO
	 * requestDTO) {
	 * 
	 * if (logger.isDebugEnabled())
	 * logger.debug("In add Query Type Master add resource");
	 * 
	 * // Check-Object list-is-not-null
	 * MethodValidationUtils.checkIfObjectIsNotNULL(requestDTO,
	 * QueryTypeMaster.class.getSimpleName());
	 * 
	 * //mapping DTO to entity if (logger.isDebugEnabled())
	 * logger.debug("mapping dtoList to entity list"); QueryTypeMaster QAInfo =
	 * queryTypeMasterDTOMapper .dtoToEntity(requestDTO);
	 * 
	 * 
	 * //calling Question Answer Master service add method if
	 * (logger.isDebugEnabled())
	 * logger.debug("calling Query Type  Master service add method");
	 * QueryTypeMaster result = queryTypeMasterService.add(QAInfo);
	 * 
	 * if(result.getQueryTypeMasterId() != 0) {
	 * 
	 * 
	 * // to check whether the list is empty or not when Sub Query is selected as No
	 * if (!requestDTO.isSubQuery()) { // Check-Object list-is-not-null
	 * MethodValidationUtils.checkIfObjectListIsNotEmpty(requestDTO.
	 * getListQueryAnsMappingReqDTO(), QueryTypeMaster.class.getSimpleName());
	 * 
	 * //mapping Question Answer DTO to entity if (logger.isDebugEnabled())
	 * logger.debug("mapping Question Answer DTO to entity");
	 * List<QueryTypeQueAnsMappingMaster> listQueryAnsMapping =
	 * queryTypeQueAnsMappingMasterDTOMapper
	 * .dtoListToEntityList(requestDTO.getListQueryAnsMappingReqDTO());
	 * 
	 * if (result.getQueryTypeMasterId() != 0) {
	 * 
	 * List<QueryTypeQueAnsMappingMaster> listQAMapping = new ArrayList<>(); for
	 * (QueryTypeQueAnsMappingMaster master :listQueryAnsMapping) {
	 * master.setQueryTypeMasterId(result.getQueryTypeMasterId());
	 * listQAMapping.add(master); }
	 * 
	 * listQueryAnsMapping =
	 * queryTypeQueAnsMappingMasterService.addAll(listQueryAnsMapping); }
	 * 
	 * }//sub Add methd call later ,add.subquerytypeID ,maped with for loop using
	 * sub queryQue Ans then Save All Call else {
	 * 
	 * // Check-Object list-is-not-null
	 * MethodValidationUtils.checkIfObjectIsNotNULL(requestDTO.getSubQueryRequestDTO
	 * (), SubQueryTypeMaster.class.getSimpleName());
	 * 
	 * //mapping DTO to entity if (logger.isDebugEnabled())
	 * logger.debug("mapping Sub Query dtoList to entity list"); SubQueryTypeMaster
	 * subQueryDetails = subQueryTypeMasterDTOMapper
	 * .dtoToEntity(requestDTO.getSubQueryRequestDTO());
	 * subQueryDetails.setQueryTypeMasterId(result.getQueryTypeMasterId());
	 * 
	 * //calling Question Answer Master service add method if
	 * (logger.isDebugEnabled())
	 * logger.debug("calling Query Type  Master service add method");
	 * SubQueryTypeMaster resultSubQuery =
	 * subQueryTypeMasterService.addSubQuery(subQueryDetails);
	 * 
	 * if (resultSubQuery.getSubQueTypeMasterId() != 0) {
	 * MethodValidationUtils.checkIfObjectListIsNotEmpty(requestDTO.
	 * getListSubQueryAnsMappingReqDTO(), SubQueryTypeMaster.class.getSimpleName());
	 * 
	 * if (logger.isDebugEnabled())
	 * logger.debug("mapping Question Answer DTO to entity");
	 * List<SubQueryTypeQueAnsMappingMaster> listSubQueryAnsMapping =
	 * subQueryTypeQueAnsMappingMasterDTOMapper
	 * .dtoListToEntityList(requestDTO.getListSubQueryAnsMappingReqDTO());
	 * 
	 * 
	 * 
	 * List<SubQueryTypeQueAnsMappingMaster> listSubQAMapping = new ArrayList<>();
	 * for (SubQueryTypeQueAnsMappingMaster master :listSubQueryAnsMapping) {
	 * master.setSubQueryTypeMasterId(resultSubQuery.getSubQueTypeMasterId());
	 * listSubQAMapping.add(master); }
	 * 
	 * listSubQueryAnsMapping =
	 * subQueryTypeQueAnsMappingMasterService.addAll(listSubQueryAnsMapping);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * //validation for priority if (requestDTO.isPriorityRequired()) { //
	 * Check-Object list-is-not-null
	 * MethodValidationUtils.checkIfObjectListIsNotEmpty(requestDTO.
	 * getListQueryPriorityRequestDTO(), QueryTypeMaster.class.getSimpleName());
	 * 
	 * //mapping priority DTO to entity if (logger.isDebugEnabled())
	 * logger.debug("mapping priority DTO to entity"); List<QueryTypePriorityMaster>
	 * listQueryPriority = queryTypePriorityMasterDTOMapper
	 * .dtoListToEntityList(requestDTO.getListQueryPriorityRequestDTO());
	 * 
	 * 
	 * if (result.getQueryTypeMasterId() != 0) { List<QueryTypePriorityMaster>
	 * listPriority = new ArrayList<>(); for (QueryTypePriorityMaster master
	 * :listQueryPriority) {
	 * master.setQueryTypeMasterId(result.getQueryTypeMasterId());
	 * listPriority.add(master); } listQueryPriority =
	 * queryTypePriorityMasterService.addAll(listPriority); }
	 * 
	 * }
	 * 
	 * // converting entity to DTO QueryTypeMasterResponseDTO responseDTO =
	 * queryTypeMasterDTOMapper.entityToDto(result);
	 * 
	 * if (logger.isDebugEnabled()) logger.
	 * debug("Query Type Master Details saved, Returning response from Query Type Master resource for "
	 * + QAInfo);
	 * 
	 * return
	 * ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
	 * .status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(),
	 * "Query Type Master Details saved Successfully")
	 * .result(responseDTO).build()); } return
	 * ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
	 * .status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(),
	 * "SubQuery Type Master Details saved Successfully")
	 * .result(requestDTO).build());
	 * 
	 * }
	 * 
	 */
	@Override
	public ResponseEntity<Response> addSubQuery(SubQueryTypeMasterRequestDTO requestDTO) {
		
		if (logger.isDebugEnabled())
			logger.debug("In add Sub Query Type Master add resource");						
		
		// Check-Object list-is-not-null
	    MethodValidationUtils.checkIfObjectIsNotNULL(requestDTO, QueryTypeMaster.class.getSimpleName());
		
		//mapping DTO to entity 
		if (logger.isDebugEnabled())
			logger.debug("mapping dtoList to entity list");
		SubQueryTypeMaster QAInfo = subQueryTypeMasterDTOMapper
				.dtoToEntity(requestDTO);

		//calling Question Answer Master service add method
				if (logger.isDebugEnabled())
					logger.debug("calling Sub Query Type  Master service add method");
				SubQueryTypeMaster result = subQueryTypeMasterService.addSubQuery(QAInfo);
											
				// converting entity to DTO
				SubQueryTypeMasterResponseDTO responseDTO = subQueryTypeMasterDTOMapper.entityToDto(result);
					
				if (logger.isDebugEnabled())
					logger.debug("Query Type Master Details saved, Returning response from Query Type Master resource for " + QAInfo);
									
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
			.status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(), "Query Type Master Details saved Successfully")
			.result(responseDTO).build());
	}

	@Override
	
		public ResponseEntity<Response>getAllSubQuery() {
			
			if(logger.isDebugEnabled())
				logger.debug("In Get All Query Type  Master List resource");
			
			//calling getAll method of company master Service
			if (logger.isDebugEnabled())
				logger.debug("calling Query Type  Master List Service getAll");
			
			List<SubQueryTypeMaster> entityList = subQueryTypeMasterService.getAllSubQuery();
		
			if(entityList !=null && !entityList.isEmpty()) {
				List<SubQueryTypeMasterResponseDTO>listResponseDTO = subQueryTypeMasterDTOMapper.entityListToDtoList(entityList);
				if(logger.isDebugEnabled())
					logger.debug("Returning response From Query Type  Master recourse for all Query Type Priority Master");
				
				return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder().results(listResponseDTO)
						.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Retrieved results Successfully")
						.build());
			}else {
				logger.error("Query type  Master list is empty");
				return ResponseEntity.status(HttpStatus.OK)
						.body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(),
								SuccessCode.NO_CONTENT.getCode(), "Query Type Master list is empty")
								.results(entityList).build());
				
			}
		
		}
	
	@Override
	public ResponseEntity<Response> addAll(List<QueryTypeQueAnsMappingMasterRequestDTO> listRequestDTO) {
		
		List<QueryTypeQueAnsMappingMaster> mappingMaster = new ArrayList<>();
		mappingMaster = queryTypeQueAnsMappingMasterDTOMapper.dtoListToEntityList(listRequestDTO);

		mappingMaster = queryTypeQueAnsMappingMasterService.addAll(mappingMaster);

	
		// converting entity to DTO
		List<QueryTypeQueAnsMappingMasterResponseDTO> responseDTO = queryTypeQueAnsMappingMasterDTOMapper.entityListToDtoList(mappingMaster);
					
		return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
				.status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(), "Question Answer Master Details saved Successfully")
				.result(responseDTO).build());

	}
	

// For Sub Query Type Ans 
@Override
public ResponseEntity<Response> SubaddAll(List<SubQueryTypeQueAnsMappingMasterRequestDTO> listRequestDTO) {
	
	List<SubQueryTypeQueAnsMappingMaster> SubmappingMaster = new ArrayList<>();
	SubmappingMaster = subQueryTypeQueAnsMappingMasterDTOMapper.dtoListToEntityList(listRequestDTO);

	SubmappingMaster = subQueryTypeQueAnsMappingMasterService.addAll(SubmappingMaster);


	// converting entity to DTO
	List<SubQueryTypeQueAnsMappingMasterResponseDTO> responseDTO = subQueryTypeQueAnsMappingMasterDTOMapper.entityListToDtoList(SubmappingMaster);
				
	return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
			.status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(), "Question Answer Master Details saved Successfully")
			.result(responseDTO).build());

   
}


	// 6/4/2021 Update Code Started

@Override
public ResponseEntity<Response> updateList(QueryTypeMasterRequestDTO requestDTO) {
	
	if (logger.isDebugEnabled())
	      logger.debug("Modifying QueryTypeMaster for Id: " + requestDTO.getQueryTypeMasterId());
	
	// Check_Id_is_Not_Null/Zero
    MethodValidationUtils.checkIfIdIsZero(requestDTO.getQueryTypeMasterId(), "QueryTypeMasterId");
    
	if (logger.isDebugEnabled())
		logger.debug("Mapping dto to entity");
	QueryTypeMaster master = queryTypeMasterDTOMapper.dtoToEntity(requestDTO);
	//List<SubQueryTypeMaster> listsubQueryMaster = new ArrayList<>();
	List<QueryTypeQueAnsMappingMaster> listQueryQA = new ArrayList<>();
	List<SubQueryTypeQueAnsMappingMaster> listSubQueryQA = new ArrayList<>();
	List<QueryTypePriorityMaster> listPriority = new ArrayList<>();
	
	if (!requestDTO.isSubQuery()) {
		if (logger.isDebugEnabled())
			logger.debug("mapping Query QA: " + requestDTO.getListQueryAnsMappingReqDTO());		
		
		listQueryQA = queryTypeQueAnsMappingMasterDTOMapper.dtoListToEntityList(requestDTO.getListQueryAnsMappingReqDTO());
				
	}else {
		if (logger.isDebugEnabled())
			logger.debug("Mapping Sub Query dto to entity");
		//listsubQueryMaster = subQueryTypeMasterDTOMapper.dtoListToEntityList(requestDTO.getSubQueryRequestDTO());
				
		/*
		 * for (SubQueryTypeMaster subMaster : listsubQueryMaster) {
		 * subMaster.setSubQueryTypeMasterId(details.getQueryTypeMasterId());
		 * listsubQueryMaster.add(subMaster); } if (logger.isDebugEnabled())
		 * logger.debug("mapping Sub Query QA: " +
		 * requestDTO.getSubQueryRequestDTO().get);
		 * 
		 * listSubQueryQA =
		 * subQueryTypeQueAnsMappingMasterDTOMapper.dtoListToEntityList(requestDTO.
		 * getListSubQueryAnsMappingReqDTO());
		 */
	}
	
	if (requestDTO.isPriorityRequired()) {
		if (logger.isDebugEnabled())
			logger.debug("mapping Query priority: " + requestDTO.getListQueryPriorityRequestDTO());		
		
		listPriority = queryTypePriorityMasterDTOMapper.dtoListToEntityList(requestDTO.getListQueryPriorityRequestDTO());
		
	}
	
	if (logger.isDebugEnabled())
		logger.debug("calling service mtd in QueryTypeMaster resource: " + master);
	
	List<QueryTypeMaster>  resultList = new ArrayList<>();
	// implementing service method for update 
	resultList = queryTypeMasterService.update(master, listQueryQA, listPriority, requestDTO.getSubQueryRequestDTO());
	
	// converting entity to DTO  to returning response
	List<QueryTypeMasterResponseDTO> responseDTO = queryTypeMasterDTOMapper.entityListToDtoList(resultList);
	
	if (logger.isDebugEnabled())
		logger.debug("Updated QueryTypeMaster Successfully");
	return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
			.status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(), "Updated QueryTypeMaster Successfully")
			.result(responseDTO).build());
}

//08/04/2021 started add new Code
@Override
public ResponseEntity<Response> AddNew(QueryTypeMasterRequestDTO requestDTO) {
	if (logger.isDebugEnabled())
		logger.debug("In add Sub Query Type Master add resource");						
	
	// Check-Object list-is-not-null
    MethodValidationUtils.checkIfObjectIsNotNULL(requestDTO, QueryTypeMaster.class.getSimpleName());
	
	//mapping DTO to entity 
    if (logger.isDebugEnabled())
		logger.debug("mapping dtoList to entity list");

	 QueryTypeMaster QAInfo = queryTypeMasterDTOMapper.dtoToEntity(requestDTO);
	//SubQueryTypeMaster subQueryMasteradd = new SubQueryTypeMaster();
	List<QueryTypeQueAnsMappingMaster> listQueryQAadd = new ArrayList<>();
	List<QueryTypePriorityMaster> listPriorityadd = new ArrayList<>();
	List<SubQueryTypeQueAnsMappingMaster> listSubQueryQAadd = new ArrayList<>();
	
	
	if (!requestDTO.isSubQuery()) {
		if (logger.isDebugEnabled())
			logger.debug("mapping Query QA: " + requestDTO.getListQueryAnsMappingReqDTO());		
		
		listQueryQAadd = queryTypeQueAnsMappingMasterDTOMapper.dtoListToEntityList(requestDTO.getListQueryAnsMappingReqDTO());
				
	}else {
		if (logger.isDebugEnabled())
			logger.debug("Mapping Sub Query dto to entity");
		/*
		 * subQueryMasteradd =
		 * subQueryTypeMasterDTOMapper.dtoToEntity(requestDTO.getSubQueryRequestDTO());
		 * 
		 * if (logger.isDebugEnabled()) logger.debug("mapping Sub Query QA: " +
		 * requestDTO.getListSubQueryAnsMappingReqDTO());
		 * 
		 * 
		 * listSubQueryQAadd =
		 * subQueryTypeQueAnsMappingMasterDTOMapper.dtoListToEntityList(requestDTO.
		 * getListSubQueryAnsMappingReqDTO());
		 */
	}
	
	if (requestDTO.isPriorityRequired()) {
		if (logger.isDebugEnabled())
			logger.debug("mapping Query priority: " + requestDTO.getListQueryPriorityRequestDTO());		
		
		listPriorityadd = queryTypePriorityMasterDTOMapper.dtoListToEntityList(requestDTO.getListQueryPriorityRequestDTO());
		
	}
	
	if (logger.isDebugEnabled())
		logger.debug("calling service mtd in QueryTypeMaster resource: " + QAInfo);
	
	List<QueryTypeMaster>  resultList = new ArrayList<>();
	// implementing service method for update 
  resultList = queryTypeMasterService.addNew(QAInfo, listQueryQAadd, listPriorityadd, requestDTO.getSubQueryRequestDTO());
	
	//resultList = queryTypeMasterService.update(master, listQueryQA, listPriority, requestDTO.getSubQueryRequestDTO());
	
	// converting entity to DTO  to returning response
	List<QueryTypeMasterResponseDTO> responseDTO = queryTypeMasterDTOMapper.entityListToDtoList(resultList);
	
	if (logger.isDebugEnabled())
		logger.debug("Added QueryTypeMaster Successfully");
	return ResponseEntity.status(HttpStatus.CREATED).body(ResponseBuilder.builder()
			.status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(), "Add Data QueryTypeMaster Successfully")
			.result(responseDTO).build());
}

// 12/04 Resource


@Override
public ResponseEntity<Response> getAllByQueryTypeMasterId(int queryTypeMasterId) {
	if (logger.isDebugEnabled())
		logger.debug("In getAllByQueryTypeMasterId");
	
	QueryTypeMasterResponseDTO responseDTO = new QueryTypeMasterResponseDTO ();
	
	responseDTO = queryTypeMasterService.getAllByQueryTypeMasterId(queryTypeMasterId);
	return ResponseEntity.status(HttpStatus.OK).body(
			ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Records found")
			.result(responseDTO).build());
}
}