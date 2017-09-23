package br.jus.cjf.mineiro.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.dao.ProjetoDao;
import br.jus.cjf.mineiro.model.Linguagem;
import br.jus.cjf.mineiro.model.Projeto;
import br.jus.cjf.redmine.model.Project;
import br.jus.cjf.redmine.service.RedmineService;

@Service
public class ProjetoServiceImpl implements ProjetoService{

	private final ProjetoDao projetoDao;
	
	private final RedmineService redmineService;
	
	private static Logger logger = LoggerFactory
	.getLogger(ProjetoServiceImpl.class);

	@Autowired
	public ProjetoServiceImpl(ProjetoDao projetoDao,
			RedmineService redmineService) {
		super();
		this.projetoDao = projetoDao;
		this.redmineService = redmineService;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Projeto getProjetoByRedmineProjectId(Integer redmineProjectId) {
		
		return projetoDao.getProjetoByRedmineProjectId(redmineProjectId);
	}



	@Override
	@Transactional("mineiroTransactionManager")
	public Boolean projetoExiste(Integer redmineProjectId) {
		
		return projetoDao.projetoExiste(redmineProjectId);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarProjeto(Projeto projeto) {
		
		projetoDao.atualizarProjeto(projeto);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void criarProjeto(Projeto projeto) {
		
		projetoDao.criarProjeto(projeto);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void extrairProjeto(Project project) {

				/*
				 * Black cat - Essa condição foi inserida por existir uma
				 * despadronização no redmine, pois existem projetos que o projeto
				 * pai possui as informações do sistema e outros as informações
				 * estão no próprio projeto.
				 */
				Integer idProjetoCamposPersonalizados = project.getParent_id();
				if (idProjetoCamposPersonalizados == null) {
					idProjetoCamposPersonalizados = project.getId();
				}
				if (projetoExiste(project.getId())) {
					Projeto projeto = getProjetoByRedmineProjectId(project.getId());
					Projeto projetoCamposPersonalizados = getProjetoByRedmineProjectId(idProjetoCamposPersonalizados);
					projeto.setNome(projetoCamposPersonalizados.getNome());
					Map<String, String> camposPersonalizadosProjeto = redmineService
							.listarCamposPersonalizadosProjeto(idProjetoCamposPersonalizados);
					projeto.setLinguagem(Linguagem.get(camposPersonalizadosProjeto
							.get("Linguagem")));
					atualizarProjeto(projeto);
					logger.debug("Projeto:" + projeto.getNome() + " - Atualizado");
				} else {

					Projeto projeto = new Projeto();
					projeto.setRedmineProjectId(project.getId());
					projeto.setNome(project.getName());
					projeto.setRedmineProjectParentId(project.getParent_id());
					Map<String, String> camposPersonalizadosProjeto = redmineService
							.listarCamposPersonalizadosProjeto(idProjetoCamposPersonalizados);
					logger.debug("Linguagem:"
							+ camposPersonalizadosProjeto.get("Linguagem"));
					projeto.setLinguagem(Linguagem.get(camposPersonalizadosProjeto
							.get("Linguagem")));
					criarProjeto(projeto);
					logger.debug("Projeto:" + projeto.getNome() + " - Criado");
				}

			

		
		
	}
	
	

	
	
	
	
}
