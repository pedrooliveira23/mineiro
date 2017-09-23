package br.jus.cjf.simus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.simus.dao.SimusDao;
import br.jus.cjf.simus.model.Usuario;

@Service
public class SimusServiceImpl implements SimusService {

	private SimusDao usuarioDao;

	@Autowired
	public SimusServiceImpl(SimusDao usuarioDao) {
		super();
		this.usuarioDao = usuarioDao;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Usuario buscaPorMatricula(String matricula) {
		return usuarioDao.buscaPorMatricula(matricula);
	}

    @Override
    @Transactional("mineiroTransactionManager")
    public Boolean validaLogin(String matricula, String hash) {
        return usuarioDao.validaLogin(matricula, hash);
    }
        

}
