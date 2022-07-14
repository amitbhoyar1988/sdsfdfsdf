package com.ps.dto;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ps.entities.tenant.KeywordTableMaster;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
public class QueryGenerationEmployeeDTO extends AbstractDTO{
	private int queryGenerationEmpId;	
	private int queryNumber;	
	private String submissionDate;
	private int employeeMasterId;	
	private boolean onBehalfOfEmployee;
	private int applicationModuleId;	
	private int queryTypeMasterId;	
	private int subQueTypeMasterId;
	private int queAnsMasterId;	
	private String priority;	
	private String escalationDate;
	private String subject;	
	private String queryDescription;
	private String queryRootCause;	
	private String status;		
	private boolean isActive;
	
	public QueryGenerationEmployeeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
