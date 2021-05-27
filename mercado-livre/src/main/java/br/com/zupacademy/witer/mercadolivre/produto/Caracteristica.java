package br.com.zupacademy.witer.mercadolivre.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tb_Caracteristica_prod")
public class Caracteristica {

//	Cada característica tem um nome e uma descricao associada.

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nomeCaracteristica;

	@NotBlank
	private String descricaoCaracteristica;

	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;

	@Deprecated
	public Caracteristica() {
	}

	public Caracteristica(@NotBlank String nomeCaracteristica, @NotBlank String descricaoCaracteristica,
			@NotNull @Valid Produto produto) {
		super();
		this.nomeCaracteristica = nomeCaracteristica;
		this.descricaoCaracteristica = descricaoCaracteristica;
		this.produto = produto;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeCaracteristica == null) ? 0 : nomeCaracteristica.hashCode());
		result = prime * result + ((produto == null) ? 0 : produto.hashCode());
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
		Caracteristica other = (Caracteristica) obj;
		if (nomeCaracteristica == null) {
			if (other.nomeCaracteristica != null)
				return false;
		} else if (!nomeCaracteristica.equals(other.nomeCaracteristica))
			return false;
		if (produto == null) {
			if (other.produto != null)
				return false;
		} else if (!produto.equals(other.produto))
			return false;
		return true;
	}

}
