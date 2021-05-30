package br.com.zupacademy.witer.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import br.com.zupacademy.witer.mercadolivre.categoria.Categoria;
import br.com.zupacademy.witer.mercadolivre.opiniao.Opiniao;
import br.com.zupacademy.witer.mercadolivre.pergunta.Pergunta;
import br.com.zupacademy.witer.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.witer.mercadolivre.produto.caracteristica.NovaCaracteristicaRequest;
import br.com.zupacademy.witer.mercadolivre.produto.imagem.ImagemProduto;
import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;

@Entity
@Table(name = "tb_produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
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

	@NotNull
	private LocalDateTime instanteCadastro = LocalDateTime.now();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	@Size(min = 3)
	private Set<Caracteristica> caracteristicas = new HashSet<>();
//	O produto possui pelo menos três características

	@NotNull
	@ManyToOne
	@Valid
	private Categoria categoria;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<>();

	@OneToMany(mappedBy = "produto")
	private Set<Pergunta> perguntas = new HashSet<>();

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Opiniao> opinioes = new HashSet<>();

	@Deprecated
	public Produto() {
	}

	public Produto(@NotBlank String nome, @NotNull @Positive BigDecimal preco,
			@NotNull @PositiveOrZero Integer qtdDisponivel, @NotBlank @Size(max = 1000) String descricao,
			@Size(min = 3) Collection<NovaCaracteristicaRequest> caracteristicas, @NotNull @Valid Categoria categoria,
			@NotNull @Valid Usuario usuario) {
		super();
		this.nome = nome;
		this.preco = preco;
		this.qtdDisponivel = qtdDisponivel;
		this.descricao = descricao;
		this.caracteristicas.addAll(caracteristicas.stream().map(caracteristica -> caracteristica.toModel(this))
				.collect(Collectors.toSet()));
		this.categoria = categoria;
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(link, this))
				.collect(Collectors.toSet());

		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario possivelDono) {
		return this.usuario.equals(possivelDono);
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getQtdDisponivel() {
		return qtdDisponivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Set<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	public Set<Pergunta> getPerguntas() {
		return perguntas;
	}

	public Set<Opiniao> getOpinioes() {
		return opinioes;
	}

	public <T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMapeadora) {
		return this.opinioes.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	// Media das notas de produto
	public double media() {
		Set<Integer> notas = mapeiaOpinioes(opiniao -> opiniao.getNota());
		OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
		return possivelMedia.orElse(0.0);
	}

}
