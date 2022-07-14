package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.QueryGenerationEmpIterationsRequestDTO;
import com.ps.RESTful.dto.response.QueryGenerationEmpIterationsResponseDTO;
import com.ps.entities.tenant.QueryGenerationEmpIterations;
import com.ps.entities.tenant.QueryGenerationEmployee;
import com.ps.entities.tenant.QuestionAnswerMasterTenant;
import com.ps.util.StringUtils;

@Component
public class QueryGenerationEmpIterationsDTOMapper implements AbstractDTOMapper<QueryGenerationEmpIterationsRequestDTO, QueryGenerationEmpIterationsResponseDTO, QueryGenerationEmpIterations> {

	@Override
	public QueryGenerationEmpIterations dtoToEntity(QueryGenerationEmpIterationsRequestDTO dto) {

		if (dto == null)
			return new QueryGenerationEmpIterations();
		QueryGenerationEmpIterations master = new QueryGenerationEmpIterations();
		
		master.setQueryIterationId(dto.getQueryIterationId());	
		
		QueryGenerationEmployee queryGeneration = new QueryGenerationEmployee();
		queryGeneration.setQueryGenerationEmpId(dto.getQueryGenerationEmpId());		
		master.setQueryGenerationEmployee(queryGeneration);	
				
		master.setRefNumber(dto.getRefNumber());
		master.setStatus(dto.getStatus());
		
//		QuestionAnswerMasterTenant QueAns = new QuestionAnswerMasterTenant();
//		QueAns.setQueAnsMasterId(dto.getQueAnsMasterId());		
//		master.setQuestionAnswerMaster(QueAns);				
		master.setQueAnsMasterId(dto.getProofSubmissionId());
		
		master.setQueryDescription(dto.getQueryDescription());		
		master.setActive(dto.isActive());
		master.setCreatedBy(dto.getCreatedBy());
		master.setCreatedDateTime(StringUtils.stringToDate(dto.getCreatedDateTime()));
		master.setLastModifiedBy(dto.getLastModifiedBy());
		master.setLastModifiedDateTime(StringUtils.stringToDate(dto.getLastModifiedDateTime()));
		return master;
	}
	
	
	@Override
	public QueryGenerationEmpIterationsResponseDTO entityToDto(QueryGenerationEmpIterations entity) {

		QueryGenerationEmpIterationsResponseDTO dto = new QueryGenerationEmpIterationsResponseDTO();
		
		dto.setQueryIterationId(entity.getQueryIterationId());				
		dto.setQueryGenerationEmpId(entity.getQueryGenerationEmployee().getQueryGenerationEmpId());
		dto.setRefNumber(entity.getRefNumber());
		dto.setStatus(entity.getStatus());
//		dto.setQueAnsMasterId(entity.getQuestionAnswerMaster().getQueAnsMasterId());	
		dto.setQueAnsMasterId(entity.getQueAnsMasterId());
		dto.setQueryDescription(entity.getQueryDescription());		
		dto.setActive(entity.isActive());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDateTime(StringUtils.dateToString(entity.getCreatedDateTime()));
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setLastModifiedDateTime(StringUtils.dateToString(entity.getLastModifiedDateTime()));
		return dto;
	}
	
	public List<QueryGenerationEmpIterationsResponseDTO> entityListToDtoList(List<QueryGenerationEmpIterations> entityList) {
		List<QueryGenerationEmpIterationsResponseDTO> responseDTOList = new ArrayList<QueryGenerationEmpIterationsResponseDTO>();
		entityList.forEach(s -> {
			responseDTOList.add(entityToDto(s));			
		});
		return responseDTOList;
	}
	
	public List<QueryGenerationEmpIterations> dtoListToEntityList(
			List<QueryGenerationEmpIterationsRequestDTO> requestDTOList) {

		if (CollectionUtils.isEmpty(requestDTOList))
			return new ArrayList<>();

		List<QueryGenerationEmpIterations> companyList = new ArrayList<>();

		for (QueryGenerationEmpIterationsRequestDTO listValue : requestDTOList) {
			QueryGenerationEmpIterations companyMaster = dtoToEntity(listValue);
			if (companyMaster != null)
				companyList.add(companyMaster);
		}
		return companyList;
	}
	
}
