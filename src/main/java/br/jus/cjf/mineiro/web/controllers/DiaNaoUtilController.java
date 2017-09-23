package br.jus.cjf.mineiro.web.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jus.cjf.mineiro.model.DiaNaoUtil;
import br.jus.cjf.mineiro.service.DiaNaoUtilService;
import br.jus.cjf.mineiro.web.controllers.commands.DiaNaoUtilCommand;


@Controller
@RequestMapping("/admin/diaNaoUtil")
public class DiaNaoUtilController {

	private final DiaNaoUtilService diaNaoUtilService;

	@Autowired
	protected DiaNaoUtilController(DiaNaoUtilService diaNaoUtilService) {
		super();
		this.diaNaoUtilService = diaNaoUtilService;
	}
	
	
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String mostrarDiaNaoUtil(Map<String, Object> model) {
		DiaNaoUtilCommand diaNaoUtilCommand = new DiaNaoUtilCommand();
		diaNaoUtilCommand.setDiasNaoUteis(diaNaoUtilService
				.listarDiaNaoUtilOrderByDia());
		model.put("diaNaoUtilCommand", diaNaoUtilCommand);
		
		return "listarDiaNaoUtil";
	}

	@RequestMapping(value = "/criar",  method = RequestMethod.GET)
	public String salvarDiaNaoUtil(Map<String, Object> model,  HttpServletRequest request) {
		model.put("diaNaoUtil", new DiaNaoUtil());
		return "criarDiaNaoUtil";
	}
	
	@RequestMapping(value = "/criar",  method = RequestMethod.POST)
	public String salvarDiaNaoUtil(@Valid DiaNaoUtil diaNaoUtil, 
			BindingResult result, Map<String, Object> model,
			HttpServletRequest request) {

		if (result.hasErrors()){
			model.put("diaNaoUtil", diaNaoUtil);
			return "criarDiaNaoUtil";
			
		}
		diaNaoUtilService.criar(diaNaoUtil);
		return "redirect:/admin/diaNaoUtil/listar";
	}

	
	
	@RequestMapping(value = "/diaNaoUtilId}/editar/bloquear", method = RequestMethod.GET)
	public String bloquearDiaNaoUtil(@PathVariable int diaNaoUtilId,
			Map<String, Object> model) {
		DiaNaoUtil diaNaoUtil =  new DiaNaoUtil();
		diaNaoUtil.setId(diaNaoUtilId);
		diaNaoUtil.setAtivo(false);
		diaNaoUtilService.alternarAtivo(diaNaoUtil);
		return "redirect:/admin/diaNaoUtil/listar";
	}

	@RequestMapping(value = "/{diaNaoUtilId}/editar/desbloquear", method = RequestMethod.GET)
	public String desbloquearDiaNaoUtil(@PathVariable int diaNaoUtilId,
			Map<String, Object> model) {
		DiaNaoUtil diaNaoUtil =  new DiaNaoUtil();
		diaNaoUtil.setId(diaNaoUtilId);
		diaNaoUtil.setAtivo(true);
		diaNaoUtilService.alternarAtivo(diaNaoUtil);
		return "redirect:/admin/diaNaoUtil/listar";
	}

	@RequestMapping(value = "/{diaNaoUtilId}/excluir", method = RequestMethod.GET)
	public String excluirDiaNaoUtil(@PathVariable int diaNaoUtilId,
			Map<String, Object> model) {
		DiaNaoUtil diaNaoUtil =  new DiaNaoUtil();
		diaNaoUtil.setId(diaNaoUtilId);
		diaNaoUtilService.excluir(diaNaoUtil);

		return "redirect:/admin/diaNaoUtil/listar";
	}
	
	
	
}
