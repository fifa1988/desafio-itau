package br.com.itau.desafio.service;

import br.com.itau.desafio.data.entity.Usuario;
import br.com.itau.desafio.exception.APIException;

public interface UsuarioService {

	public Usuario get(String email, String senha) throws APIException;
	
}
