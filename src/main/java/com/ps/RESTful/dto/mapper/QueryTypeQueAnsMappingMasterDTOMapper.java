package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.QueryTypeQueAnsMappingMasterRequestDTO;
import com.ps.RESTful.dto.response.QueryTypeQueAnsMappingMasterResponseDTO;
import com.ps.beans.QueryTypeQueAnsMappingMasterRequestBean;
import com.ps.dto.QueryTypeQueAnsMappingMasterDTO;
import com.ps.entities.master.QueryTypeMaster;
import com.ps.entities.master.QueryTypeQueAnsMappingMaster;

@Component
public class QueryTypeQueAnsMappingMasterDTOMapper implements AbstractDTOMapper <QueryTypeQueAnsMappingMasterRequestDTO,QueryTypeQueAnsMappingMasterResponseDTO,QueryTypeQueAnsMappingMaster>{

	@Override
	public QueryTypeQueAnsMappingMaster dtoToEntity(QueryTypeQueAnsMappingMasterRequestDTO dto) {
		
		if (dto == null)
			return new QueryTypeQueAnsMappingMaster();
		QueryTypeQueAnsMappingMaster master = new QueryTypeQueAnsMappingMaster();
		
		master.setQueryTypeQueAnsMappingId(dto.getQueryTypeQueAnsMappingId());
		//master.setQueryTypeMasterId(dto.getQueryTypeMasterId());
		QueryTypeMaster query= new QueryTypeMaster();
		query.setQueryTypeMasterId(dto.getQueryTypeMasterId());
		master.setQueryTypeMaster(query);
		master.setQueAnsMasterId(dto.getQueAnsMasterId());
		master.setActive(dto.isActive());
		master.setCreatedBy(dto.getCreatedBy());
		master.setLastModifiedBy(dto.getLastModifiedBy());
		return master;
	}

	@Override
	public QueryTypeQueAnsMappingMasterResponseDTO entityToDto(QueryTypeQueAnsMappingMaster entity) {
		QueryTypeQueAnsMappingMasterResponseDTO dto = new QueryTypeQueAnsMappingMasterResponseDTO();
		
		dto.setQueryTypeQueAnsMappingId(entity.getQueryTypeQueAnsMappingId());
		dto.setQueryTypeMasterId(entity.getQueryTypeMaster().getQueryTypeMasterId());
		dto.setQueAnsMasterId(entity.getQueAnsMasterId());
		dto.setActive(entity.isActive());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		return dto;
	}
	public List<QueryTypeQueAnsMappingMasterResponseDTO> entityListToDtoList(List<QueryTypeQueAnsMappingMaster> entityList) {
		List<QueryTypeQueAnsMappingMasterResponseDTO> responseDTOList = new ArrayList<QueryTypeQueAnsMappingMasterResponseDTO>();
		entityList.forEach(s -> {
			responseDTOList.add(entityToDto(s));			
		});
		return responseDTOList;
	}
	public List<QueryTypeQueAnsMappingMaster> dtoListToEntityList(
			List<QueryTypeQueAnsMappingMasterRequestDTO> requestDTOList) {

		if (CollectionUtils.isEmpty(requestDTOList))
			return new ArrayList<>();

		List<QueryTypeQueAnsMappingMaster> companyList = new ArrayList<>();

		for (QueryTypeQueAnsMappingMasterRequestDTO listValue : requestDTOList) {
			QueryTypeQueAnsMappingMaster companyMaster = dtoToEntity(listValue);
			if (companyMaster != null)
				companyList.add(companyMaster);
		}
		return companyList;
	}
	
	public Optional<QueryTypeQueAnsMappingMaster> entityToEntity(QueryTypeQueAnsMappingMaster req,
			Optional<QueryTypeQueAnsMappingMaster> dBDetails) {

		if (!dBDetails.isPresent())
			return Optional.empty();
		
		dBDetails.get().setQueryTypeQueAnsMappingId(req.getQueryTypeQueAnsMappingId());
		dBDetails.get().setQueryTypeMaster(req.getQueryTypeMaster());
		dBDetails.get().setQueAnsMasterId(req.getQueAnsMasterId());
		dBDetails.get().setActive(req.isActive());
		dBDetails.get().setCreatedBy(req.getCreatedBy());
		dBDetails.get().setLastModifiedBy(req.getLastModifiedBy());
		return dBDetails;
	}

	public QueryTypeQueAnsMappingMasterRequestBean dtoToEntity(QueryTypeQueAnsMappingMasterDTO requestDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}
