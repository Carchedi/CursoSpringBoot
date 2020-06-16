package com.carchedi.cursoSpringBoot.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.carchedi.cursoSpringBoot.domain.Categoria;

public class CategoriaDTO implements Serializable{
	  
	private static final long serialVersionUID = 1L;
	
	private Integer id;
 
	@NotEmpty(message="Preencimento obrigatório")
	@Size(min=5, max=80, message="O tamanho precisa estar entre 5 e 80 caracteres")	
	private String nome;
	
	public CategoriaDTO() {
	}
	
	public CategoriaDTO(Categoria cat) {
		id = cat.getId();
		nome = cat.getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	} 
}
