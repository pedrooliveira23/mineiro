package br.jus.cjf.mineiro.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.Projeto;

@Repository
public class ProjetoDao {
	
	private final SessionFactory sessionFactory;

	@Autowired
	public ProjetoDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	public Projeto getProjetoByRedmineProjectId(Integer redmineProjectId){
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				" select p from Projeto p "+
				" where p.redmineProjectId = :redmineProjectId");
		query.setParameter("redmineProjectId", redmineProjectId);
		return (Projeto) query.uniqueResult();
		
		
	}
	
	public Boolean projetoExiste(Integer redmineProjectId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct p from Projeto p "
						+ " where p.redmineProjectId = :redmineProjectId");
		query.setParameter("redmineProjectId", redmineProjectId);

		if(query.list().size()==0){
			return false;
		}
		else{
			return true;
		}
	}

	public void atualizarProjeto(Projeto projeto){
		Session session = sessionFactory.getCurrentSession();
		session.update(projeto);
	}

	public void criarProjeto(Projeto projeto){
		Session session = sessionFactory.getCurrentSession();
		session.save(projeto);
	}

	
	
}
