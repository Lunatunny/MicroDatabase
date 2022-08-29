package com.pups.project.authentication;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationFilter implements Filter {
	
	private JWTUtility jwtUtility = new JWTUtility();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//Get authorization token
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI();
		
		// check JWT token
		String authheader = req.getHeader("authorization");
		if (authheader != null && authheader.length() > 7 && authheader.startsWith("Bearer")) {
			String jwt_token = authheader.substring(7, authheader.length());
			if (jwtUtility.verifyToken(jwt_token)) {
				String request_scopes = jwtUtility.getScopes(jwt_token);
				String[] scopeArray = request_scopes.split(" ");
				for(String scopeInToken:scopeArray) {
					System.out.print(scopeInToken);
					System.out.print(uri);
					if (uri.startsWith(scopeInToken)) {
						// continue on to api
						chain.doFilter(request, response);
						return;
					}
				}
			}
		}
		
		// reject request and return error instead of data
		HttpServletResponse responseHttp = (HttpServletResponse) response;
		responseHttp.sendError(HttpServletResponse.SC_FORBIDDEN, "failed authentication");
		
	}
	
}
