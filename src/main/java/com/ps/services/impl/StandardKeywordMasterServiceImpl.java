package com.ps.services.impl;

import java.util.List;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ps.RESTful.dto.mapper.StandardKeywordMasterDTOMapper;
import com.ps.entities.master.QuestionAnswerMaster;
import com.ps.entities.tenant.StandardKeywordMaster;
import com.ps.services.StandardKeywordMasterService;
import com.ps.services.dao.repository.tenant.StandardKeywordMasterRepository;
import com.ps.util.RequestUtils;

@Service
public class StandardKeywordMasterServiceImpl implements StandardKeywordMasterService{

	Logger logger = Logger.getLogger(StandardKeywordMasterServiceImpl.class);

	@Autowired
	StandardKeywordMasterRepository standardKeywordMasterRepository;

	@Autowired
	StandardKeywordMasterDTOMapper standardKeywordMasterDTOMapper;

	@Autowired
	RequestUtils requestUtils;
	
	
	@Override
	public List<StandardKeywordMaster> getAll() {
		if (logger.isDebugEnabled())
			logger.debug("In Question Answer Master service getAll method");

		// fetch data from DB
		List<StandardKeywordMaster> list = standardKeywordMasterRepository.findAll();
		return list;
	}
}
