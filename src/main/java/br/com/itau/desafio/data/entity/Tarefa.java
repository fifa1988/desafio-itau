package br.com.itau.desafio.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

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
public class Tarefa implements java.io.Serializable {
	private static final long serialVersionUID = -3808269286877328785L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "increment")
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, updatable = false, insertable = true)
	private Usuario usuario;
	
	@Column
	private String resumo;
	
	@Column
	private String descricao;
	
	@Column
	private String status;
	
	@Column
	private java.util.Calendar dataCadastro;
	
	@Column
	private java.util.Calendar dataAtualizacao;
	
	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
}
