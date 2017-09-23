package br.jus.cjf.mineiro.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(schema="mineiro")
public class Penalidade {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable=false)
	private String clausula;
	
	@Column(nullable=false)
	private BigDecimal percentual;
	
	@Enumerated
	@Column(nullable=false)
	private TipoPenalidade tipoPenalidade;
	
	@ManyToOne
	private Contrato contrato;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClausula() {
		return clausula;
	}

	public void setClausula(String clausula) {
		this.clausula = clausula;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public TipoPenalidade getTipoPenalidade() {
		return tipoPenalidade;
	}

	public void setTipoPenalidade(TipoPenalidade tipoPenalidade) {
		this.tipoPenalidade = tipoPenalidade;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	
	
	

}
