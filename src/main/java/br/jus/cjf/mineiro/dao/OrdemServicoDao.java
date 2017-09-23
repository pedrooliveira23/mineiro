package br.jus.cjf.mineiro.dao;

import br.jus.cjf.mineiro.model.Contrato;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.OrdemServico;
import br.jus.cjf.mineiro.model.Situacao;
import br.jus.cjf.spring.util.CustomWebAuthenticationDetails;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;


@Repository
public class OrdemServicoDao {

	private final SessionFactory sessionFactory;
        //private Contrato contratoTela;
       // private final ContratoDao contratoDao;
        private final HttpSession session;

	@Autowired
	public OrdemServicoDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory, HttpSession session) {
		super();
		this.sessionFactory = sessionFactory;
                this.session = session;

	}
        
        public Contrato getContrato() {
            //CustomWebAuthenticationDetails webAuthenticationDetails = ((CustomWebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails());
            //this.contratoTela = contratoDao.getContratoPorId(Integer.parseInt(webAuthenticationDetails.getnContrato()));
            return (Contrato)session.getAttribute("contratada");            
         }
        
        



	public OrdemServico getOrdemServicoById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct o from OrdemServico o "
						+ " where o.id = :id");
		query.setParameter("id", id);

		return (OrdemServico) query.uniqueResult();
	}
        
        public OrdemServico getOrdemServicoByDemandaId(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct o from OrdemServico o "
						+ " where o.demanda.id = :id");
		query.setParameter("id", id);

		return (OrdemServico) query.uniqueResult();
	}
	
	public OrdemServico getOrdemServicoByRedmineIssueId(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct o from OrdemServico o "+
				"left join o.demanda d "+
				" where d.redmineIssueId = :redmineIssueId");
		query.setParameter("redmineIssueId", redmineIssueId);

		return (OrdemServico) query.uniqueResult();
	}
	
	public OrdemServico getOrdemServicoByRedmineIssueIdDetached(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct o from OrdemServico o "+
				"left join o.demanda d "+
				" where d.redmineIssueId = :redmineIssueId");
		query.setParameter("redmineIssueId", redmineIssueId);

		return (OrdemServico) query.uniqueResult();
	}
	
	public Boolean existeOrdemServicoNormalByRedmineIssueId(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct o from OrdemServico o "+
				"left join o.demanda d "+
				" where d.redmineIssueId = :redmineIssueId");
		query.setParameter("redmineIssueId", redmineIssueId);
		if(query.list().size()==0){
			return false;
		}
		else{
			return true;
		}
	}

	
	public Boolean existeOrdemServico() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct o from OrdemServico o");
		if(query.list().size()==0){
			return false;
		}
		else{
			return true;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServico() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d" +
				" order by d.redmineIssueId");
		return query.list();
	}
        
        
        @SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoContrato() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d" +
                                " where os.contrato = :contrato "    + 
				" order by d.redmineIssueId");
                
                query.setParameter("contrato", getContrato());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServico(Situacao situacao) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d" +
				" where os.situacao = :situacao "+
                                " and os.contrato = :contrato "    +        
				" order by d.redmineIssueId");
		query.setParameter("situacao", situacao);
                query.setParameter("contrato", getContrato());
		return query.list();
	}
        
        
        @SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoTodosContratos(Situacao situacao) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d" +
				" where os.situacao = :situacao "+      
				" order by d.redmineIssueId");
		query.setParameter("situacao", situacao);
		return query.list();
	}
        
        
        @SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServico(Contrato contrato) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d left join fetch d.tempoRoles tr left join fetch tr.role r" +
				" where os.contrato = :contrato "    +        
				" order by d.redmineIssueId");
                query.setParameter("contrato", contrato);
		return query.list();
	}
        
        
	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoExcetoSituacao(Situacao situacao) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d" +
				" where os.situacao != :situacao "+
                                 " and os.contrato = :contrato "    +                
				" order by d.redmineIssueId");
		query.setParameter("situacao", situacao);
                 query.setParameter("contrato", getContrato());
		return query.list();
	}
	

	public DateTime getDataCriacaoOrdemServicoMaisAntiga(Situacao situacao) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select min(d.dataCriacao) from OrdemServico os left join os.demanda d" +
				" where os.situacao = :situacao " +
                               " and os.contrato = :contrato "    +           
				" order by d.redmineIssueId");
		query.setParameter("situacao", situacao);
                query.setParameter("contrato", getContrato());
		return (DateTime) query.uniqueResult();
	}
	

	public DateTime getDataCriacaoOrdemServicoMaisAntiga() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select min(d.dataCriacao) from OrdemServico os left join os.demanda d " +
                                 " where os.contrato = :contrato "    +                 
				" order by d.redmineIssueId");
                query.setParameter("contrato", getContrato());
		return (DateTime) query.uniqueResult();
	}
	
	
	public DateTime getDataFinalizacaoOrdemServicoMaisAntiga(Situacao situacao) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select min(d.dataFinalizacao) from OrdemServico os left join os.demanda d" +
				" where os.situacao = :situacao " +
                                  " and os.contrato = :contrato "    +              
				" order by d.redmineIssueId");
		query.setParameter("situacao", situacao);
                 query.setParameter("contrato", getContrato());
		return (DateTime) query.uniqueResult();
	}
	

	public DateTime getDataFinalizacaoOrdemServicoMaisAntiga() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select min(d.dataFinalizacao) from OrdemServico os"
                             + " left join os.demanda d " +
                                " where os.contrato = :contrato "    +     
				" order by d.redmineIssueId");
                query.setParameter("contrato", getContrato());
		return (DateTime) query.uniqueResult();
	}
	
	public DateTime getDataCriacaoOrdemServicoIncompletaMaisAntiga() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select min(d.dataCriacao) from OrdemServico os left join os.demanda d" +
				" where d.incompleta = :incompleta " +
                                " and os.contrato = :contrato "    +                  
				" order by d.redmineIssueId");
		query.setParameter("incompleta", true);
                query.setParameter("contrato", getContrato());
		return (DateTime) query.uniqueResult();
	}
	
	public DateTime getDataFinalizacaoOrdemServicoIncompletaMaisAntiga() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select min(d.dataFinalizacao) from OrdemServico os left join os.demanda d" +
				" where d.incompleta = :incompleta " +
                                " and os.contrato = :contrato "    +              
				" order by d.redmineIssueId");
		query.setParameter("incompleta", true);
                query.setParameter("contrato", getContrato());
		return (DateTime) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoPorDataCriacao(DateTime de, DateTime ate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d"
				+" where d.dataCriacao >= :de "
				+" and d.dataCriacao <= :ate "
                                +" and os.contrato = :contrato "         
				+" order by d.redmineIssueId");
		query.setParameter("de", de);
		query.setParameter("ate", ate);
                query.setParameter("contrato", getContrato());
		return query.list();
	}
        
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoPorDataFinalizacao(DateTime de, DateTime ate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d"
				+" where d.dataFinalizacao >= :de "
				+" and d.dataFinalizacao <= :ate "
                                +" and os.contrato = :contrato "                
				+" order by d.redmineIssueId");
		query.setParameter("de", de);
		query.setParameter("ate", ate);
                query.setParameter("contrato", getContrato());
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoPorDataCriacao(DateTime de, DateTime ate, Situacao situacao) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d"
				+" where d.dataCriacao >= :de "
				+" and d.dataCriacao <= :ate "
				+" and os.situacao = :situacao "
                                +" and os.contrato = :contrato "                 
				+" order by d.redmineIssueId");
		query.setParameter("de", de);
		query.setParameter("ate", ate);
		query.setParameter("situacao", situacao);
                query.setParameter("contrato", getContrato());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoPorDataFinalizacao(DateTime de, DateTime ate, Situacao situacao) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d"
				+" where d.dataFinalizacao >= :de "
				+" and d.dataFinalizacao <= :ate "
				+" and os.situacao = :situacao "
                                +" and os.contrato = :contrato "                
				+" order by d.redmineIssueId");
		query.setParameter("de", de);
		query.setParameter("ate", ate);
		query.setParameter("situacao", situacao);
                query.setParameter("contrato", getContrato());
		return query.list();
	}
        
        	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoIncompleta() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d"
				+" where d.incompleta is :incompleta and os.contrato = :contrato order by d.redmineIssueId");
		query.setParameter("incompleta", true);
                query.setParameter("contrato", getContrato());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoIncompletaPorDataCriacao(DateTime de, DateTime ate, Boolean incompleta) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d"
				+" where d.dataCriacao >= :de "
				+" and d.dataCriacao <= :ate "
				+" and d.incompleta is :incompleta"
                                +" and os.contrato = :contrato "              
				+" order by d.redmineIssueId");
		query.setParameter("de", de);
		query.setParameter("ate", ate);
		query.setParameter("incompleta", incompleta);
                query.setParameter("contrato", getContrato());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoIncompletaPorDataFinalizacao(DateTime de, DateTime ate, Boolean incompleta) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d"
				+" where d.dataFinalizacao >= :de "
				+" and d.dataFinalizacao <= :ate "
				+" and d.incompleta is :incompleta"
                                +" and os.contrato = :contrato "          
				+" order by d.redmineIssueId");
		query.setParameter("de", de);
		query.setParameter("ate", ate);
		query.setParameter("incompleta", incompleta);
                query.setParameter("contrato", getContrato());
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoDeAte(Date de, Date ate) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery(
				"select distinct os from OrdemServico os left join fetch os.demanda d"
				+ "where os.concluida = :concluida " 
				+ "	and os.dataCriacao >= :from "
				+ "	and os.dataCriacao < :to and os.contrato = :contrato order by d.redmineIssueId");
                                
		q.setBoolean("concluida", true);
		q.setDate("from", de);
		q.setDate("to", ate);
                q.setParameter("contrato", getContrato());
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoVerificacao() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os " +
				" left join fetch os.projeto p" +
				" left join fetch os.contrato c " +
				" left join fetch os.demanda d" +
				" left join fetch d.tipoDemanda td" +
				" where os.situacao = :situacao "+
                              //  " and os.contrato = :contrato " +        
				" order by d.redmineIssueId");
		query.setParameter("situacao", Situacao.ABERTA);
               // query.setParameter("contrato", getContrato());
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdemServico> listarOrdemServicoGrafico() {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct os from OrdemServico os " +
				" left join fetch os.projeto p" +
				" left join fetch os.contrato c" +
				" left join fetch os.demanda d" +
				" left join fetch d.tipoDemanda td" +
                                " left join fetch d.transicoes t" +
                                " left join fetch t.estado e" +
				" where os.contrato = :contrato and os.situacao != :situacao "+
                                " order by d.redmineIssueId");
		query.setParameter("situacao", Situacao.CANCELADA);
                query.setParameter("contrato", getContrato());
		return query.list();
	}
	
	public void precificarOrdemServico(OrdemServico ordemServico) {
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update OrdemServico o set o.precificada = :precificada"
				+ " where o.id = :id");
		query.setParameter("id", ordemServico.getId());
		query.setParameter("precificada", true);
		query.executeUpdate();
	}

	public void atualizarOrdemServico(OrdemServico ordemServico) {
		Session session = sessionFactory.getCurrentSession();
		session.update(ordemServico);
	}

	public void criarOrdemServico(OrdemServico ordemServico) {
		Session session = sessionFactory.getCurrentSession();
		session.save(ordemServico);
	}

	public void excluir(OrdemServico ordemServico) {
		Session s = sessionFactory.getCurrentSession();
		s.delete(ordemServico);
                
	}
        
  

}
