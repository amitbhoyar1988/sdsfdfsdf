package com.ps.services.dao.repository.tenant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.ps.entities.tenant.SubQueryTypeQueAnsMappingMasterTenant;

public interface SubQueryTypeQueAnsMappingMasterTenantRepository extends AbstractRepository<SubQueryTypeQueAnsMappingMasterTenant, Integer>{

	@Query("SELECT e FROM SubQueryTypeQueAnsMappingMasterTenant e where e.queAnsMasterId =?1 and e.subQueryTypeMasterId = ?2 and e.isActive = true ")
	List<SubQueryTypeQueAnsMappingMasterTenant> findBySubQueryTypeAndQueAnsId(int queAnsMasterId, int subQueryTypeMasterId);
}
