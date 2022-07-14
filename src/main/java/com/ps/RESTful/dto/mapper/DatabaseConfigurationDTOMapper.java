package com.ps.RESTful.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.ps.RESTful.dto.response.DatabaseConfigurationResponseDTO;
import com.ps.dto.DatabaseConfigurationDTO;
import com.ps.entities.master.DatabseConfiguration;

@Component
public class DatabaseConfigurationDTOMapper implements AbstractDTOMapper<DatabaseConfigurationDTO,DatabaseConfigurationResponseDTO, DatabseConfiguration> {

	@Override
	public DatabseConfiguration dtoToEntity(DatabaseConfigurationDTO clientOrganizationsDTO) {
		
		if(clientOrganizationsDTO == null) return null;
		DatabseConfiguration clientOrganizations = new DatabseConfiguration();
		
		clientOrganizations.setName(clientOrganizationsDTO.getName());
		clientOrganizations.setMappedDatabaseName(clientOrganizationsDTO.getMappedDatabaseName());
		clientOrganizations.setHeadOfficeAddress(clientOrganizationsDTO.getHeadOfficeAddress());
		clientOrganizations.setCountry(clientOrganizationsDTO.getCountry());
		
		return clientOrganizations;
	}
	
	@Override
	public DatabaseConfigurationResponseDTO entityToDto(DatabseConfiguration clientOrganizations) {
		
		if(clientOrganizations == null) return null;
		
		DatabaseConfigurationResponseDTO clientOrganizationsResponseDTO = new DatabaseConfigurationResponseDTO();
		clientOrganizationsResponseDTO.setName(clientOrganizations.getName());
		clientOrganizationsResponseDTO.setMappedDatabaseName(clientOrganizations.getMappedDatabaseName());
		clientOrganizationsResponseDTO.setHeadOfficeAddress(clientOrganizations.getHeadOfficeAddress());
		clientOrganizationsResponseDTO.setCountry(clientOrganizations.getCountry());
		clientOrganizationsResponseDTO.setId(clientOrganizations.getId());
		
		return clientOrganizationsResponseDTO;
	}
	
	
	public List<DatabaseConfigurationResponseDTO> entityListToDtoList(List<DatabseConfiguration> clientOrganizationsList) {		
		
		if(CollectionUtils.isEmpty(clientOrganizationsList)) new ArrayList<DatabaseConfigurationResponseDTO>();
		
		List<DatabaseConfigurationResponseDTO> clientOrganizationsResponseDTOList = new ArrayList<DatabaseConfigurationResponseDTO>();

		for (DatabseConfiguration clientOrganization : clientOrganizationsList) {
			DatabaseConfigurationResponseDTO clientOrganizationsResponseDTO = entityToDto(clientOrganization);
			if(clientOrganizationsResponseDTO != null) clientOrganizationsResponseDTOList.add(clientOrganizationsResponseDTO);
		}
		
		return clientOrganizationsResponseDTOList;
	}
}
