package br.com.zupacademy.witer.mercadolivre.pergunta;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.witer.mercadolivre.produto.Produto;
import br.com.zupacademy.witer.mercadolivre.usuario.Usuario;

public class NovaPerguntaResquest {

	@NotBlank
	private String titulo;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Pergunta toModel(Produto produto, Usuario usuario) {

		return new Pergunta(this.titulo, usuario, produto);
	}

}
