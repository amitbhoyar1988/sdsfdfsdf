package com.ps.services.dao.repository.master;

import java.util.List;
import java.util.Optional;

import com.ps.entities.master.QueryTypeMaster;

public interface QueryTypeMasterRepository extends AbstractRepository<QueryTypeMaster,Integer> {

	//12/04
	Optional <QueryTypeMaster> findAllByQueryTypeMasterId(int queryTypeMasterId);

}
