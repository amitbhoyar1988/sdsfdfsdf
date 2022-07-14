package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.QueryDocumentInformationRequestDTO;
import com.ps.RESTful.dto.request.QueryGenerationEmpIterationsRequestDTO;
import com.ps.RESTful.dto.response.QueryDocumentInformationResponseDTO;
import com.ps.RESTful.dto.response.QueryGenerationEmpIterationsResponseDTO;
import com.ps.entities.tenant.QueryDocumentInformation;
import com.ps.entities.tenant.QueryGenerationEmpIterations;
import com.ps.entities.tenant.QueryGenerationEmployee;
import com.ps.util.StringUtils;

@Component
public class QueryDocumentInformationDTOMapper implements AbstractDTOMapper<QueryDocumentInformationRequestDTO, QueryDocumentInformationResponseDTO, QueryDocumentInformation>{

	@Override
	public QueryDocumentInformation dtoToEntity(QueryDocumentInformationRequestDTO dto) {

		if (dto == null)
			return new QueryDocumentInformation();
		QueryDocumentInformation master = new QueryDocumentInformation();
		
		master.setQueryDocumentId(dto.getQueryDocumentId());
		master.setQueryIterationId(dto.getQueryIterationId());	
		
		QueryGenerationEmployee queryGeneration = new QueryGenerationEmployee();
		queryGeneration.setQueryGenerationEmpId(dto.getQueryGenerationEmpId());		
		master.setQueryGenerationEmployee(queryGeneration);	
				
		master.setQueryBlobURI(dto.getQueryBlobURI());				
		master.setEmployeeMasterId(dto.getEmployeeMasterId());		
		master.setFileName(dto.getFileName());	
		master.setFileType(dto.getFileType());	
		master.setFileSize(dto.getFileSize());	
		master.setActive(dto.isActive());
		master.setCreatedBy(dto.getCreatedBy());
		master.setCreatedDateTime(StringUtils.stringToDate(dto.getCreatedDateTime()));
		master.setLastModifiedBy(dto.getLastModifiedBy());
		master.setLastModifiedDateTime(StringUtils.stringToDate(dto.getLastModifiedDateTime()));
		return master;
	}
	
	
	@Override
	public QueryDocumentInformationResponseDTO entityToDto(QueryDocumentInformation entity) {

		QueryDocumentInformationResponseDTO dto = new QueryDocumentInformationResponseDTO();
		
		dto.setQueryDocumentId(entity.getQueryDocumentId());
		dto.setQueryIterationId(entity.getQueryIterationId());			
		dto.setQueryGenerationEmpId(entity.getQueryGenerationEmployee().getQueryGenerationEmpId());			
		dto.setQueryBlobURI(entity.getQueryBlobURI());					
		dto.setEmployeeMasterId(entity.getEmployeeMasterId());		
		dto.setFileName(entity.getFileName());	
		dto.setFileType(entity.getFileType());	
		dto.setFileSize(entity.getFileSize());	
		dto.setActive(entity.isActive());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDateTime(StringUtils.dateToString(entity.getCreatedDateTime()));
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setLastModifiedDateTime(StringUtils.dateToString(entity.getLastModifiedDateTime()));
		return dto;
	}
	
	public List<QueryDocumentInformationResponseDTO> entityListToDtoList(List<QueryDocumentInformation> entityList) {
		List<QueryDocumentInformationResponseDTO> responseDTOList = new ArrayList<QueryDocumentInformationResponseDTO>();
		entityList.forEach(s -> {
			responseDTOList.add(entityToDto(s));			
		});
		return responseDTOList;
	}
	
	public List<QueryDocumentInformation> dtoListToEntityList(
			List<QueryDocumentInformationRequestDTO> requestDTOList) {

		if (CollectionUtils.isEmpty(requestDTOList))
			return new ArrayList<>();

		List<QueryDocumentInformation> companyList = new ArrayList<>();

		for (QueryDocumentInformationRequestDTO listValue : requestDTOList) {
			QueryDocumentInformation companyMaster = dtoToEntity(listValue);
			if (companyMaster != null)
				companyList.add(companyMaster);
		}
		return companyList;
	}
	
}
