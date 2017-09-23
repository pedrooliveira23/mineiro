package br.jus.cjf.mineiro.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.jus.cjf.mineiro.dao.DiaNaoUtilDao;
import br.jus.cjf.mineiro.model.DiaNaoUtil;

@Service
public class DiaNaoUtilServiceImpl implements DiaNaoUtilService {

	private final DiaNaoUtilDao	diaNaoUtilDao;

	private static Logger logger = LoggerFactory.getLogger(DiaNaoUtilServiceImpl.class);
	
	@Autowired
	public DiaNaoUtilServiceImpl(DiaNaoUtilDao diaNaoUtilDao) {
		super();
		this.diaNaoUtilDao = diaNaoUtilDao;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public boolean ehDiaUtil(DateTime dia) {

		boolean retorno;
		if ( dia.getDayOfWeek() == DateTimeConstants.SATURDAY|| dia.getDayOfWeek() == DateTimeConstants.SUNDAY) {
			retorno = false;
		}
		else{
			retorno = diaNaoUtilDao.ehDiaUtil(dia.toLocalDate());
		}

		logger.debug("Dia "+dia+" é dia útil:"+retorno);

		
		return retorno;
	}

	@Override
	@Transactional("mineiroTransactionManager")
	public Integer getQuantidadeDiasComunsParaDiaUtil(DateTime dia) {

                //Sempre sera retornado no minimo um dia util.
		int quantidadeDiasComuns = 1;
		
                //pular os dias nao uteis.
                while(ehDiaUtil(dia)==false){
                        quantidadeDiasComuns++;
                        dia = dia.plusDays(1);
                }
		
		
		logger.debug("Quantidade Dias Comuns:"+quantidadeDiasComuns);
		return quantidadeDiasComuns;
	}
	
	@Override
	@Transactional("mineiroTransactionManager")
	public DateTime getProximoDiaUtil(DateTime dia) {

			
			while(dia != null && ehDiaUtil(dia)==false){
				

				dia = dia.plusDays(1);

			}

		return dia;
	}


	@Transactional("mineiroTransactionManager")
	@Override
	public void atualizar(DiaNaoUtil diaNaoUtil) {

		diaNaoUtilDao.atualizar(diaNaoUtil);
	}

	@Transactional("mineiroTransactionManager")
	@Override
	public void criar(DiaNaoUtil diaNaoUtil) {

		diaNaoUtilDao.salvar(diaNaoUtil);
	}

	
	@Transactional("mineiroTransactionManager")
	@Override
	public void alternarAtivo(DiaNaoUtil diaNaoUtil) {

		diaNaoUtilDao.alternarAtivo(diaNaoUtil);
	}

	@Transactional("mineiroTransactionManager")
	@Override
	public void excluir(DiaNaoUtil diaNaoUtil) {

		diaNaoUtilDao.excluir(diaNaoUtil);
	}

	@Transactional("mineiroTransactionManager")
	@Override
	public List<DiaNaoUtil> listarDiaNaoUtilOrderByDia() {

		return diaNaoUtilDao.getDiasNaoUteis();
	}

	@Override
	public Set<LocalDate> listarDiasNaoUteisComoLocalDate() {
		List<DiaNaoUtil> diasNaoUteis = listarDiaNaoUtilOrderByDia();
		Set<LocalDate> localDateSet = new HashSet<LocalDate>();
		for (DiaNaoUtil diaNaoUtil : diasNaoUteis) {
			localDateSet.add(diaNaoUtil.getDia().toLocalDate());
		}
		return localDateSet;
	}
	
	@Override
	@Transactional("mineiroTransactionManager")
	public void realizarCargaFeriados(){
		
		
		if(listarDiaNaoUtilOrderByDia().isEmpty())
		{	
			DateTime dia = null;
			DiaNaoUtil diaNaoUtil = null;
	
			/*			JANEIRO			*/
			
			dia = new DateTime(2013, 1, 1, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Confraternização Universal");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 1, 2, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 1, 3, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 1, 4, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 1, 5, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 1, 6, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			/*			FEVEREIRO			*/
			
			dia = new DateTime(2013, 2, 11, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(false);
			diaNaoUtil.setDescricao("Carnaval");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 2, 12, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(false);
			diaNaoUtil.setDescricao("Carnaval");
			criar(diaNaoUtil);
			
			/*			MARÇO			*/
			
			dia = new DateTime(2013, 3, 27, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(false);
			diaNaoUtil.setDescricao("Semana Santa");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 3, 28, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(false);
			diaNaoUtil.setDescricao("Semana Santa");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 3, 29, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(false);
			diaNaoUtil.setDescricao("Semana Santa");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 3, 30, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(false);
			diaNaoUtil.setDescricao("Semana Santa");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 3, 31, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(false);
			diaNaoUtil.setDescricao("Semana Santa");
			criar(diaNaoUtil);
			
			/*			ABRIL			*/
			
			dia = new DateTime(2013, 4, 21, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Tiradentes");
			criar(diaNaoUtil);
			
			/*			MAIO			*/
			
			dia = new DateTime(2013, 5, 1, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Dia do Trabalho");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 5, 30, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(false);
			diaNaoUtil.setDescricao("Corpus Christi");
			criar(diaNaoUtil);
			
			/*			JUNHO			*/
			
			//Em junho não há dias não úteis
			
			/*			JULHO			*/
			
			//Em julho não há dias não úteis
			
			/*			AGOSTO			*/
			
			dia = new DateTime(2013, 8, 11, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Criação dos primeiros cursos jurídicos no Brasil");
			criar(diaNaoUtil);
			
			/*			SETEMBRO			*/
			
			dia = new DateTime(2013, 9, 7, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Independência do Brasil");
			criar(diaNaoUtil);
			
			/*			OUTUBRO			*/
			
			dia = new DateTime(2013, 10, 12, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Nossa Senhora Aparecida");
			criar(diaNaoUtil);
			
			/*			NOVEMBRO			*/
			
			dia = new DateTime(2013, 11, 1, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Lei 5010/1966");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 11, 2, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Finados");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 11, 15, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Proclamação da República");
			criar(diaNaoUtil);
			
			/*			DEZEMBRO			*/
			
			dia = new DateTime(2013, 12, 8, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Dia da Justiça");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 20, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 21, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 22, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 23, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 24, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 25, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Natal");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 26, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 27, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 28, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 29, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 30, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
			
			dia = new DateTime(2013, 12, 31, 12, 00);
			diaNaoUtil = new DiaNaoUtil();
			diaNaoUtil.setDia(dia);
			diaNaoUtil.setAnual(true);
			diaNaoUtil.setDescricao("Recesso forense");
			criar(diaNaoUtil);
		}
	}

}
