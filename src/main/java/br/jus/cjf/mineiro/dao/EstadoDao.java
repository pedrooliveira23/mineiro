package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.Estado;

@Repository
public class EstadoDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public EstadoDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Estado> listarEstados() {
		Session session = sessionFactory.getCurrentSession();
		Query query  = session.createQuery("select distinct e from Estado e "
				+ " order by e.nome");
		return query.list();
	}
	


	public Estado getEstadoByRedmineStatusId(Integer redmineStatusId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select distinct e from Estado e "
				+ " where e.redmineStatusId = :redmineStatusId");
		query.setParameter("redmineStatusId", redmineStatusId);

		return (Estado) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Estado> listarEstadosQueSensibilizamPrazo() {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("select e from Estado e "
				+ " where e.contaTempoOS = true)");
		return q.list();
	}
        
        @SuppressWarnings("unchecked")
	public Estado getEstadosPorNome(String nome) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("select e from Estado e "
				+ " where e.nome = :nome)");
                q.setParameter("nome", nome);
		return (Estado)q.uniqueResult();
	}

	public Boolean estadoExiste(Integer redmineStatusId) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select e from Estado e where e.redmineStatusId = :redmineStatusId");
		query.setParameter("redmineStatusId", redmineStatusId);
		if(query.list().size()==0){
			return false;
		}
		else{
			return true;
		}	
	}

	public void atualizarEstado(Estado estado) {
		Session session = sessionFactory.getCurrentSession();
		session.merge(estado);
	}

	public void criarEstado(Estado estado) {
		Session session = sessionFactory.getCurrentSession();
		session.save(estado);
	}


}
