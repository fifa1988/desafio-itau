package br.com.itau.desafio.data.dto;

import com.google.gson.Gson;

import lombok.Data;

@Data
public class ApiErrorResponseDTO {

	private String statusCode;
	private String message;

	public ApiErrorResponseDTO(String statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

}
