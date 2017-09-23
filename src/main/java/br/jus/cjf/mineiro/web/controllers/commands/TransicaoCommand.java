package br.jus.cjf.mineiro.web.controllers.commands;

import java.util.List;

import br.jus.cjf.mineiro.model.Transicao;

public class TransicaoCommand {

	List<Transicao> transicoes;

	public List<Transicao> getTransicoes() {
		return transicoes;
	}

	public void setTransicoes(List<Transicao> transicoes) {
		this.transicoes = transicoes;
	}

}
