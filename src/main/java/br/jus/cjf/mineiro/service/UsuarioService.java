package br.jus.cjf.mineiro.service;

import br.jus.cjf.simus.model.Usuario;

/**
 * Created by Vitor Roberto.
 */
public interface UsuarioService {

    void criarUsuario(Usuario usuario);

    String gerarMatricula(Usuario usuario);
}
