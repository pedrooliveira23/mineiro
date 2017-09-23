package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.TipoIndicador;

@Repository
public class TipoIndicadorDao {

	
	private final SessionFactory sessionFactory;

	@Autowired
	private TipoIndicadorDao(@Qualifier("mineiroSessionFactory")  SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	
	public TipoIndicador getTipoIndicador(Integer id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select ti from TipoIndicador ti where ti.id = :id");
		query.setParameter("id", id);
		
		return (TipoIndicador) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoIndicador> listarTipoIndicador(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select ti from TipoIndicador ti");
		
		return  query.list();
	}
	
	public void atualizarTipoIndicador(TipoIndicador tipoIndicador){
		Session session = sessionFactory.getCurrentSession();
		session.update(tipoIndicador);
	}

	public void criarTipoIndicador(TipoIndicador tipoIndicador){
		Session session = sessionFactory.getCurrentSession();
		session.save(tipoIndicador);
	}
	
	
}
