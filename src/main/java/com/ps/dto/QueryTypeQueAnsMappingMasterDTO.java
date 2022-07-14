package com.ps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
public class QueryTypeQueAnsMappingMasterDTO extends AbstractDTO{

	private int queryTypeQueAnsMappingId;
	private int queryTypeMasterId;
	private int queAnsMasterId;
	private boolean isActive;
	private String createdBy;
	private String lastModifiedBy;
	
	public QueryTypeQueAnsMappingMasterDTO() {
		super();


	}
	
	
}
