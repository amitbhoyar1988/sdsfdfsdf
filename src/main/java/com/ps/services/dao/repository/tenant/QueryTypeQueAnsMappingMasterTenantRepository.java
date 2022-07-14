package com.ps.services.dao.repository.tenant;

import java.util.List;

import org.springframework.data.jpa.repository.Query;


import com.ps.entities.tenant.QueryTypeQueAnsMappingMasterTenant;
import com.ps.entities.tenant.SubQueryTypeQueAnsMappingMasterTenant;

public interface QueryTypeQueAnsMappingMasterTenantRepository extends AbstractRepository<QueryTypeQueAnsMappingMasterTenant, Integer>{
	
	@Query("SELECT e FROM QueryTypeQueAnsMappingMasterTenant e where e.queryTypeMasterId = ?1 and e.isActive = true ")
	List<QueryTypeQueAnsMappingMasterTenant> findByQueryTypeId(int queryId);
	
	@Query("SELECT e FROM QueryTypeQueAnsMappingMasterTenant e where e.queAnsMasterId =?1 and e.queryTypeMasterId = ?2 and e.isActive = true ")
	List<QueryTypeQueAnsMappingMasterTenant> findByQueryTypeAndQueAnsId(int queAnsMasterId, int queryTypeMasterId);
}
