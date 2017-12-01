package br.jus.cjf.mineiro.dao;

import br.jus.cjf.simus.model.Grupo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Vitor Roberto.
 */
@Repository
public class GrupoDao {

	private final SessionFactory sessionFactory;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(GrupoDao.class);

	@Autowired
	private GrupoDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Busca grupo no sistema
	 *
	 * @param id
	 */
	public Grupo getGrupoPorId(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Grupo) session.createCriteria(Grupo.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	/**
	 * Lista todos os grupos do sistema
	 */
	public List<Grupo> listarGrupos() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(Grupo.class).list();
	}

}
