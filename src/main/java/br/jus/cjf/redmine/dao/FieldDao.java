package br.jus.cjf.redmine.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.redmine.model.custom.IssueCustomValue;
import br.jus.cjf.redmine.model.custom.ProjectCustomValue;

@Repository
public class FieldDao {

	@Autowired
	@Qualifier("redmineSessionFactory")
	private SessionFactory sessionFactory;

	
	@SuppressWarnings("unchecked")
	public List<IssueCustomValue> listarCamposPersonalizadosIssue(Integer redmineIssueId) {
		
		Session s = sessionFactory.getCurrentSession();
		
		Query query = s.createQuery(
				" select distinct icv from IssueCustomValue icv" +
				" inner join fetch icv.field f" +
				" inner join fetch icv.issue i" +
				" where i.id = :issueId");
		query.setParameter("issueId", redmineIssueId);
		
		return query.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<ProjectCustomValue> listarCamposPersonalizadosProjeto(Integer redmineProjectId) {
		
		Session s = sessionFactory.getCurrentSession();
		
		Query query = s.createQuery(
				" select distinct pcv from ProjectCustomValue pcv" +
				" inner join fetch pcv.field f" +
				" inner join fetch pcv.project p" +
				" where p.id = :projectId");
		query.setParameter("projectId", redmineProjectId);
		
		return query.list();
		
	}
	
}
