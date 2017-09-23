package br.jus.cjf.mineiro.service;

import br.jus.cjf.mineiro.model.OrdemServico;
import br.jus.cjf.mineiro.model.Resposta;
import br.jus.cjf.mineiro.model.Transicao;

public interface EmailService {


	   void enviarEmailSimples(String destinatario, String titulo, String texto);
	    
	   void enviarEmailOrdemServicoParada(OrdemServico ordemServico, Transicao ultimaTransicao);
	   
	   void gerarAlertas();
	   
	   Resposta gerarAlertaTeste();
	
	
}
