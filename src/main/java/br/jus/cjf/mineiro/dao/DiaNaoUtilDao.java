package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.DiaNaoUtil;

@Repository
public class DiaNaoUtilDao {
   
	private final SessionFactory sessionFactory;

	@Autowired
	public DiaNaoUtilDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	public void salvar(DiaNaoUtil diaNaoUtil) {
		Session s = sessionFactory.getCurrentSession();
		s.save(diaNaoUtil);
	}
	
	public void atualizar(DiaNaoUtil diaNaoUtil) {
		Session s = sessionFactory.getCurrentSession();
		s.update(diaNaoUtil);
	}
	
	public void excluir(DiaNaoUtil diaNaoUtil) {
		Session s = sessionFactory.getCurrentSession();
		s.delete(diaNaoUtil);
	}
	
	public void alternarAtivo(DiaNaoUtil diaNaoUtil) {
		

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("update DiaNaoUtil dia set ativo = :ativo where dia = :dia");
		query.setParameter("ativo", diaNaoUtil.getAtivo());
		query.setParameter("dia", diaNaoUtil);
		query.executeUpdate();
		
	}
	
	public boolean ehDiaUtil(LocalDate dia) {
		
		Session session = sessionFactory.getCurrentSession();
		Query queryDiasFixos = session.createQuery(
				"select distinct 1 from DiaNaoUtil d "
				+" where d.anual = true "
				+" 	and month(d.dia) = :mes "
				+" 	and day(d.dia) = :dia ");
		
		queryDiasFixos.setParameter("mes", dia.getMonthOfYear());
		queryDiasFixos.setParameter("dia", dia.getDayOfMonth());
		
		
		Query queryDiasMoveis = session.createQuery(
				"select distinct 1 from DiaNaoUtil d "
				+" where d.anual = false "
				+" 	and year(d.dia) = :ano "
				+" 	and month(d.dia) = :mes "
				+" 	and day(d.dia) = :dia ");
		queryDiasMoveis.setParameter("ano", dia.getYear());
		queryDiasMoveis.setParameter("mes", dia.getMonthOfYear());
		queryDiasMoveis.setParameter("dia", dia.getDayOfMonth());
		
		return queryDiasFixos.uniqueResult() == null && queryDiasMoveis.uniqueResult() == null;
	}
	
	@SuppressWarnings("unchecked")
	public List<DiaNaoUtil> getDiasNaoUteis() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(DiaNaoUtil.class).addOrder(Order.asc("dia")).list();
	}
	
}
