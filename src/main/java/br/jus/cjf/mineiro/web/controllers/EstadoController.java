package br.jus.cjf.mineiro.web.controllers;

import br.jus.cjf.mineiro.model.Roles;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.jus.cjf.mineiro.service.EstadoService;
import br.jus.cjf.mineiro.web.controllers.commands.EstadoCommand;
import br.jus.cjf.mineiro.web.controllers.validators.EstadoCommandValidator;
import java.util.List;

@Controller
@RequestMapping("/admin/estado")
public class EstadoController {



	private final EstadoService estadoService;
   
	
	@Autowired
	private EstadoCommandValidator estadoCommandValidator;

	@Autowired
	protected EstadoController(EstadoService estadoService) {
		super();
		this.estadoService = estadoService;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String mostrarEstados(Map<String, Object> model) {

		EstadoCommand estadoCommand = new EstadoCommand();

		estadoCommand.setEstados(estadoService

		.getEstadosOrderByStatusName());

		model.put("estadoCommand", estadoCommand);
                List<Roles> lista = estadoService.getRoles();
         
                model.put("roles", lista);

		return "listarEstado";

	}

	@RequestMapping(value = "/listar", method = RequestMethod.POST)
	public String salvarEstados(EstadoCommand estadoCommand,
			Map<String, Object> model, BindingResult result) {
		estadoCommandValidator.validate(estadoCommand, result);
		if (result.hasErrors()) {
			model.put("estadoCommand", estadoCommand);			
			return "redirect:listar";
		}
                
                
               // model.put("roles", estadoService.getRoles());
                
                
                
		estadoService.atualizarEstados(estadoCommand.getEstados());

		return "redirect:listar";

	}



}
