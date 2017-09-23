package br.jus.cjf.mineiro.web.controllers.validators;

import br.jus.cjf.mineiro.model.Contrato;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.jus.cjf.mineiro.model.Transicao;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.validation.ValidationUtils;

@Component
public class ContratoValidator implements Validator{
          private Pattern pattern;
	  private Matcher matcher;
 
	  private static final String TIME24HOURS_PATTERN = 
                 "([01]?[0-9]|2[0-3]):[0-5][0-9]:[0-5][0-9]";

	@Override
	public boolean supports(Class<?> clazz) {
		 return Transicao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		          Contrato contrato = (Contrato) target;

         
                          
		  if(contrato.getQuantitativo()== null ){
			  errors.rejectValue("quantitativo", "campo.vazio");
		  }
		  if(contrato.getContratada()== null || contrato.getContratada().isEmpty()){
			  errors.rejectValue("contratada", "campo.vazio");
		  }
                  
                  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "numero", "campo.vazio");
                  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valorUnitario", "campo.vazio");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "inicioExpediente1", "campo.vazio");
                  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fimExpediente1", "campo.vazio");
                  
                  if(contrato.getInicioExpediente1()!= null && !contrato.getInicioExpediente1().isEmpty() && !validate(contrato.getInicioExpediente1())){
			  errors.rejectValue("inicioExpediente1", "hora.invalida");
		  }
                  
                  if(contrato.getFimExpediente1()!= null && !contrato.getFimExpediente1().isEmpty() && !validate(contrato.getFimExpediente1())){
			  errors.rejectValue("fimExpediente1", "hora.invalida");
		  }
	}
        
        
        public boolean validate(final String time){
 
		  matcher = pattern.matcher(time);
		  return matcher.matches();
 
	  }
        
         public ContratoValidator(){
		  pattern = Pattern.compile(TIME24HOURS_PATTERN);
	  }
	
	
}
