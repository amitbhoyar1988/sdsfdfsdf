package com.ps.services;

import java.util.List;

import com.ps.entities.master.DatabseConfiguration;

public interface DatabaseConfigurationService {

	public void add(DatabseConfiguration clientOrganizations);
	public List<DatabseConfiguration> getAllClientOrganizations();
	public void deleteById(long id);

}
