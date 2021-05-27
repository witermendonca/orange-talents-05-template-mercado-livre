package br.com.zupacademy.witer.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.witer.mercadolivre.categoria.Categoria;
import br.com.zupacademy.witer.mercadolivre.categoria.CategoriaRepository;
import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;
import br.com.zupacademy.witer.mercadolivre.usuario.UsuarioRepository;
import br.com.zupacademy.witer.mercadolivre.validacao.IdExistente;
import br.com.zupacademy.witer.mercadolivre.validacao.UniqueValue;

public class NovoProdutoRequest {

	@NotBlank
	@UniqueValue(domainClass = Produto.class, fieldName = "nome", message = "Produto já Cadastrado.")
	private String nome;

	@NotNull
	@Positive
	private BigDecimal preco;

	@NotNull
	@PositiveOrZero
	private Integer qtdDisponivel;

	@NotBlank
	@Size(max = 1000)
	private String descricao;

	@Size(min = 3)
	@Valid
	private List<NovaCaracteristicaRequest> caracteristica = new ArrayList<>();
//	O produto possui pelo menos três características

	@NotNull
	@IdExistente(domainClass = Categoria.class, fieldName = "id", message = "Id de Categoria não encontrada")
	private Long idCategoria;

	@NotNull
	private String loginUsuario;

	public NovoProdutoRequest(@NotBlank String nome, @NotNull @Positive BigDecimal preco,
			@NotNull @PositiveOrZero Integer qtdDisponivel, @NotBlank @Size(max = 1000) String descricao,
			@Size(min = 3) @Valid List<NovaCaracteristicaRequest> caracteristica, @NotNull Long idCategoria,
			@NotNull String loginUsuario) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.qtdDisponivel = qtdDisponivel;
		this.descricao = descricao;
		this.caracteristica.addAll(caracteristica);
		this.idCategoria = idCategoria;
		this.loginUsuario = loginUsuario;
	}

	@Override
	public String toString() {
		return "NovoProdutoRequest [nome=" + nome + ", preco=" + preco + ", qtdDisponivel=" + qtdDisponivel
				+ ", descricao=" + descricao + ", caracteristica=" + caracteristica + ", idCategoria=" + idCategoria
				+ ", loginUsuario=" + loginUsuario + "]";
	}

	public List<NovaCaracteristicaRequest> getCaracteristica() {
		return caracteristica;
	}

	public void setCaracteristica(List<NovaCaracteristicaRequest> caracteristica) {
		this.caracteristica = caracteristica;
	}

	public Produto toModel(CategoriaRepository categoriaRepository, UsuarioRepository usarioRepository) {

		Categoria categoria = categoriaRepository.getById(idCategoria);
		Optional<Usuario> usuarioLogado = usarioRepository.findByLoginEmail(loginUsuario);

		return new Produto(nome, preco, qtdDisponivel, descricao, caracteristica, categoria, usuarioLogado.get());
	}

}
