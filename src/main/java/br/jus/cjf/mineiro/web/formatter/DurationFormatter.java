package br.jus.cjf.mineiro.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.format.Formatter;

public class DurationFormatter implements Formatter<Duration> {

	private static final PeriodFormatter PERIOD_FORMATTER;
	
	static {
		PERIOD_FORMATTER = new PeriodFormatterBuilder().appendDays().appendSuffix(" dia", " dias")
		.appendSeparator(", ")
		.appendHours().appendSuffix(" hora", " horas")
		.appendSeparator(", ")
		.appendMinutes().appendSuffix(" minuto", " minutos")
		.appendSeparator(" e ")
		.appendSeconds().appendSuffix(" segundo", " segundos").toFormatter();
	}
	
	@Override
	public String print(Duration object, Locale locale) {
		/*
		 * Black car in the dark room warning
		 * o certo seria fazer PERIOD_FORMATTER.print(object.toPeriod().normalizedStandard());
		 * porém isso estava retornando a formatação errada, ie, não havia overflow de horas para 
		 * dias.
		 */
		int durationEmSegundos = (int)object.getStandardSeconds();
		int segundos = durationEmSegundos % DateTimeConstants.SECONDS_PER_MINUTE;
		int minutos = durationEmSegundos / DateTimeConstants.SECONDS_PER_MINUTE % DateTimeConstants.MINUTES_PER_HOUR;
		int horas = durationEmSegundos / DateTimeConstants.SECONDS_PER_HOUR % DateTimeConstants.HOURS_PER_DAY;
		int dias = durationEmSegundos / DateTimeConstants.SECONDS_PER_DAY;
		/*
		 * fim do black car
		 */
		
		return PERIOD_FORMATTER.print(new Period(0, 0, 0, dias, horas, minutos, segundos, 0));
	}

	@Override
	public Duration parse(String text, Locale locale) throws ParseException {
		return new Duration(Long.parseLong(text));
	}

}
