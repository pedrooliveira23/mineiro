package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.model.Projeto;
import br.jus.cjf.redmine.model.Project;

public interface ProjetoService {

	Projeto getProjetoByRedmineProjectId(Integer redmineProjectId);
	
	Boolean projetoExiste(Integer redmineProjectId);

	void atualizarProjeto(Projeto projeto);

	void criarProjeto(Projeto projeto);
	
	void extrairProjeto(Project project);
	
}
