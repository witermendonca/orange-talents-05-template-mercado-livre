package br.com.zupacademy.witer.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioResquest {

	@Email
	@NotBlank
	private String loginEmail;

	@NotBlank
	@Size(min = 6)
	private String senha;

	public NovoUsuarioResquest(@Email @NotBlank String loginEmail, @NotBlank @Size(min = 6) String senha) {
		this.loginEmail = loginEmail;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(loginEmail, senha);
	}

}
