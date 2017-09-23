package br.jus.cjf.mineiro.service;

import java.util.List;

import br.jus.cjf.mineiro.model.Inspecao;

public interface InspecaoService {

	Inspecao getInspecaoById(Integer id);

	List<Inspecao> listarInspecoesByRedmineIssueId(Integer redmineIssueId);

	void atualizarInspecao(Inspecao inspecao);

	void criarInspecao(Inspecao inspecao);

	void excluirInspecao(Inspecao inspecao);
	
}
