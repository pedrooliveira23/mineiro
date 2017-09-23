package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.Penalidade;

@Repository
public class PenalidadeDao {

	private final SessionFactory sessionFactory;

	@Autowired
	private PenalidadeDao(@Qualifier("mineiroSessionFactory")  SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	
	public Penalidade getPenalidade(Integer id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select p from Penalidade p where p.id = :id");
		query.setParameter("id", id);
		
		return (Penalidade) query.uniqueResult();
	}
	
	public Penalidade getPenalidade(String clausula, Contrato contrato){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select p from Penalidade p left join p.contrato c where p.clausula = :clausula and c=:contrato");
		query.setParameter("clausula", clausula);
		query.setParameter("contrato", contrato);
		
		return (Penalidade) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Penalidade> listarPenalidadePorContrato(String numero){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select p from Penalidade p left join fetch p.contrato c where c.numero = :numero");
		query.setParameter("numero", numero);
		
		return  query.list();
	}
	
	public void atualizarPenalidade(Penalidade penalidade){
		Session session = sessionFactory.getCurrentSession();
		session.update(penalidade);
	}

	public void criarPenalidade(Penalidade penalidade){
		Session session = sessionFactory.getCurrentSession();
		session.save(penalidade);
	}
	
	
	
	
}
