package br.jus.cjf.redmine.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import br.jus.cjf.redmine.model.Issue;
import br.jus.cjf.redmine.model.Project;
import br.jus.cjf.redmine.model.Status;
import br.jus.cjf.redmine.model.Tracker;
import br.jus.cjf.redmine.model.journal.IssueJournal;
import br.jus.cjf.redmine.model.journal.StatusJournalDetail;

public interface RedmineService {

	List<Issue> listarIssuesFechadasDeAte(Date de, Date ate);
        
        List<Issue> listarIssuesAbertas(Date data);
	
	List<Issue> listarIssuesApartir(Date data);
        
        List<Issue> listarIssue(Integer issueId);
	
	List<Issue> listarTodasIssues();
	
	Issue getIssue(Integer id);
	
	List<Status> listarStatuses();
	
	List<Tracker> listarTrackers();

	Status getStatus(Integer id);
	
	List<StatusJournalDetail> listarStatusDaIssue(Issue issue);
	
	List<IssueJournal> listarIssueJournal(Integer redmineIssueId);
	
	Map<String,String> listarCamposPersonalizadosIssue(Integer redmineIssueId);

	Map<String,String> listarCamposPersonalizadosProjeto(Integer redmineProjectId);
	
	List<Project> listarProjetosRedmine();

}
