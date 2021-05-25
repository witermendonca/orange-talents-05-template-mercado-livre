package br.com.zupacademy.witer.mercadolivre.categoria;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	private final CategoriaRepository categoriaRepository;

	public CategoriaController(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrarCategoria(@RequestBody @Valid NovaCategoriaRequest novaCategoriaRequest) {

		Categoria novaCategoria = novaCategoriaRequest.toModel(categoriaRepository);

		categoriaRepository.save(novaCategoria);

		return ResponseEntity.ok().build();
	}
}
