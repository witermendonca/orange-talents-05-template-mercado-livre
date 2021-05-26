package br.com.zupacademy.witer.mercadolivre.seguranca;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${jwt.expiration}")
	private long expiration;

	@Value("${jwt.secret}")
	private String secret;

	public String gerarToken(Authentication authentication) {
		UserLoginDetails user = (UserLoginDetails) authentication.getPrincipal();

		final Date now = new Date();
		final Date expiration = new Date(now.getTime() + this.expiration);

		return Jwts.builder().setIssuer("API Orange Talents Mercado Livre").setSubject(user.getId().toString())
				.setIssuedAt(now).setExpiration(expiration).signWith(SignatureAlgorithm.HS256, this.secret).compact();
	}

	public boolean isTokenValido(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getIdUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
}
