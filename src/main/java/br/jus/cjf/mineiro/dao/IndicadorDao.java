package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.Indicador;

@Repository
public class IndicadorDao {

	
	private final SessionFactory sessionFactory;

	@Autowired
	private IndicadorDao(@Qualifier("mineiroSessionFactory")  SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	
	public Indicador getIndicador(String nomeTipoIndicador, Contrato contrato){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select i from Indicador i left join i.contrato c left join i.tipoIndicador ti where ti.nome = :nomeTipoIndicador and c=:contrato");
		query.setParameter("nomeTipoIndicador", nomeTipoIndicador);
		query.setParameter("contrato", contrato);
		
		return (Indicador) query.uniqueResult();
	}
	
	public Indicador getIndicador(Integer id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select i from Indicador i where i.id = :id");
		query.setParameter("id", id);
		
		return (Indicador) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Indicador> listarIndicadorPorContrato(String numero){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select i from Indicador i left join fetch i.contrato c where c.numero = :numero");
		query.setParameter("numero", numero);
		
		return  query.list();
	}
	
	public void atualizarIndicador(Indicador indicador){
		Session session = sessionFactory.getCurrentSession();
		session.update(indicador);
	}

	public void criarIndicador(Indicador indicador){
		Session session = sessionFactory.getCurrentSession();
		session.save(indicador);
	}
	
	
}
