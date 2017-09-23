package br.jus.cjf.mineiro.model;

import java.math.BigDecimal;
import java.util.Map;

public class Grafico {

	private String nome;
	
	private Map<String, BigDecimal> dados;

        private Map<String, BigDecimal> dados2;
        
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Map<String, BigDecimal> getDados() {
		return dados;
	}

	public void setDados(Map<String, BigDecimal> dados) {
		this.dados = dados;
	}

        public Map<String, BigDecimal> getDados2() {
            return dados2;
        }

        public void setDados2(Map<String, BigDecimal> dados2) {
            this.dados2 = dados2;
        }


	
	
	
}
