package br.jus.cjf.redmine.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.redmine.model.Tracker;

@Repository
public class TrackerDao {
	
	@Autowired
	@Qualifier("redmineSessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Tracker> listarTrackers() {
		Session s = sessionFactory.getCurrentSession();
		Query q = s.createQuery("select t from Tracker t order by t.id");
		return q.list();
	}

	public Tracker getTracker(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		return (Tracker) session.createCriteria(Tracker.class)
				.add(Restrictions.eq("id", id)).uniqueResult();
	}
	
}
