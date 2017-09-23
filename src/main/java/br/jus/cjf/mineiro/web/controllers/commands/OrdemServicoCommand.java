package br.jus.cjf.mineiro.web.controllers.commands;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.YearMonth;

import br.jus.cjf.mineiro.model.OrdemServico;

public class OrdemServicoCommand {

	private YearMonth anoMes;

	private List<OrdemServico> ordemServicoList = new ArrayList<OrdemServico>();

	public YearMonth getAnoMes() {
		return anoMes;
	}

	public void setAnoMes(YearMonth anoMes) {
		this.anoMes = anoMes;
	}

	public List<OrdemServico> getOrdemServicoList() {
		return ordemServicoList;
	}

	public void setOrdemServicoList(List<OrdemServico> ordemServicoList) {
		this.ordemServicoList = ordemServicoList;
	}

	

}
