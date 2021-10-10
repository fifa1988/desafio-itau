package br.com.itau.desafio.data.entity;

import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.google.gson.Gson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usuario implements java.io.Serializable {
	private static final long serialVersionUID = 7711809793117340370L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
	@Column
	private Long id;
	@Column(unique=true)
	@NotNull(message = "Campo email é obrigatório")  
	@NotEmpty(message = "Informe um valor")
	private String email;
	@Column
	private String senha;
	@Column
	boolean usersuper;
	@Column
	private Long conta;
	
	public String getSenha() {
		try {
			return new String(Base64.getDecoder().decode(this.senha.getBytes()));
		}catch (Exception e) {
			return this.senha;
		}
	}
	public void setSenha(String senha) {
		try {
			this.senha = new String(Base64.getEncoder().encode(senha.getBytes()));
		} catch (Exception e) {
			this.senha = senha;
		}
	}
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}

