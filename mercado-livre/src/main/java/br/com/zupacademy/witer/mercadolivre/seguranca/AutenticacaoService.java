package br.com.zupacademy.witer.mercadolivre.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;
import br.com.zupacademy.witer.mercadolivre.usuario.UsuarioRepository;

@Component
public class AutenticacaoService implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String loginEmail) throws UsernameNotFoundException {

		Optional<Usuario> usuario = usuarioRepository.findByLoginEmail(loginEmail);

		// Se um valor estiver presente, retorna o valor, caso contrário, lança uma
		// exceção produzida pela função de fornecimento de exceção.
		usuario.orElseThrow(() -> new UsernameNotFoundException(loginEmail + " Dados Inválidos."));

		return usuario.map(UserLoginDetails::new).get();
	}

}
