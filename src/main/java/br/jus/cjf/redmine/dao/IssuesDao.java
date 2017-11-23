package br.jus.cjf.redmine.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.redmine.model.Issue;

@Repository
public class IssuesDao {

	@Autowired
	@Qualifier("redmineSessionFactory")
	private SessionFactory sessionFactory;


	
	/**
	 * Retorna os chamados que foram fechados no Redmine desde a data from (inclusivo) 
	 * até a to (exclusivo) 
	 * @param from Date com a data inicial (inclusiva >=)
	 * @param to Date com a data final (exclusiva <)
	 * @return Todos os chamados fechados nesse período.
	 */
	@SuppressWarnings("unchecked")
	public List<Issue> listarIssuesFechadasDeAte(Date de, Date ate) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery(
				"select distinct i from StatusJournalDetail sjd "
				+ "	left join sjd.issueJournal ij "
				+ "	left join ij.issue i "
				+ "	left join fetch i.project "
				+ "	left join fetch i.author "
				+ "	left join fetch i.tracker t"
				+ "	left join fetch i.assignedTo "
				+ "	left join fetch i.parent "
				+ "	left join fetch i.status "
				+ "	left join fetch i.version "
				+ " where sjd.currentStatus.closed = :closed " 
				+ "	and ij.createdOnDate >= :from "
				+ "	and ij.createdOnDate < :to "
				+ " and t.name in('Manutenção Corretiva - Garantia','Verificação de Erro','Manutenção Corretiva - Sem Solução de Contorno','Manutenção Corretiva - Com Solução de Contorno' , 'Manutenção Evolutiva','Manutenção Adaptativa',"
				+ " 'Desenvolvimento - Inicial','Desenvolvimento - Intermediária - Normal','Desenvolvimento - Intermediária - Arquitetura')"
				+ " order by i.id");
		q.setBoolean("closed", true);
		q.setDate("from", de);
		q.setDate("to", ate);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Issue> listarIssuesApartir(Date data) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery(
				"select distinct i from StatusJournalDetail sjd "
				+ "	left join sjd.issueJournal ij "
				+ "	left join ij.issue i "
				+ "	left join fetch i.project "
				+ "	left join fetch i.author "
				+ "	left join fetch i.tracker t"
				+ "	left join fetch i.assignedTo "
				+ "	left join fetch i.parent "
				+ "	left join fetch i.status "
				+ "	left join fetch i.version "
				+ "	where ij.createdOnDate > :from "
				+ " and t.name in('Manutenção Corretiva - Garantia','Verificação de Erro','Manutenção Corretiva - Sem Solução de Contorno','Manutenção Corretiva - Com Solução de Contorno' , 'Manutenção Evolutiva','Manutenção Adaptativa',"
				+ " 'Desenvolvimento - Inicial','Desenvolvimento - Intermediária - Normal','Desenvolvimento - Intermediária - Arquitetura')"
				+ " order by i.id");
		q.setDate("from", data);
		return q.list();
	}
        
        
        
        @SuppressWarnings("unchecked")
	public List<Issue> listarIssuesAbertas(Date data) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery(
				"select distinct i from StatusJournalDetail sjd "
				+ "	left join sjd.issueJournal ij "
				+ "	left join ij.issue i "
				+ "	left join fetch i.project "
				+ "	left join fetch i.author "
				+ "	left join fetch i.tracker t"
				+ "	left join fetch i.assignedTo "
				+ "	left join fetch i.parent "
				+ "	left join fetch i.status s "
				+ "	left join fetch i.version "
				+ "	where  (s.closed=false or ij.createdOnDate > :from) " 
                                + "     and t.name in('Manutenção Corretiva - Garantia','Verificação de Erro','Manutenção Corretiva - Sem Solução de Contorno','Manutenção Corretiva - Com Solução de Contorno' , 'Manutenção Evolutiva','Manutenção Adaptativa',"
				+ " 'Desenvolvimento - Inicial','Desenvolvimento - Intermediária - Normal','Desenvolvimento - Intermediária - Arquitetura')"
				+ " order by i.id");
                q.setDate("from", data);
		return q.list();
	}
        
        
        @SuppressWarnings("unchecked")
	public List<Issue> listarIssue(Integer issueId) {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery(
				"select distinct i from StatusJournalDetail sjd "
				+ "	left join sjd.issueJournal ij "
				+ "	left join ij.issue i "
				+ "	left join fetch i.project "
				+ "	left join fetch i.author "
				+ "	left join fetch i.tracker t"
				+ "	left join fetch i.assignedTo "
				+ "	left join fetch i.parent "
				+ "	left join fetch i.status "
				+ "	left join fetch i.version "
				+ "	where  i.id = :id     "
				//+ " and t.name in('Manutenção Corretiva - Garantia','Manutenção Corretiva - Sem Solução de Contorno','Manutenção Corretiva - Com Solução de Contorno' , 'Manutenção Evolutiva','Manutenção Adaptativa',"
				//+ " 'Desenvolvimento - Inicial','Desenvolvimento - Intermediária - Normal','Desenvolvimento - Intermediária - Arquitetura')"
				+ " order by i.id");
		q.setInteger("id", issueId);
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Issue> listarTodasIssues() {
		Session s = sessionFactory.getCurrentSession();
//		Query q = s.createQuery(
//				"select distinct i from StatusJournalDetail sjd "
//				+ "	left join sjd.issueJournal ij "
//				+ "	left join ij.issue i "
//				+ "	left join fetch i.project "
//				+ "	left join fetch i.author "
//				+ "	left join fetch i.tracker t"
//				+ "	left join fetch i.assignedTo "
//				+ "	left join fetch i.parent "
//				+ "	left join fetch i.status "
//				+ "	left join fetch i.version "
//				+ " where t.name in('Manutenção Corretiva - Garantia','Verificação de Erro','Manutenção Corretiva - Sem Solução de Contorno','Manutenção Corretiva - Com Solução de Contorno' , 'Manutenção Evolutiva','Manutenção Adaptativa',"
//				+ " 'Desenvolvimento - Inicial','Desenvolvimento - Intermediária - Normal','Desenvolvimento - Intermediária - Arquitetura')"
//				+ " order by i.id");
		Query q = s.createQuery(
				"SELECT DISTINCT i.*" +
						"FROM issues i" +
						"LEFT JOIN trackers t on i.tracker_id = t.id" +
						"WHERE t.name in('Manutenção Corretiva - Garantia','Verificação de Erro','Manutenção Corretiva - Sem Solução de Contorno','Manutenção Corretiva - Com Solução de Contorno' , 'Manutenção Evolutiva','Manutenção Adaptativa','Desenvolvimento - Inicial','Desenvolvimento - Intermediária - Normal','Desenvolvimento - Intermediária - Arquitetura')" +
						"ORDER BY i.id");
		return q.list();
	}
	
	public Issue getIssue(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return  (Issue) session.createCriteria(Issue.class).add(Restrictions.eq("id",id)).uniqueResult();
	}
	
}
