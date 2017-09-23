package br.jus.cjf.mineiro.web.controllers.validators;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;

@Documented
@Target({ METHOD, FIELD })
@Retention(RUNTIME)
@ReportAsSingleViolation
@Constraint(validatedBy = BigDecimalRangeValidator.class)
public @interface BigDecimalRange {
	 public String message() default "o percentual de aprovação deve ser entre {min} e {max}";
	    public Class<?>[] groups() default {};
	    public Class<? extends Payload>[] payload() default {};

	    int min() default 0;
	    int max() default 100;
}