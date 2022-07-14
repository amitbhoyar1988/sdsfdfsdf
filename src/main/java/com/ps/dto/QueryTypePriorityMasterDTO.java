package com.ps.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
public class QueryTypePriorityMasterDTO extends AbstractDTO{

	private int queTypePriorityMasterId;		
	private int queryTypeMasterId;
	private String priorityType;
	private String resolutionTime;
	private String autoClose;
	private boolean defaultPriority;
	private String createdBy;
	private String lastModifiedBy;
	private boolean isActive;
	
	public QueryTypePriorityMasterDTO() {
		super();		
	}
}
