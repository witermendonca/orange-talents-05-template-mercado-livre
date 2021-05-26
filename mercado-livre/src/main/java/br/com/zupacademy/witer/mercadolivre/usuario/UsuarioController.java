package br.com.zupacademy.witer.mercadolivre.usuario;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioRepository usuarioRepository;

	public UsuarioController(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarUsuario(@RequestBody @Valid NovoUsuarioResquest novoUsuarioResquest) {

		Usuario novoUsuario = novoUsuarioResquest.toModel();

		usuarioRepository.save(novoUsuario);

		return ResponseEntity.ok().build();
	}
	
//	@GetMapping
//	@Transactional
//	public ResponseEntity<List<Usuario>> listaUsuarios() {
//
//		return ResponseEntity.ok(usuarioRepository.findAll());
//	}
}
