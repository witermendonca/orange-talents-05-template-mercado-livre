package br.com.zupacademy.witer.mercadolivre.compra;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.witer.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;
import br.com.zupacademy.witer.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/compras")
public class CompraController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CompraRepository compraRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criarCompra(@RequestBody @Valid NovaCompraRequest novaCompraRequest,
			UriComponentsBuilder uriComponentsBuilder) throws BindException {

		// comprador logado.
		Usuario comprador = usuarioRepository.findByLoginEmail("witer_mendonca@gmailcom").get();

		Compra novaCompra = novaCompraRequest.toModel(produtoRepository, comprador);

		boolean abateu = novaCompra.getProdutoComprado().abateEstoque(novaCompraRequest.getQuantidade());

		if (abateu) {

			compraRepository.save(novaCompra);

			if (novaCompraRequest.getGateway().equals(GatewayPagamento.pagseguro)) {

				String urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
						.buildAndExpand(novaCompra.getId()).toString();

				return ResponseEntity.status(HttpStatus.FOUND)
						.body("pagseguro.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPagseguro);
			} else {
				String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
						.buildAndExpand(novaCompra.getId()).toString();

				return ResponseEntity.status(HttpStatus.FOUND)
						.body("paypal.com/" + novaCompra.getId() + "?redirectUrl=" + urlRetornoPaypal);
			}
		}

//		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quantidade do produto indisposnivel.");

		BindException problemaComEstoque = new BindException(novaCompraRequest, "novaCompraRequest");
		problemaComEstoque.reject(null, "Não foi possível realizar a compra por conta do estoque");

		throw problemaComEstoque;

	}
}
