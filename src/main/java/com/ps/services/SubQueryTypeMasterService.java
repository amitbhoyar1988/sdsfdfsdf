package com.ps.services;

import java.util.List;

import com.ps.entities.master.SubQueryTypeMaster;

public interface SubQueryTypeMasterService {

	
	List<SubQueryTypeMaster> getAll();
	public SubQueryTypeMaster addSubQuery(SubQueryTypeMaster qAInfo);
	public List<SubQueryTypeMaster> getAllSubQuery();
	
}
