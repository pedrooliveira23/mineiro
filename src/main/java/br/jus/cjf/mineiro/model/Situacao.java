package br.jus.cjf.mineiro.model;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape= JsonFormat.Shape.OBJECT)
public enum Situacao {

	ABERTA("Aberta"),CONCLUIDA("Concluída"),CANCELADA("Cancelada"),PRECIFICADA("Precificada");
	
	
	private String nome;

	private Situacao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}


	
	
	
}
