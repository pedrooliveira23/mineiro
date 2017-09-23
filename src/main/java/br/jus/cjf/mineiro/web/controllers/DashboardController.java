package br.jus.cjf.mineiro.web.controllers;

import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.OrdemServico;
import br.jus.cjf.mineiro.service.ContratoService;
import br.jus.cjf.mineiro.service.ExtratorService;
import br.jus.cjf.mineiro.service.GraficoService;
import br.jus.cjf.mineiro.service.OrdemServicoService;
import br.jus.cjf.mineiro.web.controllers.commands.FiltroCommand;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.jus.cjf.simus.model.Usuario;
import br.jus.cjf.simus.service.SimusService;
import br.jus.cjf.spring.util.CustomWebAuthenticationDetails;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"contratada","contratos"})
public class DashboardController {

	private final SimusService simusService;
	private final ContratoService contratoService;
        private final OrdemServicoService ordemServicoService;
        private final GraficoService graficoService;
        
	
	@Autowired
	public DashboardController(SimusService simusService, ContratoService contratoService,OrdemServicoService ordemServicoService,
                GraficoService graficoService) {
		super();
		this.simusService = simusService;
                this.contratoService = contratoService;
                this.ordemServicoService = ordemServicoService;
                this.graficoService = graficoService;
               
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showHomePage(Map<String, Object> model) {
		String matricula = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                CustomWebAuthenticationDetails webAuthenticationDetails = ((CustomWebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails());
                String scontrato = webAuthenticationDetails.getnContrato();
		Usuario usuario = simusService.buscaPorMatricula(matricula);
                Contrato contrato = contratoService.getContratoPorId(Integer.parseInt(scontrato));
                
                List<Contrato> contratos = contratoService.listarContratos();
                
                List<OrdemServico> ordensServicoCache = ordemServicoService.listarOrdemServicoContrato(contrato);
                
                int total=0, abertas=0,cjf=0,fs=0;
                for(OrdemServico o : ordensServicoCache){
                    if(o.getDemanda().getCancelada() )
                        continue;
                    
                    int sizeTransicoes=o.getDemanda().getTransicoes().size()-1;
                    if(o.getDemanda().getTransicoes().get(sizeTransicoes).getEstado().getRole()==null)continue;
                    
                    total++;
                    if(o.getDemanda().getConcluida())
                        continue;
                    
                    
                    
                    
                    abertas++;
                    if(o.getDemanda().getTransicoes().get(sizeTransicoes).getEstado().getRole().getSigla().equals("FS")){
                        fs++;
                    }else{
                        cjf++;
                    }
                    
                    
                }
                
                Map<String, Integer> map = new LinkedHashMap<String, Integer>();
                map.put("total", total);
                map.put("abertas", abertas);
                map.put("cjf", cjf);
                map.put("fs", fs);
                model.put("indice", graficoService.getIndiceDiasPF().floatValue());
                
                model.put("map", map);
                model.put("contratos", contratos);
		model.put("usuario", usuario);
		model.put("contratada", contrato);
                
               
                
                //model.put("fabrica", contrato.getContratada());
		return "dashboard";
	}
        
        @RequestMapping(value = "/trocarContrato/{contratoId}", method = RequestMethod.GET)
        public String trocarContrato(@PathVariable String contratoId,Map<String, Object> model) {
		
            
                CustomWebAuthenticationDetails webAuthenticationDetails = ((CustomWebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails());
                webAuthenticationDetails.setContrato(contratoId);
                graficoService.setTimeLastUpdate();
		return "redirect:/";
	}
}
