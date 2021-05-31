package br.com.zupacademy.witer.mercadolivre.compra;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.witer.mercadolivre.produto.Produto;
import br.com.zupacademy.witer.mercadolivre.produto.ProdutoRepository;
import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;
import br.com.zupacademy.witer.mercadolivre.validacao.IdExistente;

public class NovaCompraRequest {

	@Positive
	private Integer quantidade;

	@NotNull
	@IdExistente(domainClass = Produto.class, fieldName = "id", message = "Produto não encontrado.")
	private Long idProduto;

	@NotNull
	private GatewayPagamento gateway;

	public NovaCompraRequest(@Positive Integer quantidade, @NotNull Long idProduto, GatewayPagamento gateway) {
		super();
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}

	public Compra toModel(ProdutoRepository produtoRepository, @NotNull @Valid Usuario usuario) {

		Produto produto = produtoRepository.getById(idProduto);

		return new Compra(quantidade, gateway, produto, usuario);
	}

}
