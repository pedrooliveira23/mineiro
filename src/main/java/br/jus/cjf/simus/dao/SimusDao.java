package br.jus.cjf.simus.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.simus.model.Usuario;

@Repository
public class SimusDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public SimusDao(@Qualifier("mineiroSessionFactory")   SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}


	public Usuario buscaPorMatricula(String matricula) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session
				.createQuery("from Usuario u where u.matricula = ?");
		query.setString(0, matricula);
		return (Usuario) query.uniqueResult();
	}
        
        public Boolean validaLogin(String matricula, String hash) {
		Session session = sessionFactory.getCurrentSession();
                try{
		Query query = session
				.createQuery("from Usuario u where u.matricula = ? and u.senha = ?");
		query.setString(0, matricula);
                query.setString(1, hash);
		Usuario u = ((Usuario) query.uniqueResult());
                if(u!= null &&  u.getId()>0){
                    return true;
                }else{
                    return false;
                }
                }catch(Exception ex){
                    ex.printStackTrace();
                    return false;
                }
	}

}
