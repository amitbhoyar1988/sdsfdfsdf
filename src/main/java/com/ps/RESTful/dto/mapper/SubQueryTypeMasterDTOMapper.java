package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.ps.RESTful.dto.request.SubQueryTypeMasterRequestDTO;
import com.ps.RESTful.dto.response.SubQueryTypeMasterResponseDTO;
import com.ps.entities.master.QueryTypeMaster;
import com.ps.entities.master.SubQueryTypeMaster;

@Component
public class SubQueryTypeMasterDTOMapper implements AbstractDTOMapper<SubQueryTypeMasterRequestDTO, SubQueryTypeMasterResponseDTO, SubQueryTypeMaster>{


	@Override
	public SubQueryTypeMaster dtoToEntity(SubQueryTypeMasterRequestDTO dto) {

		if (dto == null)
			return new SubQueryTypeMaster();
		SubQueryTypeMaster master = new SubQueryTypeMaster();
		QueryTypeMaster query= new QueryTypeMaster();
		query.setQueryTypeMasterId(dto.getQueryTypeMasterId());
		master.setQueryTypeMaster(query);
		master.setSubQueTypeMasterId(dto.getSubQueTypeMasterId());
		master.setSubQueryTypeCode(dto.getSubQueryTypeCode());
		master.setSubqueryTypedescription(dto.getSubqueryTypedescription());
		//master.setQueAnsMasterId(dto.getQueAnsMasterId());
		master.setRemark(dto.getRemark());
		master.setCreatedBy(dto.getCreatedBy());
		master.setLastModifiedBy(dto.getLastModifiedBy());
		master.setActive(dto.isActive());
		
		return master;
}

	@Override
	public SubQueryTypeMasterResponseDTO entityToDto(SubQueryTypeMaster entity) {
		SubQueryTypeMasterResponseDTO dto = new SubQueryTypeMasterResponseDTO();
		
		dto.setQueryTypeMasterId(entity.getQueryTypeMaster().getQueryTypeMasterId());
		dto.setSubQueTypeMasterId(entity.getSubQueTypeMasterId());
		dto.setSubQueryTypeCode(entity.getSubQueryTypeCode());
		dto.setSubqueryTypedescription(entity.getSubqueryTypedescription());
		//dto.setQueAnsMasterId(entity.getQueAnsMasterId());
		dto.setRemark(entity.getRemark());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setActive(entity.isActive());
		
		return dto;
	}
	public List<SubQueryTypeMasterResponseDTO> entityListToDtoList(List<SubQueryTypeMaster> entityList) {
		List<SubQueryTypeMasterResponseDTO> responseDTOList = new ArrayList<SubQueryTypeMasterResponseDTO>();
		entityList.forEach(s -> {
			responseDTOList.add(entityToDto(s));			
		});
		return responseDTOList;
	}
	
	public List<SubQueryTypeMaster> dtoListToEntityList(
			List<SubQueryTypeMasterRequestDTO> requestDTOList) {

		if (CollectionUtils.isEmpty(requestDTOList))
			return new ArrayList<>();

		List<SubQueryTypeMaster> companyList = new ArrayList<>();

		for (SubQueryTypeMasterRequestDTO listValue : requestDTOList) {
			SubQueryTypeMaster companyMaster = dtoToEntity(listValue);
			if (companyMaster != null)
				companyList.add(companyMaster);
		}
		return companyList;
	}
	public Optional<SubQueryTypeMaster> entityToEntity(SubQueryTypeMaster req,
			Optional<SubQueryTypeMaster> dBDetails) {

		if (!dBDetails.isPresent())
			return Optional.empty();
		
		dBDetails.get().setQueryTypeMaster(req.getQueryTypeMaster());
		dBDetails.get().setSubQueTypeMasterId(req.getSubQueTypeMasterId());
		dBDetails.get().setSubQueryTypeCode(req.getSubQueryTypeCode());
		dBDetails.get().setSubqueryTypedescription(req.getSubqueryTypedescription());
	//	dBDetails.get().setQueAnsMasterId(req.getQueAnsMasterId());
		dBDetails.get().setRemark(req.getRemark());
		dBDetails.get().setCreatedBy(req.getCreatedBy());
		dBDetails.get().setLastModifiedBy(req.getLastModifiedBy());
		dBDetails.get().setActive(req.isActive());
		
		return dBDetails;
	}
	public SubQueryTypeMasterResponseDTO entityToDtoById(SubQueryTypeMaster entity) {
		SubQueryTypeMasterResponseDTO dto = new SubQueryTypeMasterResponseDTO();
		
		dto.setQueryTypeMasterId(entity.getQueryTypeMaster().getQueryTypeMasterId());
		dto.setSubQueTypeMasterId(entity.getSubQueTypeMasterId());
		dto.setSubQueryTypeCode(entity.getSubQueryTypeCode());
		dto.setSubqueryTypedescription(entity.getSubqueryTypedescription());
		//dto.setQueAnsMasterId(entity.getQueAnsMasterId());
		dto.setRemark(entity.getRemark());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setActive(entity.isActive());
		
		return dto;
	}
}
	
	