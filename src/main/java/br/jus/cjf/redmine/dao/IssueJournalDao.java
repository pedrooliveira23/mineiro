package br.jus.cjf.redmine.dao;

import java.util.List;





import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.redmine.model.journal.IssueJournal;

@Repository
public class IssueJournalDao {

	
	@Autowired
	@Qualifier("redmineSessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<IssueJournal> listarIssueJournal(Integer redmineIssueId){
		Session s = sessionFactory.getCurrentSession();
		Query query = s.createQuery("select ij from IssueJournal ij" +
				" inner join fetch ij.issue i " +
				" inner join fetch ij.user u " +
				" where i.id = :redmineIssueId");
		query.setParameter("redmineIssueId", redmineIssueId);
		
		return query.list();
		
		
	}
	
}
