package com.jwt.employee.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * This is simple implementaion of Authentication Token.
 * 
 * @author Ankur Kumar
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JwtAuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = 2877954820905567501L;
	/**
	 * Instance of {@link String}
	 */
	private String token;
	/**
	 * Instance of {@link UserContext}
	 */
	private UserContext userContext;

	public JwtAuthenticationToken() {
		super(null);
	}
	
	public JwtAuthenticationToken(String token) {
		super(null);
		this.token = token;
		this.setAuthenticated(false);
	}

	public JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.eraseCredentials();
		this.userContext = userContext;
		super.setAuthenticated(true);
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		if (authenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}
		super.setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return this.userContext;
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.token = null;
	}
}
