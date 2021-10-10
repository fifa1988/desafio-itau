package br.com.itau.desafio.data.repository;

import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.itau.desafio.data.dto.UsuarioDTO;
import br.com.itau.desafio.data.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public static final Function<UsuarioDTO, Usuario> DTO_TO_JPA = (dto -> {
		Usuario jpa = new Usuario();		
		BeanUtils.copyProperties(dto, jpa);
		return jpa;
	});
	
	public static final Function<Usuario, UsuarioDTO> JPA_TO_DTO = (jpa -> {
		UsuarioDTO dto = UsuarioDTO.builder().build();
		BeanUtils.copyProperties(jpa, dto);
		return dto;
	});
	
	Optional<Usuario> findByEmailAndSenha(String email, String senha);
	
	Optional<Usuario> findByEmail(String email);
	
}
