package br.com.zupacademy.witer.mercadolivre.produto;

import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CaracteristicaComNomeUnicoValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		
		return NovoProdutoRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}
		
		NovoProdutoRequest novoProduto = (NovoProdutoRequest)target;
		
		Set<String> nomesIguais = novoProduto.caracteristicasIguais();
		if(!nomesIguais.isEmpty()) {
			errors.rejectValue("caracteristica", null, "Caracteristicas com nomes iguais " + nomesIguais);
		}
	}

}
