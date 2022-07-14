package com.ps.RESTful.dto.request;

import java.util.List;

import com.ps.dto.QueryTypeMasterDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryTypeMasterRequestDTONew extends QueryTypeMasterDTO{

	private List<QueryTypeQueAnsMappingMasterRequestDTO> listQueryAnsMappingReqDTO;
	
	private SubQueryTypeMasterRequestDTO subQueryRequestDTO;
	
	private List<QueryTypePriorityMasterRequestDTO> listQueryPriorityRequestDTO;

	public List<SubQueryTypeQueAnsMappingMasterRequestDTO>listSubQueryAnsMappingReqDTO;

}
