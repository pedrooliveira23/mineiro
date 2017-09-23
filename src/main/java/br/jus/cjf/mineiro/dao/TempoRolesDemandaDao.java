/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jus.cjf.mineiro.dao;

import br.jus.cjf.mineiro.model.TempoRolesDemanda;
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
public class TempoRolesDemandaDao {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
	public TempoRolesDemandaDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
        
        
    @SuppressWarnings("unchecked")
    public TempoRolesDemanda getByIdRoleIdDemanda(int idRole, int idDemanda) {
            Session s = sessionFactory.getCurrentSession();
            Query q = s.createQuery("select e from TempoRolesDemanda e "
                            + " where e.demanda.id = :demanda and e.role.id = :role)");
            q.setParameter("demanda", idDemanda);
            q.setParameter("role", idRole);
            return (TempoRolesDemanda)q.uniqueResult();
    }
        
    public void atualizarTransicao(TempoRolesDemanda tempo) {
		
		Session session = sessionFactory.getCurrentSession();
		session.merge(tempo);
	}

	public void criarTransicao(TempoRolesDemanda tempo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(tempo);
	}
    
}
