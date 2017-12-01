package br.jus.cjf.mineiro.web.controllers;

import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.ValoresContrato;
import br.jus.cjf.mineiro.service.ContratoService;
import br.jus.cjf.mineiro.service.GrupoService;
import br.jus.cjf.mineiro.service.OrdemServicoService;
import br.jus.cjf.mineiro.service.UsuarioService;
import br.jus.cjf.mineiro.web.controllers.validators.ContratoValidator;
import br.jus.cjf.mineiro.web.controllers.validators.ValoresContratoValidator;
import br.jus.cjf.simus.model.Grupo;
import br.jus.cjf.simus.model.Usuario;
import org.joda.time.DateTime;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//import br.jus.cjf.mineiro.web.controllers.validators.ValoresContratoValidator;

@Controller
@RequestMapping("/admin/grupo")
public class GrupoController {

	private final UsuarioService usuarioService;
	private final GrupoService grupoService;
	private boolean erroExcluir = false;


	@Autowired
	public GrupoController(UsuarioService usuarioService,
						   GrupoService grupoService) {
		super();

		this.grupoService = grupoService;
		this.usuarioService = usuarioService;
	}
/*
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listarContrato(Map<String, Object> model) {

		if (erroExcluir) {
			erroExcluir = false;
			model.put("erro", "Existem ordens de serviço vinculadas a este contrato, não é possível excluir.");
		}
		model.put("contratos", contratoService.listarContratos());

		return "listarContrato";
	}
*/

	@RequestMapping(value = "/{usuarioId}/editar", method = RequestMethod.GET)
	public String editarContrato(@PathVariable int usuarioId, Map<String, Object> model, HttpServletRequest request) {
		Usuario usuario = usuarioService.getUsuarioPorId(usuarioId);
		List<Grupo> gruposParaAdicionar = grupoService.listarGrupos();
		gruposParaAdicionar.removeAll(usuario.getGrupos());

		model.put("usuario", usuario);
		model.put("grupos", gruposParaAdicionar);

		return "editarGrupo";
	}

	@RequestMapping(value = "/usuario/{usuarioId}/adicionarGrupo/{grupoId}", method = RequestMethod.GET)
	public String adicionarGrupos(@PathVariable int usuarioId, @PathVariable int grupoId, Map<String, Object> model, HttpServletRequest request) {
		Usuario usuario = usuarioService.alterarGrupo(usuarioId,grupoId,false);
		List<Grupo> gruposParaAdicionar = grupoService.listarGrupos();
		gruposParaAdicionar.removeAll(usuario.getGrupos());

		model.put("usuario", usuario);
		model.put("grupos", gruposParaAdicionar);

		return "editarGrupo";
	}

	@RequestMapping(value = "/usuario/{usuarioId}/removerGrupo/{grupoId}", method = RequestMethod.GET)
	public String removerGrupos(@PathVariable int usuarioId, @PathVariable int grupoId, Map<String, Object> model, HttpServletRequest request) {
		Usuario usuario = usuarioService.alterarGrupo(usuarioId,grupoId,true);
		List<Grupo> gruposParaAdicionar = grupoService.listarGrupos();
		gruposParaAdicionar.removeAll(usuario.getGrupos());

		model.put("usuario", usuario);
		model.put("grupos", gruposParaAdicionar);

		return "editarGrupo";
	}

}
