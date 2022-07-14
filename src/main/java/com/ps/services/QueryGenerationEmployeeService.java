package com.ps.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ps.bean.QueryGenerationEmployeeBean;
import com.ps.entities.tenant.QueryGenerationEmployee;

public interface QueryGenerationEmployeeService {
	public List<QueryGenerationEmployee> add(List<QueryGenerationEmployee> QueryInfo);	
	public QueryGenerationEmployee add(QueryGenerationEmployee QueryInfo, int queAnsMasterId, String description, MultipartFile[] queryDocs);	
	public QueryGenerationEmployee update(QueryGenerationEmployee QueryInfo, int queAnsMasterId, String description);	
	public QueryGenerationEmployee update(QueryGenerationEmployee QueryInfo, int queAnsMasterId, String description, MultipartFile[] queryDocs);
	public QueryGenerationEmployee delete(int id);
	public List<QueryGenerationEmployee> getAll();	
	public List<QueryGenerationEmployeeBean> getAllSummary();	
	public QueryGenerationEmployeeBean getById(int queryGenerationEmployeeId);
//	public int deleteById(int code);
}
