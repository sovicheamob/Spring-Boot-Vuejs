package com.vehicle.springbootvuejs.exception;

import com.vehicle.springbootvuejs.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Object>> handleAllExceptions(Exception ex, WebRequest request) {
		ApiResponse<Object> response = new ApiResponse<>("ERR-001", "Something went wrong.", null);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
