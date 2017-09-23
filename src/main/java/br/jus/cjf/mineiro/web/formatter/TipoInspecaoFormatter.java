package br.jus.cjf.mineiro.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import br.jus.cjf.mineiro.model.TipoInspecao;

public class TipoInspecaoFormatter implements Formatter<TipoInspecao>{


	@Override
	public String print(TipoInspecao tipoInspecao, Locale locale) {

		if(tipoInspecao.getId()==null){
			return "";
		}
		return tipoInspecao.getId().toString();
	}

	@Override
	public TipoInspecao parse(String id, Locale locale) throws ParseException {

		TipoInspecao tipoInspecao = new TipoInspecao();
		tipoInspecao.setId(Integer.parseInt(id));
		return tipoInspecao;
	}

}
