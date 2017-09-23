package br.jus.cjf.mineiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.dao.InspecaoDao;
import br.jus.cjf.mineiro.model.Inspecao;


@Service
public class InspecaoServiceImpl implements InspecaoService {

	
	private final InspecaoDao inspecaoDao;


	@Autowired
	public InspecaoServiceImpl(InspecaoDao inspecaoDao) {
		super();
		this.inspecaoDao = inspecaoDao;
	}
	
	@Transactional("mineiroTransactionManager")
	@Override
	public Inspecao getInspecaoById(Integer id) {

		return inspecaoDao.getInspecaoById(id);
	}



	@Transactional("mineiroTransactionManager")
	@Override
	public void atualizarInspecao(Inspecao inspecao) {
		inspecaoDao.atualizarInspecao(inspecao);

	}

	@Transactional("mineiroTransactionManager")
	@Override
	public void criarInspecao(Inspecao inspecao) {
		inspecaoDao.criarInspecao(inspecao);

	}

	@Transactional("mineiroTransactionManager")
	@Override
	public void excluirInspecao(Inspecao inspecao) {
		inspecaoDao.excluirInspecao(inspecao);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<Inspecao> listarInspecoesByRedmineIssueId(Integer redmineIssueId) {

		return inspecaoDao.listarInspecoesByRedmineIssueId(redmineIssueId);
	}

}
