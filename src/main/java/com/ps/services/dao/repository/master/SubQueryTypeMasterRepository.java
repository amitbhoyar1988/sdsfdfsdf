package com.ps.services.dao.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import com.ps.entities.master.SubQueryTypeMaster;

public interface SubQueryTypeMasterRepository extends AbstractRepository <SubQueryTypeMaster,Integer>{

	@Query("SELECT e FROM SubQueryTypeMaster e where e.queryTypeMaster.queryTypeMasterId = ?1 and e.isActive = true ")
	List<SubQueryTypeMaster> getListByQueryTypeId(int queryId);
}
