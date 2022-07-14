package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.QueryTypeMasterRequestDTO;
import com.ps.RESTful.dto.request.QueryTypeMasterRequestDTONew;
import com.ps.RESTful.dto.response.QueryTypeMasterResponseDTO;
import com.ps.entities.master.QueryTypeMaster;

@Component
public class QueryTypeMasterDTOMapper implements AbstractDTOMapper<QueryTypeMasterRequestDTO, QueryTypeMasterResponseDTO, QueryTypeMaster>{

	@Override
	public QueryTypeMaster dtoToEntity(QueryTypeMasterRequestDTO dto) {

		if (dto == null)
			return new QueryTypeMaster();
		QueryTypeMaster master = new QueryTypeMaster();

		master.setQueryTypeMasterId(dto.getQueryTypeMasterId());
		master.setApplicationModuleId(dto.getApplicationModuleId());
		master.setQueryTypeCode(dto.getQueryTypeCode());
		master.setQueryTypedescription(dto.getQueryTypedescription());
		//master.setQueAnsMasterId(dto.getQueAnsMasterId());
		master.setResolutionTimeforNopriority(dto.getResolutionTimeforNopriority());
		master.setAutoCloseTimeforNopriority(dto.getAutoCloseTimeforNopriority());
		master.setSubQuery(dto.isSubQuery());
		master.setPriorityRequired(dto.isPriorityRequired());
		
		master.setReplyWorkflowId(dto.getReplyWorkflowId());
		master.setForwardWorkFlowId(dto.getForwardWorkFlowId());
		master.setRemark(dto.getRemark());
		master.setCreatedBy(dto.getCreatedBy());
		master.setLastModifiedBy(dto.getLastModifiedBy());
		master.setActive(dto.isActive());
		return master;
		
}
	
	
/*
 * public QueryTypeMaster dtoToEntityNew(QueryTypeMasterRequestDTONew dto) {
 * 
 * if (dto == null) return new QueryTypeMaster(); QueryTypeMaster master = new
 * QueryTypeMaster();
 * 
 * master.setQueryTypeMasterId(dto.getQueryTypeMasterId());
 * master.setApplicationModuleId(dto.getApplicationModuleId());
 * master.setQueryTypeCode(dto.getQueryTypeCode());
 * master.setQueryTypedescription(dto.getQueryTypedescription());
 * //master.setQueAnsMasterId(dto.getQueAnsMasterId());
 * master.setSubQuery(dto.isSubQuery());
 * master.setPriorityRequired(dto.isPriorityRequired());
 * master.setReplyWorkflowId(dto.getReplyWorkflowId());
 * master.setForwardWorkFlowId(dto.getForwardWorkFlowId());
 * master.setRemark(dto.getRemark()); master.setCreatedBy(dto.getCreatedBy());
 * master.setLastModifiedBy(dto.getLastModifiedBy());
 * master.setActive(dto.isActive()); return master;
 * 
 * }
 */
	
	
	@Override
	public QueryTypeMasterResponseDTO entityToDto(QueryTypeMaster entity) {
		QueryTypeMasterResponseDTO dto = new QueryTypeMasterResponseDTO();
		
		dto.setQueryTypeMasterId(entity.getQueryTypeMasterId());
		dto.setApplicationModuleId(entity.getApplicationModuleId());
		dto.setQueryTypeCode(entity.getQueryTypeCode());
		dto.setQueryTypedescription(entity.getQueryTypedescription());
		//dto.setQueAnsMasterId(entity.getQueAnsMasterId());
		dto.setSubQuery(entity.isSubQuery());
		dto.setPriorityRequired(entity.isPriorityRequired());
		dto.setResolutionTimeforNopriority(entity.getResolutionTimeforNopriority());
		dto.setAutoCloseTimeforNopriority(entity.getAutoCloseTimeforNopriority());
		dto.setReplyWorkflowId(entity.getReplyWorkflowId());
		dto.setForwardWorkFlowId(entity.getForwardWorkFlowId());
		dto.setRemark(entity.getRemark());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setActive(entity.isActive());
		return dto;
		
	}
	public List<QueryTypeMasterResponseDTO> entityListToDtoList(List<QueryTypeMaster> entityList) {
		List<QueryTypeMasterResponseDTO> responseDTOList = new ArrayList<QueryTypeMasterResponseDTO>();
		entityList.forEach(s -> {
			responseDTOList.add(entityToDto(s));			
		});
		return responseDTOList;
	}
	
	public List<QueryTypeMaster> dtoListToEntityList(
			List<QueryTypeMasterRequestDTO> requestDTOList) {

		if (CollectionUtils.isEmpty(requestDTOList))
			return new ArrayList<>();

		List<QueryTypeMaster> companyList = new ArrayList<>();

		for (QueryTypeMasterRequestDTO listValue : requestDTOList) {
			QueryTypeMaster companyMaster = dtoToEntity(listValue);
			if (companyMaster != null)
				companyList.add(companyMaster);
		}
		return companyList;
	}
	
	public Optional<QueryTypeMaster> entityToEntity(QueryTypeMaster req,
			Optional<QueryTypeMaster> dBDetails) {

		if (!dBDetails.isPresent())
			return Optional.empty();
		
		dBDetails.get().setQueryTypeMasterId(req.getQueryTypeMasterId());


		dBDetails.get().setQueryTypeMasterId(req.getQueryTypeMasterId());
		dBDetails.get().setApplicationModuleId(req.getApplicationModuleId());
		dBDetails.get().setQueryTypeCode(req.getQueryTypeCode());
		dBDetails.get().setQueryTypedescription(req.getQueryTypedescription());
	//	dBDetails.get().setQueAnsMasterId(req.getQueAnsMasterId());
		dBDetails.get().setSubQuery(req.isSubQuery());
		dBDetails.get().setPriorityRequired(req.isPriorityRequired());
		dBDetails.get().setResolutionTimeforNopriority(req.getResolutionTimeforNopriority());
		dBDetails.get().setAutoCloseTimeforNopriority(req.getAutoCloseTimeforNopriority());
		dBDetails.get().setReplyWorkflowId(req.getReplyWorkflowId());
		dBDetails.get().setForwardWorkFlowId(req.getForwardWorkFlowId());
		dBDetails.get().setRemark(req.getRemark());
		dBDetails.get().setCreatedBy(req.getCreatedBy());
		dBDetails.get().setLastModifiedBy(req.getLastModifiedBy());
		dBDetails.get().setActive(req.isActive());
		return dBDetails;
	}
	
	public QueryTypeMasterResponseDTO entityToDtoById(QueryTypeMaster entity) {
		QueryTypeMasterResponseDTO dto = new QueryTypeMasterResponseDTO();
		
		dto.setQueryTypeMasterId(entity.getQueryTypeMasterId());
		dto.setApplicationModuleId(entity.getApplicationModuleId());
		dto.setQueryTypeCode(entity.getQueryTypeCode());
		dto.setQueryTypedescription(entity.getQueryTypedescription());
		//dto.setQueAnsMasterId(entity.getQueAnsMasterId());
		dto.setSubQuery(entity.isSubQuery());
		dto.setPriorityRequired(entity.isPriorityRequired());
		dto.setReplyWorkflowId(entity.getReplyWorkflowId());
		dto.setForwardWorkFlowId(entity.getForwardWorkFlowId());
		dto.setRemark(entity.getRemark());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setActive(entity.isActive());
		return dto;
}
}