package br.com.itau.desafio.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.itau.desafio.data.entity.Usuario;
import br.com.itau.desafio.data.repository.UsuarioRepository;
import br.com.itau.desafio.exception.APIException;
import br.com.itau.desafio.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository usuarioRepository;
	private Logger logger;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository){
		this.usuarioRepository = usuarioRepository;
		LoggerFactory.getLogger(UsuarioServiceImpl.class);
	}
	
	@Override
	public Usuario get(String email, String senha) throws APIException {
		return usuarioRepository.findByEmailAndSenha(email, senha).orElseThrow(() -> new APIException("Usuário não encontrado", HttpStatus.BAD_REQUEST, this.logger));
	}
	
}
