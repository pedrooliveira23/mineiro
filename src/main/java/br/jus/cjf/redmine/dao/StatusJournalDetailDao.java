package br.jus.cjf.redmine.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.redmine.model.Issue;
import br.jus.cjf.redmine.model.journal.StatusJournalDetail;

@Repository
public class StatusJournalDetailDao {

	@Autowired
	@Qualifier("redmineSessionFactory")
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<StatusJournalDetail> listarStatusDaIssue(Issue issue) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery(
				"select sjd from StatusJournalDetail sjd "
				+ "	left join fetch sjd.oldStatus os "
				+ "	left join fetch sjd.currentStatus cs "
				+ "	left join fetch sjd.issueJournal ij "
				+ "	left join ij.issue i "
				+ "where i = :issue " 
				+ "order by ij.createdOnDate");
		q.setParameter("issue", issue);
		return q.list();
	}
	
	
}
