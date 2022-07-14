package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.QueryGenerationEmployeeRequestDTO;
import com.ps.RESTful.dto.response.QueryGenerationEmployeeResponseDTO;
import com.ps.entities.tenant.ApplicationModuleDetails;
import com.ps.entities.tenant.EmployeeMaster;
import com.ps.entities.tenant.QueryGenerationEmployee;
import com.ps.entities.tenant.QueryTypeMasterTenant;
import com.ps.util.StringUtils;

@Component
public class QueryGenerationEmployeeDTOMapper implements AbstractDTOMapper<QueryGenerationEmployeeRequestDTO, QueryGenerationEmployeeResponseDTO, QueryGenerationEmployee>{

	@Override
	public QueryGenerationEmployee dtoToEntity(QueryGenerationEmployeeRequestDTO dto) {

		if (dto == null)
			return new QueryGenerationEmployee();
		QueryGenerationEmployee master = new QueryGenerationEmployee();
		
		master.setQueryGenerationEmpId(dto.getQueryGenerationEmpId());	
		master.setQueryNumber(dto.getQueryNumber());
		master.setSubmissionDate(StringUtils.stringToDate(dto.getSubmissionDate()));
		
		EmployeeMaster emp = new EmployeeMaster();
		emp.setEmployeeMasterId(dto.getEmployeeMasterId());		
		master.setEmployeeMaster(emp);	
		
		master.setOnBehalfOfEmployee(dto.isOnBehalfOfEmployee());
		
		ApplicationModuleDetails modules = new ApplicationModuleDetails();
		modules.setApplicationModuleId(dto.getApplicationModuleId());		
		master.setApplicationModuleDetails(modules);
		
		QueryTypeMasterTenant qryType = new QueryTypeMasterTenant();
		qryType.setQueryTypeMasterId(dto.getQueryTypeMasterId());		
		master.setQueryTypeMasterTenant(qryType);
		
		master.setSubQueTypeMasterId(dto.getSubQueTypeMasterId());
		master.setPriority(dto.getPriority());
		master.setEscalationDate(StringUtils.stringToDate(dto.getEscalationDate()));
		master.setSubject(dto.getSubject());
//		master.setDescription(dto.getDescription());
		master.setQueryRootCause(dto.getQueryRootCause());
		master.setStatus(dto.getStatus());
		master.setActive(dto.isActive());
		master.setCreatedBy(dto.getCreatedBy());
		master.setCreatedDateTime(StringUtils.stringToDate(dto.getCreatedDateTime()));
		master.setLastModifiedBy(dto.getLastModifiedBy());
		master.setLastModifiedDateTime(StringUtils.stringToDate(dto.getLastModifiedDateTime()));
		return master;
	}
	
	
	@Override
	public QueryGenerationEmployeeResponseDTO entityToDto(QueryGenerationEmployee entity) {

		QueryGenerationEmployeeResponseDTO dto = new QueryGenerationEmployeeResponseDTO();
				
		dto.setQueryGenerationEmpId(entity.getQueryGenerationEmpId());	
		dto.setQueryNumber(entity.getQueryNumber());
		dto.setSubmissionDate(StringUtils.dateToString(entity.getSubmissionDate()));	
		
		dto.setApplicationModuleId(entity.getApplicationModuleDetails().getApplicationModuleId());	
		dto.setEmployeeMasterId(entity.getEmployeeMaster().getEmployeeMasterId());	
		dto.setOnBehalfOfEmployee(entity.isOnBehalfOfEmployee());
		dto.setQueryTypeMasterId(entity.getQueryTypeMasterTenant().getQueryTypeMasterId());		
		dto.setSubQueTypeMasterId(entity.getSubQueTypeMasterId());
		dto.setPriority(entity.getPriority());
		dto.setEscalationDate(StringUtils.dateToString(entity.getEscalationDate()));
		dto.setSubject(entity.getSubject());
//		dto.setDescription(entity.getDescription());
		dto.setQueryRootCause(entity.getQueryRootCause());
		dto.setStatus(entity.getStatus());
		dto.setActive(entity.isActive());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDateTime(StringUtils.dateToString(entity.getCreatedDateTime()));
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setLastModifiedDateTime(StringUtils.dateToString(entity.getLastModifiedDateTime()));
		return dto;
	}
	
	public List<QueryGenerationEmployeeResponseDTO> entityListToDtoList(List<QueryGenerationEmployee> entityList) {
		List<QueryGenerationEmployeeResponseDTO> responseDTOList = new ArrayList<QueryGenerationEmployeeResponseDTO>();
		entityList.forEach(s -> {
			responseDTOList.add(entityToDto(s));			
		});
		return responseDTOList;
	}
	
	public List<QueryGenerationEmployee> dtoListToEntityList(
			List<QueryGenerationEmployeeRequestDTO> requestDTOList) {

		if (CollectionUtils.isEmpty(requestDTOList))
			return new ArrayList<>();

		List<QueryGenerationEmployee> companyList = new ArrayList<>();

		for (QueryGenerationEmployeeRequestDTO listValue : requestDTOList) {
			QueryGenerationEmployee companyMaster = dtoToEntity(listValue);
			if (companyMaster != null)
				companyList.add(companyMaster);
		}
		return companyList;
	}
	
	public Optional<QueryGenerationEmployee> entityToEntity(QueryGenerationEmployee req,
			Optional<QueryGenerationEmployee> dBDetails) {

		if (!dBDetails.isPresent())
			return Optional.empty();
		
		dBDetails.get().setQueryGenerationEmpId(req.getQueryGenerationEmpId());	
//		dBDetails.get().setQueryNumber(req.getQueryNumber());
//		dBDetails.get().setSubmissionDate(req.getSubmissionDate());
		
//		EmployeeMaster emp = new EmployeeMaster();
//		emp.setEmployeeMasterId(req.getEmployeeMasterId());	
//		dBDetails.get().setEmployeeMaster(emp);	
		
		dBDetails.get().setEmployeeMaster(req.getEmployeeMaster());
//		dBDetails.get().setOnBehalfOfEmployee(req.isOnBehalfOfEmployee());
		
//		ApplicationModuleDetails modules = new ApplicationModuleDetails();
//		modules.setApplicationModuleId(req.getApplicationModuleId());		
//		dBDetails.get().setApplicationModuleDetails(modules);
		
		dBDetails.get().setApplicationModuleDetails(req.getApplicationModuleDetails());		
		dBDetails.get().setQueryTypeMasterTenant(req.getQueryTypeMasterTenant());		
		dBDetails.get().setSubQueTypeMasterId(req.getSubQueTypeMasterId());
		dBDetails.get().setPriority(req.getPriority());
		dBDetails.get().setEscalationDate(req.getEscalationDate());
		dBDetails.get().setSubject(req.getSubject());
//		dBDetails.get().setDescription(req.getDescription());
		dBDetails.get().setQueryRootCause(req.getQueryRootCause());
		dBDetails.get().setStatus(req.getStatus());
//		dBDetails.get().setActive(req.isActive());
//		dBDetails.get().setCreatedBy(dto.getCreatedBy());
//		dBDetails.get().setCreatedDateTime(StringUtils.stringToDate(dto.getCreatedDateTime()));
//		dBDetails.get().setLastModifiedBy(dto.getLastModifiedBy());
//		dBDetails.get().setLastModifiedDateTime(req.getLastModifiedDateTime());
		
		return dBDetails;
	}
	
}
