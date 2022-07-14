package com.ps.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.jboss.logging.Logger;
import com.ps.RESTful.dto.mapper.QueryTypePriorityMasterDTOMapper;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.dto.ErrorDTO;
import com.ps.entities.master.QueryTypePriorityMaster;
import com.ps.entities.master.QueryTypeQueAnsMappingMaster;
import com.ps.services.QueryTypePriorityMasterService;
import com.ps.services.dao.repository.master.QueryTypePriorityMasterRepository;
import com.ps.util.MethodValidationUtils;
import com.ps.util.RequestUtils;


@Service
public class QueryTypePriorityMasterServiceImpl  implements QueryTypePriorityMasterService {
	Logger logger = Logger.getLogger(QueryTypePriorityMasterServiceImpl.class);

	@Autowired
	QueryTypePriorityMasterRepository queryTypePriorityMasterRepository;

	@Autowired
	QueryTypePriorityMasterDTOMapper queryTypePriorityMasterMapper;

	@Autowired
	RequestUtils requestUtils;

	
	@Override
	public List<QueryTypePriorityMaster> getAll() {
		if (logger.isDebugEnabled())
			logger.debug("In Query Type Priority Master service getAll method");

		// fetch data from DB
		List<QueryTypePriorityMaster> list = queryTypePriorityMasterRepository.findAll();
		return list;
	}

	@Override
	@Transactional("transactionManager")
	public QueryTypePriorityMaster add(QueryTypePriorityMaster details) {
		if (logger.isDebugEnabled()) {
			logger.debug("In Add QueryTypePriorityMaster Details service method:  " + details);
		}

		// Validating QuestionAnswerMaster data
		ErrorDTO error = validateQAMasterFields(details, "add");
		MethodValidationUtils.errorValidation(error);

		try {
			// SavingDetails_In_DB
			details.setCreatedBy(requestUtils.getUserName());
			//details.setCode(queryTypePriorityMasterRepository.findMaxCode());
			details = queryTypePriorityMasterRepository.save(details);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Failed to save Query Type Priority Master Details: " + details + ": getLocalizedMessage: "
						+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());
				throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
						"Failed to save Query Type Priority Master Details");
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Query Type Priority Master Details saved successfully:" + details);
		}

		return details;
	}
	
	@Override
	public List<QueryTypePriorityMaster> addAll(List<QueryTypePriorityMaster> list) {
		if (logger.isDebugEnabled()) {
			logger.debug(" addQueAnsMapping method, saving User Group list: " + list);
		}

		// add/update data into GlobalCompanyMaster table
		return saveQueryTypeQueAnsMappingMaster(list);
	}

	
	private List<QueryTypePriorityMaster> saveQueryTypeQueAnsMappingMaster(
			List<QueryTypePriorityMaster> queryTypePriorityList) {

		if (logger.isDebugEnabled()) {
			logger.debug("In saveQueryTypeQueAnsMappingMaster method of QueryTypeQueAnsMappingMaster service class");
			logger.debug("counter " + queryTypePriorityList);
			logger.debug("counter " + queryTypePriorityList.size());
		}
		
		List<QueryTypePriorityMaster> list = new ArrayList<>();
	
			for (int i = 0; i < queryTypePriorityList.size(); i++) {
			

			if (logger.isDebugEnabled())
				logger.debug("counter " + i);
			QueryTypePriorityMaster master = new  QueryTypePriorityMaster(); 
			  master = queryTypePriorityList.get(i);
			  master.setCreatedBy("test");
			  list.add(master);
						  
		}
		
		
		
		List<QueryTypePriorityMaster> resultList = new ArrayList<>();

		try {
			// check the list is empty or not
			MethodValidationUtils.checkIfObjectListIsNotEmpty(queryTypePriorityList,
					QueryTypePriorityMaster.class.getSimpleName());

			// saving data into DB
			if (logger.isDebugEnabled())
				logger.debug("Saving QueryTypePriorityMaster list into DB");

			resultList = queryTypePriorityMasterRepository.saveAll(list);

			if (logger.isDebugEnabled())
				logger.debug("QueryTypeQueAnsMapping saved into database:" + queryTypePriorityList);

			return resultList;

		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug("Failed to Save Query Type Que Ans Mapping Master details: "
						+ queryTypePriorityList + ": getLocalizedMessage: " + e.getLocalizedMessage()
						+ "\n getMessage:" + e.getMessage());

			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
					"Failed To Save Query Type Que Ans Mapping Master Master details");
		}

		}

	private ErrorDTO validateQAMasterFields(QueryTypePriorityMaster details, String string) {
		// TODO Auto-generated method stub
		return null;
	}



}
