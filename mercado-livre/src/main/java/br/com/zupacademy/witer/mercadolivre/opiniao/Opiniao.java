package br.com.zupacademy.witer.mercadolivre.opiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.witer.mercadolivre.produto.Produto;
import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;

@Entity
@Table(name = "tb_opiniao")
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Min(1)
	@Max(5)
	private Integer nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	@NotNull
	@Valid
	@ManyToOne
	private Produto produto;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;

	public Opiniao(@Min(1) @Max(5) Integer nota, @NotBlank String titulo, @NotBlank @Size(max = 500) String descricao,
			@NotNull @Valid Produto produto, @NotNull @Valid Usuario usuario) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.usuario = usuario;
	}

}
