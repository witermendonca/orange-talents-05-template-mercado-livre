package br.com.zupacademy.witer.mercadolivre.detalheproduto;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.zupacademy.witer.mercadolivre.categoria.Categoria;
import br.com.zupacademy.witer.mercadolivre.opiniao.OpiniaoDTO;
import br.com.zupacademy.witer.mercadolivre.pergunta.PerguntaDTO;
import br.com.zupacademy.witer.mercadolivre.produto.Produto;
import br.com.zupacademy.witer.mercadolivre.produto.caracteristica.CaracteristicaDTO;
import br.com.zupacademy.witer.mercadolivre.produto.imagem.ImagemProdutoDTO;

public class DetalhesProdutoDTO {

	private String nome;

	private BigDecimal preco;

	private Integer qtdDisponivel;

	private String descricao;

	private Categoria categoria;

	private Set<CaracteristicaDTO> caracteristicas;

	private Set<ImagemProdutoDTO> imagens;

	private Set<PerguntaDTO> pergunta;

	private Set<OpiniaoDTO> opinioes;

	private int numeroTotalNotas;

	private double mediaNotas;

	public DetalhesProdutoDTO(Produto produto) {
		super();
		this.nome = produto.getNome();
		this.preco = produto.getPreco();
		this.qtdDisponivel = produto.getQtdDisponivel();
		this.descricao = produto.getDescricao();
		this.categoria = produto.getCategoria();
		this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaDTO::new)
				.collect(Collectors.toSet());
		this.imagens = produto.getImagens().stream().map(ImagemProdutoDTO::new).collect(Collectors.toSet());
		this.pergunta = produto.getPerguntas().stream().map(PerguntaDTO::new).collect(Collectors.toSet());
		this.opinioes = produto.getOpinioes().stream().map(OpiniaoDTO::new).collect(Collectors.toSet());
		this.numeroTotalNotas = this.opinioes.size();
		this.mediaNotas = produto.media();
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Integer getQtdDisponivel() {
		return qtdDisponivel;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Set<CaracteristicaDTO> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<ImagemProdutoDTO> getImagens() {
		return imagens;
	}

	public Set<PerguntaDTO> getPergunta() {
		return pergunta;
	}

	public Set<OpiniaoDTO> getOpinioes() {
		return opinioes;
	}

	public int getNumeroTotalNotas() {
		return numeroTotalNotas;
	}

	public double getMediaNotas() {
		return mediaNotas;
	}

}
