package br.com.itau.desafio.api.v1;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.itau.desafio.data.dto.TarefaDTO;
import br.com.itau.desafio.exception.APIException;
import br.com.itau.desafio.service.TarefaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RequestMapping(path = "/todo", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(tags = "Tarefa")
@RestController
public class TarefaController {
	
	private TarefaService tarefaService;
	
	@Autowired
	public TarefaController(TarefaService tarefaService) {
		this.tarefaService = tarefaService;
	}

	@PostMapping
	@ApiOperation(value = "Incluir", notes = "Cria Tarefa")
	public ResponseEntity<TarefaDTO> insert(@Valid @RequestBody TarefaDTO tarefa, HttpServletRequest request) throws APIException {
		return new ResponseEntity<>(tarefaService.save(request, tarefa), HttpStatus.CREATED);
	}
	
	@PutMapping(path="/{id}/{status}")
	@ApiOperation(value = "Atualizar", notes = "Atualiza Tarefa")
	public ResponseEntity<TarefaDTO> update(@Valid @PathVariable(value = "id") Long id, @Valid @PathVariable(value = "status") String status, HttpServletRequest request) throws APIException {
		return new ResponseEntity<>(tarefaService.update(request, id, status), HttpStatus.OK);
	}
	
	@GetMapping
	@ApiOperation(value = "Consultar", notes = "Consulta Tarefa")
	public ResponseEntity<List<TarefaDTO>> consulta(@RequestParam("status") String status, HttpServletRequest request) throws APIException {
		return new ResponseEntity<>(tarefaService.get(request, status), HttpStatus.OK);
	}
	
}
