package com.ps.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.ps.RESTful.error.handler.ExceptionPrinter;
import com.ps.RESTful.response.enums.Feature;
import com.ps.config.tenant.AwsCredentialManager;
import com.ps.entities.tenant.FileDetails;

@Service
public class FileService {
	
	@Autowired
	private AwsCredentialManager awsCredentialManager;

	@Autowired
	private AwsS3Service awsS3Service;

	@Value("${aws.s3.bucket.name}")
	private String bucketName;

	@Value("${aws.s3.bucket.region}")
	private String s3BucketRegion;

	/**
	 * Upload objects in parts�Using the Multipart upload API you can upload large
	 * objects, up to 5 TB.
	 *
	 * @param multipartFile
	 * @param feature       - Enum object which tells upload request for which
	 *                      feature e.g investment,payroll,employeemaster etc.
	 * @return
	 */
	public String uploadFile(MultipartFile multipartFile, Feature feature) {

		try {
			System.out.println("bucketName::" + this.bucketName);
			System.out.println("s3BucketRegion::" + this.s3BucketRegion);

			AmazonS3 s3Client = this.awsCredentialManager.getAmazonS3Client();
			System.out.println("s3Client::" + s3Client);

			String folderPath = getFileDirectory(feature);
//			String folderPath = "Query/Abbott";
	
			System.out.println("isBucketExist::" + this.awsS3Service.isBucketExist(s3Client, this.bucketName));

			if (!this.awsS3Service.isBucketExist(s3Client, this.bucketName)) {
				System.out.println("createBucket::" + this.awsS3Service.createBucket(s3Client, this.bucketName));
				if (!this.awsS3Service.createBucket(s3Client, this.bucketName)) {
					return "fail";
				}
			}

			System.out.println(
					"isObjectExist::" + this.awsS3Service.isObjectExist(this.bucketName, folderPath, s3Client));

			if (!this.awsS3Service.isObjectExist(this.bucketName, folderPath, s3Client)) {
				System.out.println(
						"createFolder::" + this.awsS3Service.createFolder(this.bucketName, folderPath, s3Client));
				if (!this.awsS3Service.createFolder(this.bucketName, folderPath, s3Client)) {
					return "fail";
				}
			}

			File file = convertMultiPartToFile(multipartFile);
			InputStream targetStream = new FileInputStream(file);
			System.out.println("file::" + file);
			awsS3Service.uploadFile(this.bucketName, folderPath, file, s3Client);
			ObjectMetadata md = new ObjectMetadata();
			// md.setContentLength(contentAsBytes.length);

			String fileName = file.getName();
			String[] fileSplitArray = fileName.split("\\.", 2);
			String fileType = fileSplitArray[fileSplitArray.length - 1];
			System.out.println("fileType::" + fileType);
			if (Stream.of("jpg", "jpeg", "png", "gif", "tiff", "webp", "psd").anyMatch(fileType::equalsIgnoreCase)) {
				md.setContentType("image/jpeg");
			} else if (Stream.of("pdf", "ps").anyMatch(fileType::equalsIgnoreCase)) {
				md.setContentType("application/pdf");
			} else if (Stream.of("doc", "docx").anyMatch(fileType::equalsIgnoreCase)) {
				md.setContentType("application/msword");
			}else if (Stream.of("txt").anyMatch(fileType::equalsIgnoreCase)) {
				md.setContentType("text/plain");
			}
			s3Client.putObject(new PutObjectRequest(this.bucketName, file.getName(), targetStream, md));

			// object = s3.getObject(bucketName, keyName)
			System.out.println("file.delete()::" + file.delete());
			// file.delete();
			return awsS3Service.generateFileDownloadUrl(this.s3BucketRegion, this.bucketName, folderPath,
					file.getName());

		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process it, so
			// it returned an error response.
			ExceptionPrinter.printWarningLogs(e, this.getClass().getName());
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client couldn't parse
			// the response from Amazon S3.
			ExceptionPrinter.printWarningLogs(e, this.getClass().getName());
		} catch (Exception e) {
			ExceptionPrinter.printWarningLogs(e, this.getClass().getName());
		}
		return "fail";
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {

		File convFile = new File(file.getOriginalFilename());
		System.out.println("file.getOriginalFilename()::" + file.getOriginalFilename());

		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();

		return convFile;
	}

	private String getFileDirectory(Feature feature) {
		switch (feature) {
		case Query:
			return "paysquare/query";
		case EMPLOYEE_MASTER:
			return "paysquare/employee";
		case PAYROLL:
			return "paysquare/payroll";
		default:
			break;
		}
		return "paysquare";
	}

//	private void saveFileDetails(String keyName, String uploadId, String name, String fileUrl) {
//		FileDetails fileDetails = new FileDetails();
//		fileDetails.setCreatedBy("Vaibhav Deshmane");
//		fileDetails.setCreatedDateTime(new Date());
//		fileDetails.setFileKey(keyName);
//		fileDetails.setUploadId(uploadId);
//		fileDetails.setFileName(name);
//		fileDetails.setFileUrl(fileUrl);
//		// this.fileDetailsRepository.save(fileDetails);
//	}

	public String getFileDownloadUrl(String key) {
		// FileDetails fileDetails = this.fileDetailsRepository.findByFileKey(key);
		// return fileDetails == null ? null : fileDetails.getFileUrl();
		return null;
	}

	/**
	 * Getting s3Object i.e. download file from aws
	 *
	 * @param key
	 * @param feature
	 * @return
	 */
	public byte[] getFile(String key, Feature feature) {
		S3Object s3Object = null;
		try {
			AmazonS3 s3Client = this.awsCredentialManager.getAmazonS3Client();
			// Get an object and print its contents.
			System.out.println("***Downloading an file***");
			s3Object = s3Client
					.getObject(new GetObjectRequest(this.bucketName + "/" + this.getFileDirectory(feature), key));
			return IOUtils.toByteArray(s3Object.getObjectContent());
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			ExceptionPrinter.printWarningLogs(e, this.getClass().getName());
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			ExceptionPrinter.printWarningLogs(e, this.getClass().getName());
		} catch (Exception e) {
			ExceptionPrinter.printWarningLogs(e, this.getClass().getName());
		} finally {
			// To ensure that the network connection doesn't remain open, close any open
			// input streams.
			try {
				if (s3Object != null) {
					s3Object.close();
				}
			} catch (Exception e) {
				ExceptionPrinter.printWarningLogs(e, this.getClass().getName());
			}
		}
		return null;
	}

//	private StringBuilder displayTextInputStream(InputStream input) throws IOException {
//		// Read the text input stream one line at a time and display each line.
//		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
//		StringBuilder builder = new StringBuilder();
//		while ((builder.append(reader.readLine())) != null) {
//			System.out.println(builder);
//		}
//		return builder;
//	}
}
