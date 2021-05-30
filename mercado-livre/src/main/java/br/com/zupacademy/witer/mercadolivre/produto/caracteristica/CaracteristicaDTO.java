package br.com.zupacademy.witer.mercadolivre.produto.caracteristica;

public class CaracteristicaDTO {

	private String nomeCaracteristica;

	private String descricaoCaracteristica;

	public CaracteristicaDTO(Caracteristica caracteristica) {
		this.nomeCaracteristica = caracteristica.getNomeCaracteristica();
		this.descricaoCaracteristica = caracteristica.getDescricaoCaracteristica();
	}

	public String getNomeCaracteristica() {
		return nomeCaracteristica;
	}

	public String getDescricaoCaracteristica() {
		return descricaoCaracteristica;
	}

}
