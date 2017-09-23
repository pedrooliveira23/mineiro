package br.jus.cjf.simus.service;

import br.jus.cjf.simus.model.Usuario;

public interface SimusService {
	
	Usuario buscaPorMatricula(String matricula);
        
        Boolean validaLogin(String matricula, String hash);
}
