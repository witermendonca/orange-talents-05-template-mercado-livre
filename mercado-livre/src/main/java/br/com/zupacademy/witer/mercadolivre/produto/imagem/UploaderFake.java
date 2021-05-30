package br.com.zupacademy.witer.mercadolivre.produto.imagem;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@Primary
public class UploaderFake implements Uploader {

	// Tipo Set para um collection que não contém elementos duplicados
	@Override
	public Set<String> envia(List<MultipartFile> imagens) {

		// recebe imagens , mapeia e retorna list de imagens para Set.
		return imagens.stream().map(imagem -> "http://bucket.io/" + imagem.getOriginalFilename())
				.collect(Collectors.toSet());
	}
}
