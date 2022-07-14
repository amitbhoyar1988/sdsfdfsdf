package com.ps.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.jboss.logging.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.ps.RESTful.error.handler.InvalidRequestException;
import com.ps.RESTful.response.enums.ErrorCode;

public class ImageUtils {
	static Logger logger = Logger.getLogger(ImageUtils.class);

	public static String getFileExtension(MultipartFile inFile) {
		if (!inFile.isEmpty())
			return inFile.getOriginalFilename().substring(inFile.getOriginalFilename().lastIndexOf('.'));

		return null;

	}

	
	public static Boolean isEmpty(MultipartFile file) {
		if (file.isEmpty()) {
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, " Please provide Image");
		}
		return true;
	}

	public static Boolean isSizeExceeds(MultipartFile file, int size) {

		if (file.getSize() > size) {
			throw new InvalidRequestException(ErrorCode.BAD_REQUEST, ("Image Size exceed's"));

		}
		return true;
	}

	public static byte[] compress(byte[] data) throws IOException {

		if (logger.isDebugEnabled())
			logger.debug("In compress " + "method from ImageUtils ");

		if (data == null) {
			logger.error("data array is empty");
		}

		if (data != null) {

			Deflater deflater = new Deflater();
			deflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			deflater.finish();
			byte[] buffer = new byte[1024];
			while (!deflater.finished()) {
				int count = deflater.deflate(buffer); // returns the generated code... index
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
			byte[] output = outputStream.toByteArray();

			if (logger.isDebugEnabled())
				logger.debug("Image compressed successfully ");

			return output;
		}
		return data;
	}

	public static byte[] decompress(byte[] data) throws IOException, DataFormatException {

		if (logger.isDebugEnabled())
			logger.debug("In decompress " + "method from ImageUtils ");

		if (data == null) {
			logger.error("data array is empty");
		}

		if (data != null) {

			Inflater inflater = new Inflater();
			inflater.setInput(data);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
			byte[] buffer = new byte[1024];
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
			byte[] output = outputStream.toByteArray();

			if (logger.isDebugEnabled())
				logger.debug("Image decompresssed successfully ");

			return output;
		}
		return data;

	}
}
