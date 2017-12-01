package br.jus.cjf.simus.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="Grupo",schema = "mineiro")
public class Grupo implements GrantedAuthority{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="grupo_id")
	private Integer id;	
	
	@Column(name="nome")
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

	@Override
	public String getAuthority() {

		return nome.trim();
	}
	
	@Override
	public String toString(){
		return getNome();

	}
	

}
