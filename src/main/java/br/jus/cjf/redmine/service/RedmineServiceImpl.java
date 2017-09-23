package br.jus.cjf.redmine.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.redmine.dao.FieldDao;
import br.jus.cjf.redmine.dao.IssueJournalDao;
import br.jus.cjf.redmine.dao.IssuesDao;
import br.jus.cjf.redmine.dao.ProjectDao;
import br.jus.cjf.redmine.dao.StatusDao;
import br.jus.cjf.redmine.dao.StatusJournalDetailDao;
import br.jus.cjf.redmine.dao.TrackerDao;
import br.jus.cjf.redmine.model.Issue;
import br.jus.cjf.redmine.model.Project;
import br.jus.cjf.redmine.model.Status;
import br.jus.cjf.redmine.model.Tracker;
import br.jus.cjf.redmine.model.custom.IssueCustomValue;
import br.jus.cjf.redmine.model.custom.ProjectCustomValue;
import br.jus.cjf.redmine.model.journal.IssueJournal;
import br.jus.cjf.redmine.model.journal.StatusJournalDetail;

@Service
public class RedmineServiceImpl implements RedmineService{

	private final IssuesDao issuesDao;
	
	private final StatusDao statusDao;
	
	private final StatusJournalDetailDao statusJournalDetailDao;
	
	private final FieldDao fieldDao;
	
	private final ProjectDao projectDao;
	
	private final TrackerDao trackerDao;
	
	private final IssueJournalDao issueJournalDao;
	
	@Autowired
	public RedmineServiceImpl(IssuesDao issuesDao, StatusDao statusDao,
			StatusJournalDetailDao statusJournalDetailDao, FieldDao fieldDao,
			ProjectDao projectDao, TrackerDao trackerDao,
			IssueJournalDao issueJournalDao) {
		super();
		this.issuesDao = issuesDao;
		this.statusDao = statusDao;
		this.statusJournalDetailDao = statusJournalDetailDao;
		this.fieldDao = fieldDao;
		this.projectDao = projectDao;
		this.trackerDao = trackerDao;
		this.issueJournalDao = issueJournalDao;
	}

	@Override
	@Transactional("redmineTransactionManager")
	public List<Issue> listarIssuesFechadasDeAte(Date de, Date ate) {
	
		return issuesDao.listarIssuesFechadasDeAte(de, ate);
	}



        @Override
	@Transactional("redmineTransactionManager")
	public List<Issue> listarIssuesAbertas(Date data) {
	
		return issuesDao.listarIssuesAbertas(data);
	}



	@Override
	@Transactional("redmineTransactionManager")
	public List<Issue> listarIssuesApartir(Date data) {
	
		return issuesDao.listarIssuesApartir(data);
	}

	@Override
	@Transactional("redmineTransactionManager")
	public List<Issue> listarTodasIssues() {
	
		return issuesDao.listarTodasIssues();
	}

	@Override
	@Transactional("redmineTransactionManager")
	public Issue getIssue(Integer id) {
		
		return issuesDao.getIssue(id);
	}
        
        
        @Override
	@Transactional("redmineTransactionManager")
	public List<Issue> listarIssue(Integer issueId) {
		
		return issuesDao.listarIssue(issueId);
	}

	@Override
	@Transactional("redmineTransactionManager")
	public List<Status> listarStatuses() {
		
		return statusDao.listarStatuses();
	}

	@Override
	@Transactional("redmineTransactionManager")
	public Status getStatus(Integer id) {
	
		return statusDao.getStatus(id);
	}

	@Override
	@Transactional("redmineTransactionManager")
	public List<StatusJournalDetail> listarStatusDaIssue(Issue issue) {
		
		return statusJournalDetailDao.listarStatusDaIssue(issue);
	}

	@Override
	@Transactional("redmineTransactionManager")
	public Map<String,String> listarCamposPersonalizadosIssue(
			Integer redmineIssueId) {
		Map<String,String> issueCustomValues = new HashMap<String,String>();
		
		for(IssueCustomValue issueCustomValue: fieldDao.listarCamposPersonalizadosIssue(redmineIssueId)){
			if(issueCustomValue.getField().getName()!=null && issueCustomValue.getValue()!=null 
					&& !issueCustomValue.getField().getName().isEmpty() && !issueCustomValue.getValue().isEmpty()){
				issueCustomValues.put(issueCustomValue.getField().getName(), issueCustomValue.getValue());
			}
			
		}
		return issueCustomValues;
		
	}

	@Override
	@Transactional("redmineTransactionManager")
	public Map<String,String> listarCamposPersonalizadosProjeto(Integer redmineProjectId) {
		Map<String,String> projectCustomValues = new HashMap<String,String>();
		for(ProjectCustomValue projectCustomValue: fieldDao.listarCamposPersonalizadosProjeto(redmineProjectId)){
			
				projectCustomValues.put(projectCustomValue.getField().getName(), projectCustomValue.getValue());
			
		
		}
		
		return projectCustomValues;
	}
	
	@Override
	@Transactional("redmineTransactionManager")
	public List<Project> listarProjetosRedmine(){
		
		return projectDao.listarProjetosRedmine();
		
	}

	@Override
	@Transactional("redmineTransactionManager")
	public List<Tracker> listarTrackers() {
		
		return trackerDao.listarTrackers();
	}

	@Override
	@Transactional("redmineTransactionManager")
	public List<IssueJournal> listarIssueJournal(Integer redmineIssueId) {

		return issueJournalDao.listarIssueJournal(redmineIssueId);
	}

}
