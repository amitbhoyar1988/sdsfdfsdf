package com.ps.RESTful.dto.response;

import com.ps.dto.StandardKeywordMasterDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardKeywordMasterResponseDTO extends StandardKeywordMasterDTO{
	private String tableName;
}
