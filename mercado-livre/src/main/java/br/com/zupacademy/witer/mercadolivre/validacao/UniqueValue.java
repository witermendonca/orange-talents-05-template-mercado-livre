package br.com.zupacademy.witer.mercadolivre.validacao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = { UniqueValueValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueValue {

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	Class<?> domainClass();

	String fieldName();

	String message();
}
