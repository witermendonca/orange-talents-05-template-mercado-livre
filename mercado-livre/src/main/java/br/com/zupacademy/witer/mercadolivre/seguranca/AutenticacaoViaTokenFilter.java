package br.com.zupacademy.witer.mercadolivre.seguranca;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;
import br.com.zupacademy.witer.mercadolivre.usuario.UsuarioRepository;

public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	private UsuarioRepository repository;

	public AutenticacaoViaTokenFilter(TokenService tokenService, UsuarioRepository repository) {
		this.tokenService = tokenService;
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = recuperarToken(request);

		boolean valido = tokenService.isTokenValido(token);

		if (valido) {
			autenticarCliente(token);
		}

		filterChain.doFilter(request, response);

	}

	private void autenticarCliente(String token) {
		Long idUsuario = tokenService.getIdUsuario(token);
		Optional<Usuario> usuario = repository.findById(idUsuario);

		if (usuario.isPresent()) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
					null);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

	private String recuperarToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}

		return token.substring(7, token.length());
	}

}
