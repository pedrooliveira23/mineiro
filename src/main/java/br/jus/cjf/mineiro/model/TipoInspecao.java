package br.jus.cjf.mineiro.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema = "mineiro")
public class TipoInspecao {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(nullable = false, unique=true)
	private String nome;
	
	@JsonIgnore
	@OneToMany(mappedBy = "tipoInspecao")
	private List<Inspecao> inspecoes;

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

	public List<Inspecao> getInspecoes() {
		return inspecoes;
	}

	public void setInspecoes(List<Inspecao> inspecoes) {
		this.inspecoes = inspecoes;
	}
	



}
