package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.dao.OrdemServicoDao;
import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.dao.TransicaoDao;
import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.Demanda;
import br.jus.cjf.mineiro.model.Duracao;
import br.jus.cjf.mineiro.model.Estado;
import br.jus.cjf.mineiro.model.OrdemServico;
import br.jus.cjf.mineiro.model.TipoDuracao;
import br.jus.cjf.mineiro.model.Transicao;
import br.jus.cjf.redmine.model.journal.IssueJournal;
import br.jus.cjf.redmine.model.journal.StatusJournalDetail;
import br.jus.cjf.spring.util.CustomWebAuthenticationDetails;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class TransicaoServiceImpl implements TransicaoService {

	private final TransicaoDao transicaoDao;
        
	private final DiaNaoUtilService diaNaoUtilService;

	private final OrdemServicoDao ordemServicoDao;

	private Contrato contrato;

	private static Logger logger = LoggerFactory.getLogger(TransicaoServiceImpl.class);
        
        private final HttpSession session;

	@Autowired
	public TransicaoServiceImpl(TransicaoDao transicaoDao, DiaNaoUtilService diaNaoUtilService, HttpSession session,
                OrdemServicoDao ordemServicoDao) {
		super();
		this.transicaoDao = transicaoDao;
		this.diaNaoUtilService = diaNaoUtilService;
		this.session = session;
                this.ordemServicoDao= ordemServicoDao;
	}

	public Contrato getContrato() {
		if (contrato == null) {
		 //CustomWebAuthenticationDetails webAuthenticationDetails = ((CustomWebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails());
                 //String scontrato = webAuthenticationDetails.getnContrato();
                 //contrato = contratoService.getContratoPorId(Integer.parseInt(scontrato));
                    contrato = (Contrato)session.getAttribute("contratada"); 
		}
		return contrato;

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Boolean contaTempoCJF(Transicao transicao) {
		String[] estadosIgnorados = { "Contagem Detalhada", "Produção - Disponibilizada", "Homologação - Aprovada", "Qualidade - Em Inspeção", "Qualidade - Aprovada" };

		for (String estadoIgnorado : estadosIgnorados) {
			if (transicao.getContaTempoOS() == true || transicao.getEstado().getNome().contains(estadoIgnorado)) {

				return false;

			}

		}

		return true;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarTransicoes(List<Transicao> transicoes) {
		Transicao transicao = null;
		for (Transicao t : transicoes) {
			transicao = transicaoDao.getTransicaoById(t.getId());
			transicao.setContaTempoOS(t.getContaTempoOS());
			transicaoDao.atualizarTransicao(transicao);
		}
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Transicao getTransicaoById(Integer id) {

		return transicaoDao.getTransicaoById(id);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Transicao getTransicao(Integer redmineIssueId, DateTime data) {

		for (Transicao transicao : transicaoDao.listarTransicaoByRedmineIssueId(redmineIssueId)) {
			DateTime dataInicio = transicao.getDataInicio();
			DateTime dataFim = transicao.getDataFim();
			if (dataFim == null) {
				dataFim = new DateTime();
			}
			if ((data.equals(dataInicio) || data.isAfter(dataInicio)) && (data.equals(dataFim) || data.isBefore(dataFim))) {

				return transicao;
			}
		}
		return null;

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Transicao getTransicaoMaisAtualizada(Integer redmineIssueId) {

		return transicaoDao.getTransicaoMaisAtualizada(redmineIssueId);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Transicao getPrimeiraTransicaoContaTempoOS(Integer redmineIssueId) {

		return transicaoDao.getPrimeiraTransicaoContaTempoOS(redmineIssueId);
	}

	public Transicao getUltimaTransicaoContaTempoOS(Integer redmineIssueId) {

		return transicaoDao.getUltimaTransicaoContaTempoOS(redmineIssueId);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Transicao getTransicaoOrdemServicoDemandada(Integer redmineIssueId) {

		return transicaoDao.getTransicaoOrdemServicoDemandadaMaisRecente(redmineIssueId);

	}
        
        @Override
	@Transactional("mineiroTransactionManager")
	public Transicao getPrimeiraTransicaoOrdemServicoDemandadaValida(Integer redmineIssueId) {

		return transicaoDao.getTransicaoOrdemServicoDemandadaMaisAntigaValida(redmineIssueId);

	}
        
        
	@Override
	@Transactional("mineiroTransactionManager")
	public Transicao getTransicaoHomologacaoAprovada(Integer redmineIssueId) {

		return transicaoDao.getTransicaoHomologacaoAprovada(redmineIssueId);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Transicao getTransicao(Integer redmineIssueId, Estado estado, DateTime dataInicio) {

		return transicaoDao.getTransicao(redmineIssueId, estado, dataInicio);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<Transicao> listarTransicao() {

		return transicaoDao.listarTransicao();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<Transicao> listarTransicaoByDemandaId(Integer demandaId) {

		return transicaoDao.listarTransicaoByDemandaId(demandaId);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<Transicao> listarTransicaoByRedmineIssueId(Integer redmineIssueId) {

		return transicaoDao.listarTransicaoByRedmineIssueId(redmineIssueId);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarTransicao(Transicao transicao) {

		transicaoDao.atualizarTransicao(transicao);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void criarTransicao(Transicao transicao) {

		transicaoDao.criarTransicao(transicao);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Boolean existeTransicao(Integer redmineIssueId, Estado estado, DateTime dataInicio) {

		return transicaoDao.existeTransicao(redmineIssueId, estado, dataInicio);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Boolean existeTransicaoContaTempoOS(Integer redmineIssueId, DateTime data) {

		for (Transicao transicao : transicaoDao.listarTransicaoByRedmineIssueId(redmineIssueId)) {
			DateTime dataInicio = transicao.getDataInicio();
			DateTime dataFim = transicao.getDataFim();
			if (dataFim == null) {
				dataFim = new DateTime();
			}
			if ((data.equals(dataInicio) || data.equals(dataFim) || (data.isAfter(dataInicio) && data.isBefore(dataFim))) && transicao.getContaTempoOS()) {
				logger.debug("Transição sensibiliza prazo:" + transicao.getEstado().getNome() + "Dias uteis:" + transicao.getDuracaoTotal().getValor().getStandardDays());
				return true;
			}
		}
		return false;

	}

	@Transactional("mineiroTransactionManager")
	private long calcularDuracaoContandoPrazoDiaUtil(DateTime dataInicio, DateTime dataFim) {
		long duracaoContandoPrazoMillis = 0;
		DateTime data = dataInicio;
		if (dataFim == null) {
			dataFim = new DateTime();
		}
		while (data.plusDays(1).isBefore(dataFim) || data.plusDays(1).isEqual(dataFim)) {
			if (diaNaoUtilService.ehDiaUtil(data)) {
				duracaoContandoPrazoMillis += 24 * 60 * 60 * 1000;
			}

			data = data.plusDays(1);

		}

		return duracaoContandoPrazoMillis;
	}
        //luis sergio
	@Override
	@Transactional("mineiroTransactionManager")
	public long calcularDuracaoContandoPrazoDiaUtilComExpediente(DateTime dataInicio, DateTime dataFim,Contrato contrato) {

		long duracaoContandoPrazoMillis = 0;

		if (dataFim == null) {
			dataFim = new DateTime();
		}
		DateTime data = dataInicio;

		if (dataInicio.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0).isEqual(dataFim.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))) {

			DateTime dataInicioExpediente = new DateTime(dataInicio.withTime(contrato.getInicioExpediente().getHourOfDay(), contrato.getInicioExpediente().getMinuteOfHour(), 0, 0));
			DateTime dataFimExpediente = new DateTime(dataFim.withTime(contrato.getFimExpediente().getHourOfDay(), contrato.getFimExpediente().getMinuteOfHour(), 0, 0));
			// logger.debug("Inicio Expediente:" +
			// dataInicioExpediente.getHourOfDay()+":"+dataInicioExpediente.getMinuteOfHour());
			// logger.debug("Fim Expediente:" +
			// dataFimExpediente.getHourOfDay()+":"+dataFimExpediente.getMinuteOfHour());

			if (!diaNaoUtilService.ehDiaUtil(dataInicio.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))) {
				duracaoContandoPrazoMillis += 0;
			} else if (dataInicio.isAfter(dataInicioExpediente) && dataFim.isBefore(dataFimExpediente)) {
				duracaoContandoPrazoMillis += new Duration(dataInicio, dataFim).getMillis();
				logger.debug("Fim e Inicio iguais - Condição 1 - Horas:" + new Duration(duracaoContandoPrazoMillis).getStandardHours());
			} else if ((dataInicio.isBefore(dataInicioExpediente) && dataFim.isBefore(dataInicioExpediente)) || (dataInicio.isAfter(dataFimExpediente) && dataFim.isAfter(dataFimExpediente))) {
				duracaoContandoPrazoMillis += 0;
				logger.debug("Fim e Inicio iguais - Condição 2 - Horas:" + new Duration(duracaoContandoPrazoMillis).getStandardHours());
			} else if ((dataInicio.isBefore(dataInicioExpediente) || dataInicio.isEqual(dataInicioExpediente)) && (dataFim.isAfter(dataFimExpediente) || dataFim.isEqual(dataFimExpediente))) {
				duracaoContandoPrazoMillis += new Duration(dataInicioExpediente, dataFimExpediente).getMillis();
				logger.debug("Fim e Inicio iguais - Condição 3 - Horas:" + new Duration(duracaoContandoPrazoMillis).getStandardHours());

			} else if ((dataInicio.isBefore(dataInicioExpediente) || dataInicio.isEqual(dataInicioExpediente)) && dataFim.isBefore(dataFimExpediente)) {
				duracaoContandoPrazoMillis += new Duration(dataInicioExpediente, dataFim).getMillis();
				logger.debug("Fim e Inicio iguais - Condição 4 - Horas:" + new Duration(duracaoContandoPrazoMillis).getStandardHours());

			} else if ((dataFim.isAfter(dataFimExpediente) || dataFim.isEqual(dataFimExpediente)) && dataInicio.isBefore(dataFimExpediente)) {
				duracaoContandoPrazoMillis += new Duration(dataInicio, dataFimExpediente).getMillis();
				logger.debug("Fim e Inicio iguais - Condição 5 - Horas:" + new Duration(duracaoContandoPrazoMillis).getStandardHours());

			}

		} else {

			while (data.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0).isBefore(dataFim.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0)) || data.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0).isEqual(dataFim.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))) {
				Duration duracao = null;
				DateTime dataInicioExpediente = new DateTime(data.withTime(contrato.getInicioExpediente().getHourOfDay(), contrato.getInicioExpediente().getMinuteOfHour(), 0, 0));
				DateTime dataFimExpediente = new DateTime(data.withTime(contrato.getFimExpediente().getHourOfDay(), contrato.getFimExpediente().getMinuteOfHour(), 0, 0));
				if (diaNaoUtilService.ehDiaUtil(data.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))) {
					if (data.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0).isEqual(dataInicio.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))) {

						if (data.isBefore(dataInicioExpediente) || data.isEqual(dataInicioExpediente)) {
							duracao = new Duration(dataInicioExpediente, dataFimExpediente);
						} else if (data.isAfter(dataFimExpediente) || data.isEqual(dataFimExpediente)) {
							duracao = Duration.ZERO;
						} else if (data.isAfter(dataInicioExpediente) && data.isBefore(dataFimExpediente)) {
							duracao = new Duration(data, dataFimExpediente);
						}
						logger.debug("Fim e Inicio Diferentes - Inicio - Horas:" + duracao.getStandardHours());

						duracaoContandoPrazoMillis += duracao.getMillis();

					} else if (data.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0).isEqual(dataFim.withZone(DateTimeZone.UTC).withTime(0, 0, 0, 0))) {

						data = dataFim;

						if (data.isBefore(dataInicioExpediente) || data.isEqual(dataInicioExpediente)) {
							duracao = Duration.ZERO;
						} else if (data.isAfter(dataFimExpediente) || data.isEqual(dataFimExpediente)) {
							duracao = new Duration(dataInicioExpediente, dataFimExpediente);
						} else if (data.isAfter(dataInicioExpediente) && data.isBefore(dataFimExpediente)) {
							duracao = new Duration(dataInicioExpediente, data);
						}
						logger.debug("Fim e Inicio Diferentes - Fim - Horas:" + duracao.getStandardHours());

						duracaoContandoPrazoMillis += duracao.getMillis();
					} else {

						duracao = new Duration(dataInicioExpediente, dataFimExpediente);
						duracaoContandoPrazoMillis += duracao.getMillis();
						logger.debug("Fim e Inicio Diferentes - Intermediario - Horas:" + duracao.getStandardHours());

					}

				}

				data = data.plusDays(1);

			}
		}
		logger.debug("Fim e Inicio Diferentes - Final - Horas:" + new Duration(duracaoContandoPrazoMillis).getStandardHours());

		return duracaoContandoPrazoMillis;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Integer getQuantidadeRecusasDemanda(Integer redmineIssueId) {

		return transicaoDao.getQuantidadeRecusasDemanda(redmineIssueId);
	}

        @Override
	@Transactional("mineiroTransactionManager")
	public Boolean extrairTransicao(Demanda demanda, Estado estado, StatusJournalDetail statusJournalDetailAtual, StatusJournalDetail statusJournalDetailProximo, List<IssueJournal> issueJournalList, boolean forcarExtracao) {
		DateTime dataInicio = null;
		DateTime dataFim = null;
		Integer redmineIssueId = demanda.getRedmineIssueId();
		TipoDuracao tipoDuracao = null;
                
                

		dataInicio = new DateTime(statusJournalDetailAtual.getIssueJournal().getCreatedOnDate());
		logger.debug("Data criação Issue Journal:" + dataInicio);
		Transicao transicaoMaisAtualizada = getTransicaoMaisAtualizada(redmineIssueId);
		DateTime ultimaAtualizacaoDemandaMineiro = null;

		if (transicaoMaisAtualizada != null && transicaoMaisAtualizada.getDataInicio() != null) {
			ultimaAtualizacaoDemandaMineiro = transicaoMaisAtualizada.getDataInicio();

			if (dataInicio.isBefore(ultimaAtualizacaoDemandaMineiro) && !forcarExtracao) {
				logger.debug("Transição - Sem necessidade de atualização");
				return false;
			}

		}

		if (statusJournalDetailAtual.getCurrentStatus().isClosed()) {
			dataFim = dataInicio;
			demanda.setDataFinalizacao(dataFim);
			if (statusJournalDetailAtual.getCurrentStatus().getName().contains("Cancelada")) {
				demanda.setCancelada(true);
			} else if (statusJournalDetailAtual.getCurrentStatus().getName().contains("Concluída")) {
				demanda.setConcluida(true);
			}
			if (demanda.getContagemDetalhada().compareTo(BigDecimal.ZERO) == 0) {
				demanda.setIncompleta(true);
			}

		} else if (statusJournalDetailProximo != null) {
			dataFim = new DateTime(statusJournalDetailProximo.getIssueJournal().getCreatedOnDate());
		}
		if (demanda.getTipoDemanda().getNome().contains("Corretiva")) {
			tipoDuracao = TipoDuracao.HORA_CORRIDA;
		} else {
			tipoDuracao = TipoDuracao.DIA_CORRIDO;
		}

		Duracao duracao = new Duracao(new Duration(dataInicio, dataFim), tipoDuracao);
		long duracaoContandoPrazoMillis = 0;
		if (duracao.getValor().getMillis() < 0) {
			duracao.setValor(new Duration(0));
		}
                
   
                
                OrdemServico ordemServico = ordemServicoDao.getOrdemServicoByDemandaId(demanda.getId());
               
		duracaoContandoPrazoMillis = calcularDuracaoContandoPrazoDiaUtilComExpediente(dataInicio, dataFim,ordemServico.getContrato());
                
                
		if (duracaoContandoPrazoMillis < 0) {
			duracaoContandoPrazoMillis = 0;
		}

		Duracao duracaoContandoPrazo = null;
		if (demanda.getTipoDemanda().getNome().contains("Corretiva")) {
			tipoDuracao = TipoDuracao.HORA_CORRIDA;
			duracaoContandoPrazo = new Duracao(duracao.getValor(), tipoDuracao);
		} else {
			tipoDuracao = TipoDuracao.DIA_UTIL;
			duracaoContandoPrazo = new Duracao(new Duration(duracaoContandoPrazoMillis), tipoDuracao);

		}

		Transicao transicao = null;
		if (existeTransicao(redmineIssueId, estado, dataInicio)) {
			transicao = getTransicao(redmineIssueId, estado, dataInicio);
			transicao.setDataFim(dataFim);
			transicao.setDuracaoTotal(duracao);
			transicao.setDuracaoContandoTempo(duracaoContandoPrazo);
			atualizarTransicao(transicao);
			logger.debug("Transição - Estado:" + estado.getNome());
			logger.debug("Transição - Data de inicio:" + transicao.getDataInicio());
			logger.debug("Transição - Data de fim:" + transicao.getDataFim());
			logger.debug("Transição - Atualizada");

		} else {

			transicao = new Transicao();
			transicao.setDemanda(demanda);
			transicao.setEstado(estado);
			transicao.setContaTempoOS(estado.getContaTempoOS());
			transicao.setContaRecusaOS(estado.getContaRecusaOS());
			transicao.setDataInicio(dataInicio);
			transicao.setDataFim(dataFim);
			transicao.setDuracaoTotal(duracao);
			transicao.setDuracaoContandoTempo(duracaoContandoPrazo);
			criarTransicao(transicao);

			logger.debug("Transição - Estado:" + estado.getNome());
			logger.debug("Transição - Data de inicio:" + transicao.getDataInicio());
			logger.debug("Transição - Data de fim:" + transicao.getDataFim());
			logger.debug("Transição - Criada");

		}

		return true;

	}

}
