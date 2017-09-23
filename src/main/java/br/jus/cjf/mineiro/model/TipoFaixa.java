package br.jus.cjf.mineiro.model;

public enum TipoFaixa {

	INTERVALO("Intervalo"), UNICO("Único"), MENOR("Menor"), MAIOR("Maior");
	
	
	private TipoFaixa(String nome) {
		this.nome = nome;
	}

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
