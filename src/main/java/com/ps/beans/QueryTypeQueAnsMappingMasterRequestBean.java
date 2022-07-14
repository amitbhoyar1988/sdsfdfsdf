package com.ps.beans;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class QueryTypeQueAnsMappingMasterRequestBean {

	private List<QueryTypeQueAnsMappingMasterRequestBean> queryTypeQueAnsMappingId;
	private int queryTypeMasterId;
	private int queAnsMasterId;
	private boolean isActive;
	private String createdBy;
	private String lastModifiedBy;
	public QueryTypeQueAnsMappingMasterRequestBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
