package com.ps.RESTful.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString 
@AllArgsConstructor
public class QueryTypePriorityResponseDTO {
	private int queryTypeMasterId;
	private boolean isPriority;
	private String resolutionTime;
	private String autoClose;
	private List<QueryTypePriorityMasterResponseDTO> listPriority;
	
	public QueryTypePriorityResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
}
