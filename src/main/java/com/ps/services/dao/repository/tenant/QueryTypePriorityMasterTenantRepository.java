package com.ps.services.dao.repository.tenant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.ps.entities.tenant.QueryTypePriorityMasterTenant;

public interface QueryTypePriorityMasterTenantRepository extends AbstractRepository<QueryTypePriorityMasterTenant, Integer>{

	@Query("select p from QueryTypePriorityMasterTenant p where p.queryTypeMasterId = ?1 and p.isActive = true ")
	List<QueryTypePriorityMasterTenant> findByQueryTypeId(int queryTypeMasterId);
}
