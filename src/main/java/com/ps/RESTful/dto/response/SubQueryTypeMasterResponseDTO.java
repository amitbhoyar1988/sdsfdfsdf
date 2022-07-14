package com.ps.RESTful.dto.response;

import java.util.List;

import com.ps.dto.SubQueryTypeMasterDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class SubQueryTypeMasterResponseDTO extends SubQueryTypeMasterDTO{

	private List<SubQueryTypeMasterResponseDTO> subQueryResponseDTO;
	
	

}
