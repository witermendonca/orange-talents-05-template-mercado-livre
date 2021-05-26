package br.com.zupacademy.witer.mercadolivre.seguranca;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	private String loginEmail;
	private String senha;

	public String getLoginEmail() {
		return loginEmail;
	}

	public String getSenha() {
		return senha;
	}

	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(loginEmail, senha);
	}

}
