package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.TipoDemanda;

@Repository
public class TipoDemandaDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public TipoDemandaDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public TipoDemanda getTipoDemanda(Integer redmineTrackerId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t from TipoDemanda t "
						+ " where t.redmineTrackerId = :pRedmineTrackerId");
		query.setParameter("pRedmineTrackerId", redmineTrackerId);

		return (TipoDemanda) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoDemanda> listarTiposDemanda() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(TipoDemanda.class).addOrder(Order.asc("nome")).list();
	}

	
	
	public Boolean existeTipoDemanda(Integer redmineTrackerId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t from TipoDemanda t "
						+ " where t.redmineTrackerId = :pRedmineTrackerId");
		query.setParameter("pRedmineTrackerId", redmineTrackerId);

		if(query.list().size()==0){
			return false;
		}
		else{
			return true;
		}
	}
	

	
	public void criarTipoDemanda(TipoDemanda tipoDemanda) {
		Session session = sessionFactory.getCurrentSession();
		session.save(tipoDemanda);
	}

	public void atualizarTipoDemanda(TipoDemanda tipoDemanda) {
		Session session = sessionFactory.getCurrentSession();
		session.update(tipoDemanda);
	}

	
}
