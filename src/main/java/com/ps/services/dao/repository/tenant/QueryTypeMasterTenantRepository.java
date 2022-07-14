package com.ps.services.dao.repository.tenant;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.ps.entities.tenant.QueryTypeMasterTenant;

public interface QueryTypeMasterTenantRepository extends AbstractRepository<QueryTypeMasterTenant, Integer>{

	@Query("select p from QueryTypeMasterTenant p where p.queryTypeMasterId = ?1 and p.priorityRequired = false and p.isActive = true ")
	Optional<QueryTypeMasterTenant> findByQueryTypeId(int queryTypeMasterId);
}
