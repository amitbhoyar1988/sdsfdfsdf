package com.ps.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractDTO implements DTO{

	private String createdBy;
	private String createdDateTime;
	private String lastModifiedBy;
	private String lastModifiedDateTime;
	
	

}
