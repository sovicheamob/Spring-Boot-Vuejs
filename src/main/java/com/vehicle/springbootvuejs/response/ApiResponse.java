package com.vehicle.springbootvuejs.response;

import lombok.Data;

@Data
public class ApiResponse<T> {
	private String code;
	private String message;
	private T data;

	public ApiResponse(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
}
