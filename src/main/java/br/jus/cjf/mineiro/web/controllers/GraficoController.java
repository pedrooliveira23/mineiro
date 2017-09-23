package br.jus.cjf.mineiro.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.jus.cjf.mineiro.model.Grafico;
import br.jus.cjf.mineiro.service.GraficoService;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping(value = "/grafico/ordemServico")
public class GraficoController {

	private final GraficoService graficoService;

	@Autowired
	public GraficoController(GraficoService graficoService) {
		super();
		this.graficoService = graficoService;
                
	}
	
	@RequestMapping(value = "/nivelServico/conformidade")
	public @ResponseBody Grafico mostrarGraficoNivelServicoConformidade(){
		
		return graficoService.getGraficoConformidadeOrdemServico();
	}
	@RequestMapping(value = "/nivelServico/atraso")
	public @ResponseBody Grafico mostrarGraficoNivelServicoAtraso(){
		
		return graficoService.getGraficoAtrasoOrdemServico();
	}
	@RequestMapping(value = "/nivelServico/recusa")
	public @ResponseBody Grafico mostrarGraficoNivelServicoRecusa(){
		
		return graficoService.getGraficoRecusaOrdemServico();
	}
	@RequestMapping(value = "/pontosFuncao/consumo")
	public @ResponseBody Grafico mostrarGraficoQuantitativoPontosFuncaoPorConsumo(){
		
		return graficoService.getGraficoQuantitativoPontosFuncaoPorConsumo();
	}
	
	@RequestMapping(value = "/pontosFuncao/linguagem")
	public @ResponseBody Grafico mostrarGraficoQuantitativoPontosFuncaoPorLinguagem(){
		
		return graficoService.getGraficoQuantitativoPontosFuncaoPorLinguagem();
	}
	
	@RequestMapping(value = "/pontosFuncao/tempo")
	public @ResponseBody Grafico mostrarGraficoQuantitativoPontosFuncaoPorTempo(){
		//graficoService.getGraficoTempoPorSituacao(2449);
		return graficoService.getGraficoQuantitativoPontosFuncaoPorMes();
	}
	
	@RequestMapping(value = "{redmineIssueId}/situacao/tempo")
	public @ResponseBody Grafico mostrarGraficoTempoPorSituacao(@PathVariable int redmineIssueId){
		
		return graficoService.getGraficoTempoPorSituacao(redmineIssueId);
	}
        
        @RequestMapping(value = "{redmineIssueId}/area/tempo")
	public @ResponseBody Grafico mostrarGraficoTempoPorArea(@PathVariable int redmineIssueId){
		
		return graficoService.getGraficoTempoPorArea(redmineIssueId);
	}
	
        @RequestMapping(value = "/pontosFuncao/estado")
	public @ResponseBody Grafico mostrarGraficoQuantitativoPontosFuncaoPorEstado(){
		//graficoService.getGraficoTempoPorSituacao(2449);
		return graficoService.getGraficoPFPorEstado();
	}
        
        @RequestMapping(value = "/situacao/tempoMedio")
	public @ResponseBody Grafico mostrarGraficoMediaTempoPorSituacao(){
		
		return graficoService.getGraficoMediaTempoPorSituacao();
	}
	
}
