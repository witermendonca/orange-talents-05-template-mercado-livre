package br.com.zupacademy.witer.mercadolivre.detalheproduto;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.witer.mercadolivre.produto.Produto;
import br.com.zupacademy.witer.mercadolivre.produto.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class DetalhesProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

	@GetMapping(value = "/{id}")
	public ResponseEntity<DetalhesProdutoDTO> getDetalhes(@PathVariable("id") Long id) {

		Optional<Produto> produto = produtoRepository.findById(id);

		if (produto.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
		}

		return ResponseEntity.ok(new DetalhesProdutoDTO(produto.get()));
	}
}
