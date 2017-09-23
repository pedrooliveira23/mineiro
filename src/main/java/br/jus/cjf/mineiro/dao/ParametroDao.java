/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.cjf.mineiro.dao;

import br.jus.cjf.mineiro.model.Parametro;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diogo.araujo
 */
@Repository
public class ParametroDao {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
	public ParametroDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
        
        @SuppressWarnings("unchecked")
	public List<Parametro> listarParametro() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Parametro.class).addOrder(Order.asc("nome")).list();
	}
        
        @SuppressWarnings("unchecked")
	public Parametro getParametro(String nome) {
		Session session = sessionFactory.getCurrentSession();
		return (Parametro) session.createCriteria(Parametro.class).add(Restrictions.eq("nome", nome)).uniqueResult();
	}
        
        public void criarParametro(Parametro parametro) {
		Session session = sessionFactory.getCurrentSession();
		session.save(parametro);
	}

	public void atualizarTipoDemanda(Parametro parametro) {
		Session session = sessionFactory.getCurrentSession();
		session.update(parametro);
	}
}
