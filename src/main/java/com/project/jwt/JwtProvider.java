package com.project.jwt;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import com.project.models.dtos.JwtDto;
import com.project.models.entitys.UserMain;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;

import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Component
public class JwtProvider {

	private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;

	public String generateToken(Authentication auth) {

		UserMain userMain = (UserMain) auth.getPrincipal();

		List<String> roles = userMain.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		return Jwts.builder().setSubject(userMain.getUsername()).claim("roles", roles).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 180)).signWith(getSecret(secret)).compact();
	}

	public String getUserNameFromToken(String token) {
		return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody()
				.getSubject();
	}

	public boolean validateToken(String token) {

		if (Objects.isNull(token)) {
			return false;
		}
		try {
			Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			log.error("token mal formado");
		} catch (UnsupportedJwtException e) {
			log.error("token no soportado");
		} catch (ExpiredJwtException e) {
			log.error("token expirado");
		} catch (SignatureException e) {
			log.error("token falla en la firma !");
		}
		return false;
	}

	public String refreshToken(JwtDto jwtDto) throws ParseException {
		try {
			Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(jwtDto.getToken());
		} catch (ExpiredJwtException e) {
			JWT jwt = JWTParser.parse(jwtDto.getToken());
			JWTClaimsSet claims = jwt.getJWTClaimsSet();
			String nombreUsuario = claims.getSubject();
			List<String> roles = (List<String>) claims.getClaim("roles");

			return Jwts.builder().setSubject(nombreUsuario).claim("roles", roles).setIssuedAt(new Date())
					.setExpiration(new Date(new Date().getTime() + expiration)).signWith(getSecret(secret)).compact();
		}
		return null;
	}

	private Key getSecret(String secret) {
		byte[] secretBytes = Decoders.BASE64URL.decode(secret);
		return Keys.hmacShaKeyFor(secretBytes);
	}

}
