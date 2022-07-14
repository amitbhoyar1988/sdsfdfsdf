package com.ps.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ps.RESTful.dto.mapper.QueryGenerationEmpIterationsDTOMapper;
import com.ps.RESTful.dto.mapper.QueryGenerationEmployeeDTOMapper;
import com.ps.RESTful.dto.mapper.QueryTypePriorityMasterDTOMapper;
import com.ps.RESTful.dto.response.QueryTypePriorityMasterResponseDTO;
import com.ps.RESTful.dto.response.QueryTypePriorityResponseDTO;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.error.handler.ResourceAlreadyExistException;
import com.ps.RESTful.error.handler.ResourceNotFoundException;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.bean.QueryGenerationEmployeeBean;
import com.ps.dto.ErrorDTO;
import com.ps.dto.QueryTypePriorityMasterDTO;
import com.ps.entities.tenant.ApplicationModuleDetails;
import com.ps.entities.tenant.EmployeeMaster;
import com.ps.entities.tenant.QueryDocumentInformation;
import com.ps.entities.tenant.QueryGenerationEmpIterations;
import com.ps.entities.tenant.QueryGenerationEmployee;
import com.ps.entities.tenant.QueryTypeMasterTenant;
import com.ps.entities.tenant.QueryTypePriorityMasterTenant;
import com.ps.entities.tenant.QueryTypeQueAnsMappingMasterTenant;
import com.ps.entities.tenant.QuestionAnswerMasterTenant;
import com.ps.entities.tenant.SubQueryTypeMasterTenant;
import com.ps.entities.tenant.SubQueryTypeQueAnsMappingMasterTenant;
import com.ps.services.QueryGenerationEmpIterationsService;
import com.ps.services.QueryGenerationEmployeeService;
import com.ps.services.dao.repository.tenant.ApplicationModuleDetailsRepository;
import com.ps.services.dao.repository.tenant.EmployeeMasterRepository;
import com.ps.services.dao.repository.tenant.QueryDocumentInformationRepository;
import com.ps.services.dao.repository.tenant.QueryGenerationEmpIterationsRepository;
import com.ps.services.dao.repository.tenant.QueryGenerationEmployeeRepository;
import com.ps.services.dao.repository.tenant.QueryTypeMasterTenantRepository;
import com.ps.services.dao.repository.tenant.QueryTypePriorityMasterTenantRepository;
import com.ps.services.dao.repository.tenant.QueryTypeQueAnsMappingMasterTenantRepository;
import com.ps.services.dao.repository.tenant.QuestionAnswerMasterTenantRepository;
import com.ps.services.dao.repository.tenant.SubQueryTypeMasterTenantRepository;
import com.ps.services.dao.repository.tenant.SubQueryTypeQueAnsMappingMasterTenantRepository;
import com.ps.util.DateUtils;
import com.ps.util.MethodValidationUtils;
import com.ps.util.RequestUtils;
import com.ps.util.StringUtils;

@Service
public class QueryGenerationEmployeeServiceImpl implements QueryGenerationEmployeeService {

	Logger logger = Logger.getLogger(QueryGenerationEmployeeServiceImpl.class);

	@Value("${queryNumber.startIndex}")
	private int iQueryNumberStartIndex;

	@Autowired
	QueryGenerationEmployeeRepository queryGenerationEmployeeRepository;

	@Autowired
	QueryGenerationEmpIterationsRepository queryGenerationEmpIterationsRepository;

	@Autowired
	ApplicationModuleDetailsRepository applicationModuleDetailsRepository;

	@Autowired
	EmployeeMasterRepository employeeMasterRepository;

	@Autowired
	QuestionAnswerMasterTenantRepository questionAnswerMasterTenantRepository;

	@Autowired
	QueryDocumentInformationRepository queryDocumentInformationRepository;

	@Autowired
	QueryTypeMasterTenantRepository queryTypeMasterTenantRepository;

	@Autowired
	SubQueryTypeMasterTenantRepository subQueryTypeMasterTenantRepository;

	@Autowired
	QueryTypePriorityMasterTenantRepository queryTypePriorityMasterTenantRepository;

	@Autowired
	SubQueryTypeQueAnsMappingMasterTenantRepository subQueryTypeQueAnsMappingMasterTenantRepository;

	@Autowired
	QueryTypeQueAnsMappingMasterTenantRepository queryTypeQueAnsMappingMasterTenantRepository;

	@Autowired
	QueryGenerationEmployeeDTOMapper queryGenerationEmployeeDTOMapper;

	@Autowired
	QueryGenerationEmpIterationsDTOMapper queryGenerationEmpIterationsDTOMapper;

	@Autowired
	QueryTypePriorityMasterDTOMapper queryTypePriorityMasterDTOMapper;

	@Autowired
	QueryGenerationEmpIterationsService queryGenerationEmpIterationsService;

	@Autowired
	RequestUtils requestUtils;

	@Override
	public List<QueryGenerationEmployee> getAll() {
		if (logger.isDebugEnabled())
			logger.debug("In Query Generation Employee service getAll method");

		// fetch data from DB
		List<QueryGenerationEmployee> list = queryGenerationEmployeeRepository.findAll();
		return list;
	}

	@Override
	public List<QueryGenerationEmployeeBean> getAllSummary() {
		if (logger.isDebugEnabled())
			logger.debug("In Query Generation Employee service getAll method");

		// fetch data from DB
		List<QueryGenerationEmployeeBean> list = queryGenerationEmployeeRepository.findAllQueriesForSummary();
		return list;
	}

	@Override
	public QueryGenerationEmployeeBean getById(int queryGenerationEmployeeId) {

		if (logger.isDebugEnabled())
			logger.debug("In Get Query Generation Employee Details service method. find by Id: "
					+ queryGenerationEmployeeId);

		// check_Id_is_Not_Null/Zero
		MethodValidationUtils.checkIfIdIsZero(queryGenerationEmployeeId, "queryGenerationEmployeeId");

		// Fetch_Details_From_DB
		Optional<QueryGenerationEmployeeBean> detailsDB = queryGenerationEmployeeRepository
				.findByQueryId(queryGenerationEmployeeId);

		if (detailsDB.isPresent()) {
			return detailsDB.get();
		}
		return null;
	}

	@Override
	@Transactional("tenantTransactionManager")
	public QueryGenerationEmployee add(QueryGenerationEmployee details, int queAnsMasterId, String description,
			MultipartFile[] queryDocs) {
		if (logger.isDebugEnabled()) {
			logger.debug("In Add QueryGenerationEmployee Details service method:  " + details);
		}

		// if new record then add new entry or update the existing data
		if (details.getQueryGenerationEmpId() == 0) {
			if (logger.isDebugEnabled())
				logger.debug("adding new User Query data into database");

			// validating data while adding new records
			ErrorDTO error = validateQueryGenerationFields(details, "add");
			MethodValidationUtils.errorValidation(error);

			// validating Question Answer data while adding new records
			ErrorDTO errorQA = validateQuestionAnswerMaster(queAnsMasterId, details);
			MethodValidationUtils.errorValidation(errorQA);

			int hh, mm;
			Date dtSubmission = new Date();
			details.setSubmissionDate(dtSubmission);

			QueryTypePriorityResponseDTO resDTO = new QueryTypePriorityResponseDTO();
			resDTO = getQueryPriorityData(details.getQueryTypeMasterTenant().getQueryTypeMasterId());

			if (logger.isDebugEnabled())
				logger.debug("QueryTypePriority Info :" + resDTO + "for Query Type Id :- "
						+ details.getQueryTypeMasterTenant().getQueryTypeMasterId());

			hh = getQueryPriority_HHMM(resDTO, "HH", details.getPriority());
			mm = getQueryPriority_HHMM(resDTO, "MM", details.getPriority());

			if (logger.isDebugEnabled())
				logger.debug("HH : " + hh + "MM : " + mm);

			Date dtEscalation = DateUtils.addHHMMToDate(dtSubmission, hh, mm);
			details.setEscalationDate(dtEscalation);

			try {

				int iQueryNumber = queryGenerationEmployeeRepository.findMaxQueryNumber();
				if (iQueryNumber == 1) {
					iQueryNumber = iQueryNumberStartIndex + iQueryNumber;
				}

				details.setQueryNumber(iQueryNumber);
				details.setCreatedBy(requestUtils.getUserName());
				details.setActive(true);

				// saving data into DB
				if (logger.isDebugEnabled())
					logger.debug("Saving User Query list into DB");

				details = queryGenerationEmployeeRepository.save(details);

				// saving query iteration data
				if (details.getQueryGenerationEmpId() != 0) {
					QueryGenerationEmpIterations queryIterations = new QueryGenerationEmpIterations();
					queryIterations.setQueryGenerationEmployee(details);
					queryIterations.setQueryDescription(description);
					queryIterations.setQueAnsMasterId(queAnsMasterId);
//					queryGenerationEmpIterationsService.add(queryIterations);

//					saveQueryIerations(queryIterations);

					queryIterations.setActive(true);
					queryIterations.setStatus("Submitted");
					queryIterations.setCreatedBy(requestUtils.getUserName());
					queryIterations.setRefNumber(queryGenerationEmpIterationsRepository
							.findMaxRefNumber(queryIterations.getQueryGenerationEmployee().getQueryGenerationEmpId()));

					// Validating QuestionAnswerMaster data
					ErrorDTO errorItertion = validateQueryEmpIterations(queryIterations);
					MethodValidationUtils.errorValidation(errorItertion);

					// SavingDetails_In_DB
					queryIterations = queryGenerationEmpIterationsRepository.save(queryIterations);

					if (queryIterations.getQueryIterationId() != 0) {
						List<QueryDocumentInformation> listDoc = new ArrayList<>();
						List<QueryDocumentInformation> updatedListDoc = new ArrayList<>();
						listDoc = requestUtils.uploadDocument(queryDocs,
								details.getEmployeeMaster().getEmployeeMasterId());
						if (!listDoc.isEmpty()) {
							for (QueryDocumentInformation master : listDoc) {
								master.setQueryGenerationEmployee(details);
								master.setQueryIterationId(queryIterations.getQueryIterationId());
								master.setCreatedBy(requestUtils.getUserName());
								updatedListDoc.add(master);
							}
							updatedListDoc = queryDocumentInformationRepository.saveAll(updatedListDoc);
						}
					}
				}

			} catch (Exception e) {
				if (logger.isDebugEnabled())
					logger.debug("Failed to Save User Query list details: " + details + ": getLocalizedMessage: "
							+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());

				throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
						"Failed To Save User Query List details");
			}
		}

		if (logger.isDebugEnabled())
			logger.debug("User Query list details added/updated into database successfully: " + details);

		return details;
	}

	@Override
	@Transactional("tenantTransactionManager")
	public QueryGenerationEmployee update(QueryGenerationEmployee details, int queAnsMasterId, String description,
			MultipartFile[] queryDocs) {
		if (logger.isDebugEnabled()) {
			logger.debug("In Update QueryGenerationEmployee Details service method:  " + details);
		}

		// Check_Id_is_Null
		MethodValidationUtils.checkIfIdIsZero(details.getQueryGenerationEmpId(), "QueryGenerationEmployeeId");

		// Fetch_Existing_Details
		Optional<QueryGenerationEmployee> optionalDB = queryGenerationEmployeeRepository
				.findById(details.getQueryGenerationEmpId());

		if (!optionalDB.isPresent()) {
			logger.error("QueryGenerationEmployee Details not found for Id: " + details.getQueryGenerationEmpId());
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
					"QueryGenerationEmployee Details not found for Id: " + details.getQueryGenerationEmpId());
		}

		ErrorDTO error = validateQueryGenerationFields(details, "update");
		MethodValidationUtils.errorValidation(error);

		// validating Question Answer data while adding new records
		ErrorDTO errorQA = validateQuestionAnswerMaster(queAnsMasterId, details);
		MethodValidationUtils.errorValidation(errorQA);

		// ConvertingEntity
		optionalDB = queryGenerationEmployeeDTOMapper.entityToEntity(details, optionalDB);
		// Set_Existing_Details_to_New_Request
		optionalDB.get().setLastModifiedBy(requestUtils.getUserName());

		// start => calculate the escalation date
		int hh, mm;
		QueryTypePriorityResponseDTO resDTO = new QueryTypePriorityResponseDTO();
		resDTO = getQueryPriorityData(details.getQueryTypeMasterTenant().getQueryTypeMasterId());

		if (logger.isDebugEnabled())
			logger.debug("QueryTypePriority Info :" + resDTO + "for Query Type Id :- "
					+ details.getQueryTypeMasterTenant().getQueryTypeMasterId());

		hh = getQueryPriority_HHMM(resDTO, "HH", details.getPriority());
		mm = getQueryPriority_HHMM(resDTO, "MM", details.getPriority());

		if (logger.isDebugEnabled())
			logger.debug("HH : " + hh + " MM : " + mm);

		Date dtEscalation = DateUtils.addHHMMToDate(optionalDB.get().getSubmissionDate(), hh, mm);
		optionalDB.get().setEscalationDate(dtEscalation);
		// end => calculate the escalation date

		try {
			// Updating data into DB
			if (logger.isDebugEnabled())
				logger.debug("Updating User Query details into DB");

			details = queryGenerationEmployeeRepository.save(optionalDB.get());

			// updating query iteration data
			if (details.getQueryGenerationEmpId() != 0) {

				// saving query iteration data
				Optional<QueryGenerationEmpIterations> optionalIteration = queryGenerationEmpIterationsRepository
						.findFirstIterationByQueryId(details.getQueryGenerationEmpId());
				QueryGenerationEmpIterations queryIterations = new QueryGenerationEmpIterations();

				if (optionalIteration.isPresent() && optionalIteration.get().getQueryIterationId() != 0) {
					optionalIteration.get().setQueryDescription(description);
					optionalIteration.get().setQueAnsMasterId(queAnsMasterId);
					optionalIteration.get().setLastModifiedBy(requestUtils.getUserName());

					// Validating Query Iteration data
					ErrorDTO errorItertion = validateQueryEmpIterations(optionalIteration.get());
					MethodValidationUtils.errorValidation(errorItertion);

					// SavingDetails_In_DB
					queryIterations = queryGenerationEmpIterationsRepository.save(optionalIteration.get());
				}

				if (queryIterations.getQueryIterationId() != 0 && queryDocs.length != 0) {
					List<QueryDocumentInformation> listDoc = new ArrayList<>();
					List<QueryDocumentInformation> updatedListDoc = new ArrayList<>();
					listDoc = requestUtils.uploadDocument(queryDocs, details.getEmployeeMaster().getEmployeeMasterId());
					if (!listDoc.isEmpty()) {
						for (QueryDocumentInformation master : listDoc) {
							master.setQueryGenerationEmployee(details);
							master.setQueryIterationId(queryIterations.getQueryIterationId());
							master.setCreatedBy(requestUtils.getUserName());
							updatedListDoc.add(master);
						}
						queryDocumentInformationRepository
								.deleteByQueryIterationId(queryIterations.getQueryIterationId());
						updatedListDoc = queryDocumentInformationRepository.saveAll(updatedListDoc);
					}
				}
			}

		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug("Failed to Update User Query details: " + details + ": getLocalizedMessage: "
						+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());

			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR, "Failed To Update User Query details");
		}

		if (logger.isDebugEnabled())
			logger.debug("User Query list details added/updated into database successfully: " + details);

		return details;
	}

	@Override
	@Transactional("tenantTransactionManager")
	public QueryGenerationEmployee update(QueryGenerationEmployee details, int queAnsMasterId, String description) {
		if (logger.isDebugEnabled()) {
			logger.debug("In update QueryGenerationEmployee Details service method:  " + details);
		}

		// Check_Id_is_Null
		MethodValidationUtils.checkIfIdIsZero(details.getQueryGenerationEmpId(), "QueryGenerationEmployeeId");

		// Fetch_Existing_Details
		Optional<QueryGenerationEmployee> optionalDB = queryGenerationEmployeeRepository
				.findById(details.getQueryGenerationEmpId());

		if (!optionalDB.isPresent()) {
			logger.error("QueryGenerationEmployee Details not found for Id: " + details.getQueryGenerationEmpId());
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
					"QueryGenerationEmployee Details not found for Id: " + details.getQueryGenerationEmpId());
		}

		ErrorDTO error = validateQueryGenerationFields(details, "update");
		MethodValidationUtils.errorValidation(error);

		// validating Question Answer data while adding new records
		ErrorDTO errorQA = validateQuestionAnswerMaster(queAnsMasterId, details);
		MethodValidationUtils.errorValidation(errorQA);

		// ConvertingEntity
		optionalDB = queryGenerationEmployeeDTOMapper.entityToEntity(details, optionalDB);
		// Set_Existing_Details_to_New_Request
		optionalDB.get().setLastModifiedBy(requestUtils.getUserName());
		try {
			// SavingDetails_In_DB
			details = queryGenerationEmployeeRepository.save(optionalDB.get());
			// saving query iteration data
			Optional<QueryGenerationEmpIterations> optionalIteration = queryGenerationEmpIterationsRepository
					.findFirstIterationByQueryId(details.getQueryGenerationEmpId());

			if (details.getQueryGenerationEmpId() != 0 && optionalIteration.isPresent()) {
				QueryGenerationEmpIterations queryIterations = new QueryGenerationEmpIterations();
				optionalIteration.get().setQueryDescription(description);
				optionalIteration.get().setQueAnsMasterId(queAnsMasterId);
				optionalIteration.get().setLastModifiedBy(requestUtils.getUserName());

				// Validating Query Iteration data
				ErrorDTO errorItertion = validateQueryEmpIterations(optionalIteration.get());
				MethodValidationUtils.errorValidation(errorItertion);

				// SavingDetails_In_DB
				queryIterations = queryGenerationEmpIterationsRepository.save(optionalIteration.get());
			}

		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug("Failed to update QueryGenerationEmployee Details: " + details + ": getLocalizedMessage: "
						+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());

			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
					"Failed To Update QueryGenerationEmployee Details");
		}

		if (logger.isDebugEnabled())
			logger.debug(
					"In update service QueryGenerationEmployee Details Service Method, record updated for QueryGenerationEmployeeID: "
							+ details.getQueryGenerationEmpId() + " with Details:" + details);

		return details;

	}

	@Override
	@Transactional("tenantTransactionManager")
	public QueryGenerationEmployee delete(int id) {

		if (logger.isDebugEnabled())
			logger.debug("In  QueryGenerationEmployee delete service method, deleting record with id-> " + id);

		// Check_Id_is_Null
		MethodValidationUtils.checkIfIdIsZero(id, "QueryGenerationEmployee ID");

		// Fetching_Existing_Details
		Optional<QueryGenerationEmployee> optionalGroup = queryGenerationEmployeeRepository.findById(id);

		if (!optionalGroup.isPresent()) {
			if (logger.isDebugEnabled())
				logger.debug("QueryGenerationEmployee Id Record not found - " + id);
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
					"QueryGenerationEmployee Id Record not found!");
		}

		// check if QueryGenerationEmployee is already deleted or not
		if (!optionalGroup.get().isActive()) {
			logger.error("Record already deleted for QueryGenerationEmployee Id: " + id);
			throw new ResourceAlreadyExistException(ErrorCode.ALREADY_DELETED,
					"Record Already Deleted for QueryGenerationEmployee Id:" + id);
		}

		// check if QueryGenerationEmployee is already deleted or not
		if (!optionalGroup.get().getStatus().equalsIgnoreCase("Draft")
				&& !optionalGroup.get().getStatus().equalsIgnoreCase("Submitted")) {
			logger.error("You can not delete the query at this stage: " + optionalGroup.get().getStatus());
			throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER,
					"You can not delete the query at this stage:" + optionalGroup.get().getStatus());
		}

		// if-there-is-no-any-Active-mapping-exist-perform-soft-delete
		if (logger.isDebugEnabled())
			logger.debug("Performing Soft-Delete for Query Generation Employee Id: " + id);

		QueryGenerationEmployee master = optionalGroup.get();

		try {

			// deleting query documents first by Query Generation Employee ID
			queryDocumentInformationRepository.deleteByQueryId(id);

			// finding list of available Iterations for this Query to Delete
			List<QueryGenerationEmpIterations> listQueryIterations = new ArrayList<>();
			listQueryIterations = queryGenerationEmpIterationsRepository.getListByQueryId(id);
			if (!listQueryIterations.isEmpty()) {
				queryGenerationEmpIterationsRepository.deleteAll(listQueryIterations);
			}

			// deleting the Query
			queryGenerationEmployeeRepository.delete(master);

		} catch (Exception e) {

			if (logger.isDebugEnabled())
				logger.debug("Failed to Soft-Delete QueryGenerationEmployee: " + optionalGroup.get() + ": Exception: "
						+ e.getMessage());

			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
					"Failed to Delete QueryGenerationEmployee Details");
		}
		return master;
	}

	public QueryGenerationEmpIterations saveQueryIerations(QueryGenerationEmpIterations details) {
		if (logger.isDebugEnabled()) {
			logger.debug("In Add QueryGenerationEmpIterations Details service method:  " + details);
		}

		details.setActive(true);
		details.setStatus("Submitted");
		details.setCreatedBy(requestUtils.getUserName());
		details.setRefNumber(queryGenerationEmpIterationsRepository
				.findMaxRefNumber(details.getQueryGenerationEmployee().getQueryGenerationEmpId()));

		// Validating QuestionAnswerMaster data
		ErrorDTO error = validateQueryEmpIterations(details);
		MethodValidationUtils.errorValidation(error);

		try {
			// SavingDetails_In_DB
			details = queryGenerationEmpIterationsRepository.save(details);

		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Failed to save Query Generation Emp Iterations Details: " + details
						+ ": getLocalizedMessage: " + e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());
				throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
						"Failed to save Query Generation Emp Iterations Details");
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Query Generation Emp Iterations Details saved successfully:" + details);
		}

		return details;
	}

	@Override
	@Transactional("tenantTransactionManager")
	public List<QueryGenerationEmployee> add(List<QueryGenerationEmployee> list) {
		if (logger.isDebugEnabled()) {
			logger.debug("In Query Generation add Query method, saving User Query list: ");
		}

		return SaveQueryList(list);
	}

	private List<QueryGenerationEmployee> SaveQueryList(List<QueryGenerationEmployee> userQueryList) {

		if (logger.isDebugEnabled())
			logger.debug("In saveUserGroup method of UserGroup service class");

		// check the list is empty or not
		MethodValidationUtils.checkIfObjectListIsNotEmpty(userQueryList, QueryGenerationEmployee.class.getSimpleName());

		List<QueryGenerationEmployee> userQueryDBList = new ArrayList<>();

//		// Check CheckCompanyNameRepeatOrNot
//		int duplicateEmpId = CheckEmployeeIdRepeatOrNot(userQueryList);
//		if (duplicateEmpId != 0) {
//			if (logger.isDebugEnabled())
//				logger.debug("Same Employee Id found multiple times:" + duplicateEmpId);
//			throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER,
//					"Same Employee Id found multiple times:" + duplicateEmpId);
//		}

		// iterate the list
		int codeCounter = 0;
		for (int i = 0; i < userQueryList.size(); i++) {
			QueryGenerationEmployee queryMaster = new QueryGenerationEmployee();
			queryMaster = userQueryList.get(i);

//			if (userGroupMaster.getCompanyGroupMaster().getCompanyGroupMasterId() != 0 && userGroupMaster.getCompanyGroupMaster().getCompanyGroupMasterId() > 0) {
//				if (!IsCompanyGroupPresent(userGroupMaster.getCompanyGroupMaster().getCompanyGroupMasterId(), companyGroupMasterRepository)) {
//					if (logger.isDebugEnabled())
//						logger.debug("Company Group Id not exist:" + userGroupMaster.getCompanyGroupMaster().getCompanyGroupMasterId());
//					throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER, "Company Group Id not exist:" + userGroupMaster.getCompanyGroupMaster().getCompanyGroupMasterId());
//				}
//			}			

			// if new record then add new entry or update the existing data
			if (queryMaster.getQueryGenerationEmpId() == 0) {
				if (logger.isDebugEnabled())
					logger.debug("adding new User Group data into database");

				// validating data while adding new records
				ErrorDTO error = validateQueryGenerationFields(queryMaster, "add");
				MethodValidationUtils.errorValidation(error);

				try {

					Date dtSubmission = new Date();
					queryMaster.setSubmissionDate(dtSubmission);
					queryMaster.setEscalationDate(dtSubmission);

					int iQueryNumber = queryGenerationEmployeeRepository.findMaxQueryNumber();
					if (iQueryNumber == 1) {
						iQueryNumber = iQueryNumberStartIndex + iQueryNumber + codeCounter;
					} else {
						iQueryNumber = iQueryNumber + codeCounter;
					}

					queryMaster.setQueryNumber(iQueryNumber);
					queryMaster.setCreatedBy(requestUtils.getUserName());
					queryMaster.setActive(true);

					// saving data into DB
					if (logger.isDebugEnabled())
						logger.debug("Saving User Query list into DB");

					queryMaster = queryGenerationEmployeeRepository.save(queryMaster);

					if (queryMaster.getQueryGenerationEmpId() != 0) {
						QueryGenerationEmpIterations queryIterations = new QueryGenerationEmpIterations();
						queryIterations.setQueryGenerationEmployee(queryMaster);
//						queryIterations.setQueryDescription(queryMaster.getDescription());
						queryGenerationEmpIterationsService.add(queryIterations);
					}
//					userQueryDBList.add(queryMaster);
					codeCounter = codeCounter + 1;

				} catch (Exception e) {
					if (logger.isDebugEnabled())
						logger.debug(
								"Failed to Save User Query list details: " + userQueryDBList + ": getLocalizedMessage: "
										+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());

					throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
							"Failed To Save User Query List details");
				}
			}
		}

//		List<QueryGenerationEmployee> resultList = new ArrayList<>();

//		try {
//			// check the list is empty or not
////			MethodValidationUtils.checkIfObjectListIsNotEmpty(userQueryDBList,
////					QueryGenerationEmployee.class.getSimpleName());
//
//			// saving data into DB
//			if (logger.isDebugEnabled())
//				logger.debug("Saving User Query list into DB");
////			userQueryDBList = queryGenerationEmployeeRepository.saveAll(userQueryDBList);
//
//		} catch (Exception e) {
//			if (logger.isDebugEnabled())
//				logger.debug("Failed to Save User Query list details: " + userQueryDBList + ": getLocalizedMessage: "
//						+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());
//
//			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
//					"Failed To Save User Query List details");
//		}

		// check the list is empty or not
//		MethodValidationUtils.checkIfObjectListIsNotEmpty(userQueryDBList,
//				QueryGenerationEmployee.class.getSimpleName());
		if (logger.isDebugEnabled())
			logger.debug("User Query list details added/updated into database successfully: " + userQueryDBList);

		return userQueryDBList;
	}

//	// CheckCompanyGroupIdRepeatOrNot in request
//	private int CheckEmployeeIdRepeatOrNot(List<QueryGenerationEmployee> list) {
//		if (logger.isDebugEnabled())
//			logger.debug("In CheckEmployeeIdRepeatOrNot method");
//
//		int duplicateEmpId = 0;
//		Set<Integer> set = new HashSet<Integer>();
//		// iterate the list
//		for (int i = 0; i < list.size(); i++) {
//			QueryGenerationEmployee data = list.get(i);
//			if (data.getEmployeeMaster().getEmployeeMasterId() != 0) {
//				int empId = data.getEmployeeMaster().getEmployeeMasterId();
//				if (set.contains(empId)) {
//					return empId;
//				}
//				set.add(empId);
//			}
//		}
//		return duplicateEmpId;
//	}

	// validating User Query Data fields
	private ErrorDTO validateQueryGenerationFields(QueryGenerationEmployee userQuery, String addOrUpdate) {

		if (userQuery.getQueryGenerationEmpId() != 0 && addOrUpdate == "add") {

			if (logger.isDebugEnabled()) {
				logger.debug("Invalid QueryGenerationEmpId: " + userQuery.getQueryGenerationEmpId());
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Invalid QueryGenerationEmpId: " + userQuery.getQueryGenerationEmpId());
		}

		if (userQuery.getQueryGenerationEmpId() == 0 && addOrUpdate == "update") {

			if (logger.isDebugEnabled()) {
				logger.debug("Provide the QueryGenerationEmpId: " + userQuery.getQueryGenerationEmpId());
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Provide the QueryGenerationEmpId: " + userQuery.getQueryGenerationEmpId());
		}

		if (userQuery.getApplicationModuleDetails().getApplicationModuleId() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("Select the Module: " + userQuery.getApplicationModuleDetails().getApplicationModuleId());
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Select the Module: " + userQuery.getApplicationModuleDetails().getApplicationModuleId());
		}

		if (userQuery.getApplicationModuleDetails().getApplicationModuleId() != 0) {
			if (!IsApplicationModuleExist(userQuery.getApplicationModuleDetails().getApplicationModuleId(),
					applicationModuleDetailsRepository)) {
				if (logger.isDebugEnabled())
					logger.debug("Application Module Id not exist:"
							+ userQuery.getApplicationModuleDetails().getApplicationModuleId());
				throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER, "Application Module Id not exist:"
						+ userQuery.getApplicationModuleDetails().getApplicationModuleId());
			}
		}

		if (userQuery.getEmployeeMaster().getEmployeeMasterId() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("Provide the Employee Id: " + userQuery.getEmployeeMaster().getEmployeeMasterId());
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Provide the Employee Id: " + userQuery.getEmployeeMaster().getEmployeeMasterId());
		}

		if (userQuery.getEmployeeMaster().getEmployeeMasterId() != 0) {
			if (!IsEmployeeExist(userQuery.getEmployeeMaster().getEmployeeMasterId(), employeeMasterRepository)) {
				if (logger.isDebugEnabled())
					logger.debug("Employee Id not exist:" + userQuery.getEmployeeMaster().getEmployeeMasterId());
				throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER,
						"Employee Id not exist:" + userQuery.getEmployeeMaster().getEmployeeMasterId());
			}
		}

		if (userQuery.getQueryTypeMasterTenant().getQueryTypeMasterId() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("Select the Query Type: " + userQuery.getQueryTypeMasterTenant().getQueryTypeMasterId());
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Select the Query Type: " + userQuery.getQueryTypeMasterTenant().getQueryTypeMasterId());
		}

		if (userQuery.getQueryTypeMasterTenant().getQueryTypeMasterId() != 0) {
			Optional<QueryTypeMasterTenant> qryTypeDB = queryTypeMasterTenantRepository
					.findById(userQuery.getQueryTypeMasterTenant().getQueryTypeMasterId());
			if (!qryTypeDB.isPresent()) {
				if (logger.isDebugEnabled()) {
					logger.debug(
							"Query Type Is Invalid: " + userQuery.getQueryTypeMasterTenant().getQueryTypeMasterId());
				}
				return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
						"Query Type Is invalid: " + userQuery.getQueryTypeMasterTenant().getQueryTypeMasterId());
			}
		}

		if (userQuery.getSubQueTypeMasterId() != 0) {
			Optional<SubQueryTypeMasterTenant> subQryTypeDB = subQueryTypeMasterTenantRepository
					.findByQryTypeIdAndSubQryTypeId(userQuery.getQueryTypeMasterTenant().getQueryTypeMasterId(),
							userQuery.getSubQueTypeMasterId());
			if (!subQryTypeDB.isPresent()) {
				if (logger.isDebugEnabled()) {
					logger.debug("Sub Query Type Is Invalid: "
							+ userQuery.getQueryTypeMasterTenant().getQueryTypeMasterId());
				}
				return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
						"Sub Query Type Is invalid: " + userQuery.getQueryTypeMasterTenant().getQueryTypeMasterId());
			}
		}

		return null;
	}

	// validating User Query Data fields
	private ErrorDTO validateQuestionAnswerMaster(int queAnsId, QueryGenerationEmployee details) {

		if (queAnsId == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("Provide the Question Answer Id: " + queAnsId);
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER, "Provide the Question Answer Id: " + queAnsId);
		}

		if (queAnsId != 0) {
			if (!IsQueAnsIdExist(queAnsId, questionAnswerMasterTenantRepository)) {
				if (logger.isDebugEnabled())
					logger.debug("Question Answer Id not exist:" + queAnsId);
				throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER,
						"Question Answer Id not exist:" + queAnsId);
			}

			if (!IsQueAnsIdValidOrNot(queAnsId, details.getQueryTypeMasterTenant().getQueryTypeMasterId(),
					details.getSubQueTypeMasterId())) {
				if (logger.isDebugEnabled())
					logger.debug("Invalid Question Answer Id:" + queAnsId);
				throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER,
						"Invalid Question Answer Id:" + queAnsId);
			}

		}

		return null;
	}

	// validating User Query Data fields
	private ErrorDTO validateQueryEmpIterations(QueryGenerationEmpIterations queryIterationData) {

		if (queryIterationData.getQueryGenerationEmployee().getQueryGenerationEmpId() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("Invalid Query Generation Employee Id for Query Iteration: "
						+ queryIterationData.getQueryGenerationEmployee().getQueryGenerationEmpId());
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Invalid Query Generation Employee Id for Query Iteration: "
							+ queryIterationData.getQueryGenerationEmployee().getQueryGenerationEmpId());
		}

		if (queryIterationData.getRefNumber() == 0) {

			if (logger.isDebugEnabled()) {
				logger.debug("Invalid RefNumber: " + queryIterationData.getRefNumber());
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER, "Invalid RefNumber: " + queryIterationData.getRefNumber());
		}

		if (!StringUtils.isValidString(queryIterationData.getQueryDescription())) {

			if (logger.isDebugEnabled()) {
				logger.debug("Invalid Query Description: " + queryIterationData.getQueryDescription());
			}
			return new ErrorDTO(ErrorCode.INVALID_PARAMETER,
					"Invalid Query Description: " + queryIterationData.getQueryDescription());
		}

		return null;
	}

	// CheckApplicationModuleId exists OrNot
	private boolean IsApplicationModuleExist(int moduleId, ApplicationModuleDetailsRepository repo) {
		boolean result = false;
		if (logger.isDebugEnabled())
			logger.debug("In IsApplicationModulePresent method checking Application Module id is exist or not.");
		Optional<ApplicationModuleDetails> groupMasterDB = repo.findById(moduleId);
		if (groupMasterDB.isPresent()) {
			result = true;
		}
		return result;
	}

	// CheckEmployeeId exists OrNot
	private boolean IsEmployeeExist(int employeeId, EmployeeMasterRepository repo) {
		boolean result = false;
		if (logger.isDebugEnabled())
			logger.debug("In IsEmployeeExist method checking employee id is exist or not.");
		Optional<EmployeeMaster> groupMasterDB = repo.findById(employeeId);
		if (groupMasterDB.isPresent()) {
			result = true;
		}
		return result;
	}

	// CheckQueAnsId exists OrNot
	private boolean IsQueAnsIdExist(int quaAnsId, QuestionAnswerMasterTenantRepository repo) {
		boolean result = false;
		if (logger.isDebugEnabled())
			logger.debug("In IsQueAnsIdExist method checking question answer id is exist or not.");
		Optional<QuestionAnswerMasterTenant> groupMasterDB = repo.findById(quaAnsId);
		if (groupMasterDB.isPresent()) {
			result = true;
		}
		return result;
	}

	// QueAnsIdValidOrNot
	private boolean IsQueAnsIdValidOrNot(int queAnsId, int queryId, int subQueryId) {
		boolean result = false;
		if (logger.isDebugEnabled())
			logger.debug("In IsQueAnsIdExist method checking question answer id is exist or not.");

		List<SubQueryTypeQueAnsMappingMasterTenant> listSubQueryQA = new ArrayList<>();
		List<QueryTypeQueAnsMappingMasterTenant> listQueryQA = new ArrayList<>();

		if (subQueryId != 0) {
			listSubQueryQA = subQueryTypeQueAnsMappingMasterTenantRepository.findBySubQueryTypeAndQueAnsId(queAnsId,
					subQueryId);
			if (!listSubQueryQA.isEmpty()) {
				result = true;
			}
		} else {
			listQueryQA = queryTypeQueAnsMappingMasterTenantRepository.findByQueryTypeAndQueAnsId(queAnsId, queryId);
			if (!listQueryQA.isEmpty()) {
				result = true;
			}
		}
		return result;
	}

	private QueryTypePriorityResponseDTO getQueryPriorityData(int queryTypeMasterId) {
		QueryTypePriorityResponseDTO responseDTO = new QueryTypePriorityResponseDTO();
		List<QueryTypePriorityMasterResponseDTO> listDTO = new ArrayList<>();
		List<QueryTypePriorityMasterTenant> listPriority = new ArrayList<>();
		Optional<QueryTypeMasterTenant> optioanlPriority = queryTypeMasterTenantRepository
				.findByQueryTypeId(queryTypeMasterId);
		if (optioanlPriority.isPresent() && !optioanlPriority.get().isPriorityRequired()) {
			if (logger.isDebugEnabled())
				logger.debug("PriorityRequired: No");
			responseDTO.setAutoClose(optioanlPriority.get().getAutoCloseTimeforNopriority());
			responseDTO.setResolutionTime(optioanlPriority.get().getResolutionTimeForNopriority());
			responseDTO.setPriority(false);
		} else {
			if (logger.isDebugEnabled())
				logger.debug("PriorityRequired : Yes");
			listPriority = queryTypePriorityMasterTenantRepository.findByQueryTypeId(queryTypeMasterId);
			listDTO = queryTypePriorityMasterDTOMapper.entityListTenantToDtoList(listPriority);
			if (!listDTO.isEmpty()) {
				if (logger.isDebugEnabled())
					logger.debug("Priority Details : " + listDTO);
				responseDTO.setListPriority(listDTO);
				responseDTO.setPriority(true);
			}
		}
		responseDTO.setQueryTypeMasterId(queryTypeMasterId);
		return responseDTO;
	}

	private int getQueryPriority_HHMM(QueryTypePriorityResponseDTO resDTO, String strHHMM, String strPriority) {
		if (logger.isDebugEnabled())
			logger.debug("fetchig HH & MM details : strHHMM => " + strHHMM + " strPriority=> " + strPriority
					+ " resDTO =>" + resDTO);
		int iResult = 0;
		String strInput = "";
		String[] output = null;
		if (resDTO != null && StringUtils.isValidString(strHHMM)) {
			if (logger.isDebugEnabled())
				logger.debug("priority : No");
			if (!resDTO.isPriority()) {
				if (StringUtils.isValidString(strPriority)) {
					logger.error("Selected Priority Type is Invalid, Priority details not found for Query Type Id: "
							+ resDTO.getQueryTypeMasterId());
					throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER,
							"Selected Priority Type is Invalid, Priority details not found for Query Type Id: "
									+ resDTO.getQueryTypeMasterId());
				}
				strInput = resDTO.getResolutionTime();
				if (logger.isDebugEnabled())
					logger.debug("strInput : " + strInput);
				output = strInput.split(":");
			} else {
				if (logger.isDebugEnabled())
					logger.debug("priority : Yes");

				if (!StringUtils.isValidString(strPriority)) {
					logger.error("Select the Priority Type");
					throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER, "Selecte the Priority Type");
				}

				if (!resDTO.getListPriority().isEmpty()) {
					for (QueryTypePriorityMasterDTO master : resDTO.getListPriority()) {
						if (logger.isDebugEnabled())
							logger.debug("priority list found: Yes");
						if (logger.isDebugEnabled()) {
							logger.debug("priority list found: Yes");
							logger.debug("PriorityType : " + master.getPriorityType() + " strPriority :" + strPriority);
						}
						if (master.getPriorityType().equals(strPriority)) {
							if (logger.isDebugEnabled()) {
								logger.debug("priority found : " + master.getPriorityType());
								logger.debug("strInput : " + strInput);
							}
							strInput = master.getResolutionTime();
							output = strInput.split(":");
							break;
						}
					}
				}
			}

			if (output != null && output.length > 0) {
				if (strHHMM == "HH") {
					iResult = Integer.valueOf(output[0]);
				} else if (strHHMM == "MM") {
					iResult = Integer.valueOf(output[1]);
				}
			}
		}
		return iResult;
	}

}
