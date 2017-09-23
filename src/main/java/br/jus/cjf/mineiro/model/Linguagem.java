package br.jus.cjf.mineiro.model;

import java.util.HashMap;
import java.util.Map;

public enum Linguagem {

	JAVA("Java", new Float(1)),
	PHP("PHP", new Float(0.6)),
	DELPHI("Delphi", new Float(0.8)),
	MUMPS("Mumps", new Float(1));
	
	private String nome;
	
	private Float fatorPonderacao;
     

	private Linguagem(String nome, Float fatorPonderacao) {
		this.nome = nome;
		this.fatorPonderacao =  fatorPonderacao;
               
	}
	
    private static final Map<String, Linguagem> lookup = new HashMap<String, Linguagem>();
    static {
        for (Linguagem l : Linguagem.values())
            lookup.put(l.getNome(), l);
    }
    
    public static Linguagem get(String nome) {
        return lookup.get(nome);
    }

	public String getNome() {
		return nome;
	}

	public Float getFatorPonderacao() {
               
		return fatorPonderacao;
	}

     

	
}
