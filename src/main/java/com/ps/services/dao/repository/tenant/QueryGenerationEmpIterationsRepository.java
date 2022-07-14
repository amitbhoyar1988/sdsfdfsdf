package com.ps.services.dao.repository.tenant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.ps.entities.tenant.QueryGenerationEmpIterations;

public interface QueryGenerationEmpIterationsRepository extends AbstractRepository<QueryGenerationEmpIterations, Integer>{

	@Query("select ISNULL(Max(refNumber),0) + 1 As refNumber from QueryGenerationEmpIterations where queryGenerationEmpId = ?1 ")
	int findMaxRefNumber(int queryGenerationEmpId);
	
	@Query("SELECT e FROM QueryGenerationEmpIterations e where e.queryGenerationEmployee.queryGenerationEmpId = ?1 and e.isActive = true ")
	List<QueryGenerationEmpIterations> getListByQueryId(int queryId);
	
//	@Query("select e from QueryGenerationEmpIterations Where e.queryGenerationEmployee.queryGenerationEmpId = ?1 "
//			+ " And e.queryIterationId = (select Min(queryIterationId) from QueryGenerationEmpIterations Where queryGenerationEmpId = ?1 And isActive = 1) ")
//	Optional<QueryGenerationEmpIterations> findByQueryId(int queryId);
	
//	@Query("select e from QueryGenerationEmpIterations Where e.queryGenerationEmployee.queryGenerationEmpId = ?1 "
//			+ " And e.refNumber = 1 e.isActive = true) ")
//	Optional<QueryGenerationEmpIterations> findByQueryId(int queryId);
	
	@Query("SELECT e FROM QueryGenerationEmpIterations e where e.queryGenerationEmployee.queryGenerationEmpId = ?1 and e.refNumber = 1 and e.isActive = true ")
	Optional<QueryGenerationEmpIterations> findFirstIterationByQueryId(int queryGenerationEmpId);
}
