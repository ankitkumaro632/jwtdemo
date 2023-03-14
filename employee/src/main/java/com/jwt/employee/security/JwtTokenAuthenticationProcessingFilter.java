package com.jwt.employee.security;

import com.jwt.employee.core.AavasConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This filter is used for authenticate the token.
 * 
 * @author Ankur Kumar
 *
 */
public class JwtTokenAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
	/**
	 * Instance of {@link AuthenticationFailureHandler}
	 */
	private final AuthenticationFailureHandler failureHandler;

	/**
	 * Instance of {@link TokenExtractor}
	 */
	private final TokenExtractor tokenExtractor;

	@Autowired
	public JwtTokenAuthenticationProcessingFilter(String url, AuthenticationFailureHandler failureHandler,
                                                  TokenExtractor tokenExtractor, AuthenticationManager authManager) {
		super(url);
		this.failureHandler = failureHandler;
		this.tokenExtractor = tokenExtractor;
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		String tokenPayload = request.getHeader(AavasConstants.AUTHORIZATION);
		String token = tokenExtractor.extract(tokenPayload);
		return getAuthenticationManager().authenticate(new JwtAuthenticationToken(token));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authentication);
		SecurityContextHolder.setContext(context);
		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		SecurityContextHolder.clearContext();
		failureHandler.onAuthenticationFailure(request, response, failed);
	}
}
