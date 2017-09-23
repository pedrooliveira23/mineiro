package br.jus.cjf.mineiro.web.controllers;

import br.jus.cjf.mineiro.config.MineiroConfiguration;
import br.jus.cjf.mineiro.model.ConfiguracaoMineiro;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jus.cjf.mineiro.model.OrdemServico;
import br.jus.cjf.mineiro.model.Situacao;
import br.jus.cjf.mineiro.model.Transicao;
import br.jus.cjf.mineiro.service.DemandaService;
import br.jus.cjf.mineiro.service.ExtratorService;
import br.jus.cjf.mineiro.service.OrdemServicoService;
import br.jus.cjf.mineiro.web.controllers.commands.FiltroCommand;
import br.jus.cjf.mineiro.web.controllers.commands.OrdemServicoCommand;
import br.jus.cjf.simus.model.Usuario;
import br.jus.cjf.simus.service.SimusService;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Controller
@RequestMapping("/ordemServico")
public class OrdemServicoController {

	private final OrdemServicoService ordemServicoService;
	
	private final ExtratorService extratorService;
	
	private final SimusService simusService;
        
        private final DemandaService demandaService;
        
        private List<OrdemServico> ordensServicoCache;

	@Autowired
	public OrdemServicoController(OrdemServicoService ordemServicoService,
			ExtratorService extratorService, SimusService simusService,DemandaService demandaService) {
		super();
		this.ordemServicoService = ordemServicoService;
		this.extratorService = extratorService;
		this.simusService = simusService;
                this.demandaService = demandaService;
	}
	
	@RequestMapping(value = "/listar", method = RequestMethod.POST)
	public String mostrarOrdemServico(FiltroCommand filtroCommand, Map<String, Object> model) {

                
		String classificacao = filtroCommand.getClassificacao();
		Situacao situacao = null;
		List<OrdemServico> ordensServico = new ArrayList<OrdemServico>();
                
                filtroCommand.setFim(filtroCommand.getFim().plusHours(23).plusMinutes(59).plusSeconds(59));
		
		model.put("classificacoes", filtroCommand.getClassificacoes());
		model.put("referenciasPesquisa", filtroCommand.getReferenciasPesquisa());
		
		if(classificacao.contains("TODAS")){
			model.put("breadcrumb", "Todas");
			model.put("classificacao", "TODAS");
		}	
		else if(classificacao.contains("INCOMPLETA")){
			
			model.put("breadcrumb", "Incompletas");
			model.put("classificacao", "INCOMPLETA");


		}
		else{
			situacao = Situacao.valueOf(classificacao);
			model.put("classificacao", classificacao);
			model.put("breadcrumb", situacao.getNome()+"s");
		}	
		
                    ordensServicoCache = ordemServicoService.listarOrdemServicoContrato();
                    ordensServico.addAll( ordensServicoCache);
 
		
		if(filtroCommand.getReferenciaPesquisa().contentEquals("ABERTURA")){
			
		
			if(classificacao.contains("INCOMPLETA")){
                            
                            for(OrdemServico ss : ordensServicoCache){ 
                                Transicao t=null;
                                try{
                                    t = ss.getDemanda().getTransicoes().get(0);
                                }catch(Exception ex){
                                    t = demandaService.getPrimeiraTransicaoOrdemServicoDemandadaValida(ss.getDemanda());
                                }
                                if(!ss.getDemanda().getIncompleta() || 
                                        !t.getDataInicio().isAfter(filtroCommand.getInicio()) || 
                                        !t.getDataInicio().isBefore(filtroCommand.getFim()) ){
                                    ordensServico.remove(ss);
                                }
                            }
				
			}
			else{

				if(situacao==null){
                                    for(OrdemServico ss : ordensServicoCache){ 
                                        Transicao t=null;
                                        try{
                                            t = ss.getDemanda().getTransicoes().get(0);
                                        }catch(Exception ex){
                                            t = demandaService.getPrimeiraTransicaoOrdemServicoDemandadaValida(ss.getDemanda());
                                        }
                                        if(     !t.getDataInicio().isAfter(filtroCommand.getInicio()) || 
                                                !t.getDataInicio().isBefore(filtroCommand.getFim()) ){
                                            ordensServico.remove(ss);
                                        }
                                    }   

				}
				else{
                                    
                                    for(OrdemServico ss : ordensServicoCache){ 
                                        Transicao t=null;
                                        try{
                                            t = ss.getDemanda().getTransicoes().get(0);
                                        }catch(Exception ex){
                                            t = demandaService.getPrimeiraTransicaoOrdemServicoDemandadaValida(ss.getDemanda());
                                        }
                                        if(     !ss.getSituacao().equals(situacao) ||
                                                !t.getDataInicio().isAfter(filtroCommand.getInicio()) || 
                                                !t.getDataInicio().isBefore(filtroCommand.getFim()) ){
                                            ordensServico.remove(ss);
                                        }
                                    } 
                                    
				}
	
			}
		}
		else{
			
			if(classificacao.contains("INCOMPLETA")){

				ordensServico = ordemServicoService
						.listarOrdemServicoIncompletaPorDataFinalizacao(filtroCommand.getInicio(), filtroCommand.getFim(), true);			
			}
			else{

				if(situacao==null){
					ordensServico = ordemServicoService
					.listarOrdemServicoPorDataFinalizacao(filtroCommand.getInicio(), filtroCommand.getFim());
				}
				else{
					ordensServico = ordemServicoService
					.listarOrdemServicoPorDataFinalizacao(filtroCommand.getInicio(), filtroCommand.getFim(), situacao);
				}
			
			}
			
		}
		
		for(OrdemServico ss : ordensServico){ 
                    if(ss.getDemanda().getDataPrevista()!=null)
                        continue;
                    DateTime dataFinalizacaoPlanejada = demandaService.getDataFinalizacaoPlanejadaDiaUtilComExpediente(ss.getDemanda());
                    ss.getDemanda().setDataPrevista(dataFinalizacaoPlanejada);
                }
                AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

                ctx.register(MineiroConfiguration.class);
                ctx.refresh();
                ConfiguracaoMineiro configuracaoMineiro = ctx.getBean(ConfiguracaoMineiro.class);
                model.put("configuracaoMineiro", configuracaoMineiro);
		model.put("ordensServico", ordensServico);
		model.put("filtroCommand", filtroCommand );
		
		return "listarOrdemServico";
		 
	}



	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String mostrarOrdemServico(Map<String, Object> model) {
		
		
		FiltroCommand filtroCommand = new FiltroCommand();
                filtroCommand.setFim(filtroCommand.getFim().plusHours(23).plusMinutes(59).plusSeconds(59));
		Situacao situacao = null;
		
		model.put("classificacoes", filtroCommand.getClassificacoes());
		model.put("referenciasPesquisa", filtroCommand.getReferenciasPesquisa());
		model.put("breadcrumb", "Aberta");
		model.put("classificacao", "ABERTA");
                
                 situacao = Situacao.valueOf("ABERTA");
				
		DateTime dataCriacaoOrdemServicoMaisAntiga = ordemServicoService.getDataCriacaoOrdemServicoMaisAntiga(situacao);

		if(filtroCommand.getInicio().isAfter(dataCriacaoOrdemServicoMaisAntiga)){

			filtroCommand.setInicio(dataCriacaoOrdemServicoMaisAntiga);
			
		}
		List<OrdemServico> ordensServico = null;

		ordensServico = ordemServicoService
			.listarOrdemServicoPorDataCriacao(filtroCommand.getInicio(), filtroCommand.getFim(),situacao);
                
                
                for(OrdemServico ss : ordensServico){ 
                    if(ss.getDemanda().getDataPrevista()!=null)
                        continue;
                    DateTime dataFinalizacaoPlanejada = demandaService.getDataFinalizacaoPlanejadaDiaUtilComExpediente(ss.getDemanda());
                    ss.getDemanda().setDataPrevista(dataFinalizacaoPlanejada);
                }
                
                
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

                ctx.register(MineiroConfiguration.class);
                ctx.refresh();
                ConfiguracaoMineiro configuracaoMineiro = ctx.getBean(ConfiguracaoMineiro.class);
                model.put("configuracaoMineiro", configuracaoMineiro);
		model.put("ordensServico", ordensServico);
		model.put("filtroCommand", filtroCommand );
		
		return "listarOrdemServico";
	}
	
	@RequestMapping(value = "/atualizar", method = RequestMethod.GET)
	public String atualizarNiveisServico(Map<String, Object> model) {
		
	
		
		extratorService.extrairManualmente();
		
		return "redirect:/ordemServico/listar";
		
		 
	}
	
	@RequestMapping(value = "{redmineIssueId}/editar", method = RequestMethod.GET)
	public String editarCronometrosOrdemServico(@PathVariable int redmineIssueId, Map<String, Object> model) {
		OrdemServico ordemServico = ordemServicoService.getOrdemServicoPrecificadaByRedmineIssueId(redmineIssueId);
                DateTime dataFinalizacaoPlanejada = demandaService.getDataFinalizacaoPlanejadaDiaUtilComExpediente(ordemServico.getDemanda());
                ordemServico.getDemanda().setDataPrevista(dataFinalizacaoPlanejada);
                
                
		model.put("ordemServico", ordemServico);
		String matricula = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = simusService.buscaPorMatricula(matricula);
		model.put("usuario", usuario);
		return "editarOrdemServico";
	}
	
	@RequestMapping(value = "{redmineIssueId}/editar", method = RequestMethod.POST)
	public String editarCronometrosOrdemServico(@PathVariable int redmineIssueId, OrdemServico ordemServico, Map<String, Object> model) {
            try{
		if(!ordemServico.getSituacao().equals(Situacao.PRECIFICADA))
		    ordemServicoService.precificarOrdemServico(ordemServico);
            }catch(Exception ex){
                ordemServicoService.precificarOrdemServico(ordemServico);
            }
		
		return "redirect:/ordemServico/"+redmineIssueId+"/editar";
	}



	@RequestMapping(value = "/relatorio/listar", method = RequestMethod.GET)
	public String mostrarRelatoriosOrdemServico(Map<String, Object> model) {

		return "listarRelatorioOrdemServico";
	}

	@RequestMapping(value = "/relatorio/{ano}/{mes}", method = RequestMethod.GET)
	public String mostrarRelatorioOrdemServico(@PathVariable int ano,
			@PathVariable int mes, Map<String, Object> model) {
		YearMonth anoMes = new YearMonth(ano, mes);
		List<OrdemServico> ordensServicoNormalDoMes = ordemServicoService
				.listarOrdemServicoFechadaNoMes(anoMes);
		OrdemServicoCommand ordemServicoCommand = new OrdemServicoCommand();
		ordemServicoCommand.setOrdemServicoList(ordensServicoNormalDoMes);
		ordemServicoCommand.setAnoMes(anoMes);
		model.put("ordemServicoCommand", ordemServicoCommand);
		return "gerarRelatorioOrdemServico";
	}
        
        
        @RequestMapping(value = "{redmineIssueId}/atualizar", method = RequestMethod.GET)
	public String extrairOrdemServico(@PathVariable int redmineIssueId, Map<String, Object> model) {
		extratorService.extractRedmineIssue(redmineIssueId);
		return "redirect:/ordemServico/listar";
	}



}
