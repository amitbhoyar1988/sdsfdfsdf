package com.ps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
public class SubQueryTypeQueAnsMappingMasterDTO extends AbstractDTO{

	private int subQueryTypeQueAnsMappingId;
	private int subQueryTypeMasterId;
	private int queAnsMasterId;
	private boolean isActive;
	private String createdBy;
	private String lastModifiedBy;
	public SubQueryTypeQueAnsMappingMasterDTO() {
		super();
		
	}
	
	
}
