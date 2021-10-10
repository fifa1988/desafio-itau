package br.com.itau.desafio.service.impl;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.itau.desafio.config.authorization.model.AuthenticationValidation;
import br.com.itau.desafio.data.dto.TarefaDTO;
import br.com.itau.desafio.data.dto.UserDTO;
import br.com.itau.desafio.data.entity.Tarefa;
import br.com.itau.desafio.data.entity.Usuario;
import br.com.itau.desafio.data.repository.TarefaRepository;
import br.com.itau.desafio.data.repository.UsuarioRepository;
import br.com.itau.desafio.exception.APIException;
import br.com.itau.desafio.service.TarefaService;

@Service
@Transactional
public class TarefaServiceImpl implements TarefaService {

	private TarefaRepository tarefaRepository;
	private UsuarioRepository usuarioRepository;
	private AuthenticationValidation authenticationValidation;
	private Logger logger = LoggerFactory.getLogger(TarefaServiceImpl.class);
	
	@Autowired
	public TarefaServiceImpl(TarefaRepository tarefaRepository, UsuarioRepository usuarioRepository, AuthenticationValidation authenticationValidation){
		this.tarefaRepository = tarefaRepository;
		this.usuarioRepository = usuarioRepository;
		this.authenticationValidation = authenticationValidation;
	}
	
	@Override
	public TarefaDTO save(HttpServletRequest request, TarefaDTO tarefa) throws APIException {
		UserDTO token = authenticationValidation.validarToken(request);
		Usuario usuario = usuarioRepository.findByEmail(token.getUsername()).orElseThrow(
				() -> new APIException("Usuário não encontrado", HttpStatus.BAD_REQUEST, this.logger));
		
		Tarefa jpa = Tarefa.builder().build();
		BeanUtils.copyProperties(tarefa, jpa);
		jpa.setId(null);
		jpa.setUsuario(usuario);
		
		jpa = tarefaRepository.saveAndFlush(jpa);

		return TarefaRepository.JPA_TO_DTO.apply(jpa);
	}
	
	@Override
	public List<TarefaDTO> get(HttpServletRequest request, String status) throws APIException {
		UserDTO token = authenticationValidation.validarToken(request);
		Usuario usuario = usuarioRepository.findByEmail(token.getUsername()).orElseThrow(
				() -> new APIException("Usuário não encontrado", HttpStatus.BAD_REQUEST, this.logger));
		Tarefa tarefa = Tarefa.builder().build();
		if(!status.isEmpty())
			tarefa.setStatus(status);
		Sort sort = Sort.by("status").descending();
		if(usuario.isUsersuper()) {			
			return TarefaRepository.JPA_LIST_TO_DTO_LIST.apply(tarefaRepository.findAll(Example.of(tarefa), sort));
		}else {
			tarefa.setUsuario(Usuario.builder().id(usuario.getId()).build());
			return TarefaRepository.JPA_LIST_TO_DTO_LIST.apply(tarefaRepository.findAll(Example.of(tarefa), sort));
		}
	}

	@Override
	public TarefaDTO update(HttpServletRequest request, @Valid Long id, @Valid String status) {
		UserDTO token = authenticationValidation.validarToken(request);
		Usuario usuario = usuarioRepository.findByEmail(token.getUsername()).orElseThrow(
				() -> new APIException("Usuário não encontrado", HttpStatus.BAD_REQUEST, this.logger));
		Tarefa jpa = tarefaRepository.findById(id).orElseThrow(
				() -> new APIException("Tarefa não encontrada", HttpStatus.BAD_REQUEST, this.logger));
		if(jpa.getUsuario().getId() != usuario.getId() && !usuario.isUsersuper())
			throw new APIException("Tarefa não encontrada", HttpStatus.BAD_REQUEST, this.logger);
		jpa.setStatus(status);
		jpa.setDataAtualizacao(Calendar.getInstance());
		return TarefaRepository.JPA_TO_DTO.apply(jpa);
	}
}
