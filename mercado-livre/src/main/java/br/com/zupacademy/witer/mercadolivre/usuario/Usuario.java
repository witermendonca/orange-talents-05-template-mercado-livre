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

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

	public Usuario(@Email @NotBlank String loginEmail, @NotBlank @Size(min = 6) String senha) {
		this.loginEmail = loginEmail;
		this.senha = new BCryptPasswordEncoder().encode(senha);
	}

}