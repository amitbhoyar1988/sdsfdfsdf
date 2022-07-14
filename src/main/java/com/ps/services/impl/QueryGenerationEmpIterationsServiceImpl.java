package com.ps.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.RESTful.dto.mapper.QueryGenerationEmpIterationsDTOMapper;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.dto.ErrorDTO;
import com.ps.entities.master.QuestionAnswerMaster;
import com.ps.entities.tenant.QueryGenerationEmpIterations;
import com.ps.entities.tenant.QueryGenerationEmployee;
import com.ps.services.QueryGenerationEmpIterationsService;
import com.ps.services.dao.repository.tenant.QueryGenerationEmpIterationsRepository;
import com.ps.util.MethodValidationUtils;
import com.ps.util.RequestUtils;
import com.ps.util.StringUtils;

@Service
public class QueryGenerationEmpIterationsServiceImpl implements QueryGenerationEmpIterationsService{

Logger logger = Logger.getLogger(QueryGenerationEmployeeServiceImpl.class);
	
	

	@Autowired
	QueryGenerationEmpIterationsRepository queryGenerationEmpIterationsRepository;

	@Autowired
	QueryGenerationEmpIterationsDTOMapper queryGenerationEmpIterationsDTOMapper;

	@Autowired
	RequestUtils requestUtils;
	
	@Override
	@Transactional("tenantTransactionManager")
	public QueryGenerationEmpIterations add(QueryGenerationEmpIterations details) {
		if (logger.isDebugEnabled()) {
			logger.debug("In Add QueryGenerationEmpIterations Details service method:  " + details);
		}
				
		details.setActive(true);
		details.setStatus("Submitted");
		details.setCreatedBy(requestUtils.getUserName());
		details.setRefNumber(queryGenerationEmpIterationsRepository.findMaxRefNumber(1));
		
		// Validating QuestionAnswerMaster data
		ErrorDTO error = validateQueryEmpIterations(details, "add");
		MethodValidationUtils.errorValidation(error);

		try {
			// SavingDetails_In_DB
			
			details = queryGenerationEmpIterationsRepository.save(details);
			
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Failed to save Query Generation Emp Iterations Details: " + details + ": getLocalizedMessage: "
						+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());
				throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
						"Failed to save Query Generation Emp Iterations Details");
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Query Generation Emp Iterations Details saved successfully:" + details);
		}

		return details;
	}
	

	// validating User Query Data fields
	private ErrorDTO validateQueryEmpIterations(QueryGenerationEmpIterations queryIterationData, String addOrUpdate) {

		if (queryIterationData.getQueryGenerationEmployee().getQueryGenerationEmpId()== 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("Invalid Query Generation Employee Id for Query Iteration: " + queryIterationData.getQueryGenerationEmployee().getQueryGenerationEmpId());
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Invalid Query Generation Employee Id for Query Iteration: " + queryIterationData.getQueryGenerationEmployee().getQueryGenerationEmpId());
		}		

		if (queryIterationData.getRefNumber() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("Invalid RefNumber: " + queryIterationData.getRefNumber());
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Invalid RefNumber: " + queryIterationData.getRefNumber());
		}
		
		if (!StringUtils.isValidString(queryIterationData.getQueryDescription())) {

			if (logger.isDebugEnabled()) {
				logger.debug("Invalid Query Description: " + queryIterationData.getQueryDescription());
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Invalid Query Description: " + queryIterationData.getQueryDescription());
		}

		return null;
	}
}
