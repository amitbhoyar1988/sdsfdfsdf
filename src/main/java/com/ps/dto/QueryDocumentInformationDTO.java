package com.ps.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
public class QueryDocumentInformationDTO extends AbstractDTO{

	private int queryDocumentId;	
	private int queryGenerationEmpId;	
	private int queryIterationId;
//	private int proofSubmissionId;	
	private String queryBlobURI;
	private String documentType;
	private String documentSubType;
	private int employeeMasterId;	
	private String fileName;	
	private String fileSize;
	private String fileType;
	private boolean isActive;
	
	public QueryDocumentInformationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
