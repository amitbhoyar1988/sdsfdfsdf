package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import com.ps.RESTful.dto.request.SubQueryTypeQueAnsMappingMasterRequestDTO;
import com.ps.RESTful.dto.response.SubQueryTypeQueAnsMappingMasterResponseDTO;
import com.ps.entities.master.SubQueryTypeQueAnsMappingMaster;
@Component
public class SubQueryTypeQueAnsMappingMasterDTOMapper implements AbstractDTOMapper<SubQueryTypeQueAnsMappingMasterRequestDTO,SubQueryTypeQueAnsMappingMasterResponseDTO,SubQueryTypeQueAnsMappingMaster>{

	@Override
	public SubQueryTypeQueAnsMappingMaster dtoToEntity(SubQueryTypeQueAnsMappingMasterRequestDTO dto) {
		
		if (dto == null)
			return new SubQueryTypeQueAnsMappingMaster();
		SubQueryTypeQueAnsMappingMaster master = new SubQueryTypeQueAnsMappingMaster();
		
		master.setSubQueryTypeQueAnsMappingId(dto.getSubQueryTypeQueAnsMappingId());
		master.setSubQueryTypeMasterId(dto.getSubQueryTypeMasterId());
		master.setQueAnsMasterId(dto.getQueAnsMasterId());
		master.setActive(dto.isActive());
		master.setCreatedBy(dto.getCreatedBy());
		master.setLastModifiedBy(dto.getLastModifiedBy());
		return master;
	}

	@Override
	public SubQueryTypeQueAnsMappingMasterResponseDTO entityToDto(SubQueryTypeQueAnsMappingMaster entity) {
		SubQueryTypeQueAnsMappingMasterResponseDTO dto = new SubQueryTypeQueAnsMappingMasterResponseDTO();
		
		dto.setSubQueryTypeQueAnsMappingId(entity.getSubQueryTypeQueAnsMappingId());
		dto.setSubQueryTypeMasterId(entity.getSubQueryTypeMasterId());
		dto.setQueAnsMasterId(entity.getQueAnsMasterId());
		dto.setActive(entity.isActive());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		return dto;
	}
	public List<SubQueryTypeQueAnsMappingMasterResponseDTO> entityListToDtoList(List<SubQueryTypeQueAnsMappingMaster> entityList) {
		List<SubQueryTypeQueAnsMappingMasterResponseDTO> responseDTOList = new ArrayList<SubQueryTypeQueAnsMappingMasterResponseDTO>();
		entityList.forEach(s -> {
			responseDTOList.add(entityToDto(s));			
		});
		return responseDTOList;
	}
	public List<SubQueryTypeQueAnsMappingMaster> dtoListToEntityList(
			List<SubQueryTypeQueAnsMappingMasterRequestDTO> requestDTOList) {

		if (CollectionUtils.isEmpty(requestDTOList))
			return new ArrayList<>();

		List<SubQueryTypeQueAnsMappingMaster> companyList = new ArrayList<>();

		for (SubQueryTypeQueAnsMappingMasterRequestDTO listValue : requestDTOList) {
			SubQueryTypeQueAnsMappingMaster companyMaster = dtoToEntity(listValue);
			if (companyMaster != null)
				companyList.add(companyMaster);
		}
		return companyList;
	}
	
	public Optional<SubQueryTypeQueAnsMappingMaster> entityToEntity(SubQueryTypeQueAnsMappingMaster req,
			Optional<SubQueryTypeQueAnsMappingMaster> dBDetails) {

		if (!dBDetails.isPresent())
			return Optional.empty();
		
		dBDetails.get().setSubQueryTypeQueAnsMappingId(req.getSubQueryTypeQueAnsMappingId());
		dBDetails.get().setSubQueryTypeMasterId(req.getSubQueryTypeMasterId());
		dBDetails.get().setQueAnsMasterId(req.getQueAnsMasterId());
		dBDetails.get().setActive(req.isActive());
		dBDetails.get().setCreatedBy(req.getCreatedBy());
		dBDetails.get().setLastModifiedBy(req.getLastModifiedBy());
		return dBDetails;
	}
}
