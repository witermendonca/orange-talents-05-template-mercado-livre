package br.com.zupacademy.witer.mercadolivre.produto;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.witer.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;
import br.com.zupacademy.witer.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UploaderFake uploaderFake;

	@Autowired
	private CaracteristicaComNomeUnicoValidator caracteristicaComNomeUnicoValidator;

	@InitBinder(value = "novoProdutoRequest") // Validador apenas será valido para novoProdutoRequest.
	public void init(WebDataBinder binder) {
		binder.addValidators(caracteristicaComNomeUnicoValidator);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadatrarProduto(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest) {

		Produto novoProduto = novoProdutoRequest.toModel(categoriaRepository, usuarioRepository);

		produtoRepository.save(novoProduto);

		return ResponseEntity.ok().build();
	}

	@PostMapping(path = "/{id}/imagens")
	@Transactional
	public ResponseEntity<?> adicionarImagem(@PathVariable("id") Long id, @Valid NovaImagemRequest novaImagemRequest) {

		Set<String> links = uploaderFake.envia(novaImagemRequest.getImagens());

		Optional<Produto> produto = produtoRepository.findById(id);

//		Usuario dono = usuarioRepository.findByLoginEmail(produto.get().getUsuario().getLoginEmail()).get();

		Usuario dono = usuarioRepository.findByLoginEmail("witer@zup.com.br").get();

		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id produto inválido.");
		}

		if (!produto.get().pertenceAoUsuario(dono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}

		produto.get().associaImagens(links);

		produtoRepository.save(produto.get());

		return ResponseEntity.ok(produto.toString());
	}
}
