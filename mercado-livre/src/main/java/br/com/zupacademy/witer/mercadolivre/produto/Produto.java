package br.com.zupacademy.witer.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
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

}
