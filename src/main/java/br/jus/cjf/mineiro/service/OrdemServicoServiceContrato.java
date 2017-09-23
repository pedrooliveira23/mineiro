package br.jus.cjf.mineiro.service;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.YearMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.dao.OrdemServicoDao;
import br.jus.cjf.mineiro.model.CategoriaNivelServico;
import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.Demanda;
import br.jus.cjf.mineiro.model.Indicador;
import br.jus.cjf.mineiro.model.OrdemServico;
import br.jus.cjf.mineiro.model.Projeto;
import br.jus.cjf.mineiro.model.Situacao;
import br.jus.cjf.redmine.service.RedmineService;
import java.math.MathContext;
import java.util.Map;


@Service
public class OrdemServicoServiceContrato implements OrdemServicoService {

	private static final BigDecimal CEM = new BigDecimal(100);

	private final OrdemServicoDao ordemServicoDao;

	private final DemandaService demandaService;

	private final ContratoService contratoService;
        
        private final RedmineService redmineService;

	

	private static Logger logger = LoggerFactory.getLogger(OrdemServicoServiceContrato.class);

	@Autowired
	public OrdemServicoServiceContrato(OrdemServicoDao ordemServicoDao, DemandaService demandaService, ContratoService contratoService,
                RedmineService redmineService) {
		super();
		this.ordemServicoDao = ordemServicoDao;
		this.demandaService = demandaService;
		this.contratoService = contratoService;
                this.redmineService = redmineService;
	}

	
	@Override
	@Transactional("mineiroTransactionManager")
	public OrdemServico getOrdemServicoById(Integer id) {

		return ordemServicoDao.getOrdemServicoById(id);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public OrdemServico getOrdemServicoByRedmineIssueId(Integer redmineIssueId) {

		return ordemServicoDao.getOrdemServicoByRedmineIssueId(redmineIssueId);
	}
        
        @Override
	@Transactional("mineiroTransactionManager")
	public OrdemServico getOrdemServicoByDemandaId(Integer demandaId) {

		return ordemServicoDao.getOrdemServicoByDemandaId(demandaId);
	}
        
        

	@Override
	@Transactional("mineiroTransactionManager")
	public DateTime getDataCriacaoOrdemServicoMaisAntiga(Situacao situacao) {

		if (situacao == null) {
			return getDataCriacaoOrdemServicoMaisAntiga();
		}
		return ordemServicoDao.getDataCriacaoOrdemServicoMaisAntiga(situacao);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public DateTime getDataCriacaoOrdemServicoMaisAntiga() {

		return ordemServicoDao.getDataCriacaoOrdemServicoMaisAntiga();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public DateTime getDataFinalizacaoOrdemServicoMaisAntiga(Situacao situacao) {

		if (situacao == null) {
			return getDataFinalizacaoOrdemServicoMaisAntiga();
		}
		return ordemServicoDao.getDataFinalizacaoOrdemServicoMaisAntiga(situacao);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public DateTime getDataFinalizacaoOrdemServicoMaisAntiga() {

		return ordemServicoDao.getDataFinalizacaoOrdemServicoMaisAntiga();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public DateTime getDataCriacaoOrdemServicoIncompletaMaisAntiga() {

		return ordemServicoDao.getDataCriacaoOrdemServicoIncompletaMaisAntiga();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public DateTime getDataFinalizacaoOrdemServicoIncompletaMaisAntiga() {

		return ordemServicoDao.getDataFinalizacaoOrdemServicoIncompletaMaisAntiga();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Boolean existeOrdemServicoByRedmineIssueId(Integer redmineIssueId) {

		return ordemServicoDao.existeOrdemServicoNormalByRedmineIssueId(redmineIssueId);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Boolean existeOrdemServico() {

		return ordemServicoDao.existeOrdemServico();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoFechadaNoMes(YearMonth anoMes) {
		DateTime de = new DateTime(anoMes.getYear(), anoMes.getMonthOfYear(), 1, 0, 0, 0);
		YearMonth proximoMes = anoMes.plusMonths(1);
		DateTime ate = new DateTime(proximoMes.getYear(), proximoMes.getMonthOfYear(), 1, 0, 0, 0);
		return ordemServicoDao.listarOrdemServicoDeAte(de.toDate(), ate.toDate());
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServico() {

		return ordemServicoDao.listarOrdemServico();
	}
        
        
        @Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoContrato(Contrato contrato) {

		return ordemServicoDao.listarOrdemServico(contrato);
	}
        
        @Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoContrato() {

		return ordemServicoDao.listarOrdemServicoContrato();
	}
        

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoPorDataCriacao(DateTime de, DateTime ate, Situacao situacao) {

		return ordemServicoDao.listarOrdemServicoPorDataCriacao(de, ate, situacao);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoPorDataFinalizacao(DateTime de, DateTime ate, Situacao situacao) {

		return ordemServicoDao.listarOrdemServicoPorDataFinalizacao(de, ate, situacao);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoPorDataCriacao(DateTime de, DateTime ate) {
		return ordemServicoDao.listarOrdemServicoPorDataCriacao(de, ate);
                //return ordemServicoDao.listarOrdemServicoPorDataDemandada(de, ate);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoPorDataFinalizacao(DateTime de, DateTime ate) {
		return ordemServicoDao.listarOrdemServicoPorDataFinalizacao(de, ate);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServico(Situacao situacao) {

		return ordemServicoDao.listarOrdemServico(situacao);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoAberta(Boolean aberta) {
		if (aberta) {
			return ordemServicoDao.listarOrdemServico(Situacao.ABERTA);
		}
		return ordemServicoDao.listarOrdemServicoExcetoSituacao(Situacao.ABERTA);
	}
        
        @Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoAbertaTodosContratos(Boolean aberta) {
		if (aberta) {
			return ordemServicoDao.listarOrdemServicoTodosContratos(Situacao.ABERTA);
		}
		return ordemServicoDao.listarOrdemServicoExcetoSituacao(Situacao.ABERTA);
	}
        

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoConcluida(Boolean concluida) {
		if (concluida) {
			return ordemServicoDao.listarOrdemServico(Situacao.CONCLUIDA);
		}
		return ordemServicoDao.listarOrdemServicoExcetoSituacao(Situacao.CONCLUIDA);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoCancelada(Boolean cancelada) {

		if (cancelada) {
			return ordemServicoDao.listarOrdemServico(Situacao.CANCELADA);
		}
		return ordemServicoDao.listarOrdemServicoExcetoSituacao(Situacao.CANCELADA);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoPrecificada(Boolean precificada) {

		if (precificada) {
			return ordemServicoDao.listarOrdemServico(Situacao.PRECIFICADA);
		}
		return ordemServicoDao.listarOrdemServicoExcetoSituacao(Situacao.PRECIFICADA);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoIncompleta() {

		return ordemServicoDao.listarOrdemServicoIncompleta();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoIncompletaPorDataCriacao(DateTime de, DateTime ate, Boolean incompleta) {

		return ordemServicoDao.listarOrdemServicoIncompletaPorDataCriacao(de, ate, incompleta);
	}
        
	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoIncompletaPorDataFinalizacao(DateTime de, DateTime ate, Boolean incompleta) {

		return ordemServicoDao.listarOrdemServicoIncompletaPorDataFinalizacao(de, ate, incompleta);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarOrdemServico(OrdemServico ordemServico) {
		ordemServicoDao.atualizarOrdemServico(ordemServico);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void criarOrdemServico(OrdemServico ordemServico) {
		ordemServicoDao.criarOrdemServico(ordemServico);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void excluir(OrdemServico ordemServico) {
		ordemServicoDao.excluir(ordemServico);

	}

	@Override
	@Transactional(value = "mineiroTransactionManager", readOnly = true, propagation = Propagation.NEVER)
	public OrdemServico getOrdemServicoPrecificadaByRedmineIssueId(Integer redmineIssueId) {
		OrdemServico ordemServico = ordemServicoDao.getOrdemServicoByRedmineIssueId(redmineIssueId);
		
		if ((ordemServico.getDemanda().getConcluida() == true && ordemServico.getDemanda().getContagemDetalhada().compareTo(BigDecimal.ZERO) > 0 && !ordemServico.getSituacao().equals(Situacao.PRECIFICADA)) || (ordemServico.getDemanda().getConcluida() == false)) {
			ordemServico.setValorBruto(calcularValorBruto(ordemServico));
			ordemServico.setValorBrutoDeflacionado(calcularValorBrutoDeflacionado(ordemServico));
			ordemServico.setGlosaAtraso(calcularGlosaAtraso(ordemServico));
			ordemServico.setGlosaRecusa(calcularGlosaRecusa(ordemServico));
			ordemServico.setGlosaConformidade(calcularGlosaConformidade(ordemServico));
			ordemServico.setValorGlosaAtraso(ordemServico.getValorBrutoDeflacionado().multiply(ordemServico.getGlosaAtraso().divide(CEM)).setScale(2, BigDecimal.ROUND_FLOOR));
			ordemServico.setValorGlosaRecusa(ordemServico.getValorBrutoDeflacionado().multiply(ordemServico.getGlosaRecusa().divide(CEM)).setScale(2, BigDecimal.ROUND_FLOOR));
			ordemServico.setValorGlosaConformidade(ordemServico.getValorBrutoDeflacionado().multiply(ordemServico.getGlosaConformidade().divide(CEM)).setScale(2, BigDecimal.ROUND_FLOOR));

			
                        
                        
			ordemServico.setMultaAtraso(calcularMultaAtraso(ordemServico));
                        ordemServico.setMultaAtrasoDemanda(calcularMultaAtrasoDemanda(ordemServico));
                        
                        
                        
                        ordemServico.setValorMultaAtrasoDemanda(ordemServico.getMultaAtrasoDemanda().multiply(ordemServico.getValorBrutoDeflacionado().divide(CEM)).setScale(2, BigDecimal.ROUND_FLOOR));
                        
                        if((ordemServico.getGlosaRecusa().add(ordemServico.getGlosaAtraso())).compareTo(new BigDecimal(30.0))>0){
                            ordemServico.setTotalGlosas(ordemServico.getValorBrutoDeflacionado().multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_FLOOR));
                        }
                        
                        // luis sergio 
			//ordemServico.setValorMultaAtraso(ordemServico.getContrato().getValorTotal().multiply(ordemServico.getMultaAtraso()).divide(CEM).setScale(2, BigDecimal.ROUND_HALF_DOWN));
                      
                      /* if(contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataCriacao(), ordemServico.getDemanda().getDataFinalizacao())!=null){
                            ordemServico.setValorMultaAtraso(contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataCriacao(), ordemServico.getDemanda().getDataFinalizacao()).getValorTotal().multiply(ordemServico.getMultaAtraso()).divide(CEM).setScale(2, BigDecimal.ROUND_HALF_DOWN));
                       } else if(contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataFinalizacao(), ordemServico.getDemanda().getDataFinalizacao())!=null){                            
                           ordemServico.setValorMultaAtraso(contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataFinalizacao(), ordemServico.getDemanda().getDataFinalizacao()).getValorTotal().multiply(ordemServico.getMultaAtraso()).divide(CEM).setScale(2, BigDecimal.ROUND_HALF_DOWN));
                       } else if(contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataCriacao(), ordemServico.getDemanda().getDataCriacao())!=null){                                 
                            ordemServico.setValorMultaAtraso(contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataCriacao(), ordemServico.getDemanda().getDataCriacao()).getValorTotal().multiply(ordemServico.getMultaAtraso()).divide(CEM).setScale(2, BigDecimal.ROUND_HALF_DOWN));
                       } else {
                            ordemServico.setValorMultaAtraso(ordemServico.getContrato().getValorTotal().multiply(ordemServico.getMultaAtraso()).divide(CEM).setScale(2, BigDecimal.ROUND_HALF_DOWN));
                       }*/
                        DateTime hoje = DateTime.now();
                        if(contratoService.getValoresContratoPorVigencia(hoje,hoje)!=null){     
                            
                            ordemServico.setValorMultaAtraso(contratoService.getValoresContratoPorVigencia(hoje, hoje).getValorTotal().multiply(ordemServico.getMultaAtraso()).divide(CEM).setScale(2, BigDecimal.ROUND_HALF_DOWN));
                       // valorPontoFuncao = contratoService.getValoresContratoPorVigencia(hoje,hoje).getValorUnitario();    
                        } else {
                            ordemServico.setValorMultaAtraso(ordemServico.getContrato().getValorTotal().multiply(ordemServico.getMultaAtraso()).divide(CEM).setScale(2, BigDecimal.ROUND_HALF_DOWN));
                               //valorPontoFuncao = ordemServico.getContrato().getValorUnitario();  
                        }
                        
                        ordemServico.setValorTotal(calcularValorFinal(ordemServico));
		}else{
                    ordemServico.setMultaAtrasoDemanda(calcularMultaAtrasoDemanda(ordemServico));
                }
		return ordemServico;
	}

	private BigDecimal calcularGlosaConformidade(OrdemServico ordemServico) {

		CategoriaNivelServico categoriaNivelServico = ordemServico.getNivelServicoConformidade();
		BigDecimal glosaConformidade = BigDecimal.ZERO;

		if (categoriaNivelServico.equals(CategoriaNivelServico.INACEITAVEL)) {

			glosaConformidade = contratoService.getPenalidade("11.1, id 18", ordemServico.getContrato()).getPercentual();

		}
		if (glosaConformidade.compareTo(CEM) == 1) {
			glosaConformidade = CEM;
		}
		logger.debug("Glosa Conformidade:" + glosaConformidade + "%");
		return glosaConformidade.setScale(2, BigDecimal.ROUND_DOWN);

	}

	private BigDecimal calcularGlosaRecusa(OrdemServico ordemServico) {

		BigDecimal quantidadeRecusas = new BigDecimal(ordemServico.getDemanda().getQuantidadeRecusas());
		CategoriaNivelServico categoriaNivelServico = ordemServico.getNivelServicoRecusa();
		BigDecimal glosaRecusa = BigDecimal.ZERO;

		if (categoriaNivelServico.equals(CategoriaNivelServico.INACEITAVEL)) {
			glosaRecusa = contratoService.getPenalidade("11.1, id 21", ordemServico.getContrato()).getPercentual().multiply(quantidadeRecusas);

		}
		if (glosaRecusa.compareTo(CEM) == 1) {
			glosaRecusa = CEM;
		}
		logger.debug("Quantidades Recusas:" + quantidadeRecusas + " Categoria de Nivel de Serviço:" + categoriaNivelServico);
		logger.debug("Glosa Recusa:" + glosaRecusa + "%");
		return glosaRecusa.setScale(2, BigDecimal.ROUND_DOWN);

	}

	private BigDecimal calcularGlosaAtraso(OrdemServico ordemServico) {

		BigDecimal quantidadeDiasAtraso = BigDecimal.ZERO;
		CategoriaNivelServico categoriaNivelServico = ordemServico.getNivelServicoAtraso();
		BigDecimal glosaAtraso = BigDecimal.ZERO;

		if (categoriaNivelServico.equals(CategoriaNivelServico.INACEITAVEL)) {

			quantidadeDiasAtraso = new BigDecimal(ordemServico.getDemanda().getDuracaoAtraso().getValor().getStandardDays());
			glosaAtraso = contratoService.getPenalidade("11.1, id 17", ordemServico.getContrato()).getPercentual().multiply(quantidadeDiasAtraso);

		}
		if (glosaAtraso.compareTo(CEM) == 1) {
			glosaAtraso = CEM;
		}
		logger.debug("Quantidades Dias de Atraso:" + quantidadeDiasAtraso + " Categoria de Nivel de Serviço:" + categoriaNivelServico);
		logger.debug("Glosa Atraso:" + glosaAtraso + "%");
		return glosaAtraso.setScale(2, BigDecimal.ROUND_DOWN);

	}

	private BigDecimal calcularMultaAtraso(OrdemServico ordemServico) {

		long quantidadeDiasAtraso = 0;
                long quantidadeHorasAtraso = 0;
                
                /*TODO: este valor deve ser colocado no banco. Item do contrato 6.8.1 */
                long tempoAtendimento = 12;
		CategoriaNivelServico categoriaNivelServico = ordemServico.getNivelServicoAtraso();
		BigDecimal multaAtraso = BigDecimal.ZERO;

		if (categoriaNivelServico.equals(CategoriaNivelServico.INACEITAVEL)) {

                    quantidadeDiasAtraso = ordemServico.getDemanda().getDuracaoAtraso().getValor().getStandardDays();

                    if(ordemServico.getContrato().getContratada().equals("Indra")){   
			if (quantidadeDiasAtraso > 30) {

				BigDecimal percentualMultaPorDia = contratoService.getPenalidade("11.1, id 19", ordemServico.getContrato()).getPercentual();

				multaAtraso = percentualMultaPorDia.multiply(new BigDecimal(quantidadeDiasAtraso - 30));

			} else if (ordemServico.getDemanda().getTipoDemanda().getNome().contains("Garantia") && quantidadeDiasAtraso > 0) {

				BigDecimal percentualMultaPorDia = contratoService.getPenalidade("11.1, id 20", ordemServico.getContrato()).getPercentual();

				multaAtraso = percentualMultaPorDia.multiply(new BigDecimal(quantidadeDiasAtraso));
			}
                    }else{
                        if (ordemServico.getDemanda().getTipoDemanda().getNome().contains("Garantia")) {
                            BigDecimal percentualMultaPorDiaConclusao = contratoService.getPenalidade("XIV_F", ordemServico.getContrato()).getPercentual();
                            
     
                            if(quantidadeDiasAtraso<0)quantidadeDiasAtraso = 0;
                            multaAtraso = percentualMultaPorDiaConclusao.multiply(new BigDecimal(quantidadeDiasAtraso));

			}
                    }
		}
                
		logger.debug("Quantidades Dias de Atraso:" + quantidadeDiasAtraso + " Categoria de Nivel de Serviço:" + categoriaNivelServico);
		logger.debug("Multa Atraso:" + multaAtraso);
		return multaAtraso;

	}
        
        
        
        
        
        private BigDecimal calcularMultaAtrasoDemanda(OrdemServico ordemServico) {

		long quantidadeDiasAtraso = 0;
                long quantidadeHorasAtraso = 0;
                
                /*TODO: este valor deve ser colocado no banco. Item do contrato 6.8.1 */
                long tempoAtendimento = 12;
		CategoriaNivelServico categoriaNivelServico = ordemServico.getNivelServicoAtraso();
		BigDecimal multaAtraso = BigDecimal.ZERO;

		if (categoriaNivelServico.equals(CategoriaNivelServico.INACEITAVEL)) {

                    quantidadeDiasAtraso = ordemServico.getDemanda().getDuracaoAtraso().getValor().getStandardDays();

                    if(!ordemServico.getContrato().getContratada().equals("Indra")){   
			if (ordemServico.getDemanda().getTipoDemanda().getNome().contains("Garantia")) {
                                BigDecimal percentualMultaPorHoraSolucao = contratoService.getPenalidade("XIV_B", ordemServico.getContrato()).getPercentual();
                                BigDecimal percentualMultaPorDiaSolucaoDefinitiva = contratoService.getPenalidade("XIV_C", ordemServico.getContrato()).getPercentual();
				
                            quantidadeHorasAtraso= demandaService.calcularDuracaoTotalSolucaoContorno(ordemServico.getDemanda()).getStandardHours()-tempoAtendimento;
                            
                            if(quantidadeHorasAtraso<0)quantidadeHorasAtraso = 0;
                            
                            multaAtraso = percentualMultaPorDiaSolucaoDefinitiva.multiply(new BigDecimal(quantidadeDiasAtraso));
                            multaAtraso = multaAtraso.add(percentualMultaPorHoraSolucao.multiply(new BigDecimal(quantidadeHorasAtraso)));
			}
                    }
		}
                
		logger.debug("Quantidades Dias de Atraso:" + quantidadeDiasAtraso + " Categoria de Nivel de Serviço:" + categoriaNivelServico);
		logger.debug("Multa Atraso:" + multaAtraso);
		return multaAtraso;

	}
        
        
        
        
        
        
        
        
        
        
        

	private BigDecimal calcularValorBruto(OrdemServico ordemServico) {
		logger.debug("Projeto:" + ordemServico.getProjeto().getNome());
		logger.debug("Linguagem:" + ordemServico.getProjeto().getLinguagem());
		BigDecimal quantitativoPontosFuncao = ordemServico.getDemanda().getContagemDetalhada();
		if (quantitativoPontosFuncao.compareTo(BigDecimal.ZERO) == 0) {
			quantitativoPontosFuncao = ordemServico.getDemanda().getContagemEstimada();
		}
                
                DateTime hoje = DateTime.now();
                
                BigDecimal valorPontoFuncao = new BigDecimal(1);
                
               /* if(contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataCriacao(), ordemServico.getDemanda().getDataFinalizacao())!=null){
                       valorPontoFuncao = contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataCriacao(), ordemServico.getDemanda().getDataFinalizacao()).getValorUnitario();    
                }else if(contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataFinalizacao(), ordemServico.getDemanda().getDataFinalizacao())!=null){                       
                        valorPontoFuncao = contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataFinalizacao(), ordemServico.getDemanda().getDataFinalizacao()).getValorUnitario();    
                }else if(contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataCriacao(), ordemServico.getDemanda().getDataCriacao())!=null){                       
                        valorPontoFuncao = contratoService.getValoresContratoPorVigencia(ordemServico.getDemanda().getDataCriacao(), ordemServico.getDemanda().getDataCriacao()).getValorUnitario();    
                } else {
                       valorPontoFuncao = ordemServico.getContrato().getValorUnitario();  
                }*/
                
                
                if(contratoService.getValoresContratoPorVigencia(hoje,hoje)!=null){                       
                        valorPontoFuncao = contratoService.getValoresContratoPorVigencia(hoje,hoje).getValorUnitario();    
                } else {
                       valorPontoFuncao = ordemServico.getContrato().getValorUnitario();  
                }
                
                
		BigDecimal valorBruto = (valorPontoFuncao.multiply(quantitativoPontosFuncao));
		logger.debug("Valor ponto de Função:" + valorPontoFuncao);
		logger.debug("Quantitativo de Pontos de Função:" + quantitativoPontosFuncao);
		logger.debug("Valor Bruto :" + valorBruto);
		return valorBruto.setScale(2, BigDecimal.ROUND_DOWN);

	}

	private BigDecimal calcularValorBrutoDeflacionado(OrdemServico ordemServico) {
                // alterado   (já era hard code no enum Linguagem)
                // para atender vigencia de contrato de forma mais rápida // não deveria ser feito dessa forma! isso mesmo!
                Float fPonderacao = new Float(1);
                if(ordemServico.getContrato().getContratada().equals("Indra")){
                    fPonderacao = ordemServico.getProjeto().getLinguagem().getFatorPonderacao();
                }else{
                    fPonderacao = ordemServico.getDemanda().getTipoDemanda().getDeflator().floatValue();
                }
                //BigDecimal fatorPonderacao = new BigDecimal(ordemServico.getProjeto().getLinguagem().getFatorPonderacao());
		BigDecimal fatorPonderacao = new BigDecimal(fPonderacao);
		BigDecimal valorBruto = ordemServico.getValorBruto();
		BigDecimal valorBrutoDeflacionado = valorBruto.multiply(fatorPonderacao);
		logger.debug("Valor Bruto Deflacionado:" + valorBrutoDeflacionado);
		return valorBrutoDeflacionado.setScale(2, BigDecimal.ROUND_DOWN);

	}

	private BigDecimal calcularValorFinal(OrdemServico ordemServico) {
                BigDecimal valorDesconto = ordemServico.getValorGlosaConformidade().add(ordemServico.getValorGlosaRecusa()).add(ordemServico.getValorGlosaAtraso());
               MathContext mc = new MathContext(2);
                
                if(!ordemServico.getContrato().getContratada().equals("Indra")){
                    if(valorDesconto.compareTo(ordemServico.getValorBrutoDeflacionado().multiply(new BigDecimal("0.3")))==1){
                        valorDesconto = (ordemServico.getValorBrutoDeflacionado().multiply(new BigDecimal("0.300")).setScale(2, BigDecimal.ROUND_FLOOR));
                    }
                }
                
                
		BigDecimal valorTotalFinal = ordemServico.getValorBrutoDeflacionado()
                        .subtract(valorDesconto);
                        
                
                if(!ordemServico.getContrato().getContratada().equals("Indra")){
                    valorTotalFinal = valorTotalFinal.subtract(ordemServico.getValorMultaAtraso()).subtract(ordemServico.getValorMultaAtrasoDemanda());
                }
                
		if (valorTotalFinal.compareTo(BigDecimal.ZERO) == -1) {
			valorTotalFinal = BigDecimal.ZERO;
		}
		logger.debug("Valor Total Final:" + valorTotalFinal);
		return valorTotalFinal.setScale(2, BigDecimal.ROUND_FLOOR);

	}

	@Transactional("mineiroTransactionManager")
	public void extrairOrdemServico(Demanda demanda, Projeto projeto) {

		Integer redmineIssueId = demanda.getRedmineIssueId();
                
                
                Map<String, String> camposPersonalizadosIssue = redmineService.listarCamposPersonalizadosIssue(redmineIssueId);
                String  contratada = "";
                Contrato contratoIssue=null;
                
                if(existeOrdemServicoByRedmineIssueId(redmineIssueId)){
                    contratoIssue = ordemServicoDao.getOrdemServicoByDemandaId(demanda.getId()).getContrato();
                }else{
                    if (camposPersonalizadosIssue.containsKey("Contrato") && camposPersonalizadosIssue.get("Contrato") != null) {
                            contratada = camposPersonalizadosIssue.get("Contrato");
                    }
                    
                    logger.debug("Contrato Issue:" + contratada);
                    contratoIssue = contratoService.getContratoPorContratada(contratada.trim());
                }
                
		if(contratoIssue==null){
                    contratoIssue = contratoService.getContratoPorContratada("Outros");
                }
                
                
		Indicador indicadorRecusa = contratoService.getIndicador("Indicador de Recusa", contratoIssue);
		Indicador indicadorConformidade = contratoService.getIndicador("Indicador de Conformidade", contratoIssue);
		Indicador indicadorAtraso = contratoService.getIndicador("Indicador de Atraso", contratoIssue);
		OrdemServico ordemServico = null;
                
                		logger.debug("Contratada:" + contratoIssue.getContratada());

		logger.debug("Ordem de serviço da demanda #" + redmineIssueId);

		if (existeOrdemServicoByRedmineIssueId(redmineIssueId)) {
			ordemServico = getOrdemServicoByRedmineIssueId(redmineIssueId);
			ordemServico.setContrato(contratoIssue);
			ordemServico.setProjeto(projeto);
			ordemServico.setSituacao(getSituacao(ordemServico));
			ordemServico.setNivelServicoRecusa(contratoService.categorizarNivelServico(indicadorRecusa, new BigDecimal(demanda.getQuantidadeRecusas())));
			ordemServico.setNivelServicoConformidade(contratoService.categorizarNivelServico(indicadorConformidade, demanda.getConformidade()));
			ordemServico.setNivelServicoAtraso(contratoService.categorizarNivelServico(indicadorAtraso, demanda.getPercentualAtraso()));
			atualizarOrdemServico(ordemServico);

			logger.debug("Ordem de serviço da demanda #" + redmineIssueId + " - Atualizada");

		} else {
			ordemServico = new OrdemServico();
			ordemServico.setContrato(contratoIssue);
			ordemServico.setDemanda(demanda);
			ordemServico.setProjeto(projeto);
			ordemServico.setSituacao(getSituacao(ordemServico));
			ordemServico.setNivelServicoRecusa(contratoService.categorizarNivelServico(indicadorRecusa, new BigDecimal(demanda.getQuantidadeRecusas())));
			ordemServico.setNivelServicoConformidade(contratoService.categorizarNivelServico(indicadorConformidade, demanda.getConformidade()));
			ordemServico.setNivelServicoAtraso(contratoService.categorizarNivelServico(indicadorAtraso, demanda.getPercentualAtraso()));

			criarOrdemServico(ordemServico);
			logger.debug("Ordem de serviço da demanda #" + redmineIssueId + " - Criada");
		}

	}

	@Transactional("mineiroTransactionManager")
	public void atualizarNiveisServicoOrdemServico(OrdemServico ordemServico) {

		Demanda demanda = ordemServico.getDemanda();
		Integer redmineIssueId = demanda.getRedmineIssueId();
		Indicador indicadorRecusa = contratoService.getIndicador("Indicador de Recusa", ordemServico.getContrato());
		Indicador indicadorConformidade = contratoService.getIndicador("Indicador de Conformidade", ordemServico.getContrato());
		Indicador indicadorAtraso = contratoService.getIndicador("Indicador de Atraso", ordemServico.getContrato());

		if (!ordemServico.getSituacao().equals(Situacao.PRECIFICADA)) {

			ordemServico.setSituacao(getSituacao(ordemServico));

		}
		ordemServico.setNivelServicoRecusa(contratoService.categorizarNivelServico(indicadorRecusa, new BigDecimal(demanda.getQuantidadeRecusas())));
		ordemServico.setNivelServicoConformidade(contratoService.categorizarNivelServico(indicadorConformidade, demanda.getConformidade()));
		ordemServico.setNivelServicoAtraso(contratoService.categorizarNivelServico(indicadorAtraso, demanda.getPercentualAtraso()));
		atualizarOrdemServico(ordemServico);
		logger.debug("Níveis de serviço da Ordem de serviço da demanda #" + redmineIssueId + " - Atualizados");

	}

	private Situacao getSituacao(OrdemServico ordemServico) {

		if (ordemServico.getDemanda().getCancelada()) {
			return Situacao.CANCELADA;
		} else if (ordemServico.getDemanda().getConcluida()) {
			return Situacao.CONCLUIDA;
		} else {
			return Situacao.ABERTA;
		}

	}

	@Transactional("mineiroTransactionManager")
	public void atualizarNiveisServicoOrdemServicoAutomaticamente() {
		logger.debug("Níveis de serviço da ordens de serviço automaticamente");
		atualizarNiveisServicoOrdemServico();

	}

	@Transactional("mineiroTransactionManager")
	public void atualizarNiveisServicoOrdemServicoManualmente() {
		logger.debug("Níveis de serviço da ordens de serviço manualmente");
		atualizarNiveisServicoOrdemServico();

	}

	private void atualizarNiveisServicoOrdemServico() {

		if (existeOrdemServico()) {

			for (OrdemServico ordemServico : listarOrdemServicoPrecificada(false)) {
				ordemServico.setDemanda(demandaService.atualizarDadosNiveisServicoDemanda(ordemServico.getDemanda().getRedmineIssueId()));
				atualizarNiveisServicoOrdemServico(ordemServico);

				logger.debug("Níveis de serviço ordem de serviço #" + ordemServico.getDemanda().getRedmineIssueId() + " " + ordemServico.getDemanda().getNome() + " - Atualizados");
			}
		}

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarNivelServicoConformidadeOrdemServicoByRedmineIssueId(Integer redmineIssueId) {

		OrdemServico ordemServico = getOrdemServicoByRedmineIssueId(redmineIssueId);
		ordemServico.setSituacao(getSituacao(ordemServico));
		Indicador indicadorConformidade = contratoService.getIndicador("Indicador de Conformidade", ordemServico.getContrato());
		ordemServico.setNivelServicoConformidade(contratoService.categorizarNivelServico(indicadorConformidade, ordemServico.getDemanda().getConformidade()));

		atualizarOrdemServico(ordemServico);
		logger.debug("Nível de serviço de conformidade ordem de serviço #" + ordemServico.getDemanda().getRedmineIssueId() + " " + ordemServico.getDemanda().getNome() + " - Atualizados");

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void precificarOrdemServico(OrdemServico ordemServico) {
		OrdemServico ordemServicoPrecificada = getOrdemServicoPrecificadaByRedmineIssueId(ordemServico.getDemanda().getRedmineIssueId());
		ordemServicoPrecificada.setSituacao(Situacao.PRECIFICADA);
		ordemServicoPrecificada.setAutorFinalizacao(ordemServico.getAutorFinalizacao());
		ordemServicoDao.atualizarOrdemServico(ordemServicoPrecificada);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Boolean ordemServicoPrecificada(Integer redmineIssueId) {
		OrdemServico ordemServico = ordemServicoDao.getOrdemServicoByRedmineIssueId(redmineIssueId);
		if (ordemServico == null || !ordemServico.getSituacao().equals(Situacao.PRECIFICADA)) {
			return false;
		}

		return true;

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoVerificacao() {

		return ordemServicoDao.listarOrdemServicoVerificacao();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<OrdemServico> listarOrdemServicoGrafico() {

		return ordemServicoDao.listarOrdemServicoGrafico();
	}
        @Override
        @Transactional("mineiroTransactionManager")
	public BigDecimal QuantitativoPontosFuncao() {

		return ordemServicoDao.getContrato().getQuantitativo();
	}
}
