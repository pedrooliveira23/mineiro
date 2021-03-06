package br.jus.cjf.mineiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="mineiro")
public class TipoIndicador {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false, unique=true)
	private String nome;


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
