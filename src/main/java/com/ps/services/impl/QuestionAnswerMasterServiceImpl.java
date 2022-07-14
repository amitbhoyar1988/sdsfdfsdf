package com.ps.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.RESTful.dto.mapper.QuestionAnswerMasterDTOMapper;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.error.handler.ResourceAlreadyExistException;
import com.ps.RESTful.error.handler.ResourceNotFoundException;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.dto.ErrorDTO;
import com.ps.entities.master.QuestionAnswerMaster;
import com.ps.services.QuestionAnswerMasterService;
import com.ps.services.dao.repository.master.QuestionAnswerMasterRepository;
import com.ps.util.MethodValidationUtils;
import com.ps.util.RequestUtils;
import com.ps.util.StringUtils;

@Service
public class QuestionAnswerMasterServiceImpl implements QuestionAnswerMasterService {
	Logger logger = Logger.getLogger(QuestionAnswerMasterServiceImpl.class);

	@Autowired
	QuestionAnswerMasterRepository questionAnswerMasterRepository;

	@Autowired
	QuestionAnswerMasterDTOMapper questionAnswerMasterDTOMapper;

	@Autowired
	RequestUtils requestUtils;

	@Override
	public List<QuestionAnswerMaster> getAll() {
		if (logger.isDebugEnabled())
			logger.debug("In Question Answer Master service getAll method");

		// fetch data from DB
		List<QuestionAnswerMaster> list = questionAnswerMasterRepository.findAll();
		return list;
	}

	@Override
	@Transactional("transactionManager")
	public List<QuestionAnswerMaster> add(QuestionAnswerMaster details) {
		if (logger.isDebugEnabled()) {
			logger.debug("In Add QuestionAnswerMaster Details service method:  " + details);
		}

		
				List<QuestionAnswerMaster> list = new ArrayList<>();				
		// Validating QuestionAnswerMaster data
		ErrorDTO error = validateQAMasterFields(details, "add");
		MethodValidationUtils.errorValidation(error);

		try {
			// SavingDetails_In_DB
			details.setCreatedBy(requestUtils.getUserName());
			details.setCode(questionAnswerMasterRepository.findMaxCode());
			details = questionAnswerMasterRepository.save(details);
			if (details.getQueAnsMasterId() != 0)
			{
				// fetch data from DB
				list = questionAnswerMasterRepository.findAll();
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Failed to save Question Answer Master Details: " + details + ": getLocalizedMessage: "
						+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());
				throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
						"Failed to save Question Answer Master Details");
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Question Answer Master Details saved successfully:" + details);
		}

		return list;
	}

	@Override
	@Transactional("transactionManager")
	public List<QuestionAnswerMaster> update(QuestionAnswerMaster details) {
		if (logger.isDebugEnabled()) {
			logger.debug("In update QuestionAnswerMaster Details service method:  " + details);
		}
		List<QuestionAnswerMaster> list = new ArrayList<>();				

		// Check_Id_is_Null
		MethodValidationUtils.checkIfIdIsZero(details.getQueAnsMasterId(), "QuestionAnswerMasterId");

		// Fetch_Existing_Details
		Optional<QuestionAnswerMaster> optionalDB = questionAnswerMasterRepository.findById(details.getQueAnsMasterId());

		if (!optionalDB.isPresent()) {
			logger.error("QuestionAnswerMaster Details not found for Id: " + details.getQueAnsMasterId());
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
					"QuestionAnswerMaster Details not found for Id: " + details.getQueAnsMasterId());
		}

		ErrorDTO error = validateQAMasterFields(details, "update");
		MethodValidationUtils.errorValidation(error);

		// ConvertingEntity
		optionalDB = questionAnswerMasterDTOMapper.entityToEntity(details, optionalDB);
		// Set_Existing_Details_to_New_Request
		optionalDB.get().setLastModifiedBy(requestUtils.getUserName());

		try {
			// SavingDetails_In_DB
			details = questionAnswerMasterRepository.save(optionalDB.get());
			if (details.getQueAnsMasterId() != 0)
			{
				// fetch data from DB
				list = questionAnswerMasterRepository.findAll();
			}

		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug("Failed to update QuestionAnswerMaster Details: " + details + ": getLocalizedMessage: "
						+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());

			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
					"Failed To Update QuestionAnswerMaster Details");
		}

		if (logger.isDebugEnabled())
			logger.debug(
					"In update service QuestionAnswerMaster Details Service Method, record updated for QuestionAnswerMasterID: "
							+ details.getQueAnsMasterId() + " with Details:" + details);

		return list;

	}

	@Override
	@Transactional("transactionManager")
	public QuestionAnswerMaster delete(int id) {

		if (logger.isDebugEnabled())
			logger.debug("In  QuestionAnswerMaster delete service method, deleting record with id-> " + id);

		// Check_Id_is_Null
		MethodValidationUtils.checkIfIdIsZero(id, "QuestionAnswerMaster ID");

		// Fetching_Existing_Details
		Optional<QuestionAnswerMaster> optionalGroup = questionAnswerMasterRepository.findById(id);

		if (!optionalGroup.isPresent()) {

			if (logger.isDebugEnabled())
				logger.debug("Provided QuestionAnswerMaster ID is not present - " + id);
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
					"QuestionAnswerMaster Id Record not found!");
		}

		// check if QuestionAnswerMaster is already deleted or not
		if (!optionalGroup.get().isActive()) {
			logger.error("Record already deleted for QuestionAnswerMaster Id: " + id);
			throw new ResourceAlreadyExistException(ErrorCode.ALREADY_DELETED,
					"Record Already Deleted for QuestionAnswerMaster Id:" + id);
		}

		// if-there-is-no-any-Active-mapping-exist-perform-soft-delete

		if (logger.isDebugEnabled())
			logger.debug("Performing Soft-Delete for Question Answer Master Id: " + id);

		QuestionAnswerMaster master = optionalGroup.get();

		try {
			master.setActive(false);
			master.setLastModifiedBy(requestUtils.getUserName());
			master = questionAnswerMasterRepository.save(master);

		} catch (Exception e) {

			if (logger.isDebugEnabled())
				logger.debug("Failed to Soft-Delete QuestionAnswerMaster: " + optionalGroup.get() + ": Exception: "
						+ e.getMessage());

			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
					"Failed to Delete QuestionAnswerMaster Details");
		}
		return master;
	}

	@Override
	@Transactional("transactionManager")
	public int deleteByCode(int code) {

		if (logger.isDebugEnabled())
			logger.debug("In  QuestionAnswerMaster delete service method, deleting record with code-> " + code);

		// Check_Id_is_Null
		MethodValidationUtils.checkIfIdIsZero(code, "QuestionAnswerMaster Code");

		// Fetching_Existing_Details
		List<QuestionAnswerMaster> listQA = questionAnswerMasterRepository.findAllActiveByCode(code);

		if (listQA.isEmpty()) {

			if (logger.isDebugEnabled())
				logger.debug("No Active records present for provided QuestionAnswerMaster Code => " + code);
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
					"No Active records present for provided QuestionAnswerMaster Code!");
		}

		if (logger.isDebugEnabled())
			logger.debug("Performing Soft-Delete for Question Answer Master Id: " + code);

		try {

			questionAnswerMasterRepository.setInActiveByCode(code, requestUtils.getUserName());

		} catch (Exception e) {

			if (logger.isDebugEnabled())
				logger.debug("Failed to Soft-Delete QuestionAnswerMaster for code : " + code + ": Exception: "
						+ e.getMessage());

			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
					"Failed to Delete QuestionAnswerMaster Details");
		}
		return code;
	}

	// validating Question Answer Master fields
	private ErrorDTO validateQAMasterFields(QuestionAnswerMaster data, String addOrUpdate) {

		// id validation
		if (data.getQueAnsMasterId() != 0 && addOrUpdate == "add") {

			if (logger.isDebugEnabled())
				logger.debug("Invalid QuestionAnswerMaster Id--> " + data.getQueAnsMasterId());
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER, "Invalid QuestionAnswerMaster Id--> " + data.getQueAnsMasterId());
		}

		// id validation
		if (data.getQueAnsMasterId() == 0 && addOrUpdate == "update") {

			if (logger.isDebugEnabled())
				logger.debug("Provide the QuestionAnswerMaster Id--> " + data.getQueAnsMasterId());
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER, "Provide the QuestionAnswerMaster Id--> " + data.getQueAnsMasterId());
		}

		// Description validation
		if (!StringUtils.isValidString(data.getDescription())) {

			if (logger.isDebugEnabled())
				logger.debug("Invalid Description ");
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER, "Invalid Description");
		}

		// Module id validation
		if (data.getModuleId() == 0) {

			if (logger.isDebugEnabled())
				logger.debug("Select the Module--> " + data.getModuleId());

			return new ErrorDTO(ErrorCode.INVALID_PARAMETER, "Select the Module--> " + data.getModuleId());
		}

		// Question Subject validation
		if (!StringUtils.isValidString(data.getQuestionSubject())) {

			if (logger.isDebugEnabled())
				logger.debug("Invalid Question Subject--> " + data.getQuestionSubject());

			return new ErrorDTO(ErrorCode.INVALID_PARAMETER, "Invalid QuestionSubject--> " + data.getQuestionSubject());
		}

		// Question Description validation
		if (!StringUtils.isValidString(data.getQuestionDescription())) {

			if (logger.isDebugEnabled())
				logger.debug("Invalid Question Description--> " + data.getQuestionDescription());

			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Invalid Question Description--> " + data.getQuestionDescription());
		}

		// Answer Subject validation
		if (!StringUtils.isValidString(data.getAnswerSubject())) {

			if (logger.isDebugEnabled())
				logger.debug("Invalid Answer Subject--> " + data.getAnswerSubject());

			return new ErrorDTO(ErrorCode.INVALID_PARAMETER, "Invalid Answer Subject--> " + data.getAnswerSubject());
		}

		// Answer Description validation
		if (!StringUtils.isValidString(data.getAnswerDescription())) {

			if (logger.isDebugEnabled())
				logger.debug("Invalid Answer Description--> " + data.getAnswerDescription());

			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Invalid Answer Description--> " + data.getAnswerDescription());
		}

		// Answer Description validation
		if (!data.isActive()) {
			if (!StringUtils.isValidString(data.getRemark())) {

				if (logger.isDebugEnabled())
					logger.debug("Remark is mandatory--> " + data.getRemark());

				return new ErrorDTO(ErrorCode.INVALID_PARAMETER, "Remark is mandatory--> " + data.getRemark());
			}
		}

		return null;
	}

}
