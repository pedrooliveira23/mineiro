package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.model.Contrato;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.model.Demanda;
import br.jus.cjf.mineiro.model.Estado;
import br.jus.cjf.mineiro.model.OrdemServico;
import br.jus.cjf.mineiro.model.Projeto;
import br.jus.cjf.mineiro.model.Transicao;
import br.jus.cjf.mineiro.util.Estatistica;
import br.jus.cjf.redmine.model.Issue;
import br.jus.cjf.redmine.model.Project;
import br.jus.cjf.redmine.model.Status;
import br.jus.cjf.redmine.model.Tracker;
import br.jus.cjf.redmine.model.journal.IssueJournal;
import br.jus.cjf.redmine.model.journal.StatusJournalDetail;
import br.jus.cjf.redmine.service.RedmineService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;
import org.joda.time.DateTime;

@Service
public class ExtratorServiceContrato implements ExtratorService {

	private final RedmineService redmineService;
	private final DemandaService demandaService;
	private final OrdemServicoService ordemServicoService;
	private final EstadoService estadoService;
	private final TransicaoService transicaoService;
	private final ProjetoService projetoService;
	private final TipoDemandaService tipoDemandaService;
	private final DiaNaoUtilService diaNaoUtilService;
	private final TipoInspecaoService tipoInspecaoService;
	private final NotaService notaService;
	private final ContratoService contratoService;
        private final ParametroService parametroService;
	private static Logger logger = LoggerFactory.getLogger(ExtratorServiceContrato.class);

	@Autowired
	public ExtratorServiceContrato(RedmineService redmineService, DemandaService demandaService, OrdemServicoService ordemServicoService, EstadoService estadoService, TransicaoService transicaoService, ProjetoService projetoService, TipoDemandaService tipoDemandaService, DiaNaoUtilService diaNaoUtilService, TipoInspecaoService tipoInspecaoService, NotaService notaService, ContratoService contratoService, ParametroService parametroService) {
		super();
		this.redmineService = redmineService;
		this.demandaService = demandaService;
		this.ordemServicoService = ordemServicoService;
		this.estadoService = estadoService;
		this.transicaoService = transicaoService;
		this.projetoService = projetoService;
		this.tipoDemandaService = tipoDemandaService;
		this.diaNaoUtilService = diaNaoUtilService;
		this.tipoInspecaoService = tipoInspecaoService;
		this.notaService = notaService;
		this.contratoService = contratoService;
                this.parametroService = parametroService;
	}

	@Scheduled(fixedRate = 3600000)
	@Override
	@Transactional("mineiroTransactionManager")
	public void extrairAutomaticamente() {

		logger.info("Extração de Rotina - Iniciada");
		extractRedmineProjects();
		extractRedmineStatuses();
		extractRedmineTrackers();
		realizarCargaDadosEstaticos();
		extractRedmineIssues();
		atualizaOS();
		logger.info("Extração de Rotina - Finalizada");

	}

	@Transactional("mineiroTransactionManager")
	private void atualizaOS() {
		demandaService.atualizarDemandasIncompletas();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void extrairManualmente() {

		logger.info("Extração Manual - Iniciada");
		extractRedmineProjects();
		extractRedmineStatuses();
		extractRedmineTrackers();
		realizarCargaDadosEstaticos();
		extractRedmineIssues();
		logger.info("Extração Manual - Finalizada");

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void extractRedmineProjects() {

		List<Project> projects = redmineService.listarProjetosRedmine();
		transformRedmineProjectToProjeto(projects);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void extractRedmineTrackers() {

		List<Tracker> trackers = redmineService.listarTrackers();
		transformRedmineTrackerToTipoDemanda(trackers);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void extractRedmineStatuses() {

		List<Status> statuses = redmineService.listarStatuses();
		transformRedmineStatusToEstado(statuses);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void extractRedmineIssues() {

		List<Issue> issues = null;

		if (!ordemServicoService.existeOrdemServico()) {
			issues = redmineService.listarTodasIssues();
			logger.info("Nenhuma issue convertida para ordem de serviço");
			logger.info("Quantidade de issues que serão extraidas:" + issues.size());

		} else {
			Date ultimaAtualizacao = demandaService.getDemandaMaisAtualizada();
                        
                        //luis - usado para ajustar
                        //Calendar cal = Calendar.getInstance();
                        //cal.set(2015, Calendar.JUNE, 30); 
                      //  Date dateacerto = cal.getTime();
                        
                        
			//issues = redmineService.listarIssuesApartir(ultimaAtualizacao);
                        issues = redmineService.listarIssuesAbertas(ultimaAtualizacao);
			logger.info("{} de issues não extraidas ou que foram alteradas posteriormente a {}", issues.size(), ultimaAtualizacao);

		}
		for (Issue issue : issues) {
			logger.info("Chamado - #" + issue.getId() + " Titulo:" + issue.getSubject());
		}
		transformRedmineIssueToOrdemServico(issues,false);

	}
        
        @Override
	@Transactional("mineiroTransactionManager")
	public void extractRedmineIssue(Integer redmineIssueId) {

		List<Issue> issues = null ;

		issues=redmineService.listarIssue(redmineIssueId);
                        
		for (Issue issue : issues) {
			logger.info("Chamado - #" + issue.getId() + " Titulo:" + issue.getSubject());
		}
		transformRedmineIssueToOrdemServico(issues,true);
	}
        
        

	@Override
	@Transactional("mineiroTransactionManager")
	public void transformRedmineProjectToProjeto(List<Project> projects) {

		for (Project project : projects) {

			projetoService.extrairProjeto(project);

		}

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void transformRedmineTrackerToTipoDemanda(List<Tracker> trackers) {

		for (Tracker tracker : trackers) {

			tipoDemandaService.extrairTipoDemanda(tracker);

		}

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void transformRedmineStatusToEstado(List<Status> statuses) {

		for (Status status : statuses) {

			estadoService.extrairEstado(status);
		}

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void transformRedmineIssueToOrdemServico(List<Issue> issues, boolean forcarAtualizacao) {

		for (Issue issue : issues) {
			logger.debug("Issue:" + issue.getId() + " - " + issue.getTracker().getName());
			Integer redmineProjectId = issue.getProject().getId();
			Projeto projeto = projetoService.getProjetoByRedmineProjectId(redmineProjectId);
			List<StatusJournalDetail> statusJournalDetailList = redmineService.listarStatusDaIssue(issue);
			Demanda demanda = demandaService.extrairDemanda(issue);
			List<IssueJournal> issueJournalList = redmineService.listarIssueJournal(issue.getId());

                        if (!ordemServicoService.existeOrdemServicoByRedmineIssueId(demanda.getRedmineIssueId())) {
				ordemServicoService.extrairOrdemServico(demanda, projeto);
			}
                        
                        
			for (int i = 0; i < statusJournalDetailList.size(); i++) {

				StatusJournalDetail statusJournalDetailAtual = statusJournalDetailList.get(i);
				StatusJournalDetail statusJournalDetailProximo = null;
				Status status = statusJournalDetailAtual.getCurrentStatus();
				Estado estado = null;
				if (status != null) {
					estado = estadoService.getEstadoByRedmineStatusId(status.getId());
				} else {

					estado = estadoService.getEstadoByRedmineStatusId(statusJournalDetailList.get(i - 1).getId());
				}
				Boolean ultimoEstado = (statusJournalDetailList.size() == (i + 1));
				if (!ultimoEstado) {
					statusJournalDetailProximo = statusJournalDetailList.get(i + 1);
				}
				logger.debug("Estado:" + estado.getNome());
                                //System.out.println(" Teste demanda(issue):"+issue.getId());
				transicaoService.extrairTransicao(demanda, estado, statusJournalDetailAtual, statusJournalDetailProximo, issueJournalList,forcarAtualizacao);

			}
                        logger.info("Issue:" + issue.getId() + " Extrair Nota");
			notaService.extrairNota(issueJournalList);

			if (!ordemServicoService.ordemServicoPrecificada(issue.getId())) {
				ordemServicoService.extrairOrdemServico(demandaService.atualizarDadosNiveisServicoDemanda(demanda), projeto);
			}
			logger.info("Issue:" + issue.getId() + " Extraida");
		}

	}

	@Transactional("mineiroTransactionManager")
	private void realizarCargaDadosEstaticos() {

		diaNaoUtilService.realizarCargaFeriados();
		tipoInspecaoService.realizarCargaTipoInspecao();
		contratoService.realizarCargaContrato();

	}
        
        
        @Scheduled(fixedRate = 600000)
	@Override
	@Transactional("mineiroTransactionManager")
	public void calcularDataEstimadaDemanda() {

		logger.info("Calculo data estimada de conclusão - Iniciada");
		List<OrdemServico> ordensServico = new ArrayList<OrdemServico>();
                
                ordensServico = ordemServicoService.listarOrdemServicoAbertaTodosContratos(Boolean.TRUE);
                
                for(OrdemServico ss : ordensServico){                
                    DateTime dataFinalizacaoPlanejada = demandaService.getDataFinalizacaoPlanejadaDiaUtilComExpediente(ss.getDemanda());
                    demandaService.atualizarDataEstimadaConclusao(ss.getDemanda(), dataFinalizacaoPlanejada);
                }
                
                
		logger.info("Calculo data estimada de conclusão - Finalizada");

	}
        
        
    @Scheduled(fixedRate = 600000)
    @Override
    public void calcularTempoMedioNoEstadoPorPF() {

        Contrato ct = contratoService.getContratoPorContratada("Outros");
        logger.info("Calculo tempo medio no estado por PF - Iniciada");
        List<OrdemServico> ordensServico = new ArrayList<OrdemServico>();
        
        /*Recupera a lista de OSs do contrato*/
        ordensServico = ordemServicoService.listarOrdemServicoContrato(ct);

        /*Map que armazenara a lista de médias de tempo por PF para estado de cada OS.*/
        Map<String, List<Double>> ind = new LinkedHashMap<String, List<Double>>();
        ind.put("OS", new ArrayList<Double>());

        /*Busca a lista de estados*/
        for (Estado e : estadoService.listarEstados()) {
            ind.put(e.getNome(), new ArrayList<Double>());
        }

        /*Armazena a média de minutos por pf para cada transição de cada OS.*/
        for (OrdemServico ss : ordensServico) {
            Map<String, Float> mapTempo = new LinkedHashMap<String, Float>();
            
            if (ss.getDemanda().getCancelada() || ss.getDemanda().getIncompleta()) {
                continue;
            }
            
            if (ss.getDemanda().getTipoDemanda().getNome().contains("Corretiva")) {
                continue;
            }

            BigDecimal pf = ss.getDemanda().getContagemDetalhada();
            if (!(pf.compareTo(BigDecimal.ZERO) > 0)) {
                pf = ss.getDemanda().getContagemEstimada();
            }

            if (!(pf.compareTo(BigDecimal.ZERO) > 0)) {
                continue;
            }
            
            /*Calcula a média de tempo por PF de cada estado da OS*/
            for (Transicao t : ss.getDemanda().getTransicoes()) {
                float tempo = t.getDuracaoContandoTempo().getValor().getStandardMinutes();
                String estado = t.getEstado().getNome();

                if (mapTempo.containsKey(estado)) {
                    mapTempo.put(estado, (mapTempo.get(estado) + tempo / pf.floatValue()));
                } else {
                    mapTempo.put(estado, tempo / pf.floatValue());
                }
            }
            
            ((List<Double>) ind.get("OS")).add((double) ss.getDemanda().getRedmineIssueId());
            
            /*Adiciona na lista de estados*/
            for (Map.Entry<String, List<Double>> t : ind.entrySet()) {
                if (t.getKey().equals("OS")) {
                    continue;
                }
                if (mapTempo.containsKey(t.getKey())) {
                    ((List<Double>) ind.get(t.getKey())).add(Double.parseDouble(mapTempo.get(t.getKey()).toString()));
                } else {
                    ((List<Double>) ind.get(t.getKey())).add(0.0);
                }
            }

        }
        
        /*Recupera o parametro porcentagem_minima_os do banco*/
        Double porcentagem=0.0;
        try{
            porcentagem = Double.parseDouble(parametroService.getParametro("porcentagem_minima_os").getValor());
        }catch(NumberFormatException ex){
            porcentagem = 0.05;
            logger.info("Calculo tempo medio no estado por PF - Erro ao buscar parametro porcentagem_minima_os.");
        }

        /*Calcula a média de tempo por PF de cada estado, levando em conta o desvio padrão e o parametro porcentagem_minima_os*/
        for (Map.Entry<String, List<Double>> t : ind.entrySet()) {
            if (t.getKey().equals("OS")) {
                continue;
            }
            List<Double> lista = new ArrayList<Double>();
            List<Double> lista2 = new ArrayList<Double>();
            for (double d : t.getValue()) {
                lista2.add(d);
                if (d != 0) {
                    lista.add(d);
                }
            }

            float tempo = 0, tempo2=0;
            
            /*Remove os valores que estão alem de dois desvios padrão da média. Executa 2 vezes este procedimento.*/
            if (lista.size() > 0)
                for(int w =0;w<2;w++)
                    lista = buscarElementos2DesviosPadrao(lista);
            
            /*Remove os valores que estão alem de dois desvios padrão da média. Executa 2 vezes este procedimento.*/
            if (lista2.size() > 0)
                for(int w =0;w<2;w++)
                    lista2 = buscarElementos2DesviosPadrao(lista2);
           
            
            if (lista.size() < ordensServico.size() * porcentagem) {
                tempo = 0;
            } else {
                for (Double dd : lista) {
                    tempo += dd;
                }
                tempo = tempo / lista.size();
            }
            
           if (lista2.size() > 0){
                for (Double dd : lista2) {
                    tempo2 += dd;
                }
                tempo2 = tempo2 / lista2.size();
           }
            

            /*Salva informações de média.*/
            Estado e = estadoService.getEstadoPorNome(t.getKey());
            e.setMediaTempoPorPF(tempo);
            if(e.getContaIndicador()){
                 e.setMediaTempoPorPFInd(tempo2);
            }else{
                e.setMediaTempoPorPFInd(0);
            }
            estadoService.atualizarEstado(e);
        }

        logger.info("Calculo tempo medio no estado por PF - Finalizado");

    }
    /*Retorna os elementos do array em que a diferença do elemento com a média 
    está a no máximo dois devios padrão*/
    private List<Double> buscarElementos2DesviosPadrao(List<Double> lista) {
        Double[] array = new Double[lista.size()];

        array = lista.toArray(array);
        Estatistica estatistica = new Estatistica();
        estatistica.setArray(array);

        double sd = estatistica.getDesvioPadrao();
        double ma = estatistica.getMediaAritmetica();

        for (int i = 0; i < lista.size(); i++) {
            double valor = lista.get(i);
            if (valor < (-2 * sd + ma) || valor > (2 * sd + ma)) {
                lista.remove(i--);
            }
        }

        return lista;
    }
        
    
    
    
    @Scheduled(fixedRate = 600000)
    @Override
    public void calcularTempoRolesDemanda() {

        Contrato ct = contratoService.getContratoPorContratada("Outros");
        logger.info("Calculo tempo Roles Demanda - Iniciada");
        List<OrdemServico> ordensServico = new ArrayList<OrdemServico>();
        
        /*Recupera a lista de OSs do contrato*/
        ordensServico = ordemServicoService.listarOrdemServicoContrato(ct);
        
        


        /*Armazena a média de minutos por pf para cada transição de cada OS.*/
        for (OrdemServico ss : ordensServico) {
             Map<String, Integer> ind = new LinkedHashMap<String, Integer>();
             
             if (ss.getDemanda().getCancelada()) {
                continue;
            }
            for(Transicao tt : ss.getDemanda().getTransicoes()){
                String idRole =null;
                try{
                    idRole=tt.getEstado().getRole().getId()+"";
                }catch(Exception ex){
                    
                }
                
                if(idRole==null || idRole.isEmpty()){
                    continue;
                }else{
                    int tempo=0;
                   if( ind.get(idRole)!=null)
                        tempo=ind.get(idRole);
                    tempo=tempo+((int)tt.getDuracaoContandoTempo().getValor().getStandardMinutes());
                    ind.put(idRole, tempo);
                }
                
                
            }
            
             for (Map.Entry<String, Integer> t : ind.entrySet()) {
                 demandaService.atualizarTempoRole(Integer.parseInt(t.getKey()), ss.getDemanda().getId(), t.getValue());
             }
            
        }
        
        

        logger.info("Calculo tempo Roles Demanda - Finalizado");

    }

}
