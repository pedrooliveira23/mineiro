package br.jus.cjf.mineiro.service;

import java.util.List;

import br.jus.cjf.mineiro.model.Estado;
import br.jus.cjf.mineiro.model.Roles;
import br.jus.cjf.redmine.model.Status;

public interface EstadoService {

	void atualizarEstados(List<Estado> estados);

	List<Estado> getEstadosOrderByStatusName();
        
        List<Roles> getRoles();
	
	List<Estado> listarEstados();

	Estado getEstadoByRedmineStatusId(Integer redmineStatusId);

	List<Estado> listarEstadosQueSensibilizamPrazo();
        
        public Estado getEstadoPorNome(String nome);

	Boolean estadoExiste(Integer redmineStatusId);

	void atualizarEstado(Estado estado);

	void criarEstado(Estado estado);
	
	void extrairEstado(Status status);
        
}
