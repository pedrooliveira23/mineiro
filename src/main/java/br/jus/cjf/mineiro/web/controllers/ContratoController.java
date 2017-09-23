package br.jus.cjf.mineiro.web.controllers;

import br.jus.cjf.mineiro.model.Contrato;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jus.cjf.mineiro.model.ValoresContrato;
import br.jus.cjf.mineiro.service.ContratoService;
import br.jus.cjf.mineiro.service.OrdemServicoService;
import br.jus.cjf.mineiro.web.controllers.validators.ContratoValidator;
import br.jus.cjf.mineiro.web.controllers.validators.ValoresContratoValidator;
//import br.jus.cjf.mineiro.web.controllers.validators.ValoresContratoValidator;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequestMapping("/admin/contrato")
public class ContratoController {

	private final ContratoService contratoService;
	private final OrdemServicoService ordemServicoService;
        private boolean erroExcluir = false;
      

	@Autowired
	public ContratoController(ContratoService contratoService,
			OrdemServicoService ordemServicoNormalService) {
		super();

		this.ordemServicoService = ordemServicoNormalService;
                this.contratoService = contratoService;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listarContrato(Map<String, Object> model) {

                if(erroExcluir){
                    erroExcluir = false;
                    model.put("erro",	"Existem ordens de serviço vinculadas a este contrato, não é possível excluir.");
                }
		model.put("contratos",	contratoService.listarContratos());

		return "listarContrato";
	}

	@RequestMapping(value = "/criar", method = RequestMethod.GET)
	public String criarContrato(Map<String, Object> model, HttpServletRequest request) {
		Contrato contrato = new Contrato();
		model.put("contrato", contrato);
		return "criarContrato";
	}

	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public String criarContrato( Contrato contrato, BindingResult result,
			Map<String, Object> model, HttpServletRequest request) {

            
                        ContratoValidator validator = new ContratoValidator();
                        validator.validate(contrato, result);
                        
		if (result.hasErrors()) {
			model.put("contrato", contrato);

			return "criarContrato";
		}
                
               
                
                DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss");
                LocalTime ini = fmt.parseLocalTime(contrato.getInicioExpediente1());
                LocalTime fim = fmt.parseLocalTime(contrato.getFimExpediente1());
                contrato.setInicioExpediente(ini);
                contrato.setFimExpediente(fim);
                
                contrato.setValorTotal(contrato.getQuantitativo().multiply(contrato.getValorUnitario()));
                
		contratoService.criarContrato(contrato);

		return "redirect:/admin/contrato/"+contrato.getId()+"/editar";
	}

	@RequestMapping(value = "/{contratoId}/editar", method = RequestMethod.GET)
	public String editarContrato(@PathVariable int contratoId, Map<String, Object> model,
			HttpServletRequest request) {
	

		Contrato contrato = contratoService.getContratoPorId(contratoId);

                
                DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss");
                contrato.setInicioExpediente1(contrato.getInicioExpediente().toString(fmt));
                contrato.setFimExpediente1(contrato.getFimExpediente().toString(fmt));
 
                List<ValoresContrato> valores = contratoService.getValoresContrato(contrato);
                
                
                Collections.reverse(valores);
		model.put("contrato", contrato);
                model.put("valores", valores);

		return "editarContrato";
	}

	@RequestMapping(value = "/{contratoId}/editar", method = RequestMethod.POST)
	public String editarContrato(@PathVariable int contratoId,Contrato contrato,
			BindingResult result, Map<String, Object> model,
			HttpServletRequest request) {
                
            ContratoValidator validator = new ContratoValidator();
                        validator.validate(contrato, result);
            
                List<ValoresContrato> valores = contratoService.getValoresContrato(contrato);

		if (result.hasErrors() || valores==null || valores.isEmpty()) {
			model.put("contrato", contrato);
                        if(valores==null || valores.isEmpty())
                            model.put("mensagem", "Erro: deve ser informado no mínimo um valor para o contrato.");
                        
			return "editarContrato";
		}
                
                Contrato cont = contratoService.getContratoPorId(contratoId);
                cont.setContratada(contrato.getContratada());
                cont.setNumero(contrato.getNumero());
                cont.setValorUnitario(contrato.getValorUnitario());
                cont.setQuantitativo(contrato.getQuantitativo());
                
                DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss");
                LocalTime ini = fmt.parseLocalTime(contrato.getInicioExpediente1());
                LocalTime fim = fmt.parseLocalTime(contrato.getFimExpediente1());
                cont.setInicioExpediente(ini);
                cont.setFimExpediente(fim);
                
                cont.setValorTotal(contrato.getQuantitativo().multiply(contrato.getValorUnitario()));
                
                
                
		contratoService.atualizarContrato(cont);
		return "redirect:/admin/contrato/listar";
	}

	@RequestMapping(value = "/{contratoId}/excluir", method = RequestMethod.GET)
	public String excluirContrato(@PathVariable int contratoId, Map<String, Object> model,
			HttpServletRequest request) {

			Contrato contrato = contratoService.getContratoPorId(contratoId);
                        
                       if(ordemServicoService.listarOrdemServicoContrato(contrato).size()>0){
                           erroExcluir=true;
                           
                       }else{
                            contratoService.excluirContrato(contrato);
                       }
		
		return "redirect:/admin/contrato/listar";
	}
        
        
        
        
        
        @RequestMapping(value = "/{contratoId}/valores/criar", method = RequestMethod.GET)
	public String criarValor(@PathVariable int contratoId,Map<String, Object> model, HttpServletRequest request) {
		ValoresContrato valor = new ValoresContrato();
                Contrato contrato = contratoService.getContratoPorId(contratoId);
                valor.setContrato(contrato);
                valor.setQuantitativo(contrato.getQuantitativo());
                valor.setValorUnitario(contrato.getValorUnitario());
                valor.setDataInicioVigencia(DateTime.now());
                valor.setDataFimVigencia(null);
		model.put("valor", valor);
                model.put("contrato", contrato);
		return "criarValor";
	}

	@RequestMapping(value = "/{contratoId}/valores/criar", method = RequestMethod.POST)
	public String criarValor( @PathVariable int contratoId,@ModelAttribute("valor") ValoresContrato valor, BindingResult result,
			Map<String, Object> model, HttpServletRequest request) {

            Contrato contrato = contratoService.getContratoPorId(contratoId);
             List <ValoresContrato> valores = contratoService.getValoresContrato(contrato);
            ValoresContrato valorAtual=null;
            for(ValoresContrato v : valores){
                    if(v.getDataFimVigencia()==null ){
                        valorAtual = v;
                    } 
            }
            
                        ValoresContratoValidator validator = new ValoresContratoValidator(valorAtual);
                        validator.validate(valor, result);
                        
		if (result.hasErrors()) {
			model.put("valor", valor);

			return "criarValor";
		}
                
                valor.setValorTotal(valor.getQuantitativo().multiply(valor.getValorUnitario()));
		contratoService.criarValoresContrato(valor);
                
                
                for(ValoresContrato v : valores){
                    if(v.getId()!=valor.getId() && v.getDataFimVigencia()==null ){
                        v.setDataFimVigencia(valor.getDataInicioVigencia().minusDays(1));
                        contratoService.atualizarValoresContrato(v);
                    } 
                }
                
		return "redirect:/admin/contrato/"+contratoId+"/editar";
	}
        
        
        
        
        
        
        
        
          @RequestMapping(value = "/{contratoId}/valores/{valorId}/editar", method = RequestMethod.GET)
	public String editarValor(@PathVariable int contratoId,@PathVariable int valorId,Map<String, Object> model, HttpServletRequest request) {
		ValoresContrato valor = contratoService.getValoresContratoPorId(valorId);
                Contrato contrato = contratoService.getContratoPorId(contratoId);

		model.put("valor", valor);
                model.put("contrato", contrato);
		return "editarValor";
	}

	@RequestMapping(value = "/{contratoId}/valores/{valorId}/editar", method = RequestMethod.POST)
	public String editarValor( @PathVariable int contratoId,@ModelAttribute("valor") ValoresContrato valor, BindingResult result,
			Map<String, Object> model, HttpServletRequest request) {
            
            Contrato contrato = contratoService.getContratoPorId(contratoId);
            List <ValoresContrato> valores = contratoService.getValoresContrato(contrato);
            ValoresContrato penultimoValor = new ValoresContrato();
            penultimoValor.setDataInicioVigencia(DateTime.now().minusYears(50));
            
            for(ValoresContrato v : valores){
                    if(v.getId()!=valor.getId()){
                        if(penultimoValor.getDataInicioVigencia().isBefore(v.getDataInicioVigencia())){
                            penultimoValor=v;
                        }
                    }
                }
            
            ValoresContratoValidator validator = new ValoresContratoValidator(penultimoValor);
            validator.validate(valor, result);
            
		if (result.hasErrors()) {
			model.put("valor", valor);

			return "editarValor";
		}
                
                ValoresContrato vsalvar = contratoService.getValoresContratoPorId(valor.getId());
                
                vsalvar.setDataInicioVigencia(valor.getDataInicioVigencia());
                
                vsalvar.setValorUnitario(valor.getValorUnitario());
                vsalvar.setQuantitativo(valor.getQuantitativo());
                vsalvar.setValorTotal(valor.getQuantitativo().multiply(valor.getValorUnitario()));
                contratoService.atualizarValoresContrato(vsalvar);
                    
                penultimoValor.setDataFimVigencia(valor.getDataInicioVigencia().minusDays(1));
                contratoService.atualizarValoresContrato(penultimoValor);
                //}catch(Exception ex){
                    
               // }
                

		return "redirect:/admin/contrato/"+contratoId+"/editar";
	}
        
        
        
        
        
        @RequestMapping(value = "/{contratoId}/valores/{valorId}/excluir", method = RequestMethod.GET)
	public String excluirValor(@PathVariable int contratoId,@PathVariable int valorId,
                Map<String, Object> model,HttpServletRequest request) {
		//if (!ordemServicoService.ordemServicoPrecificada(redmineIssueId)) {

                Contrato contrato = contratoService.getContratoPorId(contratoId);
                ValoresContrato valor = contratoService.getValoresContratoPorId(valorId);
                
                if(valor.getDataFimVigencia()!=null){
                    
                    
                    return "redirect:/admin/contrato/"+contratoId+"/editar";
                    
                }else{
                    List <ValoresContrato> valores = contratoService.getValoresContrato(contrato);

                    ValoresContrato penultimoValor = new ValoresContrato();
                    penultimoValor.setDataInicioVigencia(DateTime.now().minusYears(50));

                    for(ValoresContrato v : valores){
                        if(v.getId()!=valor.getId()){
                            if(penultimoValor.getDataInicioVigencia().isBefore(v.getDataInicioVigencia())){
                                penultimoValor=v;
                            }

                        }
                    }


                    try{
                        contratoService.excluirValoresContrato(valor);
                        penultimoValor.setDataFimVigencia(null);
                        contratoService.atualizarValoresContrato(penultimoValor);
                    }catch(Exception ex){

                    }

                    //}
                    return "redirect:/admin/contrato/"+contratoId+"/editar";
                }
	}
        
        
        
        

}
