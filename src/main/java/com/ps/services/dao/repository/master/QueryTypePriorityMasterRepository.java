package com.ps.services.dao.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.ps.entities.master.QueryTypePriorityMaster;
public interface QueryTypePriorityMasterRepository extends AbstractRepository<QueryTypePriorityMaster, Integer>{

	//Object findMaxCode();

	@Query("SELECT e FROM QueryTypePriorityMaster e where e.queryTypeMaster.queryTypeMasterId = ?1 and e.isActive = true ")
	List<QueryTypePriorityMaster> getListByQueryTypeId(int queryId);

}