package br.jus.cjf.mineiro.service;

import java.util.List;

import br.jus.cjf.redmine.model.Issue;
import br.jus.cjf.redmine.model.Project;
import br.jus.cjf.redmine.model.Status;
import br.jus.cjf.redmine.model.Tracker;

public interface ExtratorService {


	void extrairAutomaticamente();
	
	void extrairManualmente();

	void extractRedmineStatuses();

	void extractRedmineIssues();
        
        void extractRedmineIssue(Integer redmineIssueId);
	
	void extractRedmineTrackers();
	
	void extractRedmineProjects();
	
	void transformRedmineStatusToEstado(List<Status> statuses);

	void transformRedmineIssueToOrdemServico(List<Issue> issues, boolean forcarAtualizacao);
	
	void transformRedmineProjectToProjeto(List<Project> projects);
	
	void transformRedmineTrackerToTipoDemanda(List<Tracker> trackers);

	void calcularDataEstimadaDemanda();
        
        public void calcularTempoMedioNoEstadoPorPF();
        
        public void calcularTempoRolesDemanda() ;

}
