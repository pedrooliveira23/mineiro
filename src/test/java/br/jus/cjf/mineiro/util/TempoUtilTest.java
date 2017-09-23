package br.jus.cjf.mineiro.util;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TempoUtilTest {

	private static final long	SEGUNDOS	= 1 * 1000;
	private static final long	MINUTOS		= 60 * SEGUNDOS;
	private static final long	HORAS		= 60 * MINUTOS;
	private static final long	DIAS		= 24 * HORAS;

	private TempoUtil			tempoUtil;

	@Before
	public void before() {
		Set<LocalDate> feriados = new HashSet<LocalDate>();
		feriados.add(new LocalDate(2012, 11, 15));
		feriados.add(new LocalDate(2012, 9, 7));
		feriados.add(new LocalDate(2012, 10, 12));
		tempoUtil = new TempoUtil(feriados);
	}

	@Test
	public void testarEhDiaUtil() {
		assertTrue(tempoUtil.ehDiaUtil(new DateTime(2012, 11, 6, 12, 0)));
		assertTrue(tempoUtil.ehDiaUtil(new DateTime(2012, 11, 7, 13, 20)));
		assertTrue(tempoUtil.ehDiaUtil(new DateTime(2012, 11, 8, 14, 20)));
		assertFalse(tempoUtil.ehDiaUtil(new DateTime(2012, 11, 15, 13, 20)));
	}

	@Test
	public void testarContagemPrazoComFeriadoNoMeio() {
		DateTime dataInicio = new DateTime(2012, 11, 12, 12, 0);
		DateTime dataFim = new DateTime(2012, 11, 16, 14, 0);
		assertEquals(3 * DIAS + 2 * HORAS, tempoUtil.calcularDuracaoContandoPrazo(dataInicio, dataFim));
	}

	@Test
	public void testarContagemPrazoDuranteSemana() {
		DateTime dataInicio = new DateTime(2012, 11, 5, 12, 0);
		DateTime dataFim = new DateTime(2012, 11, 9, 14, 33, 13);
		assertEquals(4 * DIAS + 2 * HORAS + 33 * MINUTOS + 13 * SEGUNDOS, tempoUtil.calcularDuracaoContandoPrazo(dataInicio, dataFim));
	}

	@Test
	public void testarContagemPrazoMesesDiferentes() {
		DateTime dataInicio = new DateTime(2012, 8, 31, 12, 0);
		DateTime dataFim = new DateTime(2012, 10, 31, 15, 34, 12);
		assertEquals(41 * DIAS + 3 * HORAS + 34 * MINUTOS + 12 * SEGUNDOS, tempoUtil.calcularDuracaoContandoPrazo(dataInicio, dataFim));
	}
}
