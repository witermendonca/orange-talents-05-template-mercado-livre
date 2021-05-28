package br.com.zupacademy.witer.mercadolivre.opiniao;

import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.witer.mercadolivre.produto.Produto;
import br.com.zupacademy.witer.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;
import br.com.zupacademy.witer.mercadolivre.usuario.UsuarioRepository;

@RestController
public class OpiniaoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private OpiniaoRepository opiniaoRepository;

	@PostMapping(value = "/produtos/{id}/opinioes")
	@Transactional
	public ResponseEntity<?> cadastarOpniao(@PathVariable("id") Long id,
			@RequestBody @Valid NovaOpiniaoRequest novaOpiniaoRequest) {

		Optional<Produto> produto = produtoRepository.findById(id);

		Usuario usuario = usuarioRepository.findByLoginEmail("witer_mendonca@zup.com.br").get();

		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto Não existe.");

		}

		Opiniao novaOpiniao = novaOpiniaoRequest.toModel(produto.get(), usuario);

		opiniaoRepository.save(novaOpiniao);

		return ResponseEntity.ok().build();
	}
}
