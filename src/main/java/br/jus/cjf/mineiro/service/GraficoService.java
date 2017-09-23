package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.model.Grafico;
import java.math.BigDecimal;

public interface GraficoService {

	Grafico getGraficoConformidadeOrdemServico();
	
	Grafico getGraficoAtrasoOrdemServico();
	
	Grafico getGraficoRecusaOrdemServico(); 
	
	Grafico getGraficoQuantitativoPontosFuncaoPorConsumo();
	
	Grafico getGraficoQuantitativoPontosFuncaoPorLinguagem();
	
	Grafico getGraficoQuantitativoPontosFuncaoPorMes();
	
        Grafico getGraficoTempoPorSituacao(int redmineIssueId);
        
        Grafico getGraficoTempoPorArea(int redmineIssueId) ;
        
        Grafico getGraficoPFPorEstado() ;
        
        void setTimeLastUpdate();
        
        Grafico getGraficoMediaTempoPorSituacao();
        
        BigDecimal getIndiceDiasPF();
        
}
