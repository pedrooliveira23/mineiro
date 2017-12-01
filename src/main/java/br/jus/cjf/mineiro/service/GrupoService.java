package br.jus.cjf.mineiro.service;

import br.jus.cjf.simus.model.Grupo;
import br.jus.cjf.simus.model.Usuario;

import java.util.List;

/**
 * Created by Vitor Roberto.
 */
public interface GrupoService {

	List<Grupo> listarGrupos();

	Grupo getGrupoPorId(int id);
}
