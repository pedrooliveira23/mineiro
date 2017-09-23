/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.cjf.mineiro.dao;

import br.jus.cjf.mineiro.model.Roles;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diogo.araujo
 */
@Repository
public class RolesDao {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
	public RolesDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
        
        
    @SuppressWarnings("unchecked")
    public Roles getRolesById(int idRole) {
            Session s = sessionFactory.getCurrentSession();
            Query q = s.createQuery("select e from Roles e "
                            + " where e.id = :role)");
            q.setParameter("role", idRole);
            return (Roles)q.uniqueResult();
    }
    
    @SuppressWarnings("unchecked")
    public List<Roles> getRoles() {
            Session s = sessionFactory.getCurrentSession();
            Query q = s.createQuery("select e from Roles e ");
 
            return q.list();
    }
        
    public void atualizarRole(Roles tempo) {
		
		Session session = sessionFactory.getCurrentSession();
		session.merge(tempo);
	}

	public void criarRole(Roles tempo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(tempo);
	}
    
}
