package com.ps.RESTful.resources.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ps.RESTful.dto.mapper.QueryDocumentInformationDTOMapper;
import com.ps.RESTful.dto.mapper.QueryGenerationEmpIterationsDTOMapper;
import com.ps.RESTful.dto.mapper.QueryGenerationEmployeeDTOMapper;
import com.ps.RESTful.dto.request.QueryGenerationEmployeeRequestDTO;
import com.ps.RESTful.dto.request.QueryGenerationRequestDTO;
import com.ps.RESTful.dto.response.QueryDocumentInformationResponseDTO;
import com.ps.RESTful.dto.response.QueryGenerationEmployeeResponseDTO;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.resources.QueryGenerationEmployeeResource;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.RESTful.response.enums.StatusEnum;
import com.ps.RESTful.response.enums.SuccessCode;
import com.ps.bean.QueryGenerationEmployeeBean;
import com.ps.entities.tenant.QueryDocumentInformation;
import com.ps.entities.tenant.QueryGenerationEmployee;
import com.ps.services.QueryGenerationEmpIterationsService;
import com.ps.services.QueryGenerationEmployeeService;
import com.ps.services.dao.repository.tenant.QueryDocumentInformationRepository;
import com.ps.util.MethodValidationUtils;
import com.ps.util.ObjectMapperUtils;

@RestController
@RequestMapping(path = QueryGenerationEmployeeResource.RESOURCE_PATH)
public class QueryGenerationEmployeeResourceImpl implements QueryGenerationEmployeeResource {

	Logger logger = Logger.getLogger(QueryGenerationEmployeeResourceImpl.class);

	@Autowired
	QueryGenerationEmployeeService queryGenerationEmployeeService;

	@Autowired
	QueryGenerationEmployeeDTOMapper queryGenerationEmployeeDTOMapper;
	
	@Autowired
	QueryGenerationEmpIterationsDTOMapper queryGenerationEmpIterationsDTOMapper;
	
	@Autowired
	QueryDocumentInformationDTOMapper queryDocumentInformationDTOMapper;

	@Autowired
	QueryGenerationEmpIterationsService queryGenerationEmpIterationsService;
	
	@Autowired
	QueryDocumentInformationRepository queryDocumentInformationRepository;
	
	QueryGenerationEmployeeBean bean = new QueryGenerationEmployeeBean();

	// get all company list
	@Override
	public ResponseEntity<Response> getAll() {

		if (logger.isDebugEnabled())
			logger.debug("In Get All Query Gneration Employee list resource ");

		// calling getAll method of QueryGenerationEmployee Service
		if (logger.isDebugEnabled())
			logger.debug("calling Question Answer Master Service getAll");

		List<QueryGenerationEmployeeBean> entityList = queryGenerationEmployeeService.getAllSummary();

		if (entityList != null && !entityList.isEmpty()) {
			// converting entityList to DtoList
			QueryGenerationEmployeeBean bean = new QueryGenerationEmployeeBean();
			List<QueryGenerationEmployeeResponseDTO> listResponseDto = bean.beanListToDtoList(entityList);

			if (logger.isDebugEnabled())
				logger.debug("Returning response from Query Generation Employee resource for all Query ");

			return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder().results(listResponseDto)
					.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Retrieved results Successfully")
					.build());
		} else {
			logger.error("Query Generation Employee list is empty");
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(),
							SuccessCode.NO_CONTENT.getCode(), "Query Generation Employee list is empty")
							.results(entityList).build());
		}
	}
	
	@Override
	public ResponseEntity<Response> getById(int queryGenerationEmpId) {

		if (logger.isDebugEnabled())
			logger.debug("In getById Query Generation Employee Resource");

		// Check_Id_is_not_0
		MethodValidationUtils.checkIfIdIsZero(queryGenerationEmpId, "QueryGenerationEmpId");
		
		QueryGenerationEmployeeBean details = new QueryGenerationEmployeeBean();
		QueryGenerationEmployeeResponseDTO responseDTO = new QueryGenerationEmployeeResponseDTO();
		details = queryGenerationEmployeeService.getById(queryGenerationEmpId);
		
		List<QueryDocumentInformation> listDocs = new ArrayList<>();
		List<QueryDocumentInformationResponseDTO> listResponseDoc = new ArrayList<>();

		if (details != null && details.getQueryIterationId() != 0) {
			
			listDocs = queryDocumentInformationRepository.findByQueryIdAndIterationId(queryGenerationEmpId, details.getQueryIterationId());
			
			// Converting_entity_to_DTO
			responseDTO = bean.beanToDTO(details);
			
			if(!listDocs.isEmpty()) {
				listResponseDoc = queryDocumentInformationDTOMapper.entityListToDtoList(listDocs);
			}
			
			// to bind doc list
			responseDTO.setListDoc(listResponseDoc);

			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseBuilder.builder()
							.status(StatusEnum.SUCCESS.getValue(), SuccessCode.OK.getCode(), "Query Generation Employee detail found")
							.result(responseDTO).build());
		}else {
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(),
							SuccessCode.NO_CONTENT.getCode(), "Query Generation Employee Details Not Found")
							.result(null).build());
		}
	}

	@Override
	public ResponseEntity<Response> add(String queryGenerationEmployeeData, MultipartFile[] queryDocs) {

		if (logger.isDebugEnabled())
			logger.debug("In add Query list resource");

		// converting requestDTOString string to actual requestDTO
		if (logger.isDebugEnabled())
			logger.debug("converting requestDTOString string to actual QueryGenerationEmployeeRequestDTO from string: "
					+ queryGenerationEmployeeData);

		List<QueryGenerationEmployee> queryInfo = new ArrayList<>();
		QueryGenerationRequestDTO requestDTO = new QueryGenerationRequestDTO();
		MultipartFile[] documents;

		
//		try {
			requestDTO = ObjectMapperUtils
					.stringToQueryGeneration(queryGenerationEmployeeData);

			// Check-Object list-is-not-null
			MethodValidationUtils.checkIfObjectIsNotNULL(requestDTO, QueryGenerationEmployee.class.getSimpleName());

			// mapping dtoList to entity list
			if (logger.isDebugEnabled())
				logger.debug("mapping dtoList to entity list");
			queryInfo = queryGenerationEmployeeDTOMapper
					.dtoListToEntityList(requestDTO.getQueryRequestDTO());

//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid QueryGenerationEmployeeRequestDTO");
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
//					"Invalid QueryGenerationEmployeeRequestDTO JsonProcessingException");
//		}

		// Check-Object list-is-not-null
		MethodValidationUtils.checkIfObjectListIsNotEmpty(queryInfo, QueryGenerationEmployee.class.getSimpleName());

		// Check CheckEmployeeIdRepeatOrNot
		int duplicateEmpId = CheckEmployeeIdRepeatOrNot(requestDTO.getQueryRequestDTO());
		if (duplicateEmpId != 0) {
			if (logger.isDebugEnabled())
				logger.debug("Same Employee Id found multiple times:" + duplicateEmpId);
			throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER,
					"Same Employee Id found multiple times:" + duplicateEmpId);
		}

		for (int i = 0; i < requestDTO.getQueryRequestDTO().size(); i++) {
			if (logger.isDebugEnabled())
				logger.debug("mapping dtoList to entity list");
			QueryGenerationEmployee queryMaster = new QueryGenerationEmployee();
			queryMaster = queryGenerationEmployeeDTOMapper.dtoToEntity(requestDTO.getQueryRequestDTO().get(i));

			// calling Query service add method
			if (logger.isDebugEnabled())
				logger.debug("calling Query service add method");
			queryMaster = queryGenerationEmployeeService.add(queryMaster, requestDTO.getQueryRequestDTO().get(i).getQueAnsMasterId(),
					requestDTO.getQueryRequestDTO().get(i).getQueryDescription(), queryDocs);
		}

		// converting entity to DTO
		List<QueryGenerationEmployeeResponseDTO> responseDTO = new ArrayList<>();

		if (logger.isDebugEnabled())
			logger.debug(
					"Query Generation Employee Details saved, Returning response from Query Generation Employee resource for "
							+ responseDTO);

		return ResponseEntity
				.status(HttpStatus.CREATED).body(
						ResponseBuilder.builder()
								.status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(),
										"Query Generation Employee Details saved Successfully")
								.result(responseDTO).build());

	}

	
	@Override
	public ResponseEntity<Response> updateNew(String queryGenerationEmployeeData, MultipartFile[] queryDocs) {

		if (logger.isDebugEnabled())
			logger.debug("In update Query resource");

		// converting requestDTOString string to actual requestDTO
		if (logger.isDebugEnabled())
			logger.debug("converting queryGenerationEmployeeData string to actual QueryGenerationEmployeeRequestDTO: "
					+ queryGenerationEmployeeData);

//		List<QueryGenerationEmployee> queryInfo = new ArrayList<>();
		QueryGenerationRequestDTO requestDTO = new QueryGenerationRequestDTO();
		MultipartFile[] documents;

		
//		try {
			requestDTO = ObjectMapperUtils
					.stringToQueryGeneration(queryGenerationEmployeeData);

			// Check-Object list-is-not-null
			MethodValidationUtils.checkIfObjectIsNotNULL(requestDTO, QueryGenerationEmployee.class.getSimpleName());
			
			// Check-Object list-is-not-null
			MethodValidationUtils.checkIfObjectListIsNotEmpty(requestDTO.getQueryRequestDTO(), QueryGenerationEmployee.class.getSimpleName());
			
			
//			// mapping DTO to entity
//			if (logger.isDebugEnabled())
//				logger.debug("mapping dto to entity");
//			queryInfo = queryGenerationEmployeeDTOMapper
//					.dtoListToEntityList(requestDTO.getQueryRequestDTO());

//		} catch (JsonMappingException e) {
//			e.printStackTrace();
//			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid QueryGenerationEmployeeRequestDTO");
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//			throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
//					"Invalid QueryGenerationEmployeeRequestDTO JsonProcessingException");
//		}

//		// Check-Object list-is-not-null
//		MethodValidationUtils.checkIfObjectListIsNotEmpty(queryInfo, QueryGenerationEmployee.class.getSimpleName());

//		// Check CheckEmployeeIdRepeatOrNot
//		int duplicateEmpId = CheckEmployeeIdRepeatOrNot(requestDTO.getQueryRequestDTO());
//		if (duplicateEmpId != 0) {
//			if (logger.isDebugEnabled())
//				logger.debug("Same Employee Id found multiple times:" + duplicateEmpId);
//			throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER,
//					"Same Employee Id found multiple times:" + duplicateEmpId);
//		}

		for (int i = 0; i < requestDTO.getQueryRequestDTO().size(); i++) {
			if (logger.isDebugEnabled())
				logger.debug("mapping dtoList to entity list");
			QueryGenerationEmployee queryMaster = new QueryGenerationEmployee();
			queryMaster = queryGenerationEmployeeDTOMapper.dtoToEntity(requestDTO.getQueryRequestDTO().get(i));

			if (logger.isDebugEnabled())
				logger.debug("Modifying QueryGenerationEmployee for Id: " + queryMaster.getQueryGenerationEmpId());

			// Check_Id_is_Not_Null/Zero
			MethodValidationUtils.checkIfIdIsZero(queryMaster.getQueryGenerationEmpId(), "QueryGenerationEmpId");
			
			// calling Query service add method
			if (logger.isDebugEnabled())
				logger.debug("calling Query service update method");
			queryMaster = queryGenerationEmployeeService.update(queryMaster, requestDTO.getQueryRequestDTO().get(i).getQueAnsMasterId(),
					requestDTO.getQueryRequestDTO().get(i).getQueryDescription(), queryDocs);
		}

		// converting entity to DTO
		List<QueryGenerationEmployeeResponseDTO> responseDTO = new ArrayList<>();

		if (logger.isDebugEnabled())
			logger.debug(
					"Query Generation Employee Details updated, Returning response from Query Generation Employee resource for "
							+ responseDTO);

		return ResponseEntity
				.status(HttpStatus.CREATED).body(
						ResponseBuilder.builder()
								.status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(),
										"Query Generation Employee Details updated Successfully")
								.result(responseDTO).build());

	}
	
	
	@Override
	public ResponseEntity<Response> update(QueryGenerationEmployeeRequestDTO requestDTO) {

		if (logger.isDebugEnabled())
			logger.debug("Modifying QueryGenerationEmployee for Id: " + requestDTO.getQueryGenerationEmpId());

		// Check_Id_is_Not_Null/Zero
		MethodValidationUtils.checkIfIdIsZero(requestDTO.getQueryGenerationEmpId(), "QueryGenerationEmployeeId");

		if (logger.isDebugEnabled())
			logger.debug("Mapping dto to entity");
		QueryGenerationEmployee master = queryGenerationEmployeeDTOMapper.dtoToEntity(requestDTO);

		if (logger.isDebugEnabled())
			logger.debug("calling service mtd in QueryGenerationEmployee resource: " + master);

		// implementing service method for update
		master = queryGenerationEmployeeService.update(master, requestDTO.getQueAnsMasterId(),
				requestDTO.getQueryDescription());

		if (logger.isDebugEnabled())
			logger.debug("Updated QueryGenerationEmployee Successfully");
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(), SuccessCode.CREATED.getCode(),
						"Updated QueryGenerationEmployee Successfully").result(master).build());
	}

	@Override
	public ResponseEntity<Response> delete(int id) {

		if (logger.isDebugEnabled())
			logger.debug("In delete QueryGenerationEmployee resource, Id " + id);

		// Check_Id_Is_Not_Null/Zero
		MethodValidationUtils.checkIfIdIsZero(id, "QueryGenerationEmployee ID");

		logger.debug("Performing Soft Delete by updating Active Status to 0 / false");

		// delete method from service
		queryGenerationEmployeeService.delete(id);

		if (logger.isDebugEnabled())
			logger.debug("QueryGenerationEmployee deleted Returning response from QueryGenerationEmployee resource for "
					+ id);

		return ResponseEntity.status(HttpStatus.OK).body(ResponseBuilder.builder().status(StatusEnum.SUCCESS.getValue(),
				SuccessCode.OK.getCode(), "Deleted QueryGenerationEmployee Successfully").build());
	}

	// CheckCompanyGroupIdRepeatOrNot in request
	private int CheckEmployeeIdRepeatOrNot(List<QueryGenerationEmployeeRequestDTO> list) {
		if (logger.isDebugEnabled())
			logger.debug("In CheckEmployeeIdRepeatOrNot method");

		int duplicateEmpId = 0;
		Set<Integer> set = new HashSet<Integer>();
		// iterate the list
		for (int i = 0; i < list.size(); i++) {
			QueryGenerationEmployeeRequestDTO data = list.get(i);
			if (data.getEmployeeMasterId() != 0) {
				int empId = data.getEmployeeMasterId();
				if (set.contains(empId)) {
					return empId;
				}
				set.add(empId);
			}
		}
		return duplicateEmpId;
	}

}
