package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.request.QuestionAnswerMasterRequestDTO;
import com.ps.RESTful.dto.response.QuestionAnswerMasterResponseDTO;
import com.ps.entities.master.QuestionAnswerMaster;
import com.ps.util.StringUtils;

@Component
public class QuestionAnswerMasterDTOMapper implements AbstractDTOMapper<QuestionAnswerMasterRequestDTO, QuestionAnswerMasterResponseDTO, QuestionAnswerMaster>{

	@Override
	public QuestionAnswerMaster dtoToEntity(QuestionAnswerMasterRequestDTO dto) {

		if (dto == null)
			return new QuestionAnswerMaster();
		QuestionAnswerMaster master = new QuestionAnswerMaster();
		
		master.setQueAnsMasterId(dto.getQueAnsMasterId());	
		master.setCode(dto.getCode());	
		master.setDescription(dto.getDescription());	
		master.setModuleId(dto.getModuleId());		
		master.setQuestionSubject(dto.getQuestionSubject());
		master.setQuestionDescription(dto.getQuestionDescription());
		master.setAnswerSubject(dto.getAnswerSubject());
		master.setAnswerDescription(dto.getAnswerDescription());
		master.setRemark(dto.getRemark());
		master.setActive(dto.isActive());
		master.setCreatedBy(dto.getCreatedBy());
		master.setCreatedDateTime(StringUtils.stringToDate(dto.getCreatedDateTime()));
		master.setLastModifiedBy(dto.getLastModifiedBy());
		master.setLastModifiedDateTime(StringUtils.stringToDate(dto.getLastModifiedDateTime()));
		return master;
	}
	
	
	@Override
	public QuestionAnswerMasterResponseDTO entityToDto(QuestionAnswerMaster entity) {

		QuestionAnswerMasterResponseDTO dto = new QuestionAnswerMasterResponseDTO();
		dto.setQueAnsMasterId(entity.getQueAnsMasterId());	
		dto.setCode(entity.getCode());	
		dto.setDescription(entity.getDescription());	
		dto.setModuleId(entity.getModuleId());		
		dto.setQuestionSubject(entity.getQuestionSubject());
		dto.setQuestionDescription(entity.getQuestionDescription());
		dto.setAnswerSubject(entity.getAnswerSubject());
		dto.setAnswerDescription(entity.getAnswerDescription());	
		dto.setRemark(entity.getRemark());
		dto.setActive(entity.isActive());
		dto.setCreatedBy(entity.getCreatedBy());
		dto.setCreatedDateTime(StringUtils.dateToString(entity.getCreatedDateTime()));
		dto.setLastModifiedBy(entity.getLastModifiedBy());
		dto.setLastModifiedDateTime(StringUtils.dateToString(entity.getLastModifiedDateTime()));			
		return dto;
	}
	
	public List<QuestionAnswerMasterResponseDTO> entityListToDtoList(List<QuestionAnswerMaster> entityList) {
		List<QuestionAnswerMasterResponseDTO> responseDTOList = new ArrayList<QuestionAnswerMasterResponseDTO>();
		entityList.forEach(s -> {
			responseDTOList.add(entityToDto(s));			
		});
		return responseDTOList;
	}
	
	public List<QuestionAnswerMaster> dtoListToEntityList(
			List<QuestionAnswerMasterRequestDTO> requestDTOList) {

		if (CollectionUtils.isEmpty(requestDTOList))
			return new ArrayList<>();

		List<QuestionAnswerMaster> companyList = new ArrayList<>();

		for (QuestionAnswerMasterRequestDTO listValue : requestDTOList) {
			QuestionAnswerMaster companyMaster = dtoToEntity(listValue);
			if (companyMaster != null)
				companyList.add(companyMaster);
		}
		return companyList;
	}
	
	public Optional<QuestionAnswerMaster> entityToEntity(QuestionAnswerMaster req,
			Optional<QuestionAnswerMaster> dBDetails) {

		if (!dBDetails.isPresent())
			return Optional.empty();

		dBDetails.get().setQueAnsMasterId(req.getQueAnsMasterId());	
//		dBDetails.get().setCode(req.getCode());	
		dBDetails.get().setDescription(req.getDescription());				
		dBDetails.get().setModuleId(req.getModuleId());	
		dBDetails.get().setQuestionSubject(req.getQuestionSubject());
		dBDetails.get().setQuestionDescription(req.getQuestionDescription());
		dBDetails.get().setAnswerSubject(req.getAnswerSubject());
		dBDetails.get().setAnswerDescription(req.getAnswerDescription());	
		dBDetails.get().setRemark(req.getRemark());	
		dBDetails.get().setActive(req.isActive());		
//		dBDetails.get().setLastModifiedBy(req.getLastModifiedBy());
		dBDetails.get().setLastModifiedDateTime(req.getLastModifiedDateTime());
		return dBDetails;
	}
}
