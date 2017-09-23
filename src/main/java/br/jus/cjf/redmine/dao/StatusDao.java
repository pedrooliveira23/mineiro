package br.jus.cjf.redmine.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.redmine.model.Status;

@Repository
public class StatusDao {

	@Autowired
	@Qualifier("redmineSessionFactory")
	private SessionFactory sessionFactory;


	@SuppressWarnings("unchecked")
	public List<Status> listarStatuses() {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("select s from Status s order by s.id");
		return q.list();
	}

	public Status getStatus(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return (Status) session.createCriteria(Status.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
	}

}
