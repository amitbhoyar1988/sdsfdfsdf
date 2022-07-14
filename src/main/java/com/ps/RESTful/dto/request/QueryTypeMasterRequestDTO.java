package com.ps.RESTful.dto.request;

import java.util.List;

import com.ps.dto.QueryTypeMasterDTO;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class QueryTypeMasterRequestDTO extends QueryTypeMasterDTO{

	private List<QueryTypeQueAnsMappingMasterRequestDTO> listQueryAnsMappingReqDTO;
	
	private List<SubQueryTypeMasterRequestDTO> subQueryRequestDTO;
	
	private List<QueryTypePriorityMasterRequestDTO> listQueryPriorityRequestDTO;

	//public List<SubQueryTypeQueAnsMappingMasterRequestDTO>listSubQueryAnsMappingReqDTO;
	
    //public List<QueryTypeMasterRequestDTO>listQueryTypeMasterRequestDTO;
}
