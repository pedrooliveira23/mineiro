package br.jus.cjf.mineiro.model;

import java.util.HashMap;
import java.util.Map;

public enum TipoPenalidade {

	MULTA("Multa"), GLOSA("Glosa");
	
	private String nome;

	private static final Map<String, TipoPenalidade> lookup = new HashMap<String, TipoPenalidade>();
	
	private TipoPenalidade(String nome) {
		this.nome = nome;
	}
	
	 static {
	        for (TipoPenalidade t : TipoPenalidade.values())
	            lookup.put(t.getNome(), t);
	    }
	    
    public static TipoPenalidade get(String nome) {
    	TipoPenalidade tipoPenalidade = lookup.get(nome);
    	if(tipoPenalidade==null){
    		return null;
    	}
        return lookup.get(nome);
    }

	public String getNome() {
		return nome;
	}
	

	
	
	
	
	
	
	
}
