package com.ps.RESTful.dto.request;

import java.util.List;

import com.ps.dto.SubQueryTypeMasterDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SubQueryTypeMasterRequestDTO extends SubQueryTypeMasterDTO{

	private List<SubQueryTypeQueAnsMappingMasterRequestDTO> listSubQueryQueAnsMapping;
	
}
