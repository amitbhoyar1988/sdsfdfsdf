package com.ps.services.dao.repository.tenant;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.ps.bean.QueryGenerationEmployeeBean;
import com.ps.entities.tenant.QueryGenerationEmployee;

public interface QueryGenerationEmployeeRepository extends AbstractRepository<QueryGenerationEmployee, Integer>{

	@Query("select ISNULL(Max(queryNumber),0) + 1 As queryNumber from QueryGenerationEmployee ")
	int findMaxQueryNumber();	
	
	@Query("select  new com.ps.bean.QueryGenerationEmployeeBean (q.queryGenerationEmpId, q.queryNumber, q.submissionDate, q.escalationDate, q.createdBy, e.employeeCode , e.employeeMasterId, \r\n"
			+ " e.firstName + ' ' + e.lastName as empName, q.onBehalfOfEmployee, q.applicationModuleDetails.applicationModuleId, m.applicationModuleName, q.queryTypeMasterTenant.queryTypeMasterId, \r\n"
			+ " q.queryTypeMasterTenant.queryTypeCode, q.subQueTypeMasterId, i.queryIterationId, i.queAnsMasterId, q.subject, i.queryDescription, q.queryRootCause, q.priority, q.status , "
			+ " e.groupCompany.companyName,  q.lastModifiedDateTime, q.isActive) "
			+ " from QueryGenerationEmployee q, EmployeeMaster e, ApplicationModuleDetails m, QueryGenerationEmpIterations i, GroupCompany g  \r\n"
			+ " where q.employeeMaster.employeeMasterId = e.employeeMasterId And q.applicationModuleDetails.applicationModuleId =  m.applicationModuleId \r\n"
			+ " And q.queryGenerationEmpId = i.queryGenerationEmployee.queryGenerationEmpId "
			+ " And e.groupCompany.groupCompanyId = g.groupCompanyId And i.refNumber = 1 And q.isActive = 1")
	List<QueryGenerationEmployeeBean> findAllQueriesForSummary();
	
	@Query("select  new com.ps.bean.QueryGenerationEmployeeBean (q.queryGenerationEmpId, q.queryNumber, q.submissionDate, q.escalationDate, q.createdBy, e.employeeCode , e.employeeMasterId, \r\n"
			+ " e.firstName + ' ' + e.lastName as empName, q.onBehalfOfEmployee, q.applicationModuleDetails.applicationModuleId, m.applicationModuleName, q.queryTypeMasterTenant.queryTypeMasterId, \r\n"
			+ " q.queryTypeMasterTenant.queryTypeCode, q.subQueTypeMasterId, i.queryIterationId, i.queAnsMasterId, q.subject, i.queryDescription, q.queryRootCause, q.priority, q.status , "
			+ " e.groupCompany.companyName, q.lastModifiedDateTime, q.isActive) "
			+ " from QueryGenerationEmployee q, EmployeeMaster e, ApplicationModuleDetails m, QueryGenerationEmpIterations i, GroupCompany g  \r\n"
			+ " where q.employeeMaster.employeeMasterId = e.employeeMasterId And q.applicationModuleDetails.applicationModuleId =  m.applicationModuleId \r\n"
			+ " And q.queryGenerationEmpId = i.queryGenerationEmployee.queryGenerationEmpId And q.isActive = 1 "
			+ " And e.groupCompany.groupCompanyId = g.groupCompanyId And i.refNumber = 1 And q.queryGenerationEmpId = ?1 ")
	Optional<QueryGenerationEmployeeBean> findByQueryId(int queryGenerationEmpId);
}
