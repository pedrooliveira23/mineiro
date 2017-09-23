package br.jus.cjf.mineiro.web.controllers.commands;

import java.util.LinkedHashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class FiltroCommand {


	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private DateTime fim = new DateTime();
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private DateTime inicio =  fim.minusDays(60);
	
	private String referenciaPesquisa = "ABERTURA";
	
	private String classificacao = "ABERTA";

	public DateTime getInicio() {
		return inicio;
	}

	public void setInicio(DateTime inicio) {
		this.inicio = inicio;
	}

	public DateTime getFim() {
		return fim;
	}

	public void setFim(DateTime fim) {
		this.fim = fim;
	}

	public String getReferenciaPesquisa() {
		return referenciaPesquisa;
	}

	public void setReferenciaPesquisa(String referenciaPesquisa) {
		this.referenciaPesquisa = referenciaPesquisa;
	}
	
	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public Map<String,String> getReferenciasPesquisa() {
	
		Map<String,String> referenciasPesquisa = new LinkedHashMap<String,String>();
		referenciasPesquisa.put("ABERTURA", "Abertura");
		referenciasPesquisa.put("CONCLUSAO", "Conclusão");
		return referenciasPesquisa;

	}
	
	public Map<String,String> getClassificacoes() {
		
		Map<String,String> classificacoes = new LinkedHashMap<String,String>();
		classificacoes.put("TODAS", "Todas");
		classificacoes.put("ABERTA", "Aberta");
		classificacoes.put("CONCLUIDA", "Concluída");
		classificacoes.put("CANCELADA", "Cancelada");
		classificacoes.put("INCOMPLETA", "Incompletas");
		classificacoes.put("PRECIFICADA", "Precificadas");
		return classificacoes;

	}



	
	
	
}
