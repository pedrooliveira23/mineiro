package br.jus.cjf.mineiro.service;

import java.util.List;

import br.jus.cjf.mineiro.model.TipoInspecao;

public interface TipoInspecaoService {

	List<TipoInspecao> listarTipoInspecao();
	
	List<TipoInspecao> listarTipoInspecaoByTipoGrupoInspecaoId(Integer tipoGrupoInspecaoId);
	
	TipoInspecao getTipoInspecaoById(Integer tipoInspecaoId); 
	
	void criar(TipoInspecao tipoInspecao); 
	
	void realizarCargaTipoInspecao();
	
	
	
}
