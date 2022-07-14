package com.ps.services.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.RESTful.dto.mapper.SubQueryTypeMasterDTOMapper;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.dto.ErrorDTO;
import com.ps.entities.master.QueryTypePriorityMaster;
import com.ps.entities.master.SubQueryTypeMaster;
import com.ps.services.SubQueryTypeMasterService;
import com.ps.services.dao.repository.master.SubQueryTypeMasterRepository;
import com.ps.util.MethodValidationUtils;
import com.ps.util.RequestUtils;

@Service
public class SubQueryTypeMasterServiceImpl implements SubQueryTypeMasterService {

	Logger logger = Logger.getLogger(SubQueryTypeMasterServiceImpl.class);
	
	@Autowired
	SubQueryTypeMasterRepository subQueryTypeMasterRepository;
	
	@Autowired
	SubQueryTypeMasterDTOMapper subQueryTypeMasterDTOMapper;
	
	@Autowired
	RequestUtils requestUtils;
	
	@Override
	public List<SubQueryTypeMaster> getAll() {
		if (logger.isDebugEnabled())
			logger.debug("In Sub Query Type Master service getAll method");

		// fetch data from DB
		List<SubQueryTypeMaster> list = subQueryTypeMasterRepository.findAll();
		
		if (logger.isDebugEnabled())
			logger.debug("In Sub Query Type Master service getAll method" + list);
             
		return list;
	}
	
	@Override
	@Transactional("transactionManager")
	public SubQueryTypeMaster addSubQuery(SubQueryTypeMaster details) {
		if (logger.isDebugEnabled()) {
			logger.debug("In Add Sub Query Type Master Details service method:  " + details);
		}

		// Validating Sub Query Type Master data
		ErrorDTO error = validateQAMasterFields(details, "addSubQuery");
		MethodValidationUtils.errorValidation(error);

		try {
			// SavingDetails_In_DB
			details.setCreatedBy(requestUtils.getUserName());
			//details.setCode(queryTypePriorityMasterRepository.findMaxCode());
			details = subQueryTypeMasterRepository.save(details);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Failed to save Sub Query Type Master Details: " + details + ": getLocalizedMessage: "
						+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());
				throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
						"Failed to save Sub Query Type Master Details");
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Sub Query Type Master Details saved successfully:" + details);
		}

		return details;
	}

	private ErrorDTO validateQAMasterFields(SubQueryTypeMaster details, String string) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<SubQueryTypeMaster> getAllSubQuery() {
			if (logger.isDebugEnabled())
				logger.debug("In Query Type Priority Master service getAll method");

			// fetch data from DB
			List<SubQueryTypeMaster> list = subQueryTypeMasterRepository.findAll();
			return list;
		}

	

}
