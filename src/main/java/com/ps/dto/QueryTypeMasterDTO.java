package com.ps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
public class QueryTypeMasterDTO {

private int queryTypeMasterId;
	
	private int applicationModuleId;
	private String queryTypeCode;
	private String queryTypedescription;
	//private int queAnsMasterId;
	private boolean subQuery;
	private boolean priorityRequired;
	
	private String autoCloseTimeforNopriority;
	private String resolutionTimeforNopriority;
	private int replyWorkflowId;
	private int forwardWorkFlowId;
	private String Remark;
	private String createdBy;
	private String lastModifiedBy;
	private boolean isActive;
	public QueryTypeMasterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
