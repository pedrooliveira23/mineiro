package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.Contrato;

@Repository
public class ContratoDao {

	
	private final SessionFactory sessionFactory;

	@Autowired
	private ContratoDao(@Qualifier("mineiroSessionFactory")  SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	public Contrato getContratoPorNumero(String numero){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c from Contrato c where c.numero = :numero");
		query.setParameter("numero", numero);
		
		return (Contrato) query.uniqueResult();
	}
        
        public Contrato getContratoPorContratada(String contratada){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c from Contrato c where c.contratada = :contratada");
		query.setParameter("contratada", contratada);
		
		return (Contrato) query.uniqueResult();
	}
        
          public Contrato getContratoPorId(int id){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c from Contrato c where c.id = :id");
		query.setParameter("id", id);
		
		return (Contrato) query.uniqueResult();
	}
	
        public void excluirContrato(Contrato contrato) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(contrato);
	}
          
          
	@SuppressWarnings("unchecked")
	public List<Contrato> listarContratoPorEmpresa(String empresa){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c from Contrato c where c.empresa = :empresa");
		query.setParameter("empresa", empresa);
		
		return  query.list();
	}
	
	public void atualizarContrato(Contrato contrato){
		Session session = sessionFactory.getCurrentSession();
		session.update(contrato);
	}

	public void criarContrato(Contrato contrato){
		Session session = sessionFactory.getCurrentSession();
		session.save(contrato);
	}
	
	 //luis sergio
        @SuppressWarnings("unchecked")
	public List<Contrato> listarContratos(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select c from Contrato c order by contratada");
		
		
		return  query.list();
	}
   
	
}
