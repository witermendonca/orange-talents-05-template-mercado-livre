package br.com.zupacademy.witer.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Email
	@NotBlank
	private String loginEmail;

	@NotBlank
	@Size(min = 6)
	private String senha;

	@NotNull
	private LocalDateTime instanteCadastro = LocalDateTime.now();

	@Deprecated
	public Usuario() {
	}

	public Usuario(@Email @NotBlank String loginEmail, @NotBlank @Size(min = 6) SenhaLimpa senhaLimpa) {
		Assert.isTrue(StringUtils.hasLength(loginEmail), "LoginEmail não pode ser em branco");
		Assert.notNull(senhaLimpa, "O objeto do tipo SenhaLimpa nao pode ser nulo");
		this.loginEmail = loginEmail;
		this.senha = senhaLimpa.hash();
	}

	public Long getId() {
		return id;
	}

	public String getLoginEmail() {
		return loginEmail;
	}

	public String getSenha() {
		return senha;
	}

}