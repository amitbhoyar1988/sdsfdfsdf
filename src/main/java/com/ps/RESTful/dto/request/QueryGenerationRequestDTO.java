package com.ps.RESTful.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QueryGenerationRequestDTO {
	private List<QueryGenerationEmployeeRequestDTO> queryRequestDTO;
}
