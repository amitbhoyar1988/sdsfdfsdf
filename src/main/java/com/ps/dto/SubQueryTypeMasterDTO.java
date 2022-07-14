package com.ps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
public class SubQueryTypeMasterDTO extends AbstractDTO{

    private int subQueTypeMasterId;
	private int queryTypeMasterId;
	private String subQueryTypeCode;
	private String subqueryTypedescription;
	//private int queAnsMasterId;
	private String Remark;
	private String createdBy;
	private String lastModifiedBy;
	private boolean isActive;
	public SubQueryTypeMasterDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
