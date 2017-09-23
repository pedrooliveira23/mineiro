package br.jus.cjf.mineiro.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.dao.DemandaDao;
import br.jus.cjf.mineiro.dao.OrdemServicoDao;
import br.jus.cjf.mineiro.dao.RolesDao;
import br.jus.cjf.mineiro.dao.TempoRolesDemandaDao;
import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.Demanda;
import br.jus.cjf.mineiro.model.Duracao;
import br.jus.cjf.mineiro.model.Inspecao;
import br.jus.cjf.mineiro.model.Roles;
import br.jus.cjf.mineiro.model.TempoRolesDemanda;
import br.jus.cjf.mineiro.model.TipoDuracao;
import br.jus.cjf.mineiro.model.TipoSistema;
import br.jus.cjf.mineiro.model.Transicao;
import br.jus.cjf.redmine.model.Issue;
import br.jus.cjf.redmine.service.RedmineService;
import br.jus.cjf.spring.util.CustomWebAuthenticationDetails;

import com.google.common.collect.BoundType;
import com.google.common.collect.Ranges;
import java.math.BigInteger;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class DemandaServiceContrato implements DemandaService {

	private final DemandaDao demandaDao;

	private final TransicaoService transicaoService;

	private final DiaNaoUtilService diaNaoUtilService;

	private final TipoDemandaService tipoDemandaService;


	private final InspecaoService inspecaoService;

	private final RedmineService redmineService;

	private Contrato contrato;

	private final OrdemServicoDao ordemServicoDao;
        
        private final TempoRolesDemandaDao tempoRolesDemandaDao;
        
        private final RolesDao rolesDao;

	private static Logger logger = LoggerFactory
			.getLogger(DemandaServiceContrato.class);

	private final HttpSession session;

	@Autowired
	public DemandaServiceContrato(DemandaDao demandaDao,
			TransicaoService transicaoService,
			DiaNaoUtilService diaNaoUtilService,
			TipoDemandaService tipoDemandaService,
			InspecaoService inspecaoService, RedmineService redmineService,
			HttpSession session, OrdemServicoDao ordemServicoDao,
                        TempoRolesDemandaDao tempoRolesDemandaDao,RolesDao rolesDao) {
		super();
		this.demandaDao = demandaDao;
		this.transicaoService = transicaoService;
		this.diaNaoUtilService = diaNaoUtilService;
		this.tipoDemandaService = tipoDemandaService;
		this.inspecaoService = inspecaoService;
		this.redmineService = redmineService;
		this.session = session;
		this.ordemServicoDao = ordemServicoDao;
                this.tempoRolesDemandaDao = tempoRolesDemandaDao;
                 this.rolesDao = rolesDao;
	}

	/*
	 * public Contrato getContrato() { if (contrato == null) {
	 * //CustomWebAuthenticationDetails webAuthenticationDetails =
	 * ((CustomWebAuthenticationDetails)
	 * SecurityContextHolder.getContext().getAuthentication().getDetails());
	 * //String scontrato = webAuthenticationDetails.getnContrato(); //contrato
	 * = contratoService.getContratoPorId(Integer.parseInt(scontrato)); contrato
	 * = (Contrato)session.getAttribute("contratada"); } return contrato;
	 * 
	 * }
	 */

	public Contrato getContrato() {
		if (contrato == null) {
			// CustomWebAuthenticationDetails webAuthenticationDetails =
			// ((CustomWebAuthenticationDetails)
			// SecurityContextHolder.getContext().getAuthentication().getDetails());
			// String scontrato = webAuthenticationDetails.getnContrato();
			// contrato =
			// contratoService.getContratoPorId(Integer.parseInt(scontrato));
			contrato = (Contrato) session.getAttribute("contratada");
		}
		return contrato;

	}

	
	public Contrato getContrato(Demanda demanda) {
		
                        try{
                            contrato = (Contrato) session.getAttribute("contratada");
                            
                        }catch(Exception ex){
                            
                        }
                        if(contrato==null)			
                            contrato = (Contrato) ordemServicoDao.getOrdemServicoByDemandaId(demanda.getId()).getContrato();
		
		return contrato;

	}
	
	
	@Override
	@Transactional("mineiroTransactionManager")
	public List<Demanda> listarDemandasAbertas() {

		return demandaDao.listarDemandasAbertas();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Demanda getDemandaById(Integer id) {

		return demandaDao.getDemandaById(id);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Date getDemandaMaisAtualizada() {

		return demandaDao.getDemandaMaisAtualizada();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Demanda getDemandaByRedmineIssueId(Integer redmineIssueId) {

		return demandaDao.getDemandaByRedmineIssueId(redmineIssueId);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Demanda getPrimeriaDemandaContrato(String Contratada) {

		return demandaDao.getPrimeiraDemandaContrato(Contratada);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Boolean demandaExiste(Integer redmineIssueId) {

		return demandaDao.demandaExiste(redmineIssueId);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarConformidadeDemanda(Demanda demanda) {
		demanda.setConformidade(calcularConformidadeDemanda(demanda));
		demandaDao.atualizarConformidadeDemanda(demanda);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarDemanda(Demanda demanda) {

		demandaDao.atualizarDemanda(demanda);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void criarDemanda(Demanda demanda) {

		demandaDao.criarDemanda(demanda);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarQuantidadeRecusasDemanda(Demanda demanda) {

		demandaDao.atualizarQuantidadeRecusasDemanda(demanda);

	}

	@Transactional("mineiroTransactionManager")
	private BigDecimal calcularConformidadeDemanda(Demanda demanda) {

		List<Inspecao> inspecoes = inspecaoService
				.listarInspecoesByRedmineIssueId(demanda.getRedmineIssueId());

		BigDecimal somatorioConformidadeDemanda = new BigDecimal(0);
		BigDecimal conformidadeDemanda = new BigDecimal(100);
		logger.debug("Inspeção: {}", inspecoes.size());

		for (Inspecao inspecao : inspecoes) {
			somatorioConformidadeDemanda = somatorioConformidadeDemanda
					.add(inspecao.getPercentualAprovacao());
		}
		BigDecimal quantidadeInspecoes = new BigDecimal(inspecoes.size());
		if (quantidadeInspecoes.signum() > 0) {
			conformidadeDemanda = somatorioConformidadeDemanda.divide(
					quantidadeInspecoes, 2, BigDecimal.ROUND_DOWN);
		}

		return conformidadeDemanda;

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Duration calcularDuracaoContandoTempoOSDiaUtilComExpediente(
			Demanda demanda) {

		Integer redmineIssueId = demanda.getRedmineIssueId();
		Transicao primeiraTransicaoOrdemServico = transicaoService
				.getPrimeiraTransicaoOrdemServicoDemandadaValida(redmineIssueId);
		if (primeiraTransicaoOrdemServico == null) {
			primeiraTransicaoOrdemServico = transicaoService
					.getPrimeiraTransicaoContaTempoOS(redmineIssueId);
		}
		DateTime dataInicio = primeiraTransicaoOrdemServico.getDataInicio();
		logger.debug("#" + demanda.getRedmineIssueId()
				+ " - Tempo OS - Data Inicio Contando Prazo:" + dataInicio);

		long duracaoContandoPrazoMillis = 0;
		// luis sergio
		// Contrato contrato =
		// ordemServicoDao.getOrdemServicoById(demanda.getId()).getContrato();

		List<Transicao> transicoes = transicaoService
				.listarTransicaoByRedmineIssueId(redmineIssueId);
		for (Transicao transicao : transicoes) {

			DateTime dataInicioTransicao = transicao.getDataInicio();

			if (transicao.getContaTempoOS()
					&& (dataInicioTransicao.isAfter(dataInicio) || dataInicioTransicao
							.isEqual(dataInicio))) {

				duracaoContandoPrazoMillis += transicao
						.getDuracaoContandoTempo().getValor().getMillis();

				// logger.debug("#" + demanda.getRedmineIssueId() +
				// " - Tempo OS - Quantidade Dias Contando Prazo (" +
				// transicao.getEstado().getNome() + "):" + ((int) new
				// Duration(transicao.getDuracaoContandoTempo().getValor().getMillis()).getStandardHours())
				// / getContrato().getDuracaoHorasExpediente());
			}

		}
		// logger.debug("#" + demanda.getRedmineIssueId() +
		// " - Tempo OS - Quantidade Dias Contando Prazo Total:" + ((int) new
		// Duration(duracaoContandoPrazoMillis).getStandardHours()) /
		// getContrato().getDuracaoHorasExpediente());
		return new Duration(duracaoContandoPrazoMillis);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Duration calcularDuracaoContandoTempoCJFDiaUtilComExpediente(
			Demanda demanda) {

		Integer redmineIssueId = demanda.getRedmineIssueId();
		Transicao primeiraTransicaoOrdemServico = transicaoService
				.getTransicaoOrdemServicoDemandada(redmineIssueId);
		if (primeiraTransicaoOrdemServico == null) {
			primeiraTransicaoOrdemServico = transicaoService
					.getPrimeiraTransicaoContaTempoOS(redmineIssueId);
		}
		DateTime dataInicio = primeiraTransicaoOrdemServico.getDataInicio();
		logger.debug("#{} - Tempo CJF - Data Inicio Contando Prazo: {}",
				demanda.getRedmineIssueId(), dataInicio);

		long duracaoContandoPrazoMillis = 0;

		// Contrato contrato =
		// ordemServicoDao.getOrdemServicoById(demanda.getId()).getContrato();

		List<Transicao> transicoes = transicaoService
				.listarTransicaoByRedmineIssueId(redmineIssueId);
		for (Transicao transicao : transicoes) {

			DateTime dataInicioTransicao = transicao.getDataInicio();

			if (transicaoService.contaTempoCJF(transicao)
					&& (dataInicioTransicao.isAfter(dataInicio) || dataInicioTransicao
							.isEqual(dataInicio))) {

				duracaoContandoPrazoMillis += transicao
						.getDuracaoContandoTempo().getValor().getMillis();

				// logger.debug("#{} - Tempo CJF - Quantidade Dias Contando Prazo ({}):{}",
				// demanda.getRedmineIssueId(), transicao.getEstado().getNome(),
				// ((int) new
				// Duration(transicao.getDuracaoContandoTempo().getValor().getMillis()).getStandardHours())
				// / contrato.getDuracaoHorasExpediente());
			}

		}
		// logger.debug("#{} - Tempo CJF - Quantidade Dias Contando Prazo Total:{}",
		// demanda.getRedmineIssueId(), ((int) new
		// Duration(duracaoContandoPrazoMillis).getStandardHours()) /
		// getContrato().getDuracaoHorasExpediente());
		return new Duration(duracaoContandoPrazoMillis);

	}

	@Transactional("mineiroTransactionManager")
	private Duration calcularDuracaoTotalDemanda(Demanda demanda) {

		long tempoTotal = 0;
		for (Transicao transicao : transicaoService
				.listarTransicaoByRedmineIssueId(demanda.getRedmineIssueId())) {
			long tempoDestaEtapa = transicao.getDuracaoTotal().getValor()
					.getMillis();
			tempoTotal += tempoDestaEtapa;
		}

		return new Duration(tempoTotal);
	}

	@Transactional("mineiroTransactionManager")
	public Duration calcularDuracaoTotalSolucaoContorno(Demanda demanda) {
		long tempoTotal = 0;

		if (!tipoDemandaService.ehTipoDemandaManutecaoCorretiva(demanda))
			return new Duration(0);

		for (Transicao transicao : transicaoService
				.listarTransicaoByRedmineIssueId(demanda.getRedmineIssueId())) {
			if (transicao.getEstado().getNome()
					.equals("Solução de Contorno - Em Andamento")
					|| transicao
							.getEstado()
							.getNome()
							.equals("Solução de Contorno - Homologação Rejeitada")
					|| transicao.getEstado().getNome().equals("OS - Demandada")
					|| transicao.getEstado().getNome().equals("OS - Recebida")) {
				long tempoDestaEtapa = transicao.getDuracaoTotal().getValor()
						.getMillis();
				tempoTotal += tempoDestaEtapa;
			}
		}

		return new Duration(tempoTotal);
	}

	@Transactional("mineiroTransactionManager")
	private Duration calcularPrazoMaximoDiaUtilComExpediente(Demanda demanda) {

		Float contagem = null;
		if (demanda.getConcluida() == true
				&& demanda.getContagemDetalhada().compareTo(BigDecimal.ZERO) > 0) {
			contagem = demanda.getContagemDetalhada().floatValue();
		} else {
			contagem = demanda.getContagemEstimada().floatValue();

		}

		// Contrato contrato =
		// ordemServicoDao.getOrdemServicoById(demanda.getId()).getContrato();

		logger.debug("Quantitativo:" + contagem);
		Duration prazoMaximo = null;

	//	Integer duracaoHorasExpediente = getContrato()
	//			.getDuracaoHorasExpediente();
		
		Integer duracaoHorasExpediente  = ordemServicoDao.getOrdemServicoByDemandaId(demanda.getId()).getContrato().getDuracaoHorasExpediente();
		if (tipoDemandaService.ehTipoDemandaManutecaoCorretiva(demanda)) {
			if (ordemServicoDao.getOrdemServicoByDemandaId(demanda.getId())
					.getContrato().getContratada().equals("Indra")) {
				prazoMaximo = Duration.standardDays(2);
			} else {
				if (demanda.getTipoDemanda().getNome().contains("Com Solução")) {
					prazoMaximo = Duration.standardHours((10 * 24) + 12);
				} else {
					prazoMaximo = Duration.standardDays(10);
				}

			}

		} else if (contagem < 100) {

			if (Ranges.range(new Float(0), BoundType.CLOSED, new Float(10),
					BoundType.CLOSED).contains(contagem)) {
				prazoMaximo = Duration
						.standardHours(duracaoHorasExpediente * 10);
			} else if (Ranges.range(new Float(10), BoundType.CLOSED,
					new Float(20), BoundType.CLOSED).contains(contagem)) {
				prazoMaximo = Duration
						.standardHours(duracaoHorasExpediente * 20);
			} else if (Ranges.range(new Float(20), BoundType.CLOSED,
					new Float(30), BoundType.CLOSED).contains(contagem)) {
				prazoMaximo = Duration
						.standardHours(duracaoHorasExpediente * 30);
			} else if (Ranges.range(new Float(30), BoundType.CLOSED,
					new Float(40), BoundType.CLOSED).contains(contagem)) {
				prazoMaximo = Duration
						.standardHours(duracaoHorasExpediente * 40);
			} else if (Ranges.range(new Float(40), BoundType.CLOSED,
					new Float(50), BoundType.CLOSED).contains(contagem)) {
				prazoMaximo = Duration
						.standardHours(duracaoHorasExpediente * 50);
			} else if (Ranges.range(new Float(50), BoundType.CLOSED,
					new Float(60), BoundType.CLOSED).contains(contagem)) {
				prazoMaximo = Duration
						.standardHours(duracaoHorasExpediente * 60);
			} else if (Ranges.range(new Float(60), BoundType.CLOSED,
					new Float(70), BoundType.CLOSED).contains(contagem)) {
				prazoMaximo = Duration
						.standardHours(duracaoHorasExpediente * 70);
			} else if (Ranges.range(new Float(70), BoundType.CLOSED,
					new Float(85), BoundType.CLOSED).contains(contagem)) {
				prazoMaximo = Duration
						.standardHours(duracaoHorasExpediente * 88);
			} else if (Ranges.range(new Float(85), BoundType.CLOSED,
					new Float(100), BoundType.CLOSED).contains(contagem)) {
				prazoMaximo = Duration
						.standardHours(duracaoHorasExpediente * 104);
			}

		} else {
			Float expoente = demanda.getTipoSistema().getExpoente()
					.floatValue();
			logger.debug(" Expoente:" + expoente);
			Float prazo = ((float) (Math.pow(contagem, expoente) * 20));
			prazoMaximo = Duration.standardHours(duracaoHorasExpediente *prazo.longValue());
		}

		String contratada = ordemServicoDao
				.getOrdemServicoByDemandaId(demanda.getId()).getContrato()
				.getContratada();
		int primeira = getPrimeriaDemandaContrato(contratada).getId();
		if (!contratada.equals("Indra") && primeira == demanda.getId()) {
			prazoMaximo = Duration.standardHours(duracaoHorasExpediente * 10+ prazoMaximo.getStandardHours());
		}

		logger.debug("Prazo Máximo:" + prazoMaximo);

		return prazoMaximo;
	}

	private BigDecimal calcularPercentualAtraso(Demanda demanda) {

		// Contrato contrato =
		// ordemServicoDao.getOrdemServicoById(demanda.getId()).getContrato();

		Duration duracaoContandoPrazo = demanda.getDuracaoContandoTempoOS()
				.getValor();
		Duration prazoMaximo = demanda.getDuracaoPrazoMaximo().getValor();
		BigDecimal quantidadeDiasContandoPrazo = BigDecimal.ZERO;
		BigDecimal quantidadeDiasPrazoMaximo = BigDecimal.ZERO;
		if (demanda.getTipoDemanda().getNome().contains("Corretiva")) {
			quantidadeDiasContandoPrazo = new BigDecimal(
					((float) duracaoContandoPrazo.getStandardHours()) / 24.0);
			quantidadeDiasPrazoMaximo = new BigDecimal(
					((float) prazoMaximo.getStandardHours()) / 24.0);
		} else {
			quantidadeDiasContandoPrazo = new BigDecimal(
					((float) duracaoContandoPrazo.getStandardHours())
							/ getContrato(demanda).getDuracaoHorasExpediente());
			quantidadeDiasPrazoMaximo = new BigDecimal(
					((float) prazoMaximo.getStandardHours())
							/ getContrato(demanda).getDuracaoHorasExpediente());
		}

		BigDecimal percentualAtraso = BigDecimal.ZERO;
		if (quantidadeDiasContandoPrazo.compareTo(quantidadeDiasPrazoMaximo) == 1) {
			percentualAtraso = quantidadeDiasContandoPrazo.divide(
					quantidadeDiasPrazoMaximo, 2, RoundingMode.HALF_UP);

			logger.debug("Percentual Atraso:" + percentualAtraso);
			percentualAtraso = (percentualAtraso.subtract(BigDecimal.ONE))
					.multiply(new BigDecimal(100));
		}

		logger.debug("#" + demanda.getRedmineIssueId()
				+ " - Percentual Atraso:" + percentualAtraso);

		return percentualAtraso;

	}
        
        @Override
	@Transactional("mineiroTransactionManager")
	public Transicao getPrimeiraTransicaoOrdemServicoDemandadaValida(Demanda demanda) {
                Transicao primeiraTransicaoOrdemServico = transicaoService
                                        .getPrimeiraTransicaoOrdemServicoDemandadaValida(demanda
                                                        .getRedmineIssueId());

                        if (primeiraTransicaoOrdemServico == null) {
                                primeiraTransicaoOrdemServico = transicaoService
                                                .getPrimeiraTransicaoContaTempoOS(demanda
                                                                .getRedmineIssueId());
                        }
                        return primeiraTransicaoOrdemServico;
        }

	@Override
	@Transactional("mineiroTransactionManager")
	public DateTime getDataFinalizacaoPlanejadaDiaUtilComExpediente(
			Demanda demanda) {

		Transicao primeiraTransicaoOrdemServico = transicaoService
				.getPrimeiraTransicaoOrdemServicoDemandadaValida(demanda
						.getRedmineIssueId());

		if (primeiraTransicaoOrdemServico == null) {
			primeiraTransicaoOrdemServico = transicaoService
					.getPrimeiraTransicaoContaTempoOS(demanda
							.getRedmineIssueId());
		}

		logger.debug("#" + demanda.getRedmineIssueId() + " - Transição inicia:"
				+ primeiraTransicaoOrdemServico.getEstado().getNome() + "("
				+ primeiraTransicaoOrdemServico.getDataInicio() + ")");

		// Contrato contrato =
		// ordemServicoDao.getOrdemServicoById(demanda.getId()).getContrato();

		DateTime dataInicio = primeiraTransicaoOrdemServico.getDataInicio();
		DateTime dataFinalizacaoPlanejada = dataInicio;
                Integer quantidadeDiasPrazoMaximo=0;
               try{
                   quantidadeDiasPrazoMaximo = ((int) demanda
				.getDuracaoPrazoMaximo().getValor().getStandardHours())
				/ getContrato(demanda).getDuracaoHorasExpediente();
               }catch(Exception ex){
                   quantidadeDiasPrazoMaximo=10;
               }
		

		logger.debug("#" + demanda.getRedmineIssueId()
				+ " - Quantidade de Dias Prazo Máximo:"
				+ quantidadeDiasPrazoMaximo);

		// String contratada =
		// ordemServicoDao.getOrdemServicoByDemandaId(demanda.getId()).getContrato().getContratada();
		// int primeira = getPrimeriaDemandaContrato(contratada).getId();
		// if( !contratada.equals("Indra") && primeira==demanda.getId()){
		// quantidadeDiasPrazoMaximo+=10;
		// }
		if (tipoDemandaService.ehTipoDemandaManutecaoCorretiva(demanda)) {
			if (ordemServicoDao.getOrdemServicoByDemandaId(demanda.getId())
					.getContrato().getContratada().equals("Indra")) {
				dataFinalizacaoPlanejada = dataFinalizacaoPlanejada
						.plusHours(48);
				logger.debug("#" + demanda.getRedmineIssueId()
						+ " - Data Planejada:" + dataFinalizacaoPlanejada);
				return dataFinalizacaoPlanejada;
			} else {
				if (demanda.getTipoDemanda().getNome().contains("Com Solução")) {
					dataFinalizacaoPlanejada = dataFinalizacaoPlanejada
							.plusHours(24 * 12);
				} else {
					dataFinalizacaoPlanejada = dataFinalizacaoPlanejada
							.plusHours(24 * 10);
				}
				logger.debug("#" + demanda.getRedmineIssueId()
						+ " - Data Planejada:" + dataFinalizacaoPlanejada);
			}

		}
		long duracaoSensibilizaPrazoMillis = 0;
		long duracaoNaoSensibilizaPrazoMillis = 0;
		Integer quantidadeDiasContandoPrazo = 0;
		Transicao ultimaTransicao = null;
		List<Transicao> transicoes = transicaoService
				.listarTransicaoByRedmineIssueId(demanda.getRedmineIssueId());
		for (Transicao transicao : transicoes) {
			DateTime dataInicioTransicao = transicao.getDataInicio();
			DateTime dataFimTransicao = transicao.getDataFim();
			Duration duracaoContandoPrazo = new Duration(
					duracaoSensibilizaPrazoMillis);
			quantidadeDiasContandoPrazo = ((int) duracaoContandoPrazo
					.getStandardHours())
					/ getContrato(demanda).getDuracaoHorasExpediente();

			logger.debug("#" + demanda.getRedmineIssueId()
					+ " - Quantidade de Dias:" + quantidadeDiasContandoPrazo);

			if (quantidadeDiasContandoPrazo >= quantidadeDiasPrazoMaximo) {
				break;
			}
			if (dataInicioTransicao.isAfter(dataInicio)
					|| dataInicioTransicao.isEqual(dataInicio)) {

				if (transicao.getContaTempoOS()) {
					logger.debug("#" + demanda.getRedmineIssueId() + " - "
							+ transicao.getEstado().getNome());

					duracaoSensibilizaPrazoMillis += transicao
							.getDuracaoContandoTempo().getValor().getMillis();

					ultimaTransicao = transicao;
				} else {
					if (dataFimTransicao == null) {
						duracaoNaoSensibilizaPrazoMillis += transicaoService.calcularDuracaoContandoPrazoDiaUtilComExpediente(dataInicioTransicao, dataFimTransicao,getContrato(demanda));
                                                        
                                                      /*  new Duration(
								dataInicioTransicao, dataFimTransicao)
								.getMillis();*/
					} else
						duracaoNaoSensibilizaPrazoMillis += transicao
								.getDuracaoContandoTempo().getValor()
								.getMillis();

				}

			}
		}

		if (tipoDemandaService.ehTipoDemandaManutecaoCorretiva(demanda)) {
			Duration duracaoNaoContandoPrazo = new Duration(
					duracaoNaoSensibilizaPrazoMillis);
			int qt = (((int) duracaoNaoContandoPrazo.getStandardHours()) / 24);
			// Integer quantidadeDiasNaoSensibilizamPrazoDuranteExecucaoOS =
			// (((int) duracaoNaoContandoPrazo.getStandardHours()) / 24);

			logger.debug("#"
					+ demanda.getRedmineIssueId()
					+ " - Quantidade de Dias que Não Sensibilizam Prazo Execucao OS:"
					+ qt);
			Integer quantidadeDias = quantidadeDiasPrazoMaximo + qt;

			dataFinalizacaoPlanejada = dataFinalizacaoPlanejada
					.plusDays(quantidadeDias);
			return dataFinalizacaoPlanejada;

		}

		if (quantidadeDiasContandoPrazo > quantidadeDiasPrazoMaximo) {
			if (ultimaTransicao.getDataFim() == null) {
				ultimaTransicao.setDataFim(new DateTime());
			}
			dataFinalizacaoPlanejada = ultimaTransicao.getDataFim().minusDays(
					quantidadeDiasContandoPrazo - quantidadeDiasPrazoMaximo);

		} else if (quantidadeDiasContandoPrazo < quantidadeDiasPrazoMaximo) {

			Duration duracaoNaoContandoPrazo = new Duration(
					duracaoNaoSensibilizaPrazoMillis);
			Integer quantidadeDiasNaoSensibilizamPrazoDuranteExecucaoOS = ((int) duracaoNaoContandoPrazo
					.getStandardHours())
					/ getContrato(demanda).getDuracaoHorasExpediente();

			logger.debug("#"
					+ demanda.getRedmineIssueId()
					+ " - Quantidade de Dias que Não Sensibilizam Prazo Execucao OS:"
					+ quantidadeDiasNaoSensibilizamPrazoDuranteExecucaoOS);
			Integer quantidadeDias = quantidadeDiasPrazoMaximo
					+ quantidadeDiasNaoSensibilizamPrazoDuranteExecucaoOS;

			for (int i = 1; i <= quantidadeDias; i++) {

				dataFinalizacaoPlanejada = dataFinalizacaoPlanejada
						.plusDays(diaNaoUtilService.getQuantidadeDiasComunsParaDiaUtil(dataFinalizacaoPlanejada));

			}

		} else {
			dataFinalizacaoPlanejada = ultimaTransicao.getDataFim();
		}

		dataFinalizacaoPlanejada = diaNaoUtilService
				.getProximoDiaUtil(dataFinalizacaoPlanejada);

		logger.debug("#" + demanda.getRedmineIssueId() + " - Data Planejada:"
				+ dataFinalizacaoPlanejada);

		return dataFinalizacaoPlanejada;
	}

	@Transactional("mineiroTransactionManager")
	private DateTime getDataFinalizacaoDiaUtilComExpediente(Demanda demanda) {

		Transicao transicaoHomologacaoAprovada = transicaoService
				.getTransicaoHomologacaoAprovada(demanda.getRedmineIssueId());
		DateTime dataFinalizacao = null;

		if (transicaoHomologacaoAprovada == null
				&& demanda.getConcluida() == false) {
			dataFinalizacao = new DateTime();

		} else if (transicaoHomologacaoAprovada == null
				&& demanda.getConcluida() == true) {
			Transicao ultimaTransicaoSensibilizaPrazo = transicaoService
					.getUltimaTransicaoContaTempoOS(demanda.getRedmineIssueId());

			dataFinalizacao = ultimaTransicaoSensibilizaPrazo.getDataFim();

		} else {

			dataFinalizacao = transicaoHomologacaoAprovada.getDataFim();

		}

		logger.debug("#" + demanda.getRedmineIssueId() + " - Ultima Transição:"
				+ dataFinalizacao);
		return dataFinalizacao;
	}

	@Transactional("mineiroTransactionManager")
	private Duration calcularQuantidadeDiasAtraso(Demanda demanda) {

		DateTime dataFinalizacaoPlanejada = getDataFinalizacaoPlanejadaDiaUtilComExpediente(demanda);
		DateTime dataFinalizacao = getDataFinalizacaoDiaUtilComExpediente(demanda);

		if (dataFinalizacao.isBefore(dataFinalizacaoPlanejada)
				|| (demanda.getDataFinalizacao() != null && dataFinalizacao
						.isAfter(demanda.getDataFinalizacao()))
				|| demanda.getDuracaoContandoTempoOS().getValor().getMillis() < demanda
						.getDuracaoPrazoMaximo().getValor().getMillis()) {
			return Duration.ZERO;
		}

		logger.debug("#" + demanda.getRedmineIssueId() + " - Existe Atraso");
		long duracaoNaoSensibilizaPrazoMillis = 0;
		List<Transicao> transicoes = transicaoService
				.listarTransicaoByRedmineIssueId(demanda.getRedmineIssueId());
		for (Transicao transicao : transicoes) {

			if (!transicao.getContaTempoOS()
					&& transicao.getDataInicio().isAfter(
							dataFinalizacaoPlanejada)
					&& transicao.getDataInicio().isBefore(dataFinalizacao)) {

				duracaoNaoSensibilizaPrazoMillis += transicao.getDuracaoTotal()
						.getValor().getMillis();

			}

		}

		Integer quantidadeDiasAtraso = (int) (new Duration(
				dataFinalizacaoPlanejada, dataFinalizacao)).getStandardDays();
		Integer quantidadeDiasAtrasoNaoSensibilizamPrazo = (int) (new Duration(
				duracaoNaoSensibilizaPrazoMillis)).getStandardDays();
		if (quantidadeDiasAtrasoNaoSensibilizamPrazo > quantidadeDiasAtraso) {
			return Duration.ZERO;
		}

		quantidadeDiasAtraso = quantidadeDiasAtraso
				- quantidadeDiasAtrasoNaoSensibilizamPrazo;

		return Duration.standardDays(quantidadeDiasAtraso);

	}

	@Transactional("mineiroTransactionManager")
	private Duration calcularQuantidadeDiasUteisAtraso(Demanda demanda) {

		Duration duracaoContandoPrazo = demanda.getDuracaoContandoTempoOS()
				.getValor();
		Duration prazoMaximo = demanda.getDuracaoPrazoMaximo().getValor();
		BigDecimal quantidadeDiasContandoPrazo = BigDecimal.ZERO;
		BigDecimal quantidadeDiasPrazoMaximo = BigDecimal.ZERO;
		if (demanda.getTipoDemanda().getNome().contains("Corretiva")) {
			quantidadeDiasContandoPrazo = new BigDecimal(
					((float) duracaoContandoPrazo.getStandardHours()) / 24.0);
			quantidadeDiasPrazoMaximo = new BigDecimal(
					((float) prazoMaximo.getStandardHours()) / 24.0);
		} else {
			quantidadeDiasContandoPrazo = new BigDecimal(
					((float) duracaoContandoPrazo.getStandardHours())
							/ getContrato(demanda).getDuracaoHorasExpediente());
			quantidadeDiasPrazoMaximo = new BigDecimal(
					((float) prazoMaximo.getStandardHours())
							/ getContrato(demanda).getDuracaoHorasExpediente());
		}

		BigDecimal atraso = BigDecimal.ZERO;
		if (quantidadeDiasContandoPrazo.compareTo(quantidadeDiasPrazoMaximo) == 1) {
			atraso = quantidadeDiasContandoPrazo
					.subtract(quantidadeDiasPrazoMaximo);
		}

		return Duration.standardDays(atraso.intValue());

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarDuracaoTemposDemanda(Demanda demanda) {

		TipoDuracao tipoDuracao = null;

		if (demanda.getTipoDemanda().getNome().contains("Corretiva")) {
			tipoDuracao = TipoDuracao.HORA_CORRIDA;
		} else {
			tipoDuracao = TipoDuracao.DIA_UTIL;
		}
		Duracao duracaoContandoTempoOS = new Duracao(
				calcularDuracaoContandoTempoOSDiaUtilComExpediente(demanda),
				tipoDuracao);
		Duracao duracaoContandoTempoCJF = new Duracao(
				calcularDuracaoContandoTempoCJFDiaUtilComExpediente(demanda),
				tipoDuracao);

		demanda.setDuracaoContandoTempoOS(duracaoContandoTempoOS);
		demanda.setDuracaoContandoTempoCJF(duracaoContandoTempoCJF);
		demandaDao.atualizarDemanda(demanda);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Demanda atualizarDadosNiveisServicoDemanda(Demanda demanda) {
		Duration duracaoTotalDemanda = calcularDuracaoTotalDemanda(demanda);
		Duration duracaoContandoPrazo = calcularDuracaoContandoTempoOSDiaUtilComExpediente(demanda);
		Duration duracaoContandoTempoCJF = calcularDuracaoContandoTempoCJFDiaUtilComExpediente(demanda);
		Duration prazoMaximo = calcularPrazoMaximoDiaUtilComExpediente(demanda);
		BigDecimal conformidadeDemanda = calcularConformidadeDemanda(demanda);
		Integer quantidadeRecusas = transicaoService
				.getQuantidadeRecusasDemanda(demanda.getRedmineIssueId());
		BigDecimal percentualAtraso = null;
		Duration quantidadeDiasAtraso = null;
		logger.debug("Duracao Total Demanda: {}", duracaoTotalDemanda);
		TipoDuracao tipoDuracao = null;

		if (demanda.getTipoDemanda().getNome().contains("Corretiva")) {
			tipoDuracao = TipoDuracao.HORA_CORRIDA;
		} else {
			tipoDuracao = TipoDuracao.DIA_CORRIDO;
		}

		demanda.setDuracaoTotal(new Duracao(duracaoTotalDemanda, tipoDuracao));
		logger.debug("Duracao Contando Prazo Demanda: {}", duracaoContandoPrazo);
		if (demanda.getTipoDemanda().getNome().contains("Corretiva")) {
			tipoDuracao = TipoDuracao.HORA_CORRIDA;
		} else {
			tipoDuracao = TipoDuracao.DIA_UTIL;
		}
		demanda.setDuracaoContandoTempoOS(new Duracao(duracaoContandoPrazo,
				tipoDuracao));
		demanda.setDuracaoContandoTempoCJF(new Duracao(duracaoContandoTempoCJF,
				tipoDuracao));
		logger.debug("Prazo Máximo: {}", prazoMaximo);
		demanda.setDuracaoPrazoMaximo(new Duracao(prazoMaximo, tipoDuracao));
		percentualAtraso = calcularPercentualAtraso(demanda);
		demanda.setPercentualAtraso(percentualAtraso);
		logger.debug("Percentual de Atraso: {}", percentualAtraso);
		if (ordemServicoDao.getOrdemServicoByDemandaId(demanda.getId())
				.getContrato().getContratada().contains("Indra")) {
			quantidadeDiasAtraso = calcularQuantidadeDiasAtraso(demanda);
		} else {
			quantidadeDiasAtraso = calcularQuantidadeDiasUteisAtraso(demanda);
		}
		logger.debug("Quantidade de Dias de Atraso: {}", quantidadeDiasAtraso);
		demanda.setDuracaoAtraso(new Duracao(quantidadeDiasAtraso,
				TipoDuracao.DIA_ATRASO));
		logger.debug("Conformidade da Demanda: {}", conformidadeDemanda);
		demanda.setConformidade(conformidadeDemanda);
		logger.debug("Quantidade de Recusas: {}", quantidadeRecusas);
		demanda.setQuantidadeRecusas(quantidadeRecusas);

		demandaDao.atualizarDemanda(demanda);

		return demanda;

	}
        
        
        @Override
	@Transactional("mineiroTransactionManager")
	public Demanda atualizarDataEstimadaConclusao(Demanda demanda,DateTime dataFinalizacaoPlanejada) {
                    demanda.setDataPrevista(dataFinalizacaoPlanejada);
                    demandaDao.atualizarDemanda(demanda);
                    return demanda;
        }

	@Override
	@Transactional("mineiroTransactionManager")
	public Demanda atualizarDadosNiveisServicoDemanda(Integer redmineIssueId) {
		Demanda demanda = demandaDao.getDemandaByRedmineIssueId(redmineIssueId);

		return atualizarDadosNiveisServicoDemanda(demanda);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Demanda extrairDemanda(Issue issue) {
		logger.debug("Demanda #" + issue.getId());
		Map<String, String> camposPersonalizadosIssue = redmineService
				.listarCamposPersonalizadosIssue(issue.getId());
		TipoSistema tipoSistema = TipoSistema.get(camposPersonalizadosIssue
				.get("Tipo de Sistema"));
		Demanda demanda = null;
		Integer redmineIssueId = issue.getId();

		BigDecimal contagemEstimada = BigDecimal.ZERO;

		if (camposPersonalizadosIssue
				.containsKey("Quantitativo de PF (estimado)")
				&& camposPersonalizadosIssue
						.get("Quantitativo de PF (estimado)") != null) {

			contagemEstimada = getNumero(camposPersonalizadosIssue
					.get("Quantitativo de PF (estimado)"));
		}
		logger.debug("Quantitativo de PF (estimado):" + contagemEstimada);

		BigDecimal contagemDetalhada = BigDecimal.ZERO;
		if (camposPersonalizadosIssue.containsKey("Quantitativo de PF Final")
				&& camposPersonalizadosIssue.get("Quantitativo de PF Final") != null) {
			logger.debug("Quantitativo de PF Final:" + contagemDetalhada);
			contagemDetalhada = getNumero(camposPersonalizadosIssue
					.get("Quantitativo de PF Final"));
		}
		logger.debug("Quantitativo de PF Final:" + contagemDetalhada);

		boolean demandaExistente = demandaExiste(redmineIssueId);
		if (demandaExistente) {
			demanda = getDemandaByRedmineIssueId(redmineIssueId);
			demanda.setNome(issue.getSubject());
			demanda.setDescricao(issue.getDescription());
			demanda.setDataAtualizacao(new DateTime(issue.getUpdatedOnDate()));
			demanda.setContagemEstimada(contagemEstimada);
			demanda.setContagemDetalhada(contagemDetalhada);
			demanda.setTipoDemanda(tipoDemandaService.getTipoDemanda(issue
					.getTracker().getId()));
			demanda.setTipoSistema(tipoSistema);
                        demanda.setAutor(issue.getAuthor().getFirstName()+" "+issue.getAuthor().getLastName());
			logger.debug("Tipo de Sistema:" + tipoSistema);
			atualizarDemanda(demanda);
			logger.debug("Demanda #" + demanda.getRedmineIssueId()
					+ " - Atualizada");

		} else {
			demanda = new Demanda();
			demanda.setRedmineIssueId(redmineIssueId);
			demanda.setNome(issue.getSubject());
			demanda.setDescricao(issue.getDescription());
			demanda.setDataCriacao(new DateTime(issue.getCreatedOnDate()));
			demanda.setDataAtualizacao(new DateTime(issue.getUpdatedOnDate()));
			demanda.setContagemEstimada(contagemEstimada);
			demanda.setContagemDetalhada(contagemDetalhada);
			demanda.setTipoDemanda(tipoDemandaService.getTipoDemanda(issue
					.getTracker().getId()));
			demanda.setTipoSistema(tipoSistema);
                        demanda.setAutor(issue.getAuthor().getFirstName()+" "+issue.getAuthor().getLastName());
			logger.debug("Tipo de Sistema:" + tipoSistema);
			criarDemanda(demanda);
			logger.debug("Demanda #" + demanda.getRedmineIssueId()
					+ " - Criada");
		}
		return demanda;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarParaConcluidaDemanda(Demanda demanda) {

		demandaDao.atualizarParaConcluidaDemanda(demanda);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<Object[]> listarQuantidadePontosFuncaoPorMes() {
		return demandaDao.listarQuantidadePontosFuncaoPorMes();
	}

	private BigDecimal getNumero(String numero) {
		StringBuffer sBuffer = new StringBuffer();
		Pattern p = Pattern.compile("[0-9]+([\\.][0-9]+)?");
		Matcher m = p.matcher(numero.trim());
		while (m.find()) {
			sBuffer.append(m.group());
		}
		return new BigDecimal(sBuffer.toString());
	}

	@Override
	public void atualizarDemandasIncompletas() {
		demandaDao.atualizarDemandasIncompletas();
	}
        
        @Override
        @Transactional("mineiroTransactionManager")
	public void atualizarTempoRole(int role_id, int demanda_id,int valor) {
		TempoRolesDemanda t=tempoRolesDemandaDao.getByIdRoleIdDemanda(role_id, demanda_id);
                
                if(t==null){
                    t= new TempoRolesDemanda();
                    Demanda d=demandaDao.getDemandaById(demanda_id);
                    Roles r=rolesDao.getRolesById(role_id);
                    t.setDemanda(d);
                    t.setRole(r);
                    t.setTempo(valor);
                    tempoRolesDemandaDao.criarTransicao(t);
                }else{
                    t.setTempo(valor);
                    tempoRolesDemandaDao.atualizarTransicao(t);
                }
                
                
                
                
	}

}
