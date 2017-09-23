package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.model.Contrato;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.YearMonth;

import br.jus.cjf.mineiro.model.Demanda;
import br.jus.cjf.mineiro.model.OrdemServico;
import br.jus.cjf.mineiro.model.Projeto;
import br.jus.cjf.mineiro.model.Situacao;
import java.math.BigDecimal;

public interface OrdemServicoService {
	

	
	OrdemServico getOrdemServicoById(Integer id);
	
	OrdemServico getOrdemServicoByRedmineIssueId(Integer redmineIssueId);
        
        OrdemServico getOrdemServicoByDemandaId(Integer demandaId);
	
	OrdemServico getOrdemServicoPrecificadaByRedmineIssueId(Integer redmineIssueId);
	
	DateTime getDataCriacaoOrdemServicoMaisAntiga(Situacao situacao);
	
	DateTime getDataCriacaoOrdemServicoMaisAntiga();
	
	DateTime getDataFinalizacaoOrdemServicoMaisAntiga(Situacao situacao);
	
	DateTime getDataFinalizacaoOrdemServicoMaisAntiga();
	
	DateTime getDataCriacaoOrdemServicoIncompletaMaisAntiga();
	
	DateTime getDataFinalizacaoOrdemServicoIncompletaMaisAntiga();
	
	Boolean existeOrdemServicoByRedmineIssueId(Integer redmineIssueId);
	
	Boolean existeOrdemServico();
	
	List<OrdemServico> listarOrdemServico();
        
        List<OrdemServico> listarOrdemServicoContrato(Contrato contrato);
        
        List<OrdemServico> listarOrdemServicoContrato();
	
	List<OrdemServico> listarOrdemServico(Situacao situacao);
	
	List<OrdemServico> listarOrdemServicoFechadaNoMes(YearMonth anoMes); 
	
	List<OrdemServico> listarOrdemServicoPorDataCriacao(DateTime de, DateTime ate);
	
	List<OrdemServico> listarOrdemServicoPorDataFinalizacao(DateTime de, DateTime ate);
	
	List<OrdemServico> listarOrdemServicoPorDataCriacao(DateTime de, DateTime ate, Situacao situacao);
	
	List<OrdemServico> listarOrdemServicoPorDataFinalizacao(DateTime de, DateTime ate, Situacao situacao);
	
	List<OrdemServico> listarOrdemServicoAberta(Boolean aberta);
        
        List<OrdemServico> listarOrdemServicoAbertaTodosContratos(Boolean aberta);
	
	List<OrdemServico> listarOrdemServicoPrecificada(Boolean precificada);
	
	List<OrdemServico> listarOrdemServicoConcluida(Boolean concluida);
	
	List<OrdemServico> listarOrdemServicoCancelada(Boolean cancelada);
	
	List<OrdemServico> listarOrdemServicoIncompleta();	
	
	List<OrdemServico> listarOrdemServicoIncompletaPorDataCriacao(DateTime de, DateTime ate, Boolean incompleta);
	
	List<OrdemServico> listarOrdemServicoIncompletaPorDataFinalizacao(DateTime de, DateTime ate, Boolean incompleta);
		
	List<OrdemServico> listarOrdemServicoVerificacao();
	
	List<OrdemServico> listarOrdemServicoGrafico();
        
        BigDecimal QuantitativoPontosFuncao();
	
	void atualizarOrdemServico(OrdemServico ordemServico);

	void criarOrdemServico(OrdemServico ordemServico);

	void excluir(OrdemServico ordemServico);
	
	void extrairOrdemServico(Demanda demanda, Projeto projeto);
	
	void atualizarNiveisServicoOrdemServico(OrdemServico ordemServico);

	void atualizarNiveisServicoOrdemServicoManualmente();
	
	void atualizarNiveisServicoOrdemServicoAutomaticamente();
	
	void atualizarNivelServicoConformidadeOrdemServicoByRedmineIssueId(Integer redmineIssueId);
	
	void precificarOrdemServico(OrdemServico ordemServico);
	
	Boolean ordemServicoPrecificada(Integer redmineIssueId);
}
