package com.ps.services.dao.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import com.ps.entities.master.SubQueryTypeQueAnsMappingMaster;

public interface SubQueryTypeQueAnsMappingMasterRepository extends AbstractRepository<SubQueryTypeQueAnsMappingMaster ,Integer>{

	//List<SubQueryTypeQueAnsMappingMaster> saveAll(List<SubQueryTypeQueAnsMappingMaster> list);

	@Query("SELECT e FROM SubQueryTypeQueAnsMappingMaster e where equeryTypeMasterId = ?1 and e.isActive = true ")
	List<SubQueryTypeQueAnsMappingMaster> getListByQueryTypeId(int queryId);
}
