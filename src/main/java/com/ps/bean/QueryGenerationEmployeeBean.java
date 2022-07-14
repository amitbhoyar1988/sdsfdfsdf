package com.ps.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.response.QueryGenerationEmployeeResponseDTO;
import com.ps.util.StringUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class QueryGenerationEmployeeBean {

	private int queryGenerationEmpId;	
	private int queryNumber;
	private Date submissionDate;
	private Date escalationDate;	
	private String createdBy;
	private String employeeCode;
	private int employeeMasterId;	
	private String empName;
	private boolean onBehalfOfEmployee;
	private int applicationModuleId;
	private String applicationModuleName;
	private int queryTypeMasterId;
	private String queryTypeCode;
	private int subQueTypeMasterId;
	private int queryIterationId;
	private int queAnsMasterId;
	private String subject;
	private String queryDescription;
	private String queryRootCause;
	private String priority;	
	private String status;
	private String companyName;
	private Date lastModifiedDateTime;
	private boolean isActive;
	
	public QueryGenerationEmployeeBean() {
		super();
		// TODO Auto-generated constructor stub
	}		
	
	public QueryGenerationEmployeeResponseDTO beanToDTO(QueryGenerationEmployeeBean beanDetails) {

		// if-Details-are-not-exist
		if (beanDetails == null)
			return new QueryGenerationEmployeeResponseDTO();

		QueryGenerationEmployeeResponseDTO responseDTO = new QueryGenerationEmployeeResponseDTO();

		responseDTO.setQueryGenerationEmpId(beanDetails.getQueryGenerationEmpId());
	    responseDTO.setQueryNumber(beanDetails.getQueryNumber());
	    responseDTO.setSubmissionDate(StringUtils.dateToString(beanDetails.getSubmissionDate()));
	    responseDTO.setEscalationDate(StringUtils.dateToString(beanDetails.getEscalationDate()));
	    responseDTO.setCreatedBy(beanDetails.getCreatedBy());
	    responseDTO.setEmployeeMasterId(beanDetails.getEmployeeMasterId());
	    responseDTO.setEmployeeCode(beanDetails.getEmployeeCode());
	    responseDTO.setEmpName(beanDetails.getEmpName());
	    responseDTO.setOnBehalfOfEmployee(beanDetails.isOnBehalfOfEmployee());
	    responseDTO.setApplicationModuleId(beanDetails.getApplicationModuleId());
	    responseDTO.setApplicationModuleName(beanDetails.getApplicationModuleName());
	    responseDTO.setQueryTypeMasterId(beanDetails.getQueryTypeMasterId());
	    responseDTO.setQueryTypeCode(beanDetails.getQueryTypeCode());
	    responseDTO.setSubQueTypeMasterId(beanDetails.getSubQueTypeMasterId());
	    responseDTO.setQueryIterationId(beanDetails.getQueryIterationId());
	    responseDTO.setQueAnsMasterId(beanDetails.getQueAnsMasterId());
	    responseDTO.setSubject(beanDetails.getSubject());
	    responseDTO.setQueryDescription(beanDetails.getQueryDescription());
	    responseDTO.setPriority(beanDetails.getPriority());
	    responseDTO.setQueryRootCause(beanDetails.getQueryRootCause());
	    responseDTO.setCreatedBy(beanDetails.getCreatedBy());
	    responseDTO.setCompanyName(beanDetails.getCompanyName());
	    responseDTO.setStatus(beanDetails.getStatus());
	    responseDTO.setActive(beanDetails.isActive());	
		return responseDTO;
	}
	
	 public List<QueryGenerationEmployeeResponseDTO> beanListToDtoList(List<QueryGenerationEmployeeBean> masterList) {

		    if (CollectionUtils.isEmpty(masterList))
		      return new ArrayList <QueryGenerationEmployeeResponseDTO>();

		    List<QueryGenerationEmployeeResponseDTO> responseDTOList = new ArrayList<QueryGenerationEmployeeResponseDTO>();

		    for (QueryGenerationEmployeeBean listValue : masterList) {
		    	QueryGenerationEmployeeResponseDTO responseDTO = beanToDTO(listValue);
		      if (responseDTO != null)
		        responseDTOList.add(responseDTO); 
		    }
		    return responseDTOList;		    
	  }
}
