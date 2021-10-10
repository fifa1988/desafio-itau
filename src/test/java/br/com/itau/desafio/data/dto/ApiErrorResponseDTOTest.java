package br.com.itau.desafio.data.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ApiErrorResponseDTOTest {
	
	@Test
	void deveriaGerarObjeto() {
		ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO("0200", "Message");
		
		Assertions.assertEquals("0200",apiErrorResponseDTO.getStatusCode());
		Assertions.assertEquals("Message",apiErrorResponseDTO.getMessage());
	}
	
	@Test
	void deveriaGerarJsonDoObjeto() {
		ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO("0200", "Message");
		Assertions.assertEquals("{\"statusCode\":\"0200\",\"message\":\"Message\"}", apiErrorResponseDTO.toString()); 
	}
	
	@Test
	void deveriaTestarOsSets() {
		ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO("0100", "Message Start");
		apiErrorResponseDTO.setStatusCode("0200");
		apiErrorResponseDTO.setMessage("Message");
		Assertions.assertEquals("{\"statusCode\":\"0200\",\"message\":\"Message\"}", apiErrorResponseDTO.toString()); 
	}
	
	@Test
	void deveriaGerarValidarSeObjetoEIgualAoOutro() {
		ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO("0100", "Message Start");
		ApiErrorResponseDTO apiErrorResponseDTOCopyReference = new ApiErrorResponseDTO("0100", "Message Start");
		Assertions.assertEquals(apiErrorResponseDTOCopyReference, apiErrorResponseDTO); 
	}
	
	@Test
	void deveriaTerHashCodeIgual() {
		ApiErrorResponseDTO apiErrorResponseDTO = new ApiErrorResponseDTO("0100", "Message Start");
		ApiErrorResponseDTO apiErrorResponseDTOCopyReference = new ApiErrorResponseDTO("0100", "Message Start");
		Assertions.assertEquals(apiErrorResponseDTOCopyReference, apiErrorResponseDTO); 
		Assertions.assertEquals(apiErrorResponseDTOCopyReference.hashCode(), apiErrorResponseDTOCopyReference.hashCode()); 
	}
	
}
