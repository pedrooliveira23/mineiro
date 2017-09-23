package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.Contrato;

import br.jus.cjf.mineiro.model.ValoresContrato;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;

@Repository
public class ValoresContratoDao {

	
	private final SessionFactory sessionFactory;
        private final HttpSession session;

	@Autowired
	private ValoresContratoDao(@Qualifier("mineiroSessionFactory")  SessionFactory sessionFactory, HttpSession session) {
		super();
		this.sessionFactory = sessionFactory;
                 this.session = session;
	}
        
         private Contrato getContrato() {
            return (Contrato)session.getAttribute("contratada");            
         }
	

       
        @SuppressWarnings("unchecked")
	public ValoresContrato getValoresContratoPorVigencia(DateTime de, DateTime ate) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select vc from ValoresContrato vc "
				+" where ( ((:de between vc.dataInicioVigencia and vc.dataFimVigencia) or (:de >= vc.dataInicioVigencia and vc.dataFimVigencia is null)) "
				+" and     ((:ate between vc.dataInicioVigencia and vc.dataFimVigencia) or vc.dataFimVigencia is null) ) "
                                +" and vc.contrato = :contrato "         
				+" order by vc.dataInicioVigencia desc");
		query.setParameter("de", de);
		query.setParameter("ate", ate);
                query.setParameter("contrato", getContrato());
		return (ValoresContrato) query.uniqueResult();
	}
        
        
        @SuppressWarnings("unchecked")
	public ValoresContrato getValoresContratoPorId(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select vc from ValoresContrato vc "
				+" where  vc.id = :id ");

                query.setParameter("id", id);
		return (ValoresContrato) query.uniqueResult();
	}
        
        
         @SuppressWarnings("unchecked")
	public List<ValoresContrato> getValoresContrato(Contrato contrato) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select vc from ValoresContrato vc "
				+" where vc.contrato = :contrato "         
				+" order by vc.dataInicioVigencia desc");
                query.setParameter("contrato",contrato );
		return  query.list();
	}
        
        
	public void atualizarValoresContrato(ValoresContrato valorescontrato){
		Session session = sessionFactory.getCurrentSession();
		session.update(valorescontrato);
	}

	public void criarValoresContrato(ValoresContrato valorescontrato){
		Session session = sessionFactory.getCurrentSession();
		session.save(valorescontrato);
	}
	
        public void excluirValoresContrato(ValoresContrato valor) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(valor);
	}
	
}
