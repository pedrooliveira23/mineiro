package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.Nota;

@Repository
public class NotaDao {


		private final SessionFactory sessionFactory;

		@Autowired
		public NotaDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
			super();
			this.sessionFactory = sessionFactory;
		}

		public Nota getNota(Integer redmineJournalId){
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("select distinct n from Nota n "
					+ " where n.redmineJournalId = :redmineJournalId");
			
			query.setParameter("redmineJournalId", redmineJournalId);
			
			return (Nota) query.uniqueResult();
		}
		
		@SuppressWarnings("unchecked")
		public List<Nota> listarNota() {
			Session session = sessionFactory.getCurrentSession();
			return session.createCriteria(Nota.class).addOrder(Order.asc("dataCriacao")).list();
		}

		public Boolean notaExiste(Integer redmineJournalId) {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(
					"select distinct n from Nota n "
							+ " where n.redmineJournalId = :redmineJournalId ");
			query.setParameter("redmineJournalId", redmineJournalId);
                        try{
			if(query.list().isEmpty()){
				return false;
			}
			else{
				return true;
			}
                        }catch(Exception ex){
                            return false;
                        }
		}
		
		
		public Boolean notaAtualizada(Integer redmineJournalId, String nota) {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery(
					"select distinct n from Nota n "
							+ " where n.redmineJournalId = :redmineJournalId "
							+ " and n.nota = :nota");
			query.setParameter("redmineJournalId", redmineJournalId);
			query.setParameter("nota", nota);

			try{
                            if(query.list().isEmpty()){
                                    return false;
                            }
                            else{
                                    return true;
                            }
                        }catch(Exception ex){
                            return false;
                        }
		}
		

		
		public void criarNota(Nota nota) throws Exception  {             
			Session session = sessionFactory.getCurrentSession();
                            session.save(nota);      
		}

		public void atualizarNota(Nota nota) {
			Session session = sessionFactory.getCurrentSession();
			session.update(nota);
		}

		

	
}
