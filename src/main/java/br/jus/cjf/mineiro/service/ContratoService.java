package br.jus.cjf.mineiro.service;

import java.math.BigDecimal;
import java.util.List;

import br.jus.cjf.mineiro.model.CategoriaNivelServico;
import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.Indicador;
import br.jus.cjf.mineiro.model.NivelServico;
import br.jus.cjf.mineiro.model.Penalidade;
import br.jus.cjf.mineiro.model.TipoIndicador;
import br.jus.cjf.mineiro.model.ValoresContrato;
import org.joda.time.DateTime;

public interface ContratoService {

	
	Contrato getContratoPorNumero(String numero);
        
        Contrato getContratoPorContratada(String contratada);
        
        Contrato getContratoPorId(int id);
        
        void excluirContrato(Contrato contrato);
        
        void excluirValoresContrato(ValoresContrato valor);
	
	List<Contrato> listarContratoPorEmpresa(String empresa);
        
        List<Contrato> listarContratos();
        
        List<Contrato> getListar();
	
	void atualizarContrato(Contrato contrato);

	void criarContrato(Contrato contrato);
	
	Indicador getIndicador(Integer id);
	
	Indicador getIndicador(String nomeTipoIndicador, Contrato contrato);

	List<Indicador> listarIndicadorPorContrato(String numero);
	
	void atualizarIndicador(Indicador indicador);

	void criarIndicador(Indicador indicador);
	
	Penalidade getPenalidade(Integer id);
	
	Penalidade getPenalidade(String clausula, Contrato contrato);
	
	List<Penalidade> listarPenalidadePorContrato(String numero);
	
	void atualizarPenalidade(Penalidade penalidade);

	void criarPenalidade(Penalidade penalidade);
	
	NivelServico getNivelServico(Integer id);

	List<NivelServico> listarNivelServicoPorIndicador(Indicador indicador);
	
	void atualizarNivelServico(NivelServico nivelServico);

	void criarNivelServico(NivelServico nivelServico);
	
	TipoIndicador getTipoIndicador(Integer id);
	
	List<TipoIndicador> listarTipoIndicador();
	
	void atualizarTipoIndicador(TipoIndicador tipoIndicador);
	
	void criarTipoIndicador(TipoIndicador tipoIndicador);
	
	CategoriaNivelServico categorizarNivelServico (Indicador indicador, BigDecimal valor);
	
	void realizarCargaContrato();
        
        ValoresContrato getValoresContratoPorVigencia(DateTime de, DateTime ate);
        
        List<ValoresContrato> getValoresContrato(Contrato contrato);
        
        ValoresContrato getValoresContratoPorId(Integer id);

	void atualizarValoresContrato(ValoresContrato valorescontrato);

	void criarValoresContrato(ValoresContrato valorescontrato);
	
}
