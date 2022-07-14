package com.ps.services.dao.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ps.entities.master.QueryTypeQueAnsMappingMaster;

public interface QueryTypeQueAnsMappingMasterRepository extends AbstractRepository<QueryTypeQueAnsMappingMaster ,Integer>{

	@Query("SELECT e FROM QueryTypeQueAnsMappingMaster e where e.queryTypeMaster.queryTypeMasterId = ?1 and e.isActive = true ")
	List<QueryTypeQueAnsMappingMaster> getListByQueryTypeId(int queryId);
}
