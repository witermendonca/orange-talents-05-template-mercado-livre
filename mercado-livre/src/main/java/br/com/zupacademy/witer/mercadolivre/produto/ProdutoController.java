package br.com.zupacademy.witer.mercadolivre.produto;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.witer.mercadolivre.categoria.CategoriaRepository;
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
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadatrarProduto(@RequestBody @Valid NovoProdutoRequest novoProdutoRequest){
		
		Produto novoProduto = novoProdutoRequest.toModel(categoriaRepository, usuarioRepository);
		
		produtoRepository.save(novoProduto);
		
		return ResponseEntity.ok().build();
	}
}
