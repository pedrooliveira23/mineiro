package br.jus.cjf.mineiro.web.controllers.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.jus.cjf.mineiro.model.Transicao;

@Component
public class TransicaoValidator implements Validator{


	@Override
	public boolean supports(Class<?> clazz) {
		 return Transicao.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Transicao transicao = (Transicao) target;

	
		  if(transicao.getJustificativa().getDescricao()== null || transicao.getJustificativa().getDescricao().isEmpty()){
			  errors.rejectValue("justificativa.descricao", "campo.vazio");
		  }
		  if(transicao.getContaRecusaOS() == true && transicao.getContaTempoOS() == false){
			  errors.rejectValue("contaTempoOS", "transicao.contaTempoOS");
		  }
	
		  
	}
	
	
}
