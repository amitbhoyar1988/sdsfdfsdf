package com.ps.services.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.entities.master.DatabseConfiguration;
import com.ps.services.DatabaseConfigurationService;
import com.ps.services.dao.repository.master.DatabaseConfigurationRepository;

@Service
public class DatabaseConfigurationServiceImpl implements DatabaseConfigurationService {

	Logger logger = Logger.getLogger(DatabaseConfigurationServiceImpl.class);
	
	@Autowired
	DatabaseConfigurationRepository databseConfigurationRepository;
	
	@Override
	public void add(DatabseConfiguration clientOrganizations) {
		
		if(logger.isDebugEnabled()) logger.debug("In add ClientOrganizations "
				+ "service method adding ClientOrganizations details into databse "+clientOrganizations);
		
		if(clientOrganizations == null) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid request");
		
		databseConfigurationRepository.save(clientOrganizations);		
	}

	@Override
	public List<DatabseConfiguration> getAllClientOrganizations() {
		if(logger.isDebugEnabled()) logger.debug("In getAll client organizations "
				+ "service method retrieving all client organizations details from databse");
		
		return databseConfigurationRepository.findAll();
		
	}

	@Override
	public void deleteById(long id) {
		if(logger.isDebugEnabled()) logger.debug("In deleteById client organizations "
				+ "service method deleting record for Id "+id);
		
		if(id == 0) throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Invalid Client Organization Id! ");
		databseConfigurationRepository.deleteById(id);
	}


}
