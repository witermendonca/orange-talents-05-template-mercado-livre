package br.com.zupacademy.witer.mercadolivre.categoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@ManyToOne
	private Categoria categoriaMae;

	@Deprecated
	public Categoria() {
	}

	public Categoria(@NotBlank String nome) {
		super();
		Assert.isTrue(StringUtils.hasLength(nome), "Nome não pode ser em branco");
		this.nome = nome;

	}

	public void setCategoriaMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
	}

	public String getNome() {
		return nome;
	}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}

}
