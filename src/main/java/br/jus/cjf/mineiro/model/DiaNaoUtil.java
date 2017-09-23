package br.jus.cjf.mineiro.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.google.common.base.Objects;

@Entity
@Table(schema="mineiro")
public class DiaNaoUtil {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Index(name = "idx_dia")
	@Column(unique=true)
	@NotNull
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	private DateTime dia;

	@NotNull
	private Boolean anual;
	
	@NotEmpty
	private String descricao;
	
	private Boolean ativo = true;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DateTime getDia() {
		return dia;
	}

	public void setDia(DateTime dia) {
	
		this.dia = dia;
	}

	public Boolean getAnual() {
		return anual;
	}

	public void setAnual(Boolean anual) {
		this.anual = anual;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	
	public String toString() {
		return Objects.toStringHelper(this)
				.add("id", getId())
				.add("descricao", getDescricao())
				.add("dia", getDia().toString("dd/MM/yyyy"))
				.add("anual", getAnual())
				.add("ativo", getAtivo()).toString();
	}
	
}
