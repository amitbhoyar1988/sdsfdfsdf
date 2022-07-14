package com.ps.RESTful.resources.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.RESTful.dto.mapper.DatabaseConfigurationDTOMapper;
import com.ps.RESTful.dto.request.DatabaseConfigurationRequestDTO;
import com.ps.RESTful.dto.response.DatabaseConfigurationResponseDTO;
import com.ps.RESTful.resources.DatabaseConfigurationResource;
import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.entities.master.DatabseConfiguration;
import com.ps.services.DatabaseConfigurationService;

@RestController
@RequestMapping(path = DatabaseConfigurationResource.RESOURCE_PATH)
public class DatabaseConfigurationResourceImpl extends AbstractResourceImpl implements DatabaseConfigurationResource {

	Logger logger = Logger.getLogger(DatabaseConfigurationResourceImpl.class);
	
	@Autowired
	DatabaseConfigurationService databaseConfigurationService;
	
	@Autowired
	DatabaseConfigurationDTOMapper databaseConfigurationDTOMapper;

	
	@Override
	public ResponseEntity<Response> add(DatabaseConfigurationRequestDTO clientOrganizationsDTO) {

		if(logger.isDebugEnabled()) logger.debug("In add ClientOrganizations"
				+ "resource");
		if(logger.isDebugEnabled()) logger.debug("Building client organization for client "+clientOrganizationsDTO.getName());
		DatabseConfiguration clientOrganizations = databaseConfigurationDTOMapper.dtoToEntity(clientOrganizationsDTO);
		if(logger.isDebugEnabled()) logger.debug("Sending clientOrganization entity to service method for saving into db "+clientOrganizations.getName());
		databaseConfigurationService.add(clientOrganizations);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
				ResponseBuilder.builder().build());
	}

	@Override
	public ResponseEntity<Response> getAll() {
		
		if(logger.isDebugEnabled()) logger.debug("In getAll ClientOrganizations "
				+ "resource");				
		List<DatabseConfiguration> clientOrganizationsList = databaseConfigurationService.getAllClientOrganizations();
		List<DatabaseConfigurationResponseDTO> clientOrganizationsResponseDTO = databaseConfigurationDTOMapper.entityListToDtoList(clientOrganizationsList);
		
		return ResponseEntity.status(HttpStatus.OK).body(
				ResponseBuilder.builder().results(clientOrganizationsResponseDTO).build());
	}
}
