package com.ps.services;

import java.util.List;

import com.ps.entities.master.QueryTypePriorityMaster;

public interface QueryTypePriorityMasterService {

	public QueryTypePriorityMaster add(QueryTypePriorityMaster master);
	public List<QueryTypePriorityMaster>getAll();
	
	public List<QueryTypePriorityMaster> addAll(List<QueryTypePriorityMaster> list);
	
	
}
