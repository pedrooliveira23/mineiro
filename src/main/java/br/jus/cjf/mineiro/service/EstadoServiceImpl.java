package br.jus.cjf.mineiro.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.dao.EstadoDao;
import br.jus.cjf.mineiro.dao.RolesDao;
import br.jus.cjf.mineiro.model.Estado;
import br.jus.cjf.mineiro.model.Roles;
import br.jus.cjf.redmine.model.Status;

@Service
public class EstadoServiceImpl implements EstadoService {

	private final EstadoDao estadoDao;
        private final RolesDao rolesDao;
	
	private static Logger logger = LoggerFactory
	.getLogger(EstadoServiceImpl.class);

	@Autowired
	public EstadoServiceImpl(EstadoDao estadoDao,RolesDao rolesDao) {
		super();
		this.estadoDao = estadoDao;
                this.rolesDao = rolesDao;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarEstados(List<Estado> estados) {
		for (Estado estadoTemp : estados) {
			Estado estado = estadoDao.getEstadoByRedmineStatusId(estadoTemp.getRedmineStatusId());
			estado.setContaTempoOS(estadoTemp.getContaTempoOS());
			estado.setContaRecusaOS(estadoTemp.getContaRecusaOS());
                        estado.setContaIndicador(estadoTemp.getContaIndicador());
                        if(estadoTemp.getRole().getId()==null){
                            estado.setRole(null);
                        }else
                        estado.setRole(rolesDao.getRolesById(estadoTemp.getRole().getId()));
			estadoDao.atualizarEstado(estado);
		}
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<Estado> getEstadosOrderByStatusName() {
		return estadoDao.listarEstados();
	}
        
        @Override
	@Transactional("mineiroTransactionManager")
	public List<Roles> getRoles() {
		return rolesDao.getRoles();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<Estado> listarEstados() {
		
		return estadoDao.listarEstados();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Estado getEstadoByRedmineStatusId(Integer redmineStatusId) {
		
		return estadoDao.getEstadoByRedmineStatusId(redmineStatusId);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<Estado> listarEstadosQueSensibilizamPrazo() {
		
		return estadoDao.listarEstadosQueSensibilizamPrazo();
	}
        
        @Override
	@Transactional("mineiroTransactionManager")
	public Estado getEstadoPorNome(String nome) {
		
		return estadoDao.getEstadosPorNome(nome);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Boolean estadoExiste(Integer redmineStatusId) {

		return estadoDao.estadoExiste(redmineStatusId);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarEstado(Estado estado) {
		
		estadoDao.atualizarEstado(estado);
		
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void criarEstado(Estado estado) {
		
		estadoDao.criarEstado(estado);
		
	}

	@Override
	public void extrairEstado(Status status) {
		

		if (estadoExiste(status.getId())) {
			Estado estado = getEstadoByRedmineStatusId(status
					.getId());
			estado.setConcluido(status.getClosed());
			estado.setNome(status.getName());
			atualizarEstado(estado);
			logger.debug("Estado:" + status.getName() + " - Atualizada");
		} else {
			Boolean contaTempoOS = estadoContaTempoOS(status);
			Boolean contaRecusaOS = false;
			if (contaTempoOS) {
				contaRecusaOS = estadoContaRecusaOS(status);
			}

			Estado estado = new Estado();
			estado.setRedmineStatusId(status.getId());
			estado.setNome(status.getName());
			estado.setConcluido(status.getClosed());
			estado.setContaTempoOS(contaTempoOS);
			estado.setContaRecusaOS(contaRecusaOS);
                        estado.setContaIndicador(true);
			criarEstado(estado);
			logger.debug("Estado:" + status.getName() + " - Criada");
		}
		
	}
	
	private Boolean estadoContaTempoOS(Status status) {

		String[] palavras = { "Solicitada", "Em Espera",
				"Produção - Disponibilizada",
				"Requisitos - Qualidade Aprovada",
				"Release - Qualidade Aprovada", 
				"Homologação - Aprovada", "Em Validação",
				"Contagem Detalhada", "Qualidade - Em Inspeção", 
				"Qualidade - Aprovada"};

		for (String palavra : palavras) {
			if (status.getName().contains(palavra)) {

				logger.debug("Estado:" + status.getName()
						+ " - Não Sensibiliza Prazo");
				return false;

			}

		}

		logger.debug("Estado:" + status.getName() + " - Sensibiliza Prazo");
		return true;

	}
	




	private Boolean estadoContaRecusaOS(Status status) {

		String[] palavras = { "Rejeitada", "Rejeitado" };

		for (String palavra : palavras) {
			if (status.getName().contains(palavra)) {

				logger.debug("Estado:" + status.getName()
						+ " - Sensibiliza Recusa");
				return true;

			}

		}

		logger.debug("Estado:" + status.getName() + " - Não Sensibiliza Recusa");
		return false;

	}



}
