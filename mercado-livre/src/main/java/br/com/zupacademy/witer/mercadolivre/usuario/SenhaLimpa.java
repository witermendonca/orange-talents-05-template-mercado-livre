package br.com.zupacademy.witer.mercadolivre.usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

public class SenhaLimpa {

	private String senha;

	public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
		Assert.hasLength(senha, "Senha não pode ser em branco");
		Assert.isTrue(senha.length() >= 6, "Senha tem que ter no mínimo 6 caracteres");

		this.senha = senha;
	}

	public String hash() {
		return new BCryptPasswordEncoder().encode(senha);
	}
}
