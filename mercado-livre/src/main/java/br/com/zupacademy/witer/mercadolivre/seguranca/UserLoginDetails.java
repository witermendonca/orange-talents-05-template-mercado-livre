package br.com.zupacademy.witer.mercadolivre.seguranca;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;

public class UserLoginDetails implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String loginEmail;

	private String senha;

	private List<GrantedAuthority> authorities;

	public UserLoginDetails(Usuario usuario) {
		super();
		this.loginEmail = usuario.getLoginEmail();
		this.senha = usuario.getSenha();
		this.id = usuario.getId();
	}

	public Long getId() {
		return id;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.loginEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
