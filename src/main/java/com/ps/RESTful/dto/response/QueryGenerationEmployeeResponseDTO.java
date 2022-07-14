package com.ps.RESTful.dto.response;

import java.util.List;

import com.ps.dto.QueryGenerationEmployeeDTO;
import com.ps.entities.tenant.QueryDocumentInformation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryGenerationEmployeeResponseDTO extends QueryGenerationEmployeeDTO{
	private String employeeCode;
	private String empName;
	private String applicationModuleName;	
	private String companyName;
	private String queryTypeCode;
	private int queryIterationId;
	private List<QueryDocumentInformationResponseDTO> listDoc;
//	private String queryTypeCode;	 

}
