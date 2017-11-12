package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.dao.UsuarioDao;
import br.jus.cjf.simus.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Vitor Roberto.
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioDao usuarioDao;

    @Autowired
    private UsuarioServiceImpl(UsuarioDao usuarioDao){
        super();
        this.usuarioDao = usuarioDao;
    }


    @Override
    @Transactional("mineiroTransactionManager")
    public void criarUsuario(Usuario usuario) {
        usuarioDao.criarUsuario(usuario);
    }
}
