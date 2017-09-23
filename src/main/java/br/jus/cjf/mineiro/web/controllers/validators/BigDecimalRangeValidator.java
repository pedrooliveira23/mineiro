package br.jus.cjf.mineiro.web.controllers.validators;

import java.math.BigDecimal;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public final class BigDecimalRangeValidator implements
		ConstraintValidator<BigDecimalRange, BigDecimal> {
	private BigDecimal max;
	private BigDecimal min;

	@Override
	public void initialize(final BigDecimalRange bigDecimalRange) {
		max = new BigDecimal(bigDecimalRange.max());
		min = new BigDecimal(bigDecimalRange.min());
	}

	@Override
	public boolean isValid(final BigDecimal percentualAprovacao,
			final ConstraintValidatorContext cvc) {

		if (percentualAprovacao != null
				&& (percentualAprovacao.compareTo(min) == 1 || percentualAprovacao
						.compareTo(min) == 0)
				&& (percentualAprovacao.compareTo(max) == -1 || percentualAprovacao
						.compareTo(max) == 0)) {
			return true;
		}

		return false;
	}
}
