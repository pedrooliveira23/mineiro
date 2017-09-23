package br.jus.cjf.mineiro.service;

import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import br.jus.cjf.mineiro.model.DiaNaoUtil;

public interface DiaNaoUtilService {

	boolean ehDiaUtil(DateTime dateTime);
	
	Integer getQuantidadeDiasComunsParaDiaUtil(DateTime dia);
	
	DateTime getProximoDiaUtil(DateTime dia);
	
	void criar(DiaNaoUtil diaNaoUtil);
	
	void atualizar(DiaNaoUtil diaNaoUtil);

	List<DiaNaoUtil> listarDiaNaoUtilOrderByDia();

	void alternarAtivo(DiaNaoUtil diaNaoUtil);

	void excluir(DiaNaoUtil diaNaoUtil);

	Set<LocalDate> listarDiasNaoUteisComoLocalDate();
	
	void realizarCargaFeriados();
}
