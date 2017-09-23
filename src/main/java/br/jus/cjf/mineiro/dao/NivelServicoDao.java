package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.Indicador;
import br.jus.cjf.mineiro.model.NivelServico;

@Repository
public class NivelServicoDao {

	
	private final SessionFactory sessionFactory;

	@Autowired
	private NivelServicoDao(@Qualifier("mineiroSessionFactory")  SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	
	public NivelServico getNivelServico(Integer id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select n from NivelServico n where n.id = :id");
		query.setParameter("id", id);
		
		return (NivelServico) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<NivelServico> listarNivelServicoPorIndicador(Indicador indicador){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select n from NivelServico n left join fetch n.indicador i where i.id = :id");
		query.setParameter("id", indicador.getId());
		
		return  query.list();
	}
	
	public void atualizarNivelServico(NivelServico nivelServico){
		Session session = sessionFactory.getCurrentSession();
		session.update(nivelServico);
	}

	public void criarNivelServico(NivelServico nivelServico){
		Session session = sessionFactory.getCurrentSession();
		session.save(nivelServico);
	}
	
	
}
