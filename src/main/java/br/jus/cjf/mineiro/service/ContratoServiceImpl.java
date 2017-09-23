package br.jus.cjf.mineiro.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.LocalTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.dao.ContratoDao;
import br.jus.cjf.mineiro.dao.IndicadorDao;
import br.jus.cjf.mineiro.dao.NivelServicoDao;
import br.jus.cjf.mineiro.dao.PenalidadeDao;
import br.jus.cjf.mineiro.dao.TipoIndicadorDao;
import br.jus.cjf.mineiro.dao.ValoresContratoDao;
import br.jus.cjf.mineiro.model.CategoriaNivelServico;
import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.Faixa;
import br.jus.cjf.mineiro.model.Indicador;
import br.jus.cjf.mineiro.model.NivelServico;
import br.jus.cjf.mineiro.model.Penalidade;
import br.jus.cjf.mineiro.model.TipoFaixa;
import br.jus.cjf.mineiro.model.TipoIndicador;
import br.jus.cjf.mineiro.model.TipoPenalidade;
import br.jus.cjf.mineiro.model.ValoresContrato;
import br.jus.cjf.simus.model.Usuario;
import br.jus.cjf.spring.util.CustomWebAuthenticationDetails;
import org.joda.time.DateTime;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class ContratoServiceImpl implements ContratoService {

	private final ContratoDao contratoDao;
	
	private final PenalidadeDao penalidadeDao;
	
	private final IndicadorDao indicadorNivelServicoDao;
	
	private final NivelServicoDao nivelServicoDao;
	
	private final TipoIndicadorDao tipoIndicadorDao;
        
        private final ValoresContratoDao valoresContratoDao;
        
      
	
	@Autowired
	private ContratoServiceImpl(ContratoDao contratoDao,
			PenalidadeDao penalidadeDao, IndicadorDao indicadorNivelServicoDao,
			NivelServicoDao nivelServicoDao, TipoIndicadorDao tipoIndicadorDao,
                        ValoresContratoDao valoresContratoDao) {
		super();
		this.contratoDao = contratoDao;
		this.penalidadeDao = penalidadeDao;
		this.indicadorNivelServicoDao = indicadorNivelServicoDao;
		this.nivelServicoDao = nivelServicoDao;
		this.tipoIndicadorDao = tipoIndicadorDao;
                this.valoresContratoDao = valoresContratoDao;
               
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Contrato getContratoPorNumero(String numero) {
            
            
		return contratoDao.getContratoPorNumero(numero);
	
	}

        @Override
	@Transactional("mineiroTransactionManager")
	public Contrato getContratoPorContratada(String contratada) {
		return contratoDao.getContratoPorContratada(contratada);
	
	}
        
          @Override
	@Transactional("mineiroTransactionManager")
	public Contrato getContratoPorId(int id) {
		return contratoDao.getContratoPorId(id);
	
	}

        @Transactional("mineiroTransactionManager")
	@Override
	public void excluirContrato(Contrato contrato) {
		contratoDao.excluirContrato(contrato);

	}
        
        
        @Transactional("mineiroTransactionManager")
	@Override
	public void excluirValoresContrato(ValoresContrato valor) {
		valoresContratoDao.excluirValoresContrato(valor);

	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<Contrato> listarContratoPorEmpresa(String empresa) {
		
		return contratoDao.listarContratoPorEmpresa(empresa);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarContrato(Contrato contrato) {
		
		contratoDao.atualizarContrato(contrato);
		
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void criarContrato(Contrato contrato) {
		
		contratoDao.criarContrato(contrato);
		
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Indicador getIndicador(Integer id) {
		
		return indicadorNivelServicoDao.getIndicador(id);
	}
	
	@Override
	@Transactional("mineiroTransactionManager")
	public Indicador getIndicador(String nomeTipoIndicador, Contrato contrato){
		
		return indicadorNivelServicoDao.getIndicador(nomeTipoIndicador,contrato);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<Indicador> listarIndicadorPorContrato(
			String numero) {
		
		return indicadorNivelServicoDao.listarIndicadorPorContrato(numero);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarIndicador(
			Indicador indicadorNivelServico) {
		indicadorNivelServicoDao.atualizarIndicador(indicadorNivelServico);
		
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void criarIndicador(Indicador indicadorNivelServico) {
		
		indicadorNivelServicoDao.criarIndicador(indicadorNivelServico);
		
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Penalidade getPenalidade(Integer id) {
		
		return penalidadeDao.getPenalidade(id);
	}
	
	@Override
	@Transactional("mineiroTransactionManager")
	public Penalidade getPenalidade(String clausula, Contrato contrato) {
		return penalidadeDao.getPenalidade(clausula, contrato);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<Penalidade> listarPenalidadePorContrato(String numero) {
	
		return penalidadeDao.listarPenalidadePorContrato(numero);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarPenalidade(Penalidade penalidade) {
	
		penalidadeDao.atualizarPenalidade(penalidade);
		
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void criarPenalidade(Penalidade penalidade) {
		
		penalidadeDao.criarPenalidade(penalidade);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public NivelServico getNivelServico(Integer id) {
	
		return nivelServicoDao.getNivelServico(id);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<NivelServico> listarNivelServicoPorIndicador(Indicador indicador) {
	
		return nivelServicoDao.listarNivelServicoPorIndicador(indicador);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarNivelServico(NivelServico nivelServico) {
	
		nivelServicoDao.atualizarNivelServico(nivelServico);
		
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void criarNivelServico(NivelServico nivelServico) {
		
		nivelServicoDao.criarNivelServico(nivelServico);
		
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public TipoIndicador getTipoIndicador(Integer id) {
	
		return tipoIndicadorDao.getTipoIndicador(id);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public List<TipoIndicador> listarTipoIndicador() {
	
		return tipoIndicadorDao.listarTipoIndicador();
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void atualizarTipoIndicador(TipoIndicador tipoIndicador) {
		
		tipoIndicadorDao.atualizarTipoIndicador(tipoIndicador);
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public void criarTipoIndicador(TipoIndicador tipoIndicador) {
		
		tipoIndicadorDao.criarTipoIndicador(tipoIndicador);
		
	}
	
	@Override
	public CategoriaNivelServico categorizarNivelServico (Indicador indicador, BigDecimal valor) {

		for(NivelServico nivelServico:indicador.getNiveisServico()){
			if(nivelServico.contemValor(valor)){
				return nivelServico.getCategoriaNivelServico();
			}
		
		}
		return null;

	}
        
        @Override
	@Transactional("mineiroTransactionManager")
	public ValoresContrato getValoresContratoPorVigencia(DateTime de, DateTime ate) {
		return valoresContratoDao.getValoresContratoPorVigencia(de, ate);
	}
        
        @Override
	@Transactional("mineiroTransactionManager")
	public ValoresContrato getValoresContratoPorId(Integer id) {
		return valoresContratoDao.getValoresContratoPorId(id);
	}
        
        
        @Override
	@Transactional("mineiroTransactionManager")
	public List<ValoresContrato> getValoresContrato(Contrato contrato) {
		return valoresContratoDao.getValoresContrato(contrato);
	}
        
        @Transactional("mineiroTransactionManager")
	@Override
	public void atualizarValoresContrato(ValoresContrato valoresContrato) {
		valoresContratoDao.atualizarValoresContrato(valoresContrato);

	}

	@Transactional("mineiroTransactionManager")
	@Override
	public void criarValoresContrato(ValoresContrato valoresContrato) {
		valoresContratoDao.criarValoresContrato(valoresContrato);

	}
	
	@Override
	@Transactional("mineiroTransactionManager")
	public void realizarCargaContrato(){
            
            
             //CustomWebAuthenticationDetails webAuthenticationDetails = ((CustomWebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails());
             //String scontrato = webAuthenticationDetails.getnContrato();
             //Contrato contrato = contratoService.getContratoPorContratada(scontrato);
		
            /*		if(getContratoPorNumero("005/2012-CJF")==null)
            {
            Contrato contrato = new Contrato();
            contrato.setContratada("Indra");
            contrato.setNumero("005/2012-CJF");
            contrato.setQuantitativo(new BigDecimal("3000"));
            contrato.setValorUnitario(new BigDecimal("470.33"));
            contrato.setValorTotal(new BigDecimal("1410990"));
            contrato.setInicioExpediente(new LocalTime(9,0));
            contrato.setFimExpediente(new LocalTime(19,0));
            criarContrato(contrato);
            
            Indicador indicador = null;
            TipoIndicador tipoIndicador = null;
            NivelServico nivelServico = null;
            
            List<NivelServico> niveisServico = null;
            
            Faixa faixa = null;
            
            
            indicador = new Indicador();
            indicador.setClausula("8.6.1, id 1");
            indicador.setContrato(contrato);
            tipoIndicador = new TipoIndicador();
            tipoIndicador.setNome("Indicador de Atraso");
            criarTipoIndicador(tipoIndicador);
            indicador.setTipoIndicador(tipoIndicador);
            criarIndicador(indicador);
            niveisServico = new ArrayList<NivelServico>();
            
            
            nivelServico = new NivelServico();
            nivelServico.setCategoriaNivelServico(CategoriaNivelServico.DESEJAVEL);
            faixa = new Faixa();
            faixa.setTipoFaixa(TipoFaixa.UNICO);
            faixa.setInicio(new BigDecimal("0"));
            nivelServico.setFaixa(faixa);
            nivelServico.setIndicador(indicador);
            criarNivelServico(nivelServico);
            niveisServico.add(nivelServico);
            
            
            nivelServico = new NivelServico();
            nivelServico.setCategoriaNivelServico(CategoriaNivelServico.ACEITAVEL);
            faixa = new Faixa();
            faixa.setTipoFaixa(TipoFaixa.INTERVALO);
            faixa.setInicio(new BigDecimal("0"));
            faixa.setInicioDentroIntervalo(false);
            faixa.setFim(new BigDecimal("7"));
            faixa.setFimDentroIntervalo(true);
            nivelServico.setFaixa(faixa);
            nivelServico.setIndicador(indicador);
            criarNivelServico(nivelServico);
            niveisServico.add(nivelServico);
            
            nivelServico = new NivelServico();
            nivelServico.setCategoriaNivelServico(CategoriaNivelServico.INACEITAVEL);
            faixa = new Faixa();
            faixa.setTipoFaixa(TipoFaixa.MAIOR);
            faixa.setInicio(new BigDecimal("7"));
            faixa.setInicioDentroIntervalo(false);
            nivelServico.setFaixa(faixa);
            nivelServico.setIndicador(indicador);
            criarNivelServico(nivelServico);
            niveisServico.add(nivelServico);
            
            indicador.setNiveisServico(niveisServico);
            atualizarIndicador(indicador);
            
            
            
            
            
            
            indicador = new Indicador();
            indicador.setClausula("8.6.1, id 2");
            indicador.setContrato(contrato);
            tipoIndicador = new TipoIndicador();
            tipoIndicador.setNome("Indicador de Conformidade");
            criarTipoIndicador(tipoIndicador);
            indicador.setTipoIndicador(tipoIndicador);
            criarIndicador(indicador);
            niveisServico = new ArrayList<NivelServico>();
            
            nivelServico = new NivelServico();
            nivelServico.setCategoriaNivelServico(CategoriaNivelServico.DESEJAVEL);
            faixa = new Faixa();
            faixa.setTipoFaixa(TipoFaixa.UNICO);
            faixa.setInicio(new BigDecimal("100"));
            nivelServico.setFaixa(faixa);
            nivelServico.setIndicador(indicador);
            criarNivelServico(nivelServico);
            niveisServico.add(nivelServico);
            
            nivelServico = new NivelServico();
            nivelServico.setCategoriaNivelServico(CategoriaNivelServico.ACEITAVEL);
            faixa = new Faixa();
            faixa.setTipoFaixa(TipoFaixa.INTERVALO);
            faixa.setInicio(new BigDecimal("97"));
            faixa.setInicioDentroIntervalo(true);
            faixa.setFim(new BigDecimal("100"));
            faixa.setFimDentroIntervalo(false);
            nivelServico.setFaixa(faixa);
            nivelServico.setIndicador(indicador);
            criarNivelServico(nivelServico);
            niveisServico.add(nivelServico);
            
            nivelServico = new NivelServico();
            nivelServico.setCategoriaNivelServico(CategoriaNivelServico.INACEITAVEL);
            faixa = new Faixa();
            faixa.setTipoFaixa(TipoFaixa.INTERVALO);
            faixa.setInicio(new BigDecimal("0"));
            faixa.setInicioDentroIntervalo(true);
            faixa.setFim(new BigDecimal("97"));
            faixa.setFimDentroIntervalo(false);
            nivelServico.setFaixa(faixa);
            nivelServico.setIndicador(indicador);
            criarNivelServico(nivelServico);
            niveisServico.add(nivelServico);
            
            indicador.setNiveisServico(niveisServico);
            atualizarIndicador(indicador);
            
            
            indicador = new Indicador();
            indicador.setClausula("8.6.1, id 3");
            indicador.setContrato(contrato);
            tipoIndicador = new TipoIndicador();
            tipoIndicador.setNome("Indicador de Recusa");
            criarTipoIndicador(tipoIndicador);
            indicador.setTipoIndicador(tipoIndicador);
            criarIndicador(indicador);
            niveisServico = new ArrayList<NivelServico>();
            
            nivelServico = new NivelServico();
            nivelServico.setCategoriaNivelServico(CategoriaNivelServico.DESEJAVEL);
            faixa = new Faixa();
            faixa.setTipoFaixa(TipoFaixa.UNICO);
            faixa.setInicio(new BigDecimal("0"));
            nivelServico.setFaixa(faixa);
            nivelServico.setIndicador(indicador);
            criarNivelServico(nivelServico);
            niveisServico.add(nivelServico);
            
            nivelServico = new NivelServico();
            nivelServico.setCategoriaNivelServico(CategoriaNivelServico.ACEITAVEL);
            faixa = new Faixa();
            faixa.setTipoFaixa(TipoFaixa.UNICO);
            faixa.setInicio(new BigDecimal("1"));
            nivelServico.setFaixa(faixa);
            nivelServico.setIndicador(indicador);
            criarNivelServico(nivelServico);
            niveisServico.add(nivelServico);
            
            
            nivelServico = new NivelServico();
            nivelServico.setCategoriaNivelServico(CategoriaNivelServico.INACEITAVEL);
            faixa = new Faixa();
            faixa.setTipoFaixa(TipoFaixa.MAIOR);
            faixa.setInicio(new BigDecimal("1"));
            faixa.setInicioDentroIntervalo(false);
            nivelServico.setFaixa(faixa);
            nivelServico.setIndicador(indicador);
            criarNivelServico(nivelServico);
            niveisServico.add(nivelServico);
            
            indicador.setNiveisServico(niveisServico);
            atualizarIndicador(indicador);
            
            
            Penalidade glosaAtraso = new Penalidade();
            glosaAtraso.setClausula("11.1, id 17");
            glosaAtraso.setPercentual(new BigDecimal(2));
            glosaAtraso.setTipoPenalidade(TipoPenalidade.GLOSA);
            glosaAtraso.setContrato(contrato);
            criarPenalidade(glosaAtraso);
            
            
            Penalidade glosaConformidade = new Penalidade();
            glosaConformidade.setClausula("11.1, id 18");
            glosaConformidade.setPercentual(new BigDecimal(2));
            glosaConformidade.setTipoPenalidade(TipoPenalidade.GLOSA);
            glosaConformidade.setContrato(contrato);
            criarPenalidade(glosaConformidade);
            
            Penalidade glosaRecusa = new Penalidade();
            glosaRecusa.setClausula("11.1, id 21");
            glosaRecusa.setPercentual(new BigDecimal(2));
            glosaRecusa.setTipoPenalidade(TipoPenalidade.GLOSA);
            glosaRecusa.setContrato(contrato);
            criarPenalidade(glosaRecusa);
            
            
            Penalidade multaAtrasoAposTrintaDiasUteis = new Penalidade();
            multaAtrasoAposTrintaDiasUteis.setClausula("11.1, id 19");
            multaAtrasoAposTrintaDiasUteis.setPercentual(new BigDecimal("0.25"));
            multaAtrasoAposTrintaDiasUteis.setTipoPenalidade(TipoPenalidade.MULTA);
            multaAtrasoAposTrintaDiasUteis.setContrato(contrato);
            criarPenalidade(multaAtrasoAposTrintaDiasUteis);
            
            
            Penalidade multaAtrasoOrdemServicoGarantia = new Penalidade();
            multaAtrasoOrdemServicoGarantia.setClausula("11.1, id 20");
            multaAtrasoOrdemServicoGarantia.setPercentual(new BigDecimal("0.25"));
            multaAtrasoOrdemServicoGarantia.setTipoPenalidade(TipoPenalidade.MULTA);
            multaAtrasoOrdemServicoGarantia.setContrato(contrato);
            criarPenalidade(multaAtrasoOrdemServicoGarantia);
            }*/
	}

        //luis Sergio
        @Override
	@Transactional("mineiroTransactionManager")
	public List<Contrato> listarContratos() {
		
		return contratoDao.listarContratos();
	}

        @Override
	@Transactional("mineiroTransactionManager")
	public List<Contrato> getListar() {
		
		return contratoDao.listarContratos();
	}

	
}
