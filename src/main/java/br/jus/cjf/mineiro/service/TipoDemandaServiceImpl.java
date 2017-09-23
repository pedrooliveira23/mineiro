package br.jus.cjf.mineiro.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.dao.TipoDemandaDao;
import br.jus.cjf.mineiro.model.Demanda;
import br.jus.cjf.mineiro.model.TipoDemanda;
import br.jus.cjf.redmine.model.Tracker;
import java.math.BigDecimal;

@Service
public class TipoDemandaServiceImpl implements TipoDemandaService{

	private final TipoDemandaDao tipoDemandaDao;

	private static Logger logger = LoggerFactory
	.getLogger(TipoDemandaServiceImpl.class);
	
	@Autowired
	private TipoDemandaServiceImpl(TipoDemandaDao tipoDemandaDao) {
		super();
		this.tipoDemandaDao = tipoDemandaDao;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<TipoDemanda> listarTiposDemanda() {

		return tipoDemandaDao.listarTiposDemanda();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public boolean existeTipoDemanda(Integer redmineTrackerId) {
	
		return tipoDemandaDao.existeTipoDemanda(redmineTrackerId);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void criarTipoDemanda(TipoDemanda tipoDemadna) {
		 
		tipoDemandaDao.criarTipoDemanda(tipoDemadna);
		
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarTipoDemanda(TipoDemanda tipoDemanda) {
		
		tipoDemandaDao.atualizarTipoDemanda(tipoDemanda);
		
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public TipoDemanda getTipoDemanda(Integer redmineTrackerId) {
		
		return tipoDemandaDao.getTipoDemanda(redmineTrackerId);
	}

	@Override
	public Boolean ehTipoDemandaManutecaoCorretiva(Demanda demanda) {
		
		if(demanda.getTipoDemanda().getNome().contains("Manutenção Corretiva")||
                   demanda.getTipoDemanda().getNome().contains("Manutenção - Corretiva.")){
			return true;
		}
		return false;
	}
        
        

	@Override
	@Transactional("mineiroTransactionManager")
	public void extrairTipoDemanda(Tracker tracker) {
		
		if (existeTipoDemanda(tracker.getId())) {
			TipoDemanda tipoDemanda = getTipoDemanda(tracker.getId());
			tipoDemanda.setNome(tracker.getName());
			tipoDemanda.setRedmineTrackerId(tracker.getId());
			atualizarTipoDemanda(tipoDemanda);
			logger.debug("Tipo de Demanda:" + tipoDemanda.getNome()
					+ " - Atualizada");
		} else {
			TipoDemanda tipoDemanda = new TipoDemanda();
			tipoDemanda.setNome(tracker.getName());
			tipoDemanda.setRedmineTrackerId(tracker.getId());
                        tipoDemanda.setDeflator(new BigDecimal(1.0));
			criarTipoDemanda(tipoDemanda);

			logger.debug("Estado:" + tipoDemanda.getNome() + " - Criada");
		}
		
	}
	
	
	
	
	
}
