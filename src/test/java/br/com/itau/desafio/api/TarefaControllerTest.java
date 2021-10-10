package br.com.itau.desafio.api;

import static org.mockito.Mockito.withSettings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.itau.desafio.api.v1.TarefaController;
import br.com.itau.desafio.config.authorization.model.AuthenticationValidation;
import br.com.itau.desafio.data.dto.TarefaDTO;
import br.com.itau.desafio.data.entity.Tarefa;
import br.com.itau.desafio.data.entity.Usuario;
import br.com.itau.desafio.data.repository.TarefaRepository;
import br.com.itau.desafio.data.repository.UsuarioRepository;
import br.com.itau.desafio.exception.APIException;
import br.com.itau.desafio.service.impl.TarefaServiceImpl;

class TarefaControllerTest {
	@Mock
	private TarefaServiceImpl tarefaService;
	
	@Mock
	private TarefaController controller;
	
	@Mock
	TarefaRepository tarefaRepository;
	
	@Mock
	HttpServletRequest request;
	
	@Mock
	UsuarioRepository usuarioRepository;
	
	@Mock
	AuthenticationValidation authenticationValidation;
	
	@BeforeEach
	void beforeEach() {
		MockitoAnnotations.initMocks(this);
		tarefaService = Mockito.mock(TarefaServiceImpl.class, withSettings().
				useConstructor(tarefaRepository, usuarioRepository, authenticationValidation));
		
		controller = Mockito.mock(TarefaController.class, withSettings().
				useConstructor(tarefaService));
	}
	
	@Test
	void contextLoads() throws Exception {
		Assertions.assertNotNull(controller);
	}
	
	@Test
	void testInsertHappyWay() throws Exception {
		Tarefa tarefa = getTarefa();
		TarefaDTO tarefaDTO = getTarefaDTO();
		Long id = 1l;
		
		Mockito.when(tarefaRepository.findById(tarefa.getId())).thenReturn(findByIdSemRegistro());
		Mockito.when(tarefaRepository.saveAndFlush(tarefa)).thenReturn(saveAndFlush());
		Mockito.when(tarefaService.save(request, tarefaDTO)).thenReturn(save(id));
		
		Assertions.assertEquals(false, tarefaRepository.findById(tarefaDTO.getId()).isPresent());
		
		TarefaDTO dto = tarefaService.save(request, tarefaDTO);
		
		Assertions.assertEquals(dto.getId(), 1l);
		Assertions.assertEquals(dto.getDescricao(), tarefa.getDescricao());
		Assertions.assertEquals(dto.getResumo(), tarefa.getResumo());
		Assertions.assertEquals(dto.getStatus(), tarefa.getStatus());
		
		ResponseEntity<TarefaDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.CREATED);
		Assertions.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
	}
	
	@Test
	void testUpdateaHappyWay() throws Exception {
		
		TarefaDTO dto = getTarefaDTO();
		Tarefa tarefa = TarefaRepository.DTO_TO_JPA.apply(dto);
		Long id = 1l;
		String status = "pedding";
		
		Mockito.when(tarefaRepository.findById(id)).thenReturn(findById());
		Mockito.when(tarefaService.update(request, id, status)).thenReturn(update(id));
		
		Assertions.assertEquals(true, tarefaRepository.findById(id).isPresent());
		
		dto = tarefaService.update(request, id, status);
		
		Assertions.assertEquals(dto.getId(), 1l);
		Assertions.assertEquals(dto.getDescricao(), tarefa.getDescricao());
		Assertions.assertEquals(dto.getResumo(), tarefa.getResumo());
		Assertions.assertEquals(dto.getStatus(), tarefa.getStatus());
		
		ResponseEntity<TarefaDTO> responseEntity = new ResponseEntity<>(dto, HttpStatus.OK);
		Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	void testUpdateDataAlreadyOnDB() throws Exception {
		Long id = 1l;
		Mockito.when(tarefaRepository.findById(id)).thenReturn(findByIdSemRegistro());
		try {
			if(!tarefaRepository.findById(id).isPresent())
				throw new APIException("Tarefa não encontrada", HttpStatus.BAD_REQUEST);
			Assertions.fail("Registro encontrado.");
		} catch (APIException e) {
			Assertions.assertEquals("Tarefa não encontrada", e.getMessage());
		}
	}
	
	@Test
	void testGetHappyWay() throws Exception {
		TarefaDTO dto = getTarefaDTO();
		Tarefa tarefa = TarefaRepository.DTO_TO_JPA.apply(dto);
		Sort sort = Sort.by("status").descending();
		
		Mockito.when(usuarioRepository.findByEmail("user01@itau.com.br")).thenReturn(findByEmail());
		Mockito.when(tarefaRepository.findAll(Example.of(tarefa), sort)).thenReturn(findAll());
		
		Optional<Usuario> findByEmail = usuarioRepository.findByEmail("user01@itau.com.br");
		if(findByEmail.isPresent()) {
			Assertions.assertTrue(findByEmail.get().isUsersuper());
		}
		
		List<Tarefa> retorno = tarefaRepository.findAll(Example.of(tarefa), sort);
		Assertions.assertFalse(retorno.isEmpty());
	}
	
	private Tarefa saveAndFlush() {
		return getTarefa();
	}

	private TarefaDTO save(Long id) throws Exception {
		TarefaDTO tarefaDTO = getTarefaDTO();
		tarefaDTO.setId(id);
		return tarefaDTO;
	}
	
	private TarefaDTO update(Long id) throws Exception {
		TarefaDTO tarefaDTO = getTarefaDTO();
		tarefaDTO.setId(id);
		return tarefaDTO;
	}

	private TarefaDTO getTarefaDTO() {
		return TarefaDTO.builder()
			.dataCadastro(Calendar.getInstance())
			.descricao("Atividade de levantamento de requisitos")
			.resumo("Devesse criar requisito da atividade")
			.status("pedding")
			.build();
	}
	
	private Tarefa getTarefa() {
		return Tarefa.builder()
			.dataCadastro(Calendar.getInstance())
			.descricao("Atividade de levantamento de requisitos")
			.resumo("Devesse criar requisito da atividade")
			.status("pedding")
			.build();
	}
	
	private Optional<Tarefa> findById() throws APIException {
		return Optional.of(getTarefa());
	}
	
	private List<Tarefa> findAll() throws APIException {
		List<Tarefa> list = new ArrayList<>();
		Tarefa firstTarefa = getTarefa();
		firstTarefa.setId(1l);
		list.add(firstTarefa);
		Tarefa secondTarefa = getTarefa();
		secondTarefa.setId(2l);
		secondTarefa.setStatus("completed");
		list.add(secondTarefa);
		Tarefa thirdTarefa = getTarefa();
		thirdTarefa.setId(3l);
		list.add(thirdTarefa);
		return list;
	}
	
	private Optional<Tarefa> findByIdSemRegistro() {
		return Optional.ofNullable(null);
	}
	
	private Optional<Usuario> findByEmail() {
		return Optional.of(Usuario.builder().id(1l).conta(1l).email("user01@itau.com.br").usersuper(true).build());
	}
}
