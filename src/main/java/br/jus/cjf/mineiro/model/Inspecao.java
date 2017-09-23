package br.jus.cjf.mineiro.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.jus.cjf.mineiro.web.controllers.validators.BigDecimalRange;

@Entity
@Table(schema = "mineiro")
public class Inspecao {

	@Id
	@GeneratedValue
	private Integer id;

	@JsonIgnore
	@ManyToOne
	private Demanda demanda;

	@NotNull
	@ManyToOne
	private TipoInspecao tipoInspecao;

	@NotNull
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private DateTime dia;

	@NotBlank
	private String artefatoInspecionado;

	@BigDecimalRange
	private BigDecimal percentualAprovacao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Demanda getDemanda() {
		return demanda;
	}

	public void setDemanda(Demanda demanda) {
		this.demanda = demanda;
	}

	public TipoInspecao getTipoInspecao() {
		return tipoInspecao;
	}

	public void setTipoInspecao(TipoInspecao tipoInspecao) {
		this.tipoInspecao = tipoInspecao;
	}

	public DateTime getDia() {
		return dia;
	}

	public void setDia(DateTime dia) {
		this.dia = dia;
	}

	public String getArtefatoInspecionado() {
		return artefatoInspecionado;
	}

	public void setArtefatoInspecionado(String artefatoInspecionado) {
		this.artefatoInspecionado = artefatoInspecionado;
	}

	public BigDecimal getPercentualAprovacao() {
		return percentualAprovacao;
	}

	public void setPercentualAprovacao(BigDecimal percentualAprovacao) {
		this.percentualAprovacao = percentualAprovacao;
	}

	



}
