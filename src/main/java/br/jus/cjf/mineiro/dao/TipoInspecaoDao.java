package br.jus.cjf.mineiro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import br.jus.cjf.mineiro.model.TipoInspecao;

@Repository
public class TipoInspecaoDao {

	private final SessionFactory sessionFactory;

	@Autowired
	public TipoInspecaoDao(@Qualifier("mineiroSessionFactory") SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	public TipoInspecao getTipoInspecaoById(Integer tipoInspecaoId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t from TipoInspecao t "
						+ " where t.id = :tipoInspecaoId");
		query.setParameter("tipoInspecaoId", tipoInspecaoId);

		return (TipoInspecao) query.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoInspecao> listarTipoInspecao() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(TipoInspecao.class).addOrder(Order.asc("nome")).list();
	}

	
	@SuppressWarnings("unchecked")
	public List<TipoInspecao> listarTipoInspecaoByTipoGrupoInspecaoId(Integer tipoGrupoInspecaoId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct ti from TipoInspecao ti "+
				"left join ti.tipoGrupoInspecao tgi "+
				"where tgi.id = :tipoGrupoInspecaoId");
		query.setParameter("tipoGrupoInspecaoId", tipoGrupoInspecaoId);

		return  query.list();
	}

	public boolean existeTipoInspecao(TipoInspecao tipoInspecao) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery(
				"select distinct t from TipoInspecao t "
						+ " where lower(t.nome) like lower(:pNome)");
		query.setParameter("pNome", "%" + tipoInspecao.getNome() + "%");

		return query.uniqueResult() == null;
	}

	public void atualizarTipoInspecao(TipoInspecao tipoInspecao) {
		Session session = sessionFactory.getCurrentSession();
		session.update(tipoInspecao);
	}

	public void criarTipoInspecao(TipoInspecao tipoInspecao) {
		Session session = sessionFactory.getCurrentSession();
		session.save(tipoInspecao);
	}

}
