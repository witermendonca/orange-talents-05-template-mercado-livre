package br.com.zupacademy.witer.mercadolivre.produto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class NovaCaracteristicaRequest {

	@NotBlank
	private String nomeCaracteristica;
	
	@NotBlank
	private String descricaoCaracteristica;

	public NovaCaracteristicaRequest(@NotBlank String nomeCaracteristica, @NotBlank String descricaoCaracteristica) {
		super();
		this.nomeCaracteristica = nomeCaracteristica;
		this.descricaoCaracteristica = descricaoCaracteristica;
	}

	public String getNomeCaracteristica() {
		return nomeCaracteristica;
	}

	public String getDescricaoCaracteristicaescricao() {
		return descricaoCaracteristica;
	}

	public Caracteristica toModel(@NotNull @Valid Produto produto) {
		return new Caracteristica(nomeCaracteristica, descricaoCaracteristica, produto);
	}

}
