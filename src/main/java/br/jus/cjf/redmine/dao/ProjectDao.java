package br.jus.cjf.redmine.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.redmine.model.Project;

@Repository
public class ProjectDao {

	@Autowired
	@Qualifier("redmineSessionFactory")
	private SessionFactory sessionFactory;

	
	@SuppressWarnings("unchecked")
	public List<Project> listarProjetosRedmine(){
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(
				"select distinct p from Project p");
		
		return query.list();
		
	}
	
	
	
	
}
