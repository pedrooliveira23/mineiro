package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.dao.GrupoDao;
import br.jus.cjf.mineiro.dao.UsuarioDao;
import br.jus.cjf.simus.model.Grupo;
import br.jus.cjf.simus.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vitor Roberto.
 */
@Service
public class GrupoServiceImpl implements GrupoService {

	private final GrupoDao grupoDao;

	@Autowired
	private GrupoServiceImpl(GrupoDao grupoDao) {
		super();
		this.grupoDao = grupoDao;
	}

	@Override
	public List<Grupo> listarGrupos() {
		return grupoDao.listarGrupos();
	}

	@Override
	public Grupo getGrupoPorId(int id) {
		return grupoDao.getGrupoPorId(id);
	}
}
