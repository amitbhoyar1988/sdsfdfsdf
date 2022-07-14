package com.ps.RESTful.error.handler;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ps.RESTful.resources.response.handler.Response;
import com.ps.RESTful.resources.response.handler.ResponseBuilder;
import com.ps.RESTful.response.enums.ErrorCode;
import com.ps.RESTful.response.enums.StatusEnum;
import com.ps.dto.StatusDTO;
import com.ps.util.ErrorMessageConstants;
import com.ps.util.StringUtils;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LogManager.getLogger(RestExceptionHandler.class);

	@Autowired
	private Environment env;

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception exception) {
		return handleExceptionInternal(exception, null, null, null, null);
	}

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Response> handleBusinessException(BusinessException businessException) {
		if (logger.isDebugEnabled())
			logger.debug("Handling business exception : " + businessException.getMessage());

		// Update-by-MayurG
		if (businessException.getDescription() != null || !businessException.getDescription().isEmpty()) {
			StatusDTO statusDTO = new StatusDTO(StatusEnum.FAILURE.getValue(),
					businessException.getErrorCode().getCode(), businessException.getDescription());

			return new ResponseEntity<Response>(ResponseBuilder.builder().status(statusDTO).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Response>(
				ResponseBuilder.builder()
						.status(new StatusDTO(StatusEnum.FAILURE.getValue(), businessException.getErrorCode().getCode(),
								ErrorMessageConstants.ERROR_SOMETHING_WENT_WRONG))
						.build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}// BusinessExcaption-Close

	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ExceptionHandler(InvalidRequestException.class)
	public ResponseEntity<Response> handleInvalidRequestException(InvalidRequestException invRequestException) {
		if (logger.isDebugEnabled())
			logger.debug("Handling invalid request exception : " + invRequestException.getMessage());

		return new ResponseEntity<Response>(
				ResponseBuilder.builder()
						.status(new StatusDTO(StatusEnum.FAILURE.getValue(),
								invRequestException.getErrorCode().getCode(), invRequestException.getDescription()))
						.build(),
				HttpStatus.BAD_REQUEST);
	}// InvalidRequestException-Close

	// ResourceNotFoundException-Exception-Handler
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Response> handleResourceNotFoundException(
			ResourceNotFoundException resourceNotFoundException) {
		if (logger.isDebugEnabled())
			logger.debug("Handling resource not found exception : " + resourceNotFoundException.getMessage());

		StatusDTO statusDTO = null;

		if (StringUtils.isValidString(resourceNotFoundException.getDescription())) {
			statusDTO = new StatusDTO(StatusEnum.FAILURE.getValue(), resourceNotFoundException.getErrorCode().getCode(),
					resourceNotFoundException.getDescription());
		} else {
			statusDTO = new StatusDTO(StatusEnum.FAILURE.getValue(), resourceNotFoundException.getErrorCode().getCode(),
					ErrorMessageConstants.ERROR_BAD_REQUEST);
		}

		return new ResponseEntity<Response>(ResponseBuilder.builder().status(statusDTO).build(), HttpStatus.NOT_FOUND);
	}// ResourceNotFoundException-close

	// Resource Already Exist Exception Handler
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ExceptionHandler(ResourceAlreadyExistException.class)
	public ResponseEntity<Response> handleResourceNotFoundException(
			ResourceAlreadyExistException resourceAlreadyExistException) {
		if (logger.isDebugEnabled())
			logger.debug("Handling Resource Already Exist Exception : " + resourceAlreadyExistException.getMessage());

		StatusDTO statusDTO = null;

		if (StringUtils.isValidString(resourceAlreadyExistException.getDescription())) {
			statusDTO = new StatusDTO(StatusEnum.SUCCESS.getValue(),
					resourceAlreadyExistException.getErrorCode().getCode(),
					resourceAlreadyExistException.getDescription());
		} else {
			statusDTO = new StatusDTO(StatusEnum.SUCCESS.getValue(),
					resourceAlreadyExistException.getErrorCode().getCode(),
					ErrorMessageConstants.ERROR_RESOURCE_ALREADY_EXIST);
		}

		return new ResponseEntity<Response>(ResponseBuilder.builder().status(statusDTO).build(),
				HttpStatus.ALREADY_REPORTED);
	}// ResourceAlreadyExistException-close

	// RequestNotAcceptable Exception handler
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ExceptionHandler(RequestNotAcceptable.class)
	public ResponseEntity<Response> handleResourceNotFoundException(RequestNotAcceptable requestNotAcceptable) {
		if (logger.isDebugEnabled())
			logger.debug("Handling Request Not Acceptable Exception : " + requestNotAcceptable.getMessage());

		StatusDTO statusDTO = null;

		if (StringUtils.isValidString(requestNotAcceptable.getDescription())) {
			statusDTO = new StatusDTO(StatusEnum.FAILURE.getValue(), requestNotAcceptable.getErrorCode().getCode(),
					requestNotAcceptable.getDescription());
		} else {
			statusDTO = new StatusDTO(StatusEnum.SUCCESS.getValue(), requestNotAcceptable.getErrorCode().getCode(),
					ErrorMessageConstants.ERROR_REQUEST_NOT_ACCEPTABLE);
		}

		return new ResponseEntity<Response>(ResponseBuilder.builder().status(statusDTO).build(),
				HttpStatus.PRECONDITION_FAILED);
	}// RequestNotAcceptable-Close

	// InvalidJsonRequestException Exception Handler
	public ResponseEntity<Response> InvalidJsonRequestException(
			InvalidJsonRequestException invalidJsonRequestException) {
		if (logger.isDebugEnabled())
			logger.debug(
					"Handling invalid Json Request exception:" + invalidJsonRequestException.getErrorCode().getCode());
		return new ResponseEntity<Response>(ResponseBuilder.builder()
				.status(new StatusDTO(StatusEnum.FAILURE.getValue(),
						invalidJsonRequestException.getErrorCode().getCode(),
						invalidJsonRequestException.getDescription()))
				.build(), HttpStatus.BAD_REQUEST);

	}// InvalidJsonRequestException-Close

	// ############### Overridden methods go here ###############
	@Override
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException methodArgumentNotValidException, HttpHeaders headers, HttpStatus status,
			WebRequest request) {
		if (logger.isDebugEnabled()) {
			logger.debug(
					"Handling method argument not valid exception : " + methodArgumentNotValidException.getMessage());
		}

		StatusDTO statusDTO = new StatusDTO();

		List<ObjectError> objectErrors = methodArgumentNotValidException.getBindingResult().getAllErrors();

		if (logger.isDebugEnabled())
			logger.debug("Number of objectErrors: " + objectErrors.size());

		for (ObjectError objectError : objectErrors) {

			statusDTO = new StatusDTO(StatusEnum.FAILURE.getValue(), ErrorCode.INVALID_PARAMETER.getCode(),
					objectError.getDefaultMessage());
			break;
		}

		return new ResponseEntity<Object>(ResponseBuilder.builder().status(statusDTO).build(), HttpStatus.BAD_REQUEST);
	}

	@Override
	@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	protected ResponseEntity<Object> handleExceptionInternal(Exception exception, @Nullable Object body,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StatusDTO statusDTO = null;
		String message = exception.getMessage();

		if (exception instanceof HttpMessageNotReadableException
				|| exception instanceof MissingServletRequestParameterException
				|| exception instanceof UnsupportedOperationException
				|| exception instanceof MethodArgumentTypeMismatchException) {
			if (logger.isDebugEnabled()) {
				logger.debug("Handling " + exception.getClass().getSimpleName() + ": " + message);
			}
			statusDTO = new StatusDTO(StatusEnum.FAILURE.getValue(), ErrorCode.BAD_REQUEST.getCode(),
					ErrorMessageConstants.ERROR_BAD_REQUEST);
			return new ResponseEntity<>(ResponseBuilder.builder().status(statusDTO).build(), HttpStatus.BAD_REQUEST);
		}
		logger.error("Handling exception internal: " + exception.getClass() + " " + message, exception);
		statusDTO = new StatusDTO(StatusEnum.FAILURE.getValue(), ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
				ErrorMessageConstants.ERROR_SOMETHING_WENT_WRONG);
		return new ResponseEntity<Object>(ResponseBuilder.builder().status(statusDTO).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}// Class Close
