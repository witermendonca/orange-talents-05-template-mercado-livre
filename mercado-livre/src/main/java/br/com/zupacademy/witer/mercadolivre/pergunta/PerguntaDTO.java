package br.com.zupacademy.witer.mercadolivre.pergunta;

public class PerguntaDTO {

	private String titulo;

	private String usuario;

	public PerguntaDTO(Pergunta pergunta) {
		this.titulo = pergunta.getTitulo();
		this.usuario = pergunta.getUsurio().getLoginEmail();
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUsuario() {
		return usuario;
	}

}
