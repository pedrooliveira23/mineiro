package br.jus.cjf.mineiro.service;

import java.util.Date;
import java.util.List;

import org.joda.time.Duration;

import br.jus.cjf.mineiro.model.Demanda;
import br.jus.cjf.mineiro.model.Transicao;
import br.jus.cjf.redmine.model.Issue;
import org.joda.time.DateTime;

public interface DemandaService {

	
	List<Demanda> listarDemandasAbertas();

	Demanda getDemandaById(Integer id);
	
	Date getDemandaMaisAtualizada();

	Demanda getDemandaByRedmineIssueId(Integer redmineIssueId);
        
        Demanda getPrimeriaDemandaContrato(String Contratada);
        
	Boolean demandaExiste(Integer redmineIssueId);
	
	void atualizarConformidadeDemanda(Demanda demanda);

	void atualizarDemanda(Demanda demanda);
	
	void atualizarQuantidadeRecusasDemanda(Demanda demanda);

	void criarDemanda(Demanda demanda);
	
	void atualizarDuracaoTemposDemanda(Demanda demanda);
	
	void atualizarParaConcluidaDemanda(Demanda demanda);
	
	Demanda atualizarDadosNiveisServicoDemanda(Demanda demanda);
	
	Demanda atualizarDadosNiveisServicoDemanda(Integer redmineIssueId);
        
        Demanda atualizarDataEstimadaConclusao(Demanda demanda,DateTime dataFinalizacaoPlanejada);
	
	Demanda extrairDemanda(Issue issue);
	
	Duration calcularDuracaoContandoTempoOSDiaUtilComExpediente(Demanda demanda);
	
	Duration calcularDuracaoContandoTempoCJFDiaUtilComExpediente(Demanda demanda);
        
        DateTime getDataFinalizacaoPlanejadaDiaUtilComExpediente(Demanda demanda) ;
        
        Transicao getPrimeiraTransicaoOrdemServicoDemandadaValida(Demanda demanda);

	Duration calcularDuracaoTotalSolucaoContorno(Demanda demanda);
	
	List<Object[]> listarQuantidadePontosFuncaoPorMes();
	
	/**
	 * Atualiza as demandas que estão com status de incompletas( demandas finalizadas que não possuem contagem detalhada) verificando se a contagem foi inserida.
	 */
	void atualizarDemandasIncompletas();
        public void atualizarTempoRole(int role_id, int demanda_id,int valor);

}
