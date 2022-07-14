package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ps.RESTful.dto.request.QuestionAnswerMasterRequestDTO;
import com.ps.RESTful.dto.request.StandardKeywordMasterRequestDTO;
import com.ps.RESTful.dto.response.QuestionAnswerMasterResponseDTO;
import com.ps.RESTful.dto.response.StandardKeywordMasterResponseDTO;
import com.ps.entities.master.QuestionAnswerMaster;
import com.ps.entities.tenant.KeywordTableMaster;
import com.ps.entities.tenant.StandardKeywordMaster;
import com.ps.util.StringUtils;

@Component
public class StandardKeywordMasterDTOMapper implements AbstractDTOMapper<StandardKeywordMasterRequestDTO, StandardKeywordMasterResponseDTO, StandardKeywordMaster>{

	@Override
	public StandardKeywordMaster dtoToEntity(StandardKeywordMasterRequestDTO dto) {

		if (dto == null)
			return new StandardKeywordMaster();
		StandardKeywordMaster master = new StandardKeywordMaster();
		
		master.setStndKeywordMasterId(dto.getStndKeywordMasterId());
		
		KeywordTableMaster keywordTableMaster = new KeywordTableMaster();
		keywordTableMaster.setKeywordTableMasterId(dto.getKeywordTableMasterId());		
		master.setKeywordTableMaster(keywordTableMaster);	
		
		master.setDbFieldName(dto.getDbFieldName());	
		master.setDisplayName(dto.getDisplayName());		
		master.setDateFormatMasterId(dto.getDateFormatMasterId());		
		master.setActive(dto.isActive());
		master.setCreatedBy(dto.getCreatedBy());
		master.setCreatedDateTime(StringUtils.stringToDate(dto.getCreatedDateTime()));
		master.setLastModifiedBy(dto.getLastModifiedBy());
		master.setLastModifiedDateTime(StringUtils.stringToDate(dto.getLastModifiedDateTime()));
		return master;
	}
	
	
	@Override
	public StandardKeywordMasterResponseDTO entityToDto(StandardKeywordMaster entity) {

		StandardKeywordMasterResponseDTO dto = new StandardKeywordMasterResponseDTO();
		
		dto.setStndKeywordMasterId(entity.getStndKeywordMasterId());		
		dto.setKeywordTableMasterId(entity.getKeywordTableMaster().getKeywordTableMasterId());	
		dto.setTableName(entity.getKeywordTableMaster().getTableName());	
		dto.setDbFieldName(entity.getDbFieldName());	
		dto.setDisplayName(entity.getDisplayName());		
		dto.setDateFormatMasterId(entity.getDateFormatMasterId());		
		dto.setActive(entity.isActive());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDateTime(StringUtils.dateToString(entity.getCreatedDateTime()));
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setLastModifiedDateTime(StringUtils.dateToString(entity.getLastModifiedDateTime()));			
		return dto;
	}
	
	public List<StandardKeywordMasterResponseDTO> entityListToDtoList(List<StandardKeywordMaster> entityList) {
		List<StandardKeywordMasterResponseDTO> responseDTOList = new ArrayList<StandardKeywordMasterResponseDTO>();
		entityList.forEach(s -> {
			responseDTOList.add(entityToDto(s));			
		});
		return responseDTOList;
	}
	
}
