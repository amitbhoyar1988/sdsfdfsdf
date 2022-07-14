package com.ps.util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.RESTful.response.enums.Feature;
import com.ps.entities.tenant.QueryDocumentInformation;
import com.ps.services.FileService;

@Component
public class RequestUtils {

	static Logger logger = Logger.getLogger(RequestUtils.class);

	@Autowired
	protected HttpServletRequest request;

	@Autowired
	private FileService fileService;

	@RequestScope
	public String getUserName() {

		if (logger.isDebugEnabled())
			logger.debug("In getUserName method retrieving username from request");

		Object userNameObject = request.getAttribute(Constants.USER_NAME);
		if (logger.isDebugEnabled())
			logger.debug("In getUserName username-> " + userNameObject);
		if (userNameObject != null)
			return (String) userNameObject;

		// if-its-Null-Return-PaysquareDefault
		if (logger.isDebugEnabled())
			logger.debug("In getUserName username is null Returning -> PaysquareDefault");

		return "PaysquareDefault";
	}

	public List<QueryDocumentInformation> uploadDocument(MultipartFile[] documents, int employeeId) {

		List<QueryDocumentInformation> listDoc = new ArrayList<>();
		QueryDocumentInformation doc;
//		String queyDocPath = "Abbott/Abbott1/Employees/Investment";
		String url = "";

		String currentUser = getUserName();
		String strEmpId = String.valueOf(employeeId);

		if (logger.isDebugEnabled())
			logger.debug("Uploading Documents for employeeId " + employeeId);

//		List<DocumentInformation> documentInformationList = new ArrayList<>();

		for (int i = 0; i < documents.length; i++) {
			doc = new QueryDocumentInformation();

			String fileName = documents[i].getOriginalFilename();
			if (logger.isDebugEnabled())
				logger.debug("fileName  " + fileName);

			String[] fileSplitArray = fileName.split("\\.", 2);
			String fileType = fileSplitArray[fileSplitArray.length - 1];
			System.out.println("fileType::" + fileType);

			if (!Stream.of("txt", "jpg", "jpeg", "png", "gif", "tiff", "webp", "psd", "pdf", "ps", "doc", "docx")
					.anyMatch(fileType::equalsIgnoreCase)) {
				if (logger.isDebugEnabled())
					logger.debug("File type : " + fileType + " is not Supported. Please try again");
				throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
						"File type is not Supported. Please try again.");
			}

			if (fileName.startsWith(strEmpId)) {
				if (logger.isDebugEnabled())
					logger.debug("file : " + fileName + " found to upload for employee" + strEmpId);
				url = fileService.uploadFile(documents[i], Feature.Query);

				System.out.println("url::" + url);

				if (url.equals("fail")) {
					throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
							"Files are not saved. Please try again." + fileName);
				} else {
					doc.setEmployeeMasterId(employeeId);
					doc.setFileName(fileName);
					doc.setFileType(fileType);
					doc.setFileSize(null);
					doc.setQueryBlobURI(url);
					doc.setActive(true);
					listDoc.add(doc);
					if (logger.isDebugEnabled())
						logger.debug("file uploaded successfully for employee " + strEmpId);
				}
			}

			/*
			 * URI url = azureBlobAdapter.upload(documents[i], companyPath,
			 * getCurrentFinancialYear(), getLoggedinEmployeeID());
			 *
			 * if (url == null) { throw new InvalidRequestException(ErrorCode.BAD_REQUEST,
			 * "Files are not saved. Please try again."); }
			 */

//			DocumentInformation documentInformation = new DocumentInformation();
//
//			documentInformation.setEmployeeMasterId(employeeMasterId);
//			documentInformation.setFileName(documents[i].getOriginalFilename());
//			documentInformation.setProofSubmissionId(proofSubmissionId);
//			documentInformation.setBlobURI(url);
//			documentInformation.setDocumentRemark(documentRemark);
//			documentInformation.setDocumentType(documentType);
//			documentInformation.setFileType(documents[i].getContentType());
//			documentInformation.setFinancialYear(getCurrentFinancialYear());
//			documentInformation.setCreatedBy(getCurrentUserName());
//			documentInformation.setLastModifiedBy(getCurrentUserName());
//			documentInformation.setDocumentCode("");
//			documentInformation.setIsActive(1);

//			if (logger.isDebugEnabled()) {
//				logger.debug("Sending DocumentInfo entity to service method for saving into db " + documentInformation);
//			}

//			documentInformationList.add(documentInformation);
		}

//		documentInformationService.add(documentInformationList);
//
//		// Save proof submission detail.
//		ProofSubmissionDetail proofSubmissionDetail = new ProofSubmissionDetail();
//		proofSubmissionDetail.setProofSubmissionId(proofSubmissionId);
//		proofSubmissionDetail.setRemark(documentRemark);
//		proofSubmissionDetail.setReceiptAmount(receiptAmount);
//		proofSubmissionDetail.setEmployeeMasterId(employeeMasterId);
//		proofSubmissionDetail.setCreatedBy(getCurrentUserName());
//		proofSubmissionDetail.setLastModifiedBy(getCurrentUserName());
//		proofSubmissionDetail.setFinancialYear(getCurrentFinancialYear());
//		proofSubmissionDetail.setActive(true);
//		proofSubmissionDetail.setSeries(documentType);
//
//		proofSubmissionDetailService.add(proofSubmissionDetail);

		return listDoc;
	}

}