package br.jus.cjf.mineiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(schema="mineiro")
public class Nota {


	@Id
	@GeneratedValue
	private Integer id;
	
	@Lob
	private String nota;
	
	@Column(nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime dataCriacao;
	
	private String autor;
	
	@ManyToOne
	private Transicao transicao;
	
	@Column(nullable=false, unique= true)
	private Integer redmineJournalId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	public DateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(DateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Transicao getTransicao() {
		return transicao;
	}

	public void setTransicao(Transicao transicao) {
		this.transicao = transicao;
	}

	public Integer getRedmineJournalId() {
		return redmineJournalId;
	}

	public void setRedmineJournalId(Integer redmineJournalId) {
		this.redmineJournalId = redmineJournalId;
	}
	
	
	
}
