package com.ps.services;

import java.util.List;

import com.ps.RESTful.dto.request.SubQueryTypeMasterRequestDTO;
import com.ps.RESTful.dto.response.QueryTypeMasterResponseDTO;
import com.ps.entities.master.QueryTypeMaster;
import com.ps.entities.master.QueryTypePriorityMaster;
import com.ps.entities.master.QueryTypeQueAnsMappingMaster;

public interface QueryTypeMasterService {

	public QueryTypeMaster add(QueryTypeMaster QueryType);

	List<QueryTypeMaster> getAll();

//  6/4/2021 Update Code Start
	public List<QueryTypeMaster> update(QueryTypeMaster master, List<QueryTypeQueAnsMappingMaster> queryQA,
			List<QueryTypePriorityMaster> priorityDetails, List<SubQueryTypeMasterRequestDTO> listsubQueryDetails);

	// 9/4/2021
	public List<QueryTypeMaster> addNew(QueryTypeMaster qAInfo, List<QueryTypeQueAnsMappingMaster> listQueryQAadd,
			List<QueryTypePriorityMaster> listPriorityadd, List<SubQueryTypeMasterRequestDTO> listsubQueryMasteradd);

	public QueryTypeMasterResponseDTO getAllByQueryTypeMasterId(int queryTypeMasterId);

	
}
