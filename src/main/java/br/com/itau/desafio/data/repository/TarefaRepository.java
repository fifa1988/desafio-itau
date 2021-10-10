package br.com.itau.desafio.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itau.desafio.data.dto.TarefaDTO;
import br.com.itau.desafio.data.dto.UsuarioDTO;
import br.com.itau.desafio.data.entity.Tarefa;
import br.com.itau.desafio.data.entity.Usuario;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	public static final Function<TarefaDTO, Tarefa> DTO_TO_JPA = (dto -> {
		Tarefa jpa = new Tarefa();		
		BeanUtils.copyProperties(dto, jpa);
		return jpa;
	});
	
	public static final Function<Tarefa, TarefaDTO> JPA_TO_DTO = (jpa -> {
		TarefaDTO dto = TarefaDTO.builder().build();
		UsuarioDTO usuarioDTO = UsuarioDTO.builder().build();
		BeanUtils.copyProperties(jpa.getUsuario(), usuarioDTO);
		BeanUtils.copyProperties(jpa, dto);
		dto.setUsuario(usuarioDTO);
		return dto;
	});
	
	public static final Function<List<Tarefa>, List<TarefaDTO>> JPA_LIST_TO_DTO_LIST = (jpa -> {
		List<TarefaDTO> dtoList = new ArrayList<>();
		jpa.forEach(tarefa -> {
			TarefaDTO tarefaDTO = TarefaDTO.builder().build();
			UsuarioDTO usuarioDTO = UsuarioDTO.builder().build();
			BeanUtils.copyProperties(tarefa, tarefaDTO);
			BeanUtils.copyProperties(tarefa.getUsuario(), usuarioDTO);
			tarefaDTO.setUsuario(usuarioDTO);
			dtoList.add(tarefaDTO);
		});
		return dtoList;
	});
	
	List<Tarefa> findAllByOrderByStatusDesc();
	
	List<Tarefa> findByStatusAndUsuario(String status, Usuario usuario);

}
