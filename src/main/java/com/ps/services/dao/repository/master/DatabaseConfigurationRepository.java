package com.ps.services.dao.repository.master;

import com.ps.entities.master.DatabseConfiguration;

public interface DatabaseConfigurationRepository extends AbstractRepository<DatabseConfiguration, Long> {
	DatabseConfiguration findByName(String name);
}