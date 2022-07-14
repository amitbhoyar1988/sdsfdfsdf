package com.ps.services.dao.repository.tenant;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.ps.entities.tenant.SubQueryTypeMasterTenant;

public interface SubQueryTypeMasterTenantRepository extends AbstractRepository<SubQueryTypeMasterTenant, Integer>{

	@Query("select e from SubQueryTypeMasterTenant e where e.queryTypeMasterId = ?1 and e.subQueTypeMasterId = ?2 ")
	Optional<SubQueryTypeMasterTenant> findByQryTypeIdAndSubQryTypeId(int queryTypeMasterId, int subQueTypeMasterId);
	
}
