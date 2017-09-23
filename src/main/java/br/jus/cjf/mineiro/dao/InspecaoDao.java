package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.Inspecao;

@Repository
public class InspecaoDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public InspecaoDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public Inspecao getInspecaoById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct i from Inspecao i "+
				" where i.id = :id");
		query.setParameter("id", id);

		return (Inspecao) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Inspecao> listarInspecoesByRedmineIssueId(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select i from Inspecao i "
										  +"left join i.demanda d "
										  +"where d.redmineIssueId=:redmineIssueId");
		
		query.setParameter("redmineIssueId", redmineIssueId);
		
		return query.list();
	}
	
	public void criarInspecao(Inspecao inspecao) {
		
		Session session = sessionFactory.getCurrentSession();
		session.save(inspecao);
	}
	
	public void excluirInspecao(Inspecao inspecao) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(inspecao);
	}

	public void atualizarInspecao(Inspecao inspecao) {
		Session session = sessionFactory.getCurrentSession();
		session.update(inspecao);
	}
	


}
