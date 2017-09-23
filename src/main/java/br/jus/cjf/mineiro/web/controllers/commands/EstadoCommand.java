package br.jus.cjf.mineiro.web.controllers.commands;

import java.util.List;

import br.jus.cjf.mineiro.model.Estado;

public class EstadoCommand {

	private List<Estado> estados;

	public List<Estado> getEstados() {
		return estados;
	}

	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
}
