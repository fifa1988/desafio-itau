package br.com.itau.desafio.data.dto;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioDTO{
	
	private Long id;
	private String email;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
