package com.ps.services;

import java.util.List;

import com.ps.entities.master.QuestionAnswerMaster;

public interface QuestionAnswerMasterService {
	public List<QuestionAnswerMaster> add(QuestionAnswerMaster QAMaster);	
	public List<QuestionAnswerMaster> update(QuestionAnswerMaster QAMaster);	
	public List<QuestionAnswerMaster> getAll();
	public QuestionAnswerMaster delete(int id);
	public int deleteByCode(int code);
}
