package br.jus.cjf.mineiro.model;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

@Embeddable
public class Justificativa {

	@Lob
	private String descricao;
	
	private String autor;

	public Justificativa() {
		super();

	}
	
	public Justificativa(String descricao, String autor) {
		super();
		this.descricao = descricao;
		this.autor = autor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	
}
