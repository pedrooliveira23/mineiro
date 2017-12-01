package br.jus.cjf.mineiro.service;

import br.jus.cjf.simus.model.Usuario;

import java.util.List;

/**
 * Created by Vitor Roberto.
 */
public interface UsuarioService {

    void criarUsuario(Usuario usuario);

    String gerarMatricula(Usuario usuario);

    List<Usuario> listarUsuarios();

    Usuario getUsuarioPorId(int id);

    Usuario alterarGrupo(int idUsuario, int idGrupo, boolean remover);

}
