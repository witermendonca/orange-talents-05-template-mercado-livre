package br.com.zupacademy.witer.mercadolivre.opiniao;

public class OpiniaoDTO {

	private Integer nota;

	private String titulo;

	private String descricao;

	public OpiniaoDTO(Opiniao opiniao) {
		this.nota = opiniao.getNota();
		this.titulo = opiniao.getTitulo();
		this.descricao = opiniao.getDescricao();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getNota() {
		return nota;
	}

}
