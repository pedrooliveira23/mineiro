package br.jus.cjf.mineiro.model;

public enum CategoriaNivelServico {

	DESEJAVEL("Desejável"), ACEITAVEL("Aceitável"), INACEITAVEL("Inaceitável");

	private String nome;


	private CategoriaNivelServico(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	

}
