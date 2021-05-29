package br.com.zupacademy.witer.mercadolivre.pergunta;

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
import br.com.zupacademy.witer.mercadolivre.util.Emails;

@RestController
public class PerguntaController {

	@Autowired
	private PerguntaRepository perguntaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private Emails emails;

	@PostMapping(value = "/produtos/{id}/perguntas")
	@Transactional
	public ResponseEntity<?> cadastrarPergunta(@PathVariable("id") Long id,
			@RequestBody @Valid NovaPerguntaResquest novaPerguntaResquest) {

		Optional<Produto> produto = produtoRepository.findById(id);

		Usuario usuarioLogado = usuarioRepository.findByLoginEmail("witer_mendonca@zup.com.br").get();

		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Produto Não existe.");

		}

		Pergunta novaPergunta = novaPerguntaResquest.toModel(produto.get(), usuarioLogado);

		perguntaRepository.save(novaPergunta);

		emails.novaPergunta(novaPergunta);

		return ResponseEntity.ok(novaPerguntaResquest.getTitulo());
	}
}
