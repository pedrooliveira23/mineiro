package br.jus.cjf.mineiro.util;

import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.LocalDate;

public class TempoUtil {

	private final Set<LocalDate>	feriados;

	public TempoUtil(Set<LocalDate> feriados) {
		super();
		this.feriados = feriados;
	}

	/**
	 * Calcula a duração em milissegundos entre a data inicial e final,
	 * desconsiderando os feriados e finais de semana.
	 * 
	 * @param dataInicio
	 *            DateTime com a data inicial
	 * @param dataFim
	 *            DateTime com a data final.
	 * @return Quantidade de segundos úteis entre a data inicial e final
	 */
	public long calcularDuracaoContandoPrazo(DateTime dataInicio, DateTime dataFim) {
		long duracaoContandoPrazoMillis = 0;
		DateTime data = dataInicio;
		if(dataFim == null){
			dataFim = new DateTime();
		}
		while (data.plusDays(1).isBefore(dataFim)) {
			if (ehDiaUtil(data)) {
				duracaoContandoPrazoMillis += 24 * 60 * 60 * 1000;
			}
			data = data.plusDays(1);
		}
		if (ehDiaUtil(data)) {
			if (ehDiaUtil(dataFim)) {
				duracaoContandoPrazoMillis += new Duration(data, dataFim).getMillis();
			} else {
				duracaoContandoPrazoMillis += new Duration(data, data.withTime(23, 59, 59, 0)).getMillis();
			}
		} else if (ehDiaUtil(dataFim)) {
			duracaoContandoPrazoMillis += new Duration(dataFim.withTime(0, 0, 1, 0), dataFim).getMillis();
		}
		return duracaoContandoPrazoMillis;
	}

	public boolean ehDiaUtil(DateTime data) {
		return !(data.getDayOfWeek() == DateTimeConstants.SATURDAY
				|| data.getDayOfWeek() == DateTimeConstants.SUNDAY || feriados.contains(data.toLocalDate()));
	}
}
