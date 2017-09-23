package br.jus.cjf.mineiro.model;

import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

@Embeddable
public class Faixa {


	private BigDecimal inicio;
	
	private Boolean inicioDentroIntervalo;
	
	private BigDecimal fim;
	
	private Boolean fimDentroIntervalo;
	
	@Enumerated
	private TipoFaixa tipoFaixa;

	public BigDecimal getInicio() {
		return inicio;
	}

	public void setInicio(BigDecimal inicio) {
		this.inicio = inicio;
	}

	public Boolean getInicioDentroIntervalo() {
		return inicioDentroIntervalo;
	}

	public void setInicioDentroIntervalo(Boolean inicioDentroIntervalo) {
		this.inicioDentroIntervalo = inicioDentroIntervalo;
	}

	public BigDecimal getFim() {
		return fim;
	}

	public void setFim(BigDecimal fim) {
		this.fim = fim;
	}

	public Boolean getFimDentroIntervalo() {
		return fimDentroIntervalo;
	}

	public void setFimDentroIntervalo(Boolean fimDentroIntervalo) {
		this.fimDentroIntervalo = fimDentroIntervalo;
	}

	public TipoFaixa getTipoFaixa() {
		return tipoFaixa;
	}

	public void setTipoFaixa(TipoFaixa tipoFaixa) {
		this.tipoFaixa = tipoFaixa;
	}
	
	
	
	
}
