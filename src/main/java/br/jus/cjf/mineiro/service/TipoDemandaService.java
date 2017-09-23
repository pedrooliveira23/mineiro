package br.jus.cjf.mineiro.service;

import java.util.List;

import br.jus.cjf.mineiro.model.Demanda;
import br.jus.cjf.mineiro.model.TipoDemanda;
import br.jus.cjf.redmine.model.Tracker;

public interface TipoDemandaService {

	List<TipoDemanda> listarTiposDemanda();

	boolean existeTipoDemanda(Integer redmineTrackerId);
	
	void criarTipoDemanda(TipoDemanda tipoDemanda);

	void atualizarTipoDemanda(TipoDemanda tipoDemanda);
	
	TipoDemanda getTipoDemanda(Integer redmineTrackerId);
	
	Boolean ehTipoDemandaManutecaoCorretiva(Demanda demanda);
	
	void extrairTipoDemanda(Tracker tracker);
	
}
