package br.jus.cjf.mineiro.web.controllers.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.jus.cjf.mineiro.model.Estado;
import br.jus.cjf.mineiro.web.controllers.commands.EstadoCommand;

@Component
public class EstadoCommandValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return EstadoCommand.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		EstadoCommand estadoCommand = (EstadoCommand) target;
		
		for(Estado estado:estadoCommand.getEstados()){
			if((estado.getContaTempoOS() == false && estado.getContaRecusaOS() == true)){
				  errors.reject("estadoCommand.invalido");

			}
			
		}
		
	}

	
	
}

	
	
