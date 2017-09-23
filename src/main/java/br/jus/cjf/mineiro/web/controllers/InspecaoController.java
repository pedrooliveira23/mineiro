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

import br.jus.cjf.mineiro.model.Inspecao;
import br.jus.cjf.mineiro.model.OrdemServico;
import br.jus.cjf.mineiro.service.InspecaoService;
import br.jus.cjf.mineiro.service.OrdemServicoService;
import br.jus.cjf.mineiro.service.TipoInspecaoService;

@Controller
@RequestMapping("/ordemServico/{redmineIssueId}/inspecao")
public class InspecaoController {

	private final InspecaoService inspecaoService;
	private final OrdemServicoService ordemServicoService;
	private final TipoInspecaoService tipoInspecaoService;

	@Autowired
	public InspecaoController(InspecaoService inspecaoService,
			OrdemServicoService ordemServicoNormalService,
			TipoInspecaoService tipoInspecaoService) {
		super();
		this.inspecaoService = inspecaoService;
		this.ordemServicoService = ordemServicoNormalService;
		this.tipoInspecaoService = tipoInspecaoService;
	}

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listarInspecao(@PathVariable int redmineIssueId,
			Map<String, Object> model) {

		model.put("ordemServico", ordemServicoService
				.getOrdemServicoByRedmineIssueId(redmineIssueId));
		model.put("inspecoes",
				inspecaoService.listarInspecoesByRedmineIssueId(redmineIssueId));

		return "listarInspecao";
	}

	@RequestMapping(value = "/criar", method = RequestMethod.GET)
	public String criarInspecao(@PathVariable int redmineIssueId,
			Map<String, Object> model, HttpServletRequest request) {
		OrdemServico ordemServicoNormal = ordemServicoService
				.getOrdemServicoByRedmineIssueId(redmineIssueId);
		Inspecao inspecao = new Inspecao();
		inspecao.setDemanda(ordemServicoNormal.getDemanda());
		model.put("tiposInspecao", tipoInspecaoService.listarTipoInspecao());
		model.put("ordemServico", ordemServicoNormal);
		model.put("inspecao", inspecao);

		return "criarInspecao";
	}

	@RequestMapping(value = "/criar", method = RequestMethod.POST)
	public String criarInspecao(@PathVariable int redmineIssueId,
			@Valid Inspecao inspecao, BindingResult result,
			Map<String, Object> model, HttpServletRequest request) {

		if (result.hasErrors()
				|| ordemServicoService.ordemServicoPrecificada(redmineIssueId)) {

			model.put("tiposInspecao", tipoInspecaoService.listarTipoInspecao());
			model.put("ordemServico", ordemServicoService
					.getOrdemServicoByRedmineIssueId(redmineIssueId));
			model.put("inspecao", inspecao);

			return "criarInspecao";
		}
		inspecaoService.criarInspecao(inspecao);

		return "redirect:/ordemServico/" + redmineIssueId + "/inspecao/listar";
	}

	@RequestMapping(value = "/{inspecaoId}/editar", method = RequestMethod.GET)
	public String editarInspecao(@PathVariable int redmineIssueId,
			@PathVariable int inspecaoId, Map<String, Object> model,
			HttpServletRequest request) {
	

		Inspecao inspecao = inspecaoService.getInspecaoById(inspecaoId);
		model.put("tiposInspecao", tipoInspecaoService.listarTipoInspecao());
		model.put("ordemServico", ordemServicoService
				.getOrdemServicoByRedmineIssueId(redmineIssueId));
		model.put("inspecao", inspecao);

		return "editarInspecao";
	}

	@RequestMapping(value = "/{inspecaoId}/editar", method = RequestMethod.POST)
	public String editarInspecao(@PathVariable int redmineIssueId,
			@PathVariable int inspecaoId, @Valid Inspecao inspecao,
			BindingResult result, Map<String, Object> model,
			HttpServletRequest request) {
		if (result.hasErrors()
				|| ordemServicoService.ordemServicoPrecificada(redmineIssueId)) {
			model.put("tiposInspecao", tipoInspecaoService.listarTipoInspecao());
			model.put("ordemServico", ordemServicoService
					.getOrdemServicoByRedmineIssueId(redmineIssueId));
			model.put("inspecao", inspecao);

			return "editarInspecao";
		}
		inspecaoService.atualizarInspecao(inspecao);
		return "redirect:/ordemServico/" + redmineIssueId + "/inspecao/listar";
	}

	@RequestMapping(value = "/{inspecaoId}/excluir", method = RequestMethod.GET)
	public String excluirInspecao(@PathVariable int redmineIssueId,
			@PathVariable int inspecaoId, Map<String, Object> model,
			HttpServletRequest request) {
		if (!ordemServicoService.ordemServicoPrecificada(redmineIssueId)) {

			Inspecao inspecao = inspecaoService.getInspecaoById(inspecaoId);
			inspecaoService.excluirInspecao(inspecao);
		}
		return "redirect:/ordemServico/" + redmineIssueId + "/inspecao/listar";
	}

}
