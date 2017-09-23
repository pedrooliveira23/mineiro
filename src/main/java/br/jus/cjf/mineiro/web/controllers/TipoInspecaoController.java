package br.jus.cjf.mineiro.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.jus.cjf.mineiro.model.TipoInspecao;
import br.jus.cjf.mineiro.service.TipoInspecaoService;

@Controller
public class TipoInspecaoController {

	private final TipoInspecaoService tipoInspecaoService;

	@Autowired
	public TipoInspecaoController(TipoInspecaoService tipoInspecaoService) {
		super();
		this.tipoInspecaoService = tipoInspecaoService;
	}
	
	@RequestMapping(value = "/tipoInspecao/{tipoInspecaoId}", produces = "application/json",  method = RequestMethod.GET)
	public @ResponseBody
	TipoInspecao getTipoInspecao(@PathVariable int tipoInspecaoId) {

		return tipoInspecaoService.getTipoInspecaoById(tipoInspecaoId);

	}
	
}
