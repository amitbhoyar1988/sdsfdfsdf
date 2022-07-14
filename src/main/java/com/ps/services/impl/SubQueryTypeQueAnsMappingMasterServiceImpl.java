package com.ps.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.entities.master.SubQueryTypeQueAnsMappingMaster;
import com.ps.services.SubQueryTypeQueAnsMappingMasterService;
import com.ps.services.dao.repository.master.SubQueryTypeQueAnsMappingMasterRepository;
import com.ps.util.MethodValidationUtils;



@Service
public class SubQueryTypeQueAnsMappingMasterServiceImpl implements SubQueryTypeQueAnsMappingMasterService {

	Logger logger = Logger.getLogger(getClass());

	@Autowired
	SubQueryTypeQueAnsMappingMasterRepository subQueryTypeQueAnsMappingMasterRepository;

	@Override

	public List<SubQueryTypeQueAnsMappingMaster> addAll(List<SubQueryTypeQueAnsMappingMaster> list) {
		if (logger.isDebugEnabled()) {
			logger.debug(" addQueAnsMapping method, saving User Group list: " + list);
		}

		// add/update data into GlobalCompanyMaster table
		return saveSubQueryTypeQueAnsMappingMaster(list);
	}
	private List<SubQueryTypeQueAnsMappingMaster> saveSubQueryTypeQueAnsMappingMaster(
			List<SubQueryTypeQueAnsMappingMaster> subQueryTypeQueAnsMappingMasterList) {

		if (logger.isDebugEnabled()) {
			logger.debug("In saveQueryTypeQueAnsMappingMaster method of QueryTypeQueAnsMappingMaster service class");
			logger.debug("counter " + subQueryTypeQueAnsMappingMasterList);
			logger.debug("counter " + subQueryTypeQueAnsMappingMasterList.size());
		}
		
		List<SubQueryTypeQueAnsMappingMaster> list = new ArrayList<>();
	
			for (int i = 0; i < subQueryTypeQueAnsMappingMasterList.size(); i++) {
			

			if (logger.isDebugEnabled())
				logger.debug("counter " + i);
			  SubQueryTypeQueAnsMappingMaster master = new  SubQueryTypeQueAnsMappingMaster(); 
			  master = subQueryTypeQueAnsMappingMasterList.get(i);
			  list.add(master);
						  
		}
			List<SubQueryTypeQueAnsMappingMaster> resultList = new ArrayList<>();

			try {
				// check the list is empty or not
				MethodValidationUtils.checkIfObjectListIsNotEmpty(subQueryTypeQueAnsMappingMasterList,
						SubQueryTypeQueAnsMappingMaster.class.getSimpleName());

				// saving data into DB
				if (logger.isDebugEnabled())
					logger.debug("Saving SubQueryTypeQueAnsMappingMaster list into DB");

				resultList = subQueryTypeQueAnsMappingMasterRepository.saveAll(list);

				if (logger.isDebugEnabled())
					logger.debug("QueryTypeQueAnsMapping saved into database:" + subQueryTypeQueAnsMappingMasterList);

				return resultList;

			} catch (Exception e) {
				if (logger.isDebugEnabled())
					logger.debug("Failed to Save Query Type Que Ans Mapping Master details: "
							+ subQueryTypeQueAnsMappingMasterList + ": getLocalizedMessage: " + e.getLocalizedMessage()
							+ "\n getMessage:" + e.getMessage());

				throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
						"Failed To Save Query Type Que Ans Mapping Master Master details");
			}

			}
}

