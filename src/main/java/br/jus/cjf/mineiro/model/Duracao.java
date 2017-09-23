package br.jus.cjf.mineiro.model;

import javax.persistence.Embeddable;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Type;
import org.joda.time.Duration;

@Embeddable
public class Duracao {
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDurationAsString")
	private Duration valor = Duration.ZERO;
	
	@Enumerated
	private TipoDuracao tipoDuracao;

	public Duracao() {

	}

	
	public Duracao(Duration duracao, TipoDuracao tipoDuracao) {
		super();
		this.valor = duracao;
		this.tipoDuracao = tipoDuracao;
	}
	
	public Duracao(Duration duracao) {
		super();
		this.valor = duracao;
	}

	public Duration getValor() {
		return valor;
	}

	public void setValor(Duration duracao) {
		this.valor = duracao;
	}

	public TipoDuracao getTipoDuracao() {
		return tipoDuracao;
	}

	public void setTipoDuracao(TipoDuracao tipoDuracao) {
		this.tipoDuracao = tipoDuracao;
	}
        	
}
