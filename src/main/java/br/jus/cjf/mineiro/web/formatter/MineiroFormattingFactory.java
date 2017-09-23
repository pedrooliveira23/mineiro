package br.jus.cjf.mineiro.web.formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.stereotype.Component;

import br.jus.cjf.mineiro.model.TipoInspecao;
import br.jus.cjf.redmine.model.Issue;

@Component
public class MineiroFormattingFactory extends FormattingConversionServiceFactoryBean {

	
	private final DiaAnnotationFormatterFactory  diaAnnotationFormatterFactory;
	
	
	@Autowired
	private MineiroFormattingFactory(
			DiaAnnotationFormatterFactory diaAnnotationFormatterFactory) {
		super();
		this.diaAnnotationFormatterFactory = diaAnnotationFormatterFactory;
	}



	public void installFormatters(FormatterRegistry registry) {
		registry.addFormatterForFieldType(TipoInspecao.class, new TipoInspecaoFormatter());
		registry.addFormatterForFieldType(Issue.class, new IssueFormatter());
		registry.addFormatterForFieldAnnotation(diaAnnotationFormatterFactory);
	}
}