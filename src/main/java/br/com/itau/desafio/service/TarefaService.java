package br.com.itau.desafio.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import br.com.itau.desafio.data.dto.TarefaDTO;
import br.com.itau.desafio.exception.APIException;

public interface TarefaService {

	TarefaDTO save(HttpServletRequest request, TarefaDTO tarefa) throws APIException;
	
	List<TarefaDTO> get(HttpServletRequest request, String status) throws APIException;

	TarefaDTO update(HttpServletRequest request, @Valid Long id, @Valid String status);
	
}
