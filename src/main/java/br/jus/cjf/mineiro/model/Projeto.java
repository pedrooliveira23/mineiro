package br.jus.cjf.mineiro.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(schema = "mineiro")
public class Projeto {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Index(name = "idx_redmineProjectId")	
	@Column(nullable = false,unique = true)
	private Integer redmineProjectId;
	
	private Integer redmineProjectParentId;
	
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private Linguagem linguagem;
	
	@JsonIgnore
	@OneToMany(mappedBy="projeto")
	private List<OrdemServico> ordensServico;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRedmineProjectId() {
		return redmineProjectId;
	}

	public void setRedmineProjectId(Integer redmineProjectId) {
		this.redmineProjectId = redmineProjectId;
	}

	public Integer getRedmineProjectParentId() {
		return redmineProjectParentId;
	}

	public void setRedmineProjectParentId(Integer redmineProjectParentId) {
		this.redmineProjectParentId = redmineProjectParentId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Linguagem getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(Linguagem linguagem) {
		this.linguagem = linguagem;
	}

	public List<OrdemServico> getOrdensServico() {
		return ordensServico;
	}

	public void setOrdensServico(List<OrdemServico> ordensServico) {
		this.ordensServico = ordensServico;
	}
	
	
	
	
	
}
