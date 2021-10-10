package br.com.itau.desafio.data.dto;

import com.google.gson.Gson;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
	private String token_login;
	private String password;
	private String client_id;
	private String client_user;
	private String client_key;
	private Long user_id;
	private String username;
	private Long exp;
	private String email;
	private Boolean super_admin;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
