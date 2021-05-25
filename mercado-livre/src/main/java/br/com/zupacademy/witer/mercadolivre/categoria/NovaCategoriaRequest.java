package br.com.zupacademy.witer.mercadolivre.categoria;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zupacademy.witer.mercadolivre.validacao.IdExistente;
import br.com.zupacademy.witer.mercadolivre.validacao.UniqueValue;

public class NovaCategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Categoria já cadastrada.")
	private String nome;

	@Positive
	@IdExistente(domainClass = Categoria.class, fieldName = "id", message = "Id de Categoria não encontrada.")
	private Long idCategoriaMae;

	public NovaCategoriaRequest(@NotBlank String nome, @Positive Long idCategoriaMae) {
		super();
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}

	public Categoria toModel(CategoriaRepository categoriaRepository) {

		Categoria categoria = new Categoria(nome);

		if (idCategoriaMae != null) {
			Categoria categoriaMae = categoriaRepository.getById(idCategoriaMae);
			Assert.notNull(categoriaMae, "O id da Categoria Mãe deve ser válido.");
			categoria.setCategoriaMae(categoriaMae);
		}

		return categoria;
	}

}
