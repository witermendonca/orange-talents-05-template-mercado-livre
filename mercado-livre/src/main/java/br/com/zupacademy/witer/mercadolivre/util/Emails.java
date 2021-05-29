package br.com.zupacademy.witer.mercadolivre.util;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.zupacademy.witer.mercadolivre.pergunta.Pergunta;

@Component
public class Emails {

	@Autowired
	private Mailer mailer;

	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		mailer.send("<html>...</html>", "Nova pergunta...", pergunta.getUsurio().getLoginEmail(),
				"novapergunta@nossomercadolivre.com", pergunta.getDonoProduto().getLoginEmail());

	}

}
