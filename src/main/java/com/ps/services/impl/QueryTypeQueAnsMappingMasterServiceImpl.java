package com.ps.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.beans.QueryTypeQueAnsMappingMasterRequestBean;
import com.ps.entities.master.QueryTypeMaster;
import com.ps.entities.master.QueryTypeQueAnsMappingMaster;
import com.ps.services.QueryTypeQueAnsMappingMasterService;
import com.ps.services.dao.repository.master.QueryTypeMasterRepository;
import com.ps.services.dao.repository.master.QueryTypeQueAnsMappingMasterRepository;
import com.ps.util.MethodValidationUtils;
import com.ps.util.RequestUtils;

@Service
public class QueryTypeQueAnsMappingMasterServiceImpl implements QueryTypeQueAnsMappingMasterService {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	QueryTypeQueAnsMappingMasterRepository queryTypeQueAnsMappingMasterRepository;

	@Autowired
	QueryTypeMasterRepository queryTypeMasterRepository;
	
	@Override

	public List<QueryTypeQueAnsMappingMaster> addAll(List<QueryTypeQueAnsMappingMaster> list) {
		if (logger.isDebugEnabled()) {
			logger.debug(" addQueAnsMapping method, saving User Group list: " + list);
		}

		// add/update data into GlobalCompanyMaster table
		return saveQueryTypeQueAnsMappingMaster(list);
	}

	
	private List<QueryTypeQueAnsMappingMaster> saveQueryTypeQueAnsMappingMaster(
			List<QueryTypeQueAnsMappingMaster> queryTypeQueAnsMappingMasterList) {

		if (logger.isDebugEnabled()) {
			logger.debug("In saveQueryTypeQueAnsMappingMaster method of QueryTypeQueAnsMappingMaster service class");
			logger.debug("counter " + queryTypeQueAnsMappingMasterList);
			logger.debug("counter " + queryTypeQueAnsMappingMasterList.size());
		}
		
		List<QueryTypeQueAnsMappingMaster> list = new ArrayList<>();
	
			for (int i = 0; i < queryTypeQueAnsMappingMasterList.size(); i++) {
			

			if (logger.isDebugEnabled())
				logger.debug("counter " + i);
			  QueryTypeQueAnsMappingMaster master = new  QueryTypeQueAnsMappingMaster(); 
			  master = queryTypeQueAnsMappingMasterList.get(i);
			  list.add(master);
						  
		}
		
		List<QueryTypeQueAnsMappingMaster> resultList = new ArrayList<>();

		try {
			// check the list is empty or not
			MethodValidationUtils.checkIfObjectListIsNotEmpty(queryTypeQueAnsMappingMasterList,
					QueryTypeQueAnsMappingMaster.class.getSimpleName());

			// saving data into DB
			if (logger.isDebugEnabled())
				logger.debug("Saving QueryTypeQueAnsMappingMaster list into DB");

			resultList = queryTypeQueAnsMappingMasterRepository.saveAll(list);

			if (logger.isDebugEnabled())
				logger.debug("QueryTypeQueAnsMapping saved into database:" + queryTypeQueAnsMappingMasterList);

			return resultList;

		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug("Failed to Save Query Type Que Ans Mapping Master details: "
						+ queryTypeQueAnsMappingMasterList + ": getLocalizedMessage: " + e.getLocalizedMessage()
						+ "\n getMessage:" + e.getMessage());

			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
					"Failed To Save Query Type Que Ans Mapping Master Master details");
		}

		}
	// validation for QueryMasterId valid or not
		
		  private void validateQueryTypeQAMappingFields(List<QueryTypeQueAnsMappingMaster>queryTypeQueAnsMappinglist) 
		  { 
		  
		  for(QueryTypeQueAnsMappingMaster qaMaster : queryTypeQueAnsMappinglist) {
		  Integer queryTypeMasterId =qaMaster.getQueryTypeMaster().getQueryTypeMasterId();
		  Optional<QueryTypeMaster> queryTypeMaster=queryTypeMasterRepository.findById(queryTypeMasterId);
		  if(queryTypeMaster.isEmpty())
		  { 
			  throw new
		  InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,"Failed To Save Query Type MasterId Is Invalid "); 
		  }
		}
	}
		 
}
