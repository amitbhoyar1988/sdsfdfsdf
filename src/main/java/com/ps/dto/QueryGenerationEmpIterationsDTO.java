package com.ps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
public class QueryGenerationEmpIterationsDTO extends AbstractDTO{

	private int queryIterationId;	
	private int queryGenerationEmpId;	
	private int refNumber;
	private int proofSubmissionId;	
	private String status;	
	private int queAnsMasterId;	
	private String queryDescription;
	private boolean isActive;
	
	public QueryGenerationEmpIterationsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
