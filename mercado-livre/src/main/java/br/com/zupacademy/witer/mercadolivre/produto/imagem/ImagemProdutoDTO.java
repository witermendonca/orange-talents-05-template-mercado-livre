package br.com.zupacademy.witer.mercadolivre.produto.imagem;

public class ImagemProdutoDTO {

	private String link;

	public ImagemProdutoDTO(ImagemProduto imagemProduto) {
		super();
		this.link = imagemProduto.getLink();
	}

	public String getLink() {
		return link;
	}

}
