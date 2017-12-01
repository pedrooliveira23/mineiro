package br.jus.cjf.mineiro.dao;

import br.jus.cjf.simus.model.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
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
public class UsuarioDao {

	private final SessionFactory sessionFactory;
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(UsuarioDao.class);

	@Autowired
	private UsuarioDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	/**
	 * Cria um novo usuario no sistema
	 *
	 * @param usuario
	 */
	public void criarUsuario(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		usuario.setSenha(SHA1(usuario.getSenha()));
		session.save(usuario);
	}

	/**
	 * Busca usuario no sistema
	 *
	 * @param id
	 */
	public Usuario getUsuarioPorId(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Usuario) session.createCriteria(Usuario.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	/**
	 * Lista todos os usuários do sistema
	 */
	public List<Usuario> listarUsuarios() {
		Session session = sessionFactory.getCurrentSession();
		return (List<Usuario>) session.createCriteria(Usuario.class).list();
	}

	/**
	 * Lista todos os usuários do sistema
	 */
	public void alterarUsuario(Usuario usuario) {
		Session session = sessionFactory.getCurrentSession();
		session.update(usuario);
	}

	/**
	 * Lista todos os usuários do sistema
	 */
	public void addGrupoNoUsuario(int usuarioId, int grupoId){
	Session session = sessionFactory.getCurrentSession();
		session.createSQLQuery("INSERT INTO usuariogrupo VALUES (:id1,:id2)")
				.setParameter("id1", usuarioId)
				.setParameter("id2", grupoId).executeUpdate();
	}

	/**
	 * Lista todos os usuários do sistema
	 */
	public void removeGrupoNoUsuario(int usuarioId, int grupoId){
	Session session = sessionFactory.getCurrentSession();
		session.createSQLQuery("DELETE FROM usuariogrupo WHERE usuario_id =:id1 AND grupo_id = :id2)")
				.setParameter("id1", usuarioId)
				.setParameter("id2", grupoId).executeUpdate();
	}

	private String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9)) {
					buf.append((char) ('0' + halfbyte));
				} else {
					buf.append((char) ('a' + (halfbyte - 10)));
				}
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	private String SHA1(String text) {
		MessageDigest md;
		byte[] sha1hash = new byte[40];
		try {
			md = MessageDigest.getInstance("SHA-1");
			md.update(text.getBytes("iso-8859-1"), 0, text.length());
			sha1hash = md.digest();
		} catch (NoSuchAlgorithmException n) {
			logger.error(n.getMessage());
		} catch (UnsupportedEncodingException n) {
			logger.error(n.getMessage());
		}
		return convertToHex(sha1hash);
	}
}
