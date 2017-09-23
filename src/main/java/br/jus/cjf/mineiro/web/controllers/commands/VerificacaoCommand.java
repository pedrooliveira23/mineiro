package br.jus.cjf.mineiro.web.controllers.commands;

import java.util.List;

import br.jus.cjf.mineiro.model.Inspecao;

public class VerificacaoCommand {

	private List<Inspecao> verificacoes;

	public List<Inspecao> getVerificacoes() {
		return verificacoes;
	}

	public void setInspecoes(List<Inspecao> verificacoes) {
		this.verificacoes = verificacoes;
	}

	
	
}
