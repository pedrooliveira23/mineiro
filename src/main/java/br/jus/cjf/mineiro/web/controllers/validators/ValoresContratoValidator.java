package br.jus.cjf.mineiro.web.controllers.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.jus.cjf.mineiro.model.Transicao;
import br.jus.cjf.mineiro.model.ValoresContrato;
import org.springframework.validation.ValidationUtils;

@Component
public class ValoresContratoValidator implements Validator{
          private ValoresContrato valor;
 

    public ValoresContratoValidator() {
    }

	@Override
	public boolean supports(Class<?> clazz) {
		 return ValoresContrato.class.isAssignableFrom(clazz);
	}

        @Override
	public void validate(Object target, Errors errors) {
		          ValoresContrato valorContrato = (ValoresContrato) target;

         
                          
		  if(valorContrato.getQuantitativo()== null ){
			  errors.rejectValue("quantitativo", "campo.vazio");
		  }

                  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "valorUnitario", "campo.vazio");
		  ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dataInicioVigencia", "campo.vazio");
                  
                  if(valor !=null && valorContrato.getDataInicioVigencia().isBefore(valor.getDataInicioVigencia().plusDays(1)) ){
			  errors.rejectValue("dataInicioVigencia", "data.menor");
		  }
	}
        

        
         public ValoresContratoValidator( ValoresContrato valor){
                this.valor = valor;
             
	  }
	
	
}
