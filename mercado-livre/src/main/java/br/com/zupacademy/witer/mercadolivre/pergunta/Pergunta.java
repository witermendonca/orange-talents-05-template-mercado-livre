package br.com.zupacademy.witer.mercadolivre.pergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.witer.mercadolivre.produto.Produto;
import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;

@Entity
@Table(name = "tb_pergunta")
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotNull
	private LocalDateTime instanteCriacao = LocalDateTime.now();

	@ManyToOne
	@NotNull
	@Valid
	private Usuario usuario;

	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;

	@Deprecated
	public Pergunta() {
	}

	public Pergunta(@NotBlank String titulo, @NotNull @Valid Usuario usurio, @NotNull @Valid Produto produto) {
		super();
		this.titulo = titulo;
		this.usuario = usurio;
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Pergunta other = (Pergunta) obj;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}

	public Usuario getUsurio() {
		return usuario;
	}

	public Usuario getDonoProduto() {
		return produto.getUsuario();
	}

}
