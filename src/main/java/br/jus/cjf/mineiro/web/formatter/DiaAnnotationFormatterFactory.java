package br.jus.cjf.mineiro.web.formatter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.stereotype.Component;

import br.jus.cjf.mineiro.model.Contrato;
import br.jus.cjf.mineiro.model.Duracao;
import br.jus.cjf.mineiro.service.ContratoService;
import br.jus.cjf.spring.util.CustomWebAuthenticationDetails;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;

@Component
public final class DiaAnnotationFormatterFactory implements AnnotationFormatterFactory<DiaFormat> {

	private Contrato contrato;
	
	//private final ContratoService contratoService;
         private final HttpSession session;
	
	@Autowired
    private DiaAnnotationFormatterFactory(HttpSession session) {
		super();
		//this.contratoService = contratoService;
                this.session = session;
	}

	public Set<Class<?>> getFieldTypes() {
        return new HashSet<Class<?>>(Arrays.asList(new Class<?>[] {
            Duracao.class }));
    }

	public Printer<Duracao> getPrinter(DiaFormat annotation, Class<?> fieldType) {
        return configureFormatterFrom(annotation, fieldType);
    }

    public Parser<Duracao> getParser(DiaFormat annotation, Class<?> fieldType) {
        return configureFormatterFrom(annotation, fieldType);
    }

    private Formatter<Duracao> configureFormatterFrom(DiaFormat annotation, Class<?> fieldType) {
       return new DiaFormatter(getContrato());
       
       
    }
    
	public Contrato getContrato(){
		if(contrato==null){
		 //CustomWebAuthenticationDetails webAuthenticationDetails = ((CustomWebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails());
                 //String scontrato = webAuthenticationDetails.getnContrato();
                 //contrato = contratoService.getContratoPorId(Integer.parseInt(scontrato));
                     contrato = (Contrato)session.getAttribute("contratada");
		}
		return contrato;
		
	}
	

}