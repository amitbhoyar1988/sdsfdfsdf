package com.ps.services.dao.repository.master;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ps.entities.master.QuestionAnswerMaster;
import com.ps.entities.master.SubQueryTypeMaster;

public interface QuestionAnswerMasterRepository extends AbstractRepository<QuestionAnswerMaster, Integer>{

	@Query("select ISNULL(Max(code),0) + 1 As code from QuestionAnswerMaster ")
	int findMaxCode();
	
//	@Query("select e from QuestionAnswerMaster e where e.code = ?1 and e.languageId = ?2 ")
//	Optional<QuestionAnswerMaster> findByCodeAndLanguage(int code, int languageId);
	
	@Query("select e from QuestionAnswerMaster e where e.code = ?1 and e.isActive = true")
	List<QuestionAnswerMaster> findAllActiveByCode(int code);
	
	@Modifying
	@Query("Update QuestionAnswerMaster a Set a.isActive = 0 , a.lastModifiedBy =  ?2 , a.lastModifiedDateTime = CURRENT_TIMESTAMP Where a.isActive = true And a.code = ?1 ")
	void setInActiveByCode(int code, String currUser);
	

	@Query("SELECT e FROM QueryTypeQueAnsMappingMaster e where e.queryTypeMaster.queryTypeMasterId = ?1 and e.isActive = true ")
	List<SubQueryTypeMaster> getListByQueryTypeId(int queryId);
}
