package com.ps.services.dao.repository.tenant;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ps.entities.tenant.QueryDocumentInformation;

public interface QueryDocumentInformationRepository extends AbstractRepository<QueryDocumentInformation, Integer>{
	
	@Modifying
	@Query("Delete FROM QueryDocumentInformation e where e.queryIterationId = ?1 ")
	void  deleteByQueryIterationId(int queryIterationId);
	
	@Modifying
	@Query("Delete FROM QueryDocumentInformation e where e.queryGenerationEmployee.queryGenerationEmpId = ?1 ")
	void  deleteByQueryId(int queryd);
	
	@Query("SELECT e FROM QueryDocumentInformation e where e.queryGenerationEmployee.queryGenerationEmpId = ?1 and e.queryIterationId = ?2 and e.isActive = true ")
	List<QueryDocumentInformation> findByQueryIdAndIterationId(int queryGenerationEmpId, int queryIterationId);
}
