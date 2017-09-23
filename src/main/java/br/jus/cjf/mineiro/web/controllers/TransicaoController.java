package br.jus.cjf.mineiro.web.controllers;

import br.jus.cjf.mineiro.config.MineiroConfiguration;
import br.jus.cjf.mineiro.model.ConfiguracaoMineiro;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jus.cjf.mineiro.model.Transicao;
import br.jus.cjf.mineiro.model.Demanda;
import br.jus.cjf.mineiro.service.TransicaoService;
import br.jus.cjf.mineiro.service.DemandaService;
import br.jus.cjf.mineiro.service.OrdemServicoService;
import br.jus.cjf.mineiro.web.controllers.commands.TransicaoCommand;
import br.jus.cjf.mineiro.web.controllers.validators.TransicaoValidator;
import br.jus.cjf.simus.model.Usuario;
import br.jus.cjf.simus.service.SimusService;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Controller
@RequestMapping("/ordemServico/{redmineIssueId}/transicao")
public class TransicaoController {

	private final DemandaService demandaService;
	
	private final OrdemServicoService ordemServicoService;

	private final TransicaoService transicaoService;
	
	private final SimusService simusService;

	@Autowired
	private TransicaoValidator transicaoValidator;
	
	@Autowired
	public TransicaoController(DemandaService demandaService,
			OrdemServicoService ordemServicoService,
			TransicaoService transicaoService, SimusService simusService,
			TransicaoValidator transicaoValidator) {
		super();
		this.demandaService = demandaService;
		this.ordemServicoService = ordemServicoService;
		this.transicaoService = transicaoService;
		this.simusService = simusService;
		this.transicaoValidator = transicaoValidator;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String mostrarTransicoesOrdemServico(@PathVariable int redmineIssueId,
			Map<String, Object> model) {
            
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MineiroConfiguration.class);
		ctx.refresh();
		ConfiguracaoMineiro configuracaoMineiro = ctx.getBean(ConfiguracaoMineiro.class);

		model.put("configuracaoMineiro", configuracaoMineiro);
		ctx.close();
                
		TransicaoCommand transicaoCommand = new TransicaoCommand();
		transicaoCommand.setTransicoes(transicaoService.listarTransicaoByRedmineIssueId(redmineIssueId));
		model.put("ordemServico", ordemServicoService.getOrdemServicoByRedmineIssueId(redmineIssueId));
		model.put("transicaoCommand", transicaoCommand);
		return "listarTransicaoOrdemServico";
	}





	@RequestMapping(value = "{transicaoId}/editar", method = RequestMethod.GET)
	public String editarTransicao(@PathVariable int redmineIssueId, @PathVariable int transicaoId, Map<String, Object> model, HttpServletRequest request) {
            
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
		ctx.register(MineiroConfiguration.class);
		ctx.refresh();
		ConfiguracaoMineiro configuracaoMineiro = ctx.getBean(ConfiguracaoMineiro.class);

		model.put("configuracaoMineiro", configuracaoMineiro);
		ctx.close();
            
            
		model.put("ordemServico", ordemServicoService.getOrdemServicoByRedmineIssueId(redmineIssueId));
		model.put("transicao", transicaoService.getTransicaoById(transicaoId));
		String matricula = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Usuario usuario = simusService.buscaPorMatricula(matricula);
		model.put("usuario", usuario);
		return "editarTransicaoOrdemServico";
	}

	@RequestMapping(value = "{transicaoId}/editar", method = RequestMethod.POST)
	public String editarTransicao(@PathVariable int redmineIssueId, @PathVariable int transicaoId, Transicao transicao, Map<String, Object> model, BindingResult result, HttpServletRequest request) {
		
		if(!ordemServicoService.ordemServicoPrecificada(redmineIssueId)){
			

		transicaoValidator.validate(transicao, result);

		if (result.hasErrors()) {
			model.put("ordemServico", ordemServicoService.getOrdemServicoByRedmineIssueId(redmineIssueId));
			model.put("transicao", transicao);
			return "editarTransicaoOrdemServico";
			
		}
		Transicao transicaoExistenteBanco = transicaoService.getTransicaoById(transicaoId);
		transicaoExistenteBanco.setJustificativa(transicao.getJustificativa());
		transicaoExistenteBanco.setContaTempoOS(transicao.getContaTempoOS());
                // inclusão da linha abaixo (luis sergio)
                transicaoExistenteBanco.setContaRecusaOS(transicao.getContaRecusaOS());
		transicaoService.atualizarTransicao(transicaoExistenteBanco);
		Demanda demanda = demandaService.getDemandaByRedmineIssueId(redmineIssueId);
		demandaService.atualizarDuracaoTemposDemanda(demanda);
                demandaService.atualizarDadosNiveisServicoDemanda(demanda);
                ordemServicoService.atualizarNiveisServicoOrdemServico(ordemServicoService.getOrdemServicoByRedmineIssueId(redmineIssueId));
		
		}
		return "redirect:/ordemServico/"+redmineIssueId+"/transicao/listar";
	}

}