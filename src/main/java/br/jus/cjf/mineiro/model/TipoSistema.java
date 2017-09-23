package br.jus.cjf.mineiro.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public enum TipoSistema {

	NAO_SE_APLICA("Não se aplica",new BigDecimal(0.32)),
	MUMPS("Mumps",new BigDecimal(0.32)),
	WEB_OU_CLIENTE_SERVIDOR("WEB ou Cliente Servidor",new BigDecimal(0.32)),
	BIBLIOTECA_COMPONENTE_OU_FRAMEWORKS("Biblioteca, Componente ou Frameworks",new BigDecimal(0.32));
	
	private String nome;
	
	private BigDecimal expoente;

    private static final Map<String, TipoSistema> lookup = new HashMap<String, TipoSistema>();

	private TipoSistema(String nome, BigDecimal expoente) {
		this.nome = nome;
		this.expoente =  expoente;
	}
	
    static {
        for (TipoSistema t : TipoSistema.values())
            lookup.put(t.getNome(), t);
    }
    
    public static TipoSistema get(String nome) {
    	TipoSistema tipoSistema = lookup.get(nome);
    	if(tipoSistema==null){
    		return TipoSistema.NAO_SE_APLICA;
    	}
        return lookup.get(nome);
    }

	public String getNome() {
		return nome;
	}

	public BigDecimal getExpoente() {
		return expoente;
	}
	
}
