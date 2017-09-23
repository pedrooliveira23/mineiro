package br.jus.cjf.mineiro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.dao.TipoInspecaoDao;
import br.jus.cjf.mineiro.model.TipoInspecao;

@Service
public class TipoInspecaoServiceImpl implements TipoInspecaoService {

	private final TipoInspecaoDao tipoInspecaoDao;

	@Autowired
	public TipoInspecaoServiceImpl(TipoInspecaoDao tipoInspecaoDao) {
		super();
		this.tipoInspecaoDao = tipoInspecaoDao;
	}

	@Transactional("mineiroTransactionManager")
	@Override
	public List<TipoInspecao> listarTipoInspecao() {

		return tipoInspecaoDao.listarTipoInspecao();
	}
	
	@Transactional("mineiroTransactionManager")
	@Override
	public List<TipoInspecao> listarTipoInspecaoByTipoGrupoInspecaoId(Integer tipoGrupoInspecaoId) {

		return tipoInspecaoDao.listarTipoInspecaoByTipoGrupoInspecaoId(tipoGrupoInspecaoId);
	}


	@Transactional("mineiroTransactionManager")
	@Override
	public TipoInspecao getTipoInspecaoById(Integer tipoInspecaoId) {
	
		return tipoInspecaoDao.getTipoInspecaoById(tipoInspecaoId);
	}

	@Transactional("mineiroTransactionManager")
	@Override
	public void criar(TipoInspecao tipoInspecao) {
		
		tipoInspecaoDao.criarTipoInspecao(tipoInspecao);
		
	}
	
	@Override
	@Transactional("mineiroTransactionManager")
	public void realizarCargaTipoInspecao(){
		
		if(listarTipoInspecao().isEmpty()){
		
			TipoInspecao tipoInspecao = null;
	
			/************ INICIO - Carga de Gerência de Projetos ************/
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Proposta de Execução");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Ata de Reunião");
			criar(tipoInspecao);
	
			/************ INICIO - Carga de Requisitos ************/
	
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Documentos de Mensagem");
	
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Documento de Visão");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Especificacão de Regras de Negócio Gerais");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Especificação Suplementar");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao
					.setNome("Especificacões de Caso de Uso e Regras de Negócio");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Glossário");
			criar(tipoInspecao);
			
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Matriz de Acesso");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Matriz de Rastreabilidade");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Modelos de casos de uso");
			criar(tipoInspecao);
	
			/************ INICIO - Análise e Projeto ************/
	
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Arquitetura");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao
					.setNome("Modelo Entidade e Relacionamento e Dicionário de Dados");
			criar(tipoInspecao);
	
			/************ INICIO - Desenvolvimento ************/
	
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Build");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Fontes em Java");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Fontes em PHP");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Fontes em Delphi");
			criar(tipoInspecao);
	
			/************ INICIO - Teste ************/
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Evidência de Sucesso");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Plano de Teste");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Roteiro de Teste");
			criar(tipoInspecao);
	
			/************ INICIO - Release ************/
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Manual de Implantacao");
			criar(tipoInspecao);
	
			tipoInspecao = new TipoInspecao();
			tipoInspecao.setNome("Notas de Release");
			criar(tipoInspecao);
	
		}
	}


}
