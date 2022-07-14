package com.ps.RESTful.error.handler;

import com.ps.RESTful.response.enums.ErrorCode;

public class IllegalResourceAccessException extends BusinessException {
	private static final long serialVersionUID = 1L;

	public IllegalResourceAccessException(ErrorCode errorCode, String description) {
		super(errorCode, description);
	}
}
