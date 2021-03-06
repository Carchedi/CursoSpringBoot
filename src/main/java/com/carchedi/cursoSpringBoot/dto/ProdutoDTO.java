package com.carchedi.cursoSpringBoot.dto;

import java.io.Serializable;

import com.carchedi.cursoSpringBoot.domain.Produto;

public class ProdutoDTO implements Serializable{
	 
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private double preco;
	
	public ProdutoDTO() {
	}
	
	public ProdutoDTO(Produto p) {
		id = p.getId();
		nome = p.getNome();
		preco = p.getPreco();
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	} 
}
