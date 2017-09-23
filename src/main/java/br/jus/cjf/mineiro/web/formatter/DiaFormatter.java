package br.jus.cjf.mineiro.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.joda.time.DateTimeConstants;
import org.joda.time.Duration;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.springframework.format.Formatter;

import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.Duracao;
import br.jus.cjf.mineiro.model.TipoDuracao;


public class DiaFormatter implements Formatter<Duracao> {

	private static final PeriodFormatter PERIOD_FORMATTER_DIA_CORRIDO;
	
	private static final PeriodFormatter PERIOD_FORMATTER_DIA_UTIL;
	
	private static final PeriodFormatter PERIOD_FORMATTER_DIA_ATRASO;
	
	private static final PeriodFormatter PERIOD_FORMATTER_HORA;
	
	
	private final Contrato contrato;
	
	
	
	public DiaFormatter(Contrato contrato) {
		super();
	
	      
	      this.contrato = contrato;
	}

	static {
		PERIOD_FORMATTER_DIA_CORRIDO = new PeriodFormatterBuilder().appendDays().appendSuffix(" dia", " dias")
		.appendSeparator(", ")
		.appendHours().appendSuffix(" hora", " horas")
		.appendSeparator(", ")
		.appendMinutes().appendSuffix(" minuto", " minutos")
		.appendSeparator(" e ")
		.appendSeconds().appendSuffix(" segundo", " segundos").toFormatter();
		
		PERIOD_FORMATTER_DIA_UTIL = new PeriodFormatterBuilder().appendDays().appendSuffix(" dia útil", " dias úteis")
		.appendSeparator(", ")
		.appendHours().appendSuffix(" hora", " horas")
		.appendSeparator(", ")
		.appendMinutes().appendSuffix(" minuto", " minutos")
		.appendSeparator(" e ")
		.appendSeconds().appendSuffix(" segundo", " segundos").toFormatter();
		
		PERIOD_FORMATTER_DIA_ATRASO = new PeriodFormatterBuilder().appendDays().appendSuffix(" dia", " dias").toFormatter();
		
		PERIOD_FORMATTER_HORA = new PeriodFormatterBuilder().appendHours().appendSuffix(" hora", " horas")
		.appendSeparator(", ")
		.appendMinutes().appendSuffix(" minuto", " minutos")
		.appendSeparator(" e ")
		.appendSeconds().appendSuffix(" segundo", " segundos").toFormatter();
	}
	@Override
	public String print(Duracao duracao, Locale locale) {

		/*
		 * Black car in the dark room warning
		 * o certo seria fazer PERIOD_FORMATTER.print(object.toPeriod().normalizedStandard());
		 * porém isso estava retornando a formatação errada, ie, não havia overflow de horas para 
		 * dias.
		 */
	
		int durationEmSegundos = (int)duracao.getValor().getStandardSeconds();
		int segundos = 0;
		int minutos = 0;
		int horas = 0;
		int dias = 0;
		
		if(duracao.getTipoDuracao().equals(TipoDuracao.DIA_CORRIDO))
		{
			segundos = durationEmSegundos % DateTimeConstants.SECONDS_PER_MINUTE;
			minutos = durationEmSegundos / DateTimeConstants.SECONDS_PER_MINUTE % DateTimeConstants.MINUTES_PER_HOUR;
			horas = durationEmSegundos / DateTimeConstants.SECONDS_PER_HOUR % DateTimeConstants.HOURS_PER_DAY;
			dias = durationEmSegundos / DateTimeConstants.SECONDS_PER_DAY;
			
			return PERIOD_FORMATTER_DIA_CORRIDO.print(new Period(0, 0, 0, dias, horas, minutos, segundos, 0));
		}
		if(duracao.getTipoDuracao().equals(TipoDuracao.DIA_ATRASO))
		{

			dias = durationEmSegundos / DateTimeConstants.SECONDS_PER_DAY;
			
			return PERIOD_FORMATTER_DIA_ATRASO.print(new Period(0, 0, 0, dias, 0, 0, 0, 0));
		}
		else if(duracao.getTipoDuracao().equals(TipoDuracao.DIA_UTIL))
		{
			
			segundos = durationEmSegundos % DateTimeConstants.SECONDS_PER_MINUTE;
			minutos = durationEmSegundos / DateTimeConstants.SECONDS_PER_MINUTE % DateTimeConstants.MINUTES_PER_HOUR;
			horas = durationEmSegundos  / DateTimeConstants.SECONDS_PER_HOUR % contrato.getDuracaoHorasExpediente();
			dias = durationEmSegundos / contrato.getDuracaoSegundosExpediente();

			return PERIOD_FORMATTER_DIA_UTIL.print(new Period(0, 0, 0, dias, horas, minutos, segundos, 0));	
		}
		else
		{
			
			segundos = durationEmSegundos % DateTimeConstants.SECONDS_PER_MINUTE;
			minutos = durationEmSegundos / DateTimeConstants.SECONDS_PER_MINUTE % DateTimeConstants.MINUTES_PER_HOUR;
			horas = durationEmSegundos  / DateTimeConstants.SECONDS_PER_HOUR;
			return PERIOD_FORMATTER_HORA.print(new Period(0, 0, 0, 0, horas, minutos, segundos, 0));
	

		}
		/*
		 * fim do black car
		 */
		
		
	}

	@Override
	public Duracao parse(String text, Locale locale) throws ParseException {
		return new Duracao(new Duration(Long.parseLong(text)));
	}

}
