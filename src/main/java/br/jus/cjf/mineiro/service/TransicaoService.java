package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.model.Contrato;
import java.util.List;

import org.joda.time.DateTime;

import br.jus.cjf.mineiro.model.Demanda;
import br.jus.cjf.mineiro.model.Estado;
import br.jus.cjf.mineiro.model.Transicao;
import br.jus.cjf.redmine.model.journal.IssueJournal;
import br.jus.cjf.redmine.model.journal.StatusJournalDetail;



public interface TransicaoService {

	void atualizarTransicoes(List<Transicao> cronometros);
	
	Boolean contaTempoCJF(Transicao transicao);
	
	Transicao getTransicaoById(Integer id);
	
	Transicao getTransicao(Integer redmineIssueId, DateTime data);
	
	Transicao getTransicaoMaisAtualizada(Integer redmineIssueId);
	
	Transicao getTransicaoOrdemServicoDemandada(Integer redmineIssueId);
	
	Transicao getTransicaoHomologacaoAprovada(Integer redmineIssueId);
	
	Transicao getPrimeiraTransicaoContaTempoOS(Integer redmineIssueId);
	
	Transicao getUltimaTransicaoContaTempoOS(Integer redmineIssueId);
	
	Transicao getTransicao(Integer redmineIssueId, Estado estado, DateTime dataInicio);
	
	Boolean existeTransicao(Integer redmineIssueId, Estado estado, DateTime dataInicio);
	
	Boolean existeTransicaoContaTempoOS(Integer redmineIssueId, DateTime data);
	
	List<Transicao> listarTransicao();

	List<Transicao> listarTransicaoByDemandaId(Integer demandaId);

	List<Transicao> listarTransicaoByRedmineIssueId(Integer redmineIssueId);
	
	void atualizarTransicao(Transicao transicao);

	void criarTransicao(Transicao transicao);
	
	Integer getQuantidadeRecusasDemanda(Integer redmineIssueId);
	
	Boolean extrairTransicao(Demanda demanda, Estado estado, StatusJournalDetail statusJournalDetailAtual, StatusJournalDetail statusJournalDetailProximo, List<IssueJournal> issueJournalList, boolean forcarExtracao );
	
	long calcularDuracaoContandoPrazoDiaUtilComExpediente(DateTime dataInicio, DateTime dataFim, Contrato contrato);

        public Transicao getPrimeiraTransicaoOrdemServicoDemandadaValida(Integer redmineIssueId);
	
}
