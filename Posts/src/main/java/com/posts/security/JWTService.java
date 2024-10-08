package com.posts.security;

import java.security.Key;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

	private String secretKey = "63a94a7cc7954a798ce5b3d081fc3ae684eba8f2f722ff5f90018a579494bddb";

	private long jwtExpiration = 60000 * 30L;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

//	public String generateToken(Account userDetails) {
//		return generateToken(new HashMap<>(), userDetails);
//	}

//	public String generateToken(Map<String, Object> extraClaims, Account userDetails) {
//		return buildToken(extraClaims, userDetails, jwtExpiration);
//	}

	public long getExpirationTime() {
		return jwtExpiration;
	}

//	private String buildToken(Map<String, Object> extraClaims, Account userDetails, long expiration) {
//		return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
//				.claim("UserID", userDetails.getId())
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + expiration))
//				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
//	}

	public boolean isTokenValid(String token, String username) {
		return (username.equals(extractUsername(token))) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	public Integer extractUID(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.get("UserID",Integer.class);
	}
	
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	 public Collection<? extends GrantedAuthority> extractAuthorities(String token) {
	        Claims claims = extractAllClaims(token);
	        @SuppressWarnings("unchecked")
	        List<String> authorities = (List<String>) claims.get("Authorities");
	        return authorities.stream()
	                .map(SimpleGrantedAuthority::new)
	                .collect(Collectors.toList());
	    }
}
