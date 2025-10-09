

package com.centime.l1.commons.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.*;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * ControllerAdvice to handle all kind of Exception from the application.
 *
 * @author Bhagan Ram
 *
 */
@RestControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

	private final String ERROR_CODE = "errorCode";
	private final String ERROR_TYPE = "errorType";
	private final String ERROR_DESC = "errorDescription";
	private final String BAD_REQUEST = "BadRequest";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleExceptions(Exception exception, WebRequest request) {
		return getResponseEntity(exception, request, null);
	}

	public ResponseEntity<Object> getResponseEntity(Exception ex, WebRequest request, HttpStatusCode status) {
		Map<String, Object> responsePair = new HashMap<>();
		Map<String,String> statusMessage = new HashMap<>();
		statusMessage.put("status", "FAILED");

		if (ex instanceof HttpMessageNotReadableException) {
			status = HttpStatus.BAD_REQUEST;
			statusMessage.put(ERROR_TYPE, BAD_REQUEST);
			statusMessage.put(ERROR_CODE, "error.requestBody.invalid");
			statusMessage.put(ERROR_DESC, "Either request body is invalid or some fields are having mismatch of data types");
		} else if (ex instanceof HttpMediaTypeNotSupportedException subEx) {
			status = subEx.getStatusCode();
			statusMessage.put(ERROR_TYPE, BAD_REQUEST);
			statusMessage.put(ERROR_CODE, "error.mediaType.invalid");
			statusMessage.put(ERROR_DESC, "Invalid Media Type provided");
		} else if (ex instanceof MissingRequestHeaderException subEx) {
			status = subEx.getStatusCode();
			statusMessage.put(ERROR_TYPE, BAD_REQUEST);
			statusMessage.put(ERROR_CODE, "error.missing.mandatory.header");
			statusMessage.put(ERROR_DESC, ex.getMessage());
		} else {
			status = HttpStatus.BAD_REQUEST;
			statusMessage.put(ERROR_TYPE, BAD_REQUEST);
			statusMessage.put(ERROR_CODE, "bad.request");
			statusMessage.put(ERROR_DESC, ex.getMessage());
		}
		responsePair.put("message", statusMessage);

		return new ResponseEntity<>(responsePair,
				new HttpHeaders(), status);
	}


}