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
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioDao usuarioDao;
	private final GrupoDao grupoDao;

	@Autowired
	private UsuarioServiceImpl(UsuarioDao usuarioDao, GrupoDao grupoDao) {
		super();
		this.usuarioDao = usuarioDao;
		this.grupoDao = grupoDao;
	}


	@Override
	@Transactional("mineiroTransactionManager")
	public void criarUsuario(Usuario usuario) {
		usuarioDao.criarUsuario(usuario);
	}

	@Override
	public String gerarMatricula(Usuario usuario) {
		int numeroMatricula = (int) (Math.random() * 1000000);
		usuario.setId(numeroMatricula);
		usuario.setMatricula(Integer.toString(numeroMatricula));
		return usuario.getMatricula();
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioDao.listarUsuarios();
	}

	@Override
	public Usuario getUsuarioPorId(int id) {
		return usuarioDao.getUsuarioPorId(id);
	}

	@Override
	public Usuario alterarGrupo(int usuarioId, int grupoId, boolean remover) {
		Usuario usuario = usuarioDao.getUsuarioPorId(usuarioId);
		if (remover) {
			usuarioDao.removeGrupoNoUsuario(usuarioId,grupoId);
		} else {
			usuarioDao.addGrupoNoUsuario(usuarioId,grupoId);
		}
		return usuarioDao.getUsuarioPorId(usuario.getId());
	}
}
