package br.com.itau.desafio.exception;

import javax.validation.UnexpectedTypeException;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.itau.desafio.data.dto.ApiErrorResponseDTO;
import br.com.itau.desafio.utils.Constants;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {
	
	@ExceptionHandler(value = { APIException.class } )
	public ResponseEntity<ApiErrorResponseDTO> apiException(APIException ex) {
		return response(ex.getStatusCode(), ex.getMessage());
	}
	
	@ExceptionHandler(value = { HttpMessageNotReadableException.class, InvalidDataAccessApiUsageException.class, IllegalArgumentException.class})
	public ResponseEntity<ApiErrorResponseDTO> paramInvalidException() {
		return response(HttpStatus.BAD_REQUEST, Constants.PARAMETERS_INVALID_ERROR_MSG);
	}
	
	@ExceptionHandler(value = { UnexpectedTypeException.class })
	public ResponseEntity<ApiErrorResponseDTO> parameterValidation(UnexpectedTypeException ex) {
		return response(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
	}
	
	@ExceptionHandler(value = { HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiErrorResponseDTO> notSupportedException(HttpRequestMethodNotSupportedException ex) {
		return response(HttpStatus.FORBIDDEN, ex.getMessage());
    }
	
	@ExceptionHandler(value = { HttpMediaTypeNotSupportedException.class })
    public ResponseEntity<ApiErrorResponseDTO> notSupportedException(HttpMediaTypeNotSupportedException ex) {
		return response(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage());
    }
	
	@ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ApiErrorResponseDTO> unknownException() {
        return response(HttpStatus.INTERNAL_SERVER_ERROR, Constants.INTERNAL_SERVER_ERROR_MESSAGE);
    }

	private ResponseEntity<ApiErrorResponseDTO> response(HttpStatus status, String message) {
		String statusCode = String.format(Constants.STATUS_CODE_FORMAT, status.value(), status.name());
		ApiErrorResponseDTO response = new ApiErrorResponseDTO(statusCode, message);
		return new ResponseEntity<>(response, status);
	}
	
	

}
