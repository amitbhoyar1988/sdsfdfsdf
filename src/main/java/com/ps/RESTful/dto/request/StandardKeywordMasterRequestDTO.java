package com.ps.RESTful.dto.request;

import com.ps.dto.StandardKeywordMasterDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StandardKeywordMasterRequestDTO extends StandardKeywordMasterDTO{
	private int keywordTableMasterId;
}
