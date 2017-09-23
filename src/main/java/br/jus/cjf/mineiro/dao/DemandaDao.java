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

import br.jus.cjf.mineiro.model.Demanda;
import br.jus.cjf.mineiro.model.Inspecao;
import br.jus.cjf.spring.util.CustomWebAuthenticationDetails;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;

@Repository
public class DemandaDao {

	private final SessionFactory sessionFactory;
        //private Contrato contratoTela;
        //private final ContratoDao contratoDao;
        private final HttpSession session;

	@Autowired

        public DemandaDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory,HttpSession session) {
		super();
		this.sessionFactory = sessionFactory;
                this.session = session;
	}
        
        private Contrato getContrato() {
            //CustomWebAuthenticationDetails webAuthenticationDetails = ((CustomWebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails());
            //this.contratoTela = contratoDao.getContratoPorId(Integer.parseInt(webAuthenticationDetails.getnContrato()));
            return (Contrato)session.getAttribute("contratada");              
         }

	@SuppressWarnings("unchecked")
	public List<Demanda> listarDemandasAbertas() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Demanda.class).list();
	}

	public Demanda getDemandaById(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct d from Demanda d "
						+ " where d.id = :id");
		query.setParameter("id", id);

		return (Demanda) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public  List<Object[]> listarQuantidadePontosFuncaoPorMes(){
		
		 Session session = sessionFactory.getCurrentSession();
                 /*
		    Query query = session.createQuery("select month(d.dataFinalizacao), year(d.dataFinalizacao), sum(d.contagemDetalhada) from " +
		            " Demanda d where d.concluida = :concluida group by year(d.dataFinalizacao), month(d.dataFinalizacao) having sum(d.contagemDetalhada) > 0 " +
		            " order by year(d.dataFinalizacao) desc, month(d.dataFinalizacao) desc");
		    query.setParameter("concluida", true);
                 */
                 
                 Query query = session.createQuery("select month(d.dataFinalizacao), year(d.dataFinalizacao), sum(d.contagemDetalhada) from " +
		            " OrdemServico os left join os.demanda d where d.concluida = :concluida and os.contrato =:contrato group by year(d.dataFinalizacao), month(d.dataFinalizacao) having sum(d.contagemDetalhada) > 0 " +
		            " order by year(d.dataFinalizacao) desc, month(d.dataFinalizacao) desc");
                 
		 query.setParameter("concluida", true);
                 query.setParameter("contrato", getContrato());
		 List<Object[]> dados = query.list();
		 
		 return dados; 
	}
	
	
	public Date getDemandaMaisAtualizada() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct max(d.dataAtualizacao) from Demanda d");
		return ((DateTime) query.uniqueResult()).toDate();
	}

	public Demanda getDemandaByRedmineIssueId(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct d from Demanda d "
						+ " where d.redmineIssueId = :pRedmineIssueId");
		query.setParameter("pRedmineIssueId", redmineIssueId);

		return (Demanda) query.uniqueResult();
	}
        
        public Demanda getPrimeiraDemandaContrato(String Contratada) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
                        "select d from OrdemServico s "
			+" join s.demanda d "
                        +" join s.contrato c "
			+" where c.contratada = :pContratada"
                        +" order by d.dataCriacao" );
                query.setParameter("pContratada", Contratada);
                query.setMaxResults(1);
		

		return (Demanda) query.uniqueResult();
	}
        
	
	public Boolean demandaExiste(Integer redmineIssueId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct d from Demanda d "
						+ " where d.redmineIssueId = :redmineIssueId");
		query.setParameter("redmineIssueId", redmineIssueId);

		if(query.list().size()==0){
			return false;
		}
		else{
			return true;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Inspecao> listarInspecoesDemanda(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select d.inspecoes from Demanda d "
						+ " where d.id = :id");
		query.setParameter("id", id);

		return query.list();
	}
	
	public void atualizarConformidadeDemanda(Demanda demanda) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Demanda d set conformidadeDemanda = :pConformidadeDemanda "
				+ " where d.id = :pId");
		query.setParameter("pId", demanda.getId());
		query.setParameter("pConformidadeDemanda", demanda.getConformidade());
		query.executeUpdate();
	}
	
	public void atualizarQuantidadeRecusasDemanda(Demanda demanda) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Demanda d set quantidadeRecusas = :pQuantidadeRecusas"
				+ " where d.id = :pId");
		query.setParameter("pId", demanda.getId());
		query.setParameter("pQuantidadeRecusas", demanda.getQuantidadeRecusas());
		query.executeUpdate();
	}
	
	public void atualizarParaConcluidaDemanda(Demanda demanda) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Demanda d set d.concluida = :concluida"
				+ " where d.id = :id");
		query.setParameter("id", demanda.getId());
		query.setParameter("concluida", demanda.getConcluida());
		query.executeUpdate();
	}

	public void atualizarDemanda(Demanda demanda) {
		Session session = sessionFactory.getCurrentSession();
		session.update(demanda);
	}

	public void criarDemanda(Demanda demanda) {
		Session session = sessionFactory.getCurrentSession();
		session.save(demanda);
	}

	public void atualizarDemandasIncompletas() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update Demanda d set d.incompleta = false "
				+ " where d.contagemDetalhada > 0");
		System.out.println(query.executeUpdate());
	}


}
