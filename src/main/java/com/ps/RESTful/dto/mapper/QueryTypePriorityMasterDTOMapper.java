package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.ps.RESTful.dto.request.QueryTypePriorityMasterRequestDTO;
import com.ps.RESTful.dto.response.QueryTypePriorityMasterResponseDTO;
import com.ps.entities.master.QueryTypeMaster;
import com.ps.entities.master.QueryTypePriorityMaster;
import com.ps.entities.tenant.QueryTypePriorityMasterTenant;

@Component
public class QueryTypePriorityMasterDTOMapper implements
		AbstractDTOMapper<QueryTypePriorityMasterRequestDTO, QueryTypePriorityMasterResponseDTO, QueryTypePriorityMaster> {

	@Override
	public QueryTypePriorityMaster dtoToEntity(QueryTypePriorityMasterRequestDTO dto) {

		if (dto == null)
			return new QueryTypePriorityMaster();
		QueryTypePriorityMaster master = new QueryTypePriorityMaster();

		// master.setQueryTypeMasterId(dto.getQueryTypeMasterId());
		QueryTypeMaster query = new QueryTypeMaster();
		query.setQueryTypeMasterId(dto.getQueryTypeMasterId());
		master.setQueryTypeMaster(query);

		master.setQueTypePriorityMasterId(dto.getQueTypePriorityMasterId());
		master.setPriorityType(dto.getPriorityType());
		master.setResolutionTime(dto.getResolutionTime());
		master.setAutoClose(dto.getAutoClose());
		master.setDefaultPriority(dto.isDefaultPriority());
		master.setCreatedBy(dto.getCreatedBy());
		master.setLastModifiedBy(dto.getLastModifiedBy());
		master.setActive(dto.isActive());
		return master;

	}

	@Override
	public QueryTypePriorityMasterResponseDTO entityToDto(QueryTypePriorityMaster entity) {
		QueryTypePriorityMasterResponseDTO dto = new QueryTypePriorityMasterResponseDTO();

		dto.setQueryTypeMasterId(entity.getQueryTypeMaster().getQueryTypeMasterId());
		dto.setQueTypePriorityMasterId(entity.getQueTypePriorityMasterId());
		dto.setPriorityType(entity.getPriorityType());
		dto.setResolutionTime(entity.getResolutionTime());
		dto.setAutoClose(entity.getAutoClose());
		dto.setDefaultPriority(entity.isDefaultPriority());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setActive(entity.isActive());
		return dto;

	}

	public List<QueryTypePriorityMasterResponseDTO> entityListToDtoList(List<QueryTypePriorityMaster> entityList) {
		List<QueryTypePriorityMasterResponseDTO> responseDTOList = new ArrayList<QueryTypePriorityMasterResponseDTO>();
		entityList.forEach(s -> {
			responseDTOList.add(entityToDto(s));
		});
		return responseDTOList;
	}

	public List<QueryTypePriorityMaster> dtoListToEntityList(List<QueryTypePriorityMasterRequestDTO> requestDTOList) {

		if (CollectionUtils.isEmpty(requestDTOList))
			return new ArrayList<>();

		List<QueryTypePriorityMaster> companyList = new ArrayList<>();

		for (QueryTypePriorityMasterRequestDTO listValue : requestDTOList) {
			QueryTypePriorityMaster companyMaster = dtoToEntity(listValue);
			if (companyMaster != null)
				companyList.add(companyMaster);
		}
		return companyList;
	}

	public Optional<QueryTypePriorityMaster> entityToEntity(QueryTypePriorityMaster req,
			Optional<QueryTypePriorityMaster> dBDetails) {

		if (!dBDetails.isPresent())
			return Optional.empty();

		dBDetails.get().setQueryTypeMaster(req.getQueryTypeMaster());
		dBDetails.get().setQueTypePriorityMasterId(req.getQueTypePriorityMasterId());
		dBDetails.get().setPriorityType(req.getPriorityType());
		dBDetails.get().setResolutionTime(req.getResolutionTime());
		dBDetails.get().setAutoClose(req.getAutoClose());
		dBDetails.get().setDefaultPriority(req.isDefaultPriority());
		dBDetails.get().setCreatedBy(req.getCreatedBy());
		dBDetails.get().setLastModifiedBy(req.getLastModifiedBy());
		dBDetails.get().setActive(req.isActive());
		return dBDetails;
	}

	public QueryTypePriorityMasterResponseDTO entityTenantToDto(QueryTypePriorityMasterTenant entity) {
		QueryTypePriorityMasterResponseDTO dto = new QueryTypePriorityMasterResponseDTO();

		dto.setQueryTypeMasterId(entity.getQueryTypeMasterId());
		dto.setQueTypePriorityMasterId(entity.getQueTypePriorityMasterId());
		dto.setPriorityType(entity.getPriorityType());
		dto.setResolutionTime(entity.getResolutionTime());
		dto.setAutoClose(entity.getAutoClose());
		dto.setDefaultPriority(entity.isDefaultPriority());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setActive(entity.isActive());
		return dto;
	}

	public List<QueryTypePriorityMasterResponseDTO> entityListTenantToDtoList(
			List<QueryTypePriorityMasterTenant> entityList) {
		List<QueryTypePriorityMasterResponseDTO> responseDTOList = new ArrayList<QueryTypePriorityMasterResponseDTO>();
		entityList.forEach(s -> {
			responseDTOList.add(entityTenantToDto(s));
		});
		return responseDTOList;
	}

}
