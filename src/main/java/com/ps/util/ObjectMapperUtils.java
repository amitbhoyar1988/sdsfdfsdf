package com.ps.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.RESTful.dto.request.QueryGenerationEmployeeRequestDTO;
import com.ps.RESTful.dto.request.QueryGenerationRequestDTO;

public class ObjectMapperUtils {
	
	public static QueryGenerationRequestDTO stringToQueryGeneration(String requestDTOString) {

		QueryGenerationRequestDTO requestDTO = new QueryGenerationRequestDTO();
		try {
			System.out.println(requestDTOString);
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			requestDTO = objectMapper.readValue(requestDTOString, QueryGenerationRequestDTO.class);
			System.out.println(requestDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return requestDTO;
	}			
}
