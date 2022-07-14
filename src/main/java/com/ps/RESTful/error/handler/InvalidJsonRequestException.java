package com.ps.RESTful.error.handler;

import com.ps.RESTful.response.enums.ErrorCode;

public class InvalidJsonRequestException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public InvalidJsonRequestException(ErrorCode errorCode, String description) {
		super(errorCode, description);
	}

}
