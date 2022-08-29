package com.pups.project.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtility {
	
	private String secret="121212";
	
	public DecodedJWT getToken(String token) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("jeff@austin.com")
		        .build();
		    return verifier.verify(token);
		} catch (JWTVerificationException exception){
			return null;
		}
	}
	
	public boolean verifyToken(String token) {
		DecodedJWT jwt = getToken(token);
		
		if (jwt == null) { return false; }
		return true;
	}
	
	public String getScopes(String token) {
		DecodedJWT jwt = getToken(token);
		
		if (jwt == null) { return null; }
		return jwt.getClaim("scopes").asString();
	}
}
