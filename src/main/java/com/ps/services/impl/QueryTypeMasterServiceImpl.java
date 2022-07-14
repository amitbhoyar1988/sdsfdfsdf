package com.ps.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ps.RESTful.dto.mapper.QueryTypeMasterDTOMapper;
import com.ps.RESTful.dto.mapper.QueryTypePriorityMasterDTOMapper;
import com.ps.RESTful.dto.mapper.QueryTypeQueAnsMappingMasterDTOMapper;
import com.ps.RESTful.dto.mapper.SubQueryTypeMasterDTOMapper;
import com.ps.RESTful.dto.mapper.SubQueryTypeQueAnsMappingMasterDTOMapper;
import com.ps.RESTful.dto.request.SubQueryTypeMasterRequestDTO;
import com.ps.RESTful.dto.response.QueryTypeMasterResponseDTO;
import com.ps.RESTful.dto.response.QueryTypePriorityMasterResponseDTO;
import com.ps.RESTful.dto.response.QueryTypeQueAnsMappingMasterResponseDTO;
import com.ps.RESTful.dto.response.SubQueryTypeMasterResponseDTO;
import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.error.handler.ResourceNotFoundException;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.entities.master.QueryTypeMaster;
import com.ps.entities.master.QueryTypePriorityMaster;
import com.ps.entities.master.QueryTypeQueAnsMappingMaster;
import com.ps.entities.master.SubQueryTypeMaster;
import com.ps.entities.master.SubQueryTypeQueAnsMappingMaster;
import com.ps.services.QueryTypeMasterService;
import com.ps.services.dao.repository.master.QueryTypeMasterRepository;
import com.ps.services.dao.repository.master.QueryTypePriorityMasterRepository;
import com.ps.services.dao.repository.master.QueryTypeQueAnsMappingMasterRepository;
import com.ps.services.dao.repository.master.SubQueryTypeMasterRepository;
import com.ps.services.dao.repository.master.SubQueryTypeQueAnsMappingMasterRepository;
import com.ps.util.MethodValidationUtils;
import com.ps.util.RequestUtils;
import com.ps.util.StringUtils;

@Service
public class QueryTypeMasterServiceImpl implements QueryTypeMasterService {

	Logger logger = Logger.getLogger(QueryTypeMasterServiceImpl.class);

	@Autowired
	QueryTypeMasterRepository queryTypeMasterRepository;

	@Autowired
	QueryTypeQueAnsMappingMasterRepository queryTypeQueAnsMappingMasterRepository;

	@Autowired
	QueryTypePriorityMasterRepository queryTypePriorityMasterRepository;

	@Autowired
	SubQueryTypeMasterRepository subQueryTypeMasterRepository;

	@Autowired
	SubQueryTypeQueAnsMappingMasterRepository subQueryTypeQueAnsMappingMasterRepository;

	@Autowired
	SubQueryTypeQueAnsMappingMasterDTOMapper subQueryTypeQueAnsMappingMasterDTOMapper;

	@Autowired
	QueryTypeMasterDTOMapper queryTypeMasterMapper;

	@Autowired
	SubQueryTypeMasterDTOMapper subQueryTypeMasterDTOMapper;
	
	@Autowired
	QueryTypePriorityMasterDTOMapper queryTypePriorityMasterDTOMapper;

	@Autowired
	SubQueryTypeMasterDTOMapper subQueryTypeMasterMapper;

	@Autowired
	QueryTypeQueAnsMappingMasterDTOMapper queryTypeQueAnsMappingMasterDTOMapper;

	@Autowired
	RequestUtils requestUtils;

	@Override
	public List<QueryTypeMaster> getAll() {
		if (logger.isDebugEnabled())
			logger.debug("In Query Type Master service getAll method");

		// fetch data from DB
		List<QueryTypeMaster> list = queryTypeMasterRepository.findAll();
		return list;
	}

	@Override
	@Transactional("transactionManager")
	public QueryTypeMaster add(QueryTypeMaster details) {
		if (logger.isDebugEnabled()) {
			logger.debug("In Add Query Type Master Details service method:  " + details);
		}

		// Validating QueryType Master data
		validateQAMasterFields(details, "add");
		// MethodValidationUtils.errorValidation(error);

		try {
			// SavingDetails_In_DB
			details.setCreatedBy(requestUtils.getUserName());
			// details.setCode(queryTypePriorityMasterRepository.findMaxCode());
			details = queryTypeMasterRepository.save(details);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Failed to save Query Type Master Details: " + details + ": getLocalizedMessage: "
						+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());
				throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
						"Failed to save Query Type Master Details");
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Query Type Master Details saved successfully:" + details);
		}

		return details;
	}

	private void validateQAMasterFields(QueryTypeMaster queryTypeMaster, String addOrUpdate) {
		// TODO Auto-generated method stub

		if (addOrUpdate == "add" && queryTypeMaster.getQueryTypeMasterId() != 0) {
			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR, " Query Type MasterId Is Invalid");
		}

		if (addOrUpdate == "update" && queryTypeMaster.getQueryTypeMasterId() == 0) {
			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
					" Please Provide the Query Type MasterId");
		}
	}

	private void validateSubQAMasterFields(SubQueryTypeMaster subQueryTypeMaster, String addOrUpdate) {
		// TODO Auto-generated method stub

		if (addOrUpdate == "add" && subQueryTypeMaster.getSubQueTypeMasterId() != 0) {
			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR, "Sub Query Type MasterId Is Invalid");
		}

		if (addOrUpdate == "update" && subQueryTypeMaster.getSubQueTypeMasterId() == 0) {
			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
					" Please Provide the Sub Query Type MasterId");
		}

	}

	// 6/4/2021 update code started

	@Override
	@Transactional("transactionManager")
	public List<QueryTypeMaster> update(QueryTypeMaster details, List<QueryTypeQueAnsMappingMaster> listQueryQA,
			List<QueryTypePriorityMaster> listPriorityDetails, List<SubQueryTypeMasterRequestDTO> listSubQueryDetails) {
		if (logger.isDebugEnabled()) {
			logger.debug("In update QueryTypeMaster Details service method:  " + details);
		}
		List<QueryTypeMaster> list = new ArrayList<>();

		// Check_Id_is_Null
		MethodValidationUtils.checkIfIdIsZero(details.getQueryTypeMasterId(), "QueryTypeMasterId");

		if (logger.isDebugEnabled()) {
			logger.debug("before condn:  " + details);
		}
		// Fetch_Existing_Details
		Optional<QueryTypeMaster> optionalDB = queryTypeMasterRepository.findById(details.getQueryTypeMasterId());

		if (logger.isDebugEnabled()) {
			logger.debug("after condn:  " + details);
		}

		if (!optionalDB.isPresent()) {
			logger.error("QueryTypeMaster Details not found for Id: " + details.getQueryTypeMasterId());
			throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
					"QueryTypeMasterId Details not found for Id: " + details.getQueryTypeMasterId());
		}

		optionalDB = queryTypeMasterMapper.entityToEntity(details, optionalDB);
		try {
			// SavingDetails_In_DB
			details = queryTypeMasterRepository.save(optionalDB.get());
			if (details.getQueryTypeMasterId() != 0) {

				if (!details.isSubQuery()) {
					// Check-Object list-is-not-null
					MethodValidationUtils.checkIfObjectListIsNotEmpty(listQueryQA,
							QueryTypeQueAnsMappingMaster.class.getSimpleName());

					List<QueryTypeQueAnsMappingMaster> listQAMapping = new ArrayList<>();
					for (QueryTypeQueAnsMappingMaster master : listQueryQA) {
						// master.setQueryTypeMasterId(details.getQueryTypeMasterId());
						QueryTypeMaster query = new QueryTypeMaster();
						query.setQueryTypeMasterId(details.getQueryTypeMasterId());
						master.setQueryTypeMaster(query);
						listQAMapping.add(master);
					}

					listQAMapping = queryTypeQueAnsMappingMasterRepository.saveAll(listQAMapping);

					// start => deleting SubQuery Type QueAns data when subQuery is NO
					List<SubQueryTypeQueAnsMappingMaster> listSubQueryTypeQA = new ArrayList<>();
					listSubQueryTypeQA = subQueryTypeQueAnsMappingMasterRepository.getListByQueryTypeId(details.getQueryTypeMasterId());
					if (!listSubQueryTypeQA.isEmpty())
					{

						subQueryTypeQueAnsMappingMasterRepository.deleteAll(listSubQueryTypeQA);
					}
					// end => deleting SubQuery Type QueAns data when subQuery is NO

				} else {

					if (logger.isDebugEnabled()) {
						logger.debug("Saving Sub Query Data: ");
					}

					for (int i = 0; i < listSubQueryDetails.size(); i++) {
						SubQueryTypeMasterRequestDTO subQueryMasterDto = new SubQueryTypeMasterRequestDTO();
						subQueryMasterDto = listSubQueryDetails.get(i);
						SubQueryTypeMaster subQueryMaster = new SubQueryTypeMaster();
						subQueryMaster = subQueryTypeMasterDTOMapper.dtoToEntity(subQueryMasterDto);
						// subQueryMaster.setQueryTypeMasterId(details.getQueryTypeMasterId());
						QueryTypeMaster query = new QueryTypeMaster();
						query.setQueryTypeMasterId(details.getQueryTypeMasterId());
						subQueryMaster.setQueryTypeMaster(query);

						List<SubQueryTypeQueAnsMappingMaster> listSubQAMapping = new ArrayList<>();
						listSubQAMapping = subQueryTypeQueAnsMappingMasterDTOMapper
								.dtoListToEntityList(listSubQueryDetails.get(i).getListSubQueryQueAnsMapping());

						if (subQueryMaster.getSubQueTypeMasterId() == 0) {
							if (logger.isDebugEnabled()) {
								logger.debug("In Add Sub Query Type Master Details service method:  " + subQueryMaster);
							}

							// Validating subQueryDetails Master data
							validateSubQAMasterFields(subQueryMaster, "add");

							// SavingDetails_In_DB
							subQueryMaster.setCreatedBy(requestUtils.getUserName());
							// details.setCode(queryTypePriorityMasterRepository.findMaxCode());
							subQueryMaster = subQueryTypeMasterRepository.save(subQueryMaster);
						} else {

							if (logger.isDebugEnabled()) {
								logger.debug("Updating Sub Query Type Master Details " + subQueryMaster);
							}

							// Check_Id_is_Null
							MethodValidationUtils.checkIfIdIsZero(subQueryMaster.getSubQueTypeMasterId(),
									"Sub QueryTypeMasterId");

							// Fetch_Existing_Details
							Optional<SubQueryTypeMaster> optionalSubDB = subQueryTypeMasterRepository
									.findById(subQueryMaster.getSubQueTypeMasterId());

							if (logger.isDebugEnabled()) {
								logger.debug("In Query MasterID:  " + optionalSubDB.get().getSubQueTypeMasterId());
							}

							if (!optionalSubDB.isPresent()) {
								logger.error("SubQueryTypeMaster Details not found for Id: "
										+ details.getQueryTypeMasterId());
								throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
										"SubQueryTypeMasterId Details not found for Id: "
												+ details.getQueryTypeMasterId());
							}

							optionalSubDB = subQueryTypeMasterMapper.entityToEntity(subQueryMaster, optionalSubDB);

							// Validating subQueryDetails Master data
							validateSubQAMasterFields(subQueryMaster, "update");

							// SavingDetails_In_DB
							optionalSubDB.get().setCreatedBy(requestUtils.getUserName());
							// details.setCode(queryTypePriorityMasterRepository.findMaxCode());
							subQueryMaster = subQueryTypeMasterRepository.save(optionalSubDB.get());
						}

						// Sub Query QA

						// start => deleting Query Type QueAns data when subQuery is yes
						List<QueryTypeQueAnsMappingMaster> listQueryTypeQA = new ArrayList<>();
						listQueryTypeQA = queryTypeQueAnsMappingMasterRepository
								.getListByQueryTypeId(details.getQueryTypeMasterId());
						if (!listQueryTypeQA.isEmpty()) {

							queryTypeQueAnsMappingMasterRepository.deleteAll(listQueryTypeQA);
						}
						// end => deleting Query Type QueAns data when subQuery is yes

						if (logger.isDebugEnabled()) {
							logger.debug("Saving Sub Query Question  Answer Details " + listSubQAMapping);

							// Check-Object list-is-not-null
							MethodValidationUtils.checkIfObjectListIsNotEmpty(listSubQAMapping,
									SubQueryTypeQueAnsMappingMaster.class.getSimpleName());

							List<SubQueryTypeQueAnsMappingMaster> updatedlistSubQAMapping = new ArrayList<>();
							for (SubQueryTypeQueAnsMappingMaster master : listSubQAMapping) {
								master.setSubQueryTypeMasterId(subQueryMaster.getSubQueTypeMasterId());
								master.setCreatedBy(requestUtils.getUserName());
								updatedlistSubQAMapping.add(master);

							}

							updatedlistSubQAMapping = subQueryTypeQueAnsMappingMasterRepository
									.saveAll(updatedlistSubQAMapping);
						}
					}
				}

			}

			// saving priority master data
			if (details.isPriorityRequired()) {
				// Check-Object list-is-not-null
				MethodValidationUtils.checkIfObjectListIsNotEmpty(listPriorityDetails,
						QueryTypePriorityMaster.class.getSimpleName());

				List<QueryTypePriorityMaster> listPriority = new ArrayList<>();
				for (QueryTypePriorityMaster pMaster : listPriorityDetails) {
					// pMaster.setQueryTypeMasterId(details.getQueryTypeMasterId());
					pMaster.setCreatedDateTime(details.getCreatedDateTime());
					pMaster.setCreatedBy(requestUtils.getUserName());

					QueryTypeMaster query = new QueryTypeMaster();
					query.setQueryTypeMasterId(details.getQueryTypeMasterId());
					pMaster.setQueryTypeMaster(query);
					listPriority.add(pMaster);
				}

				listPriority = queryTypePriorityMasterRepository.saveAll(listPriority);

			} else {
				// start => deleting QueryTypePriorityMaster data when subQuery is NO
				List<QueryTypePriorityMaster> lisQueryTypePM = new ArrayList<>();
				lisQueryTypePM = queryTypePriorityMasterRepository.getListByQueryTypeId(details.getQueryTypeMasterId());
				if (!lisQueryTypePM.isEmpty()) {

					queryTypePriorityMasterRepository.deleteAll(lisQueryTypePM);
				}
				// end => deleting QueryTypePriorityMasters data when subQuery is NO

			}

			// fetch data from DB
			list = queryTypeMasterRepository.findAll();

			// is sub query is yes update
			// optionalDB = subQueryTypeMasterMapper.entityToEntity(details, optionalDB);

		} catch (Exception e) {
			if (logger.isDebugEnabled())
				logger.debug("Failed to update QueryTypeMaster Details: " + details + ": getLocalizedMessage: "
						+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());

			throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
					"Failed To Update QueryTypeMaster Details");
		}

		if (logger.isDebugEnabled())
			logger.debug(
					"In update service QueryTypeMaster Details Service Method, record updated for QueryTypeMasterID: "
							+ details.getQueryTypeMasterId() + " with Details:" + details);

		return list;

	}

	@Override
	@Transactional("transactionManager")
	public List<QueryTypeMaster> addNew(QueryTypeMaster QAInfo, List<QueryTypeQueAnsMappingMaster> listQueryQAadd,
			List<QueryTypePriorityMaster> listPriorityDetails, List<SubQueryTypeMasterRequestDTO> listSubQueryDetails) {
		if (logger.isDebugEnabled()) {
			logger.debug("In Add Query Type Master Details service method:  " + QAInfo);
		}
		// Declaration
		List<QueryTypeMaster> list = new ArrayList<>();

		// Validating QueryType Master data
		validateQAMasterFields(QAInfo, "add");
		// MethodValidationUtils.errorValidation(error);

		try {
			// SavingDetails_In_DB
			QAInfo.setCreatedBy(requestUtils.getUserName());
			// details.setCode(queryTypePriorityMasterRepository.findMaxCode());
			QAInfo = queryTypeMasterRepository.save(QAInfo);

			if (!QAInfo.isSubQuery()) {
				// Check-Object list-is-not-null
				MethodValidationUtils.checkIfObjectListIsNotEmpty(listQueryQAadd,
						QueryTypeQueAnsMappingMaster.class.getSimpleName());
				List<QueryTypeQueAnsMappingMaster> listQAMapping = new ArrayList<>();
				for (QueryTypeQueAnsMappingMaster master : listQueryQAadd) {

					// master.setQueryTypeMasterId(QAInfo.getQueryTypeMasterId());
					QueryTypeMaster query = new QueryTypeMaster();
					query.setQueryTypeMasterId(QAInfo.getQueryTypeMasterId());
					master.setQueryTypeMaster(query);
					listQAMapping.add(master);
				}

				listQAMapping = queryTypeQueAnsMappingMasterRepository.saveAll(listQAMapping);
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("Saving Sub Query Data: ");
				}
				for (int i = 0; i < listSubQueryDetails.size(); i++) {
					SubQueryTypeMasterRequestDTO subQueryMasterDto = new SubQueryTypeMasterRequestDTO();
					subQueryMasterDto = listSubQueryDetails.get(i);
					SubQueryTypeMaster subQueryMasterAdd = new SubQueryTypeMaster();
					subQueryMasterAdd = subQueryTypeMasterDTOMapper.dtoToEntity(subQueryMasterDto);
					// subQueryMasterAdd.setQueryTypeMasterId(QAInfo.getQueryTypeMasterId());
					QueryTypeMaster query = new QueryTypeMaster();
					query.setQueryTypeMasterId(QAInfo.getQueryTypeMasterId());
					subQueryMasterAdd.setQueryTypeMaster(query);
					if (logger.isDebugEnabled()) {
						logger.debug("Saving Sub Query Data: "+subQueryMasterAdd);
					}
					List<SubQueryTypeQueAnsMappingMaster> listSubQAMapping = new ArrayList<>();
					listSubQAMapping = subQueryTypeQueAnsMappingMasterDTOMapper
							.dtoListToEntityList(listSubQueryDetails.get(i).getListSubQueryQueAnsMapping());

					if (subQueryMasterAdd.getSubQueTypeMasterId() == 0) {
						if (logger.isDebugEnabled()) {
							logger.debug("In Add Query Type Master Details service method:  " + QAInfo);
						}

//						subQueryMasterAdd.setSubQueTypeMasterId(QAInfo.getQueryTypeMasterId());

						// Validating subQueryDetails Master data
						validateSubQAMasterFields(subQueryMasterAdd, "add");

						// SavingDetails_In_DB
						subQueryMasterAdd.setCreatedBy(requestUtils.getUserName());
						// details.setCode(queryTypePriorityMasterRepository.findMaxCode());
						subQueryMasterAdd = subQueryTypeMasterRepository.save(subQueryMasterAdd);
					} else {

						if (logger.isDebugEnabled()) {
							logger.debug("Updating Query Type Master Details " + subQueryMasterAdd);
						}

						// Check_Id_is_Null
						MethodValidationUtils.checkIfIdIsZero(subQueryMasterAdd.getSubQueTypeMasterId(),
								"SubQueryTypeMasterId");

						// Fetch_Existing_Details
						Optional<SubQueryTypeMaster> optionalSubDB = subQueryTypeMasterRepository
								.findById(subQueryMasterAdd.getSubQueTypeMasterId());

						if (logger.isDebugEnabled()) {
							logger.debug("In Query MasterID:  " + optionalSubDB.get().getSubQueTypeMasterId());
						}

						if (!optionalSubDB.isPresent()) {
							logger.error(
									"SubQueryTypeMaster Details not found for Id: " + QAInfo.getQueryTypeMasterId());
							throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND,
									"SubQueryTypeMasterId Details not found for Id: " + QAInfo.getQueryTypeMasterId());
						}

						optionalSubDB = subQueryTypeMasterMapper.entityToEntity(subQueryMasterAdd, optionalSubDB);

						// Validating subQueryDetails Master data
						validateSubQAMasterFields(subQueryMasterAdd, "Update");

						// SavingDetails_In_DB
						optionalSubDB.get().setCreatedBy(requestUtils.getUserName());
						// details.setCode(queryTypePriorityMasterRepository.findMaxCode());
						subQueryMasterAdd = subQueryTypeMasterRepository.save(optionalSubDB.get());
					}

					// Sub Query QA
					if (logger.isDebugEnabled()) {
						logger.debug("Saving Sub Query Question  Answer Details " + subQueryMasterAdd);

						// Check-Object list-is-not-null
						MethodValidationUtils.checkIfObjectListIsNotEmpty(listSubQueryDetails,
								SubQueryTypeQueAnsMappingMaster.class.getSimpleName());

						List<SubQueryTypeQueAnsMappingMaster> newlistSubQAMapping = new ArrayList<>();
						for (SubQueryTypeQueAnsMappingMaster master : listSubQAMapping) {
							master.setSubQueryTypeMasterId(subQueryMasterAdd.getSubQueTypeMasterId());
							master.setCreatedBy(requestUtils.getUserName());
							newlistSubQAMapping.add(master);
						}

						newlistSubQAMapping = subQueryTypeQueAnsMappingMasterRepository.saveAll(newlistSubQAMapping);
					}
				}
			}

			// saving priority master data
			if (QAInfo.isPriorityRequired()) {
				// Check-Object list-is-not-null
				MethodValidationUtils.checkIfObjectListIsNotEmpty(listPriorityDetails,
						QueryTypePriorityMaster.class.getSimpleName());

				List<QueryTypePriorityMaster> listPriorityadd = new ArrayList<>();
				for (QueryTypePriorityMaster pMaster : listPriorityDetails) {
					// pMaster.setQueryTypeMasterId(QAInfo.getQueryTypeMasterId());
					QueryTypeMaster query = new QueryTypeMaster();
					query.setQueryTypeMasterId(QAInfo.getQueryTypeMasterId());
					pMaster.setQueryTypeMaster(query);
					pMaster.setCreatedDateTime(QAInfo.getCreatedDateTime());
					pMaster.setCreatedBy(requestUtils.getUserName());
					listPriorityadd.add(pMaster);
				}

				listPriorityadd = queryTypePriorityMasterRepository.saveAll(listPriorityadd);

			}

			// fetch data from DB
			list = queryTypeMasterRepository.findAll();
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("Failed to save Query Type Master Details: " + QAInfo + ": getLocalizedMessage: "
						+ e.getLocalizedMessage() + "\n getMessage:" + e.getMessage());
				throw new InvalidRequestException(ErrorCode.INTERNAL_SERVER_ERROR,
						"Failed to save Query Type Master Details");
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Query Type Master Details saved successfully:" + QAInfo);
		}
		// Query Ans Description validation // 9/4

		if (!StringUtils.isValidString(QAInfo.getQueryTypedescription())) {

			if (logger.isDebugEnabled())
				logger.debug("Invalid Description ");
			throw new InvalidRequestException(ErrorCode.INVALID_PARAMETER, "Invalid Description");
		}

		return list;
	}
	// new API For GET data From 5 Tables 12/04

	@Override
	@Transactional("transactionManager")

	public QueryTypeMasterResponseDTO getAllByQueryTypeMasterId(int queryTypeMasterId) {
		QueryTypeMasterResponseDTO resQueryReq = new QueryTypeMasterResponseDTO();
		/*
		 * SubQueryTypeMasterResponseDTO subResQueryType= new
		 * SubQueryTypeMasterResponseDTO(); QueryTypePriorityMasterResponseDTO
		 * priorityQueryType= new QueryTypePriorityMasterResponseDTO();
		 */
		if (logger.isDebugEnabled())
			logger.debug("In getByQueryTypeMaster or queryTypeMasterId: " + queryTypeMasterId);
		isZero(queryTypeMasterId);
		Optional<QueryTypeMaster> queryTypeMaster = queryTypeMasterRepository
				.findAllByQueryTypeMasterId(queryTypeMasterId);
		if (queryTypeMaster.isPresent()) {
			resQueryReq = queryTypeMasterMapper.entityToDto(queryTypeMaster.get());

			// fetching Query type qA details
			List<QueryTypeQueAnsMappingMasterResponseDTO> listResQueryTypeQA = new ArrayList<>();
			List<QueryTypeQueAnsMappingMaster> listQueryQA = new ArrayList<>();
			listQueryQA = queryTypeQueAnsMappingMasterRepository.getListByQueryTypeId(queryTypeMasterId);
			listResQueryTypeQA = queryTypeQueAnsMappingMasterDTOMapper.entityListToDtoList(listQueryQA);
			resQueryReq.setListQueryAnsMappingResponseDTO(listResQueryTypeQA);

			// fetching Query type qA details
			List<SubQueryTypeMasterResponseDTO> listSubResQueryTypeQA = new ArrayList<>();
			List<SubQueryTypeMaster> listSubQueryQA = new ArrayList<>();
			listSubQueryQA = subQueryTypeMasterRepository.getListByQueryTypeId(queryTypeMasterId);
			System.out.println(" List is Present" + listSubQueryQA);
			listSubResQueryTypeQA = subQueryTypeMasterDTOMapper.entityListToDtoList(listSubQueryQA);
			resQueryReq.setSubQueryResponseDTO(listSubResQueryTypeQA);
			
			List<QueryTypePriorityMasterResponseDTO> listPriorityTypeQA = new ArrayList<>();
			List<QueryTypePriorityMaster> listPriorityQueryQA = new ArrayList<>();
			listPriorityQueryQA = queryTypePriorityMasterRepository.getListByQueryTypeId(queryTypeMasterId);
			listPriorityTypeQA = queryTypePriorityMasterDTOMapper.entityListToDtoList(listPriorityQueryQA);
			resQueryReq.setListQueryPriorityResponseDTO(listPriorityTypeQA);

		}

		logger.debug("In getByMasterId records found for queryTypeMasterId " + queryTypeMasterId);
		return resQueryReq;
	}

	private void isZero(int id) {
		if (id == 0) {
			logger.error("Id is null. ");
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, "Query Type Master Id is invalid");
		}
	}

}