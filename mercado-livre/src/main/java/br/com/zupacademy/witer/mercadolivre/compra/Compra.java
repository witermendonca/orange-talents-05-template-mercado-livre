package br.com.zupacademy.witer.mercadolivre.compra;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zupacademy.witer.mercadolivre.produto.Produto;
import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;

@Entity
@Table(name = "tb_compra")
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Positive
	private Integer quantidade;

	@Enumerated
	@NotNull
	private GatewayPagamento gateway;

	@ManyToOne
	@NotNull
	@Valid
	private Produto produtoComprado;

	@ManyToOne
	@NotNull
	@Valid
	private Usuario comprador;

	@Deprecated
	public Compra() {
	}

	public Compra(@Positive Integer quantidade, @NotNull GatewayPagamento gateway,
			@NotNull @Valid Produto produtoComprado, @NotNull @Valid Usuario comprador) {
		super();
		this.quantidade = quantidade;
		this.gateway = gateway;
		this.produtoComprado = produtoComprado;
		this.comprador = comprador;
	}

	public Produto getProdutoComprado() {
		return produtoComprado;
	}

	public Long getId() {
		return id;
	}

}
