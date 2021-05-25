package br.com.zupacademy.witer.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zupacademy.witer.mercadolivre.validacao.UniqueValue;

public class NovoUsuarioResquest {

	@Email
	@NotBlank
	@UniqueValue(domainClass = Usuario.class, fieldName = "loginEmail", message = "E-mail com usuário já cadastrado.")
	private String loginEmail;

	@NotBlank
	@Size(min = 6)
	private String senha;

	public NovoUsuarioResquest(@Email @NotBlank String loginEmail, @NotBlank @Size(min = 6) String senha) {
		this.loginEmail = loginEmail;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(loginEmail, new SenhaLimpa(senha));
	}

}
