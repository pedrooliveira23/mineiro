package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.Transicao;
import br.jus.cjf.mineiro.model.Estado;
import org.hibernate.annotations.OrderBy;

@Repository
public class TransicaoDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public TransicaoDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public Transicao getTransicaoById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t from Transicao t "
						+ " where t.id = :id");
		query.setParameter("id", id);

		return (Transicao) query.uniqueResult();
	}
	
	public Transicao getTransicaoMaisAtualizada(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t1 from Transicao t1 "+
				" left join t1.demanda d1 "+
				" where t1.dataInicio ="+
				" (select max(t2.dataInicio) from Transicao t2 "+
				" left join t2.demanda d2 "+
				" where d2.redmineIssueId = :pRedmineIssueId) and d1.redmineIssueId = :pRedmineIssueId");
		query.setParameter("pRedmineIssueId", redmineIssueId);	
		
		if(query.list().size() > 0){
			return (Transicao) query.list().get(0);
		}
		
		return null;
		
		
		
	}
	
	public Transicao getTransicaoHomologacaoAprovada(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t from Transicao t "+
				" where t.id=" +
				" (select max(t.id) from Transicao t "+
				" left join t.demanda d "+
				" left join t.estado e "+
				" where d.redmineIssueId = :redmineIssueId and e.nome = :estadoNome)");
		query.setParameter("redmineIssueId", redmineIssueId);	
		query.setParameter("estadoNome", "Homologação - Aprovada");	
				
		return (Transicao) query.uniqueResult();
	}
	
	
	public Transicao getTransicaoOrdemServicoDemandada(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t1 from Transicao t1 "+
				" left join t1.demanda d1 "+
				" where t1.dataInicio ="+
				" (select min(t2.dataInicio) from Transicao t2 "+
				" left join t2.demanda d2 "+
				" where d2.redmineIssueId = :pRedmineIssueId and t2.contaTempoOS = true) and d1.redmineIssueId = :pRedmineIssueId");
		query.setParameter("pRedmineIssueId", redmineIssueId);	
				
		return (Transicao) query.uniqueResult();
	}
	
	public Transicao getTransicaoOrdemServicoDemandadaMaisRecente(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t1 from Transicao t1 "+
				" where t1.id ="+
				" (select max(t2.id) from Transicao t2 "+
				" left join t2.demanda d2 "+
				" left join t2.estado e2 "+
				" where d2.redmineIssueId = :pRedmineIssueId and " +
				" (e2.nome = 'OS - Demandada' " +
				" or e2.nome = 'OS - Recebida'))");
		query.setParameter("pRedmineIssueId", redmineIssueId);	
				
		return (Transicao) query.uniqueResult();
	}
	
	public Transicao getPrimeiraTransicaoContaTempoOS(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t1 from Transicao t1 "+
				" where t1.id ="+
				" (select min(t2.id) from Transicao t2 "+
				" left join t2.demanda d2 "+
				" where d2.redmineIssueId = :redmineIssueId and " +
				" t2.contaTempoOS = true)");
		query.setParameter("redmineIssueId", redmineIssueId);	
				
		return (Transicao) query.uniqueResult();
	}
	
        public Transicao getTransicaoOrdemServicoDemandadaMaisAntigaValida(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t1 from Transicao t1 "+
				" where t1.id ="+
				" (select min(t2.id) from Transicao t2 "+
				" left join t2.demanda d2 "+
				" left join t2.estado e2 "+
				" where d2.redmineIssueId = :pRedmineIssueId and " +
				" (e2.nome = 'OS - Demandada' " +
				" or e2.nome = 'OS - Recebida')and t2.contaTempoOS = true) ");
		query.setParameter("pRedmineIssueId", redmineIssueId);	
				
		return (Transicao) query.uniqueResult();
	}
	
	public Transicao getUltimaTransicaoContaTempoOS(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t1 from Transicao t1 "+
				" where t1.id ="+
				" (select max(t2.id) from Transicao t2 "+
				" left join t2.demanda d2 "+
				" where d2.redmineIssueId = :redmineIssueId and " +
				" t2.contaTempoOS = true)");
		query.setParameter("redmineIssueId", redmineIssueId);	
				
		return (Transicao) query.uniqueResult();
	}
	
	public Transicao getTransicao(Integer redmineIssueId, Estado estado, DateTime dataInicio) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t from Transicao t "+
				" left join t.demanda d "+
				" left join t.estado e "+
				" where d.redmineIssueId = :redmineIssueId " +
				" and t.dataInicio = :dataInicio" +
				" and e.id = :estadoId");
		query.setParameter("redmineIssueId", redmineIssueId);
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("estadoId", estado.getId());
		return (Transicao) query.uniqueResult();
	}
	
	public Integer getQuantidadeRecusasDemanda(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select count (t) from Transicao t "+
				" left join t.demanda d "+
				" left join t.estado e " +
				" where t.contaRecusaOS = :contaRecusaOS " +
				" and d.redmineIssueId = :redmineIssueId ");
		query.setParameter("redmineIssueId", redmineIssueId);
		query.setParameter("contaRecusaOS", true);
	 return ((Long) query.uniqueResult()).intValue();
		
	}
	
	public Boolean existeTransicao(Integer redmineIssueId, Estado estado, DateTime dataInicio) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t from Transicao t "+
				" left join t.demanda d "+
				" left join t.estado e "+
				" where d.redmineIssueId = :redmineIssueId " +
				" and t.dataInicio = :dataInicio" +
				" and e.id = :estadoId");
		query.setParameter("redmineIssueId", redmineIssueId);
		query.setParameter("dataInicio", dataInicio);
		query.setParameter("estadoId", estado.getId());
		if(query.list().size()==0){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	
	
	
	@SuppressWarnings("unchecked")
	public List<Transicao> listarTransicao() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Transicao.class).list();
	}

	@SuppressWarnings("unchecked")
	public List<Transicao> listarTransicaoByDemandaId(Integer demandaId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select distinct t from Transicao t "
						+ " left join fetch t.demanda d"
						+ " where d.id = :demandaId"
                                                + " order by t.id");
		query.setParameter("demandaId", demandaId);

		return query.list();
	}

	@SuppressWarnings("unchecked")
        @org.hibernate.annotations.OrderBy(clause = "id")
	public List<Transicao> listarTransicaoByRedmineIssueId(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("select distinct t from Transicao t "
						+ "left join fetch t.demanda d"
						+ " where d.redmineIssueId = :pRedmineIssueId"
                                                + " order by t.id");
		query.setParameter("pRedmineIssueId", redmineIssueId);

		return query.list();
	}
	
	public void atualizarTransicao(Transicao transicao) {
		
		Session session = sessionFactory.getCurrentSession();
		session.merge(transicao);
	}

	public void criarTransicao(Transicao transicao) {
		Session session = sessionFactory.getCurrentSession();
		session.save(transicao);
	}


}