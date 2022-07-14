package com.ps.RESTful.dto.response;

import java.util.List;

import com.ps.RESTful.dto.request.QueryTypePriorityMasterRequestDTO;
import com.ps.RESTful.dto.request.QueryTypeQueAnsMappingMasterRequestDTO;
import com.ps.RESTful.dto.request.SubQueryTypeMasterRequestDTO;
import com.ps.dto.QueryTypeMasterDTO;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QueryTypeMasterResponseDTO extends QueryTypeMasterDTO{

private List<QueryTypeQueAnsMappingMasterResponseDTO> listQueryAnsMappingResponseDTO;
	
	private List<SubQueryTypeMasterResponseDTO> subQueryResponseDTO;
	
	private List<QueryTypePriorityMasterResponseDTO> listQueryPriorityResponseDTO;

	
}
