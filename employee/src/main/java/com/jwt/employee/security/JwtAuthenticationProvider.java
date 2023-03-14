package com.jwt.employee.security;

import com.jwt.employee.core.AavasConstants;
import com.jwt.employee.entity.Employee;
import com.jwt.employee.model.ServiceResult;
import com.jwt.employee.model.TokenDetails;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * This is used for authenticate the token.
 * 
 * @author Ankur Kumar
 *
 */
@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
	/**
	 * Instance of {@link Logger}
	 */
//	private static Logger logger = Logger.getLogger(JwtAuthenticationProvider.class);
	/**
	 * Instance of {@link MessageSource}
	 */
	@Autowired
	private MessageSource messageSource;
	/**
	 * Instance of {@link UserService}
	 */
//	@Autowired
//	private UserService userService;

	/**
	 * This method is used for authenticate the token.
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//		logger.debug("Inside JwtAuthenticationProvider in authenticate method :::::::");
		String token = (String) authentication.getCredentials();
		Jws<Claims> jwsClaims = parseClaims(token, JwtTokenSettings.getTokenSigningKey());
		String subject = jwsClaims.getBody().getId();
		Claims claims = jwsClaims.getBody();
		Long id = 0l;
		try {
			id = Long.parseLong(subject);
		} catch (NumberFormatException e) {
			id = 0l;
			throw new BadCredentialsException(messageSource.getMessage("msg.token.invalid", null, Locale.US));
		}
		String USER_TOKEN_SCOPE = (String) claims.get(AavasConstants.SCOPE);
		/**
		 * if token scope is not for authentication then token is invalid.
		 */
		if (USER_TOKEN_SCOPE == null || !USER_TOKEN_SCOPE.equals(ServiceResult.UserTokenScope.AUTH.getValue())) {
			throw new BadCredentialsException(messageSource.getMessage("msg.token.invalid", null, Locale.US));
		}
		/**
		 * get user info based on id.
		 */
//		UserLoginHistory loginData = userService.getUserLoginDataBasedOnIdAndToken(id, token);
		/**
		 * if there is no user then throw bad credentials exception.
		 */
//		if (loginData == null) {
//			throw new BadCredentialsException(messageSource.getMessage("msg.token.invalid", null, Locale.US));
//		}
//		Employee user = loginData.getUserId();
//		if (ServiceResult.UserStatus.DELETED.getValue().equals(user.getStatus().getKey())) {
//			throw new BadCredentialsException(messageSource.getMessage("msg.user.deleted", null, Locale.US));
//		}
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(ServiceResult.UserRole.PARTNER.getValue()));
		UserContext context = UserContext.create(subject, authorities);
		return new JwtAuthenticationToken(context, context.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	}

	/**
	 * Parses and validates JWT Token signature.
	 * 
	 * @param token
	 *            {@link String}
	 * @param signingKey
	 *            {@link String}
	 * @throws BadCredentialsException
	 * @throws
	 * 
	 */
	private Jws<Claims> parseClaims(String token, String signingKey) {
		try {
			return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(token);
		} catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | SignatureException ex) {
//			logger.error("Inside JwtAuthenticationProvider, Invalid JWT Token", ex);
			throw new BadCredentialsException(messageSource.getMessage("msg.token.invalid", null, Locale.US));
		} catch (ExpiredJwtException expiredEx) {
//			logger.info("Inside JwtAuthenticationProvider, JWT Token is expired", expiredEx);
			throw new BadCredentialsException(messageSource.getMessage("msg.token.expired", null, Locale.US));
		}
	}

	/**
	 * This method is used for extracting the user details from token.
	 * 
	 * @param token
	 *            {@link String}
	 * @return firmId {@link Long}
	 */
	public TokenDetails getUserDetailsFromToken(String token) {
//		logger.debug("Inside JwtAuthenticationProvider in getUserDetailsFromToken method :::::::");
		TokenDetails tokenDetails = new TokenDetails();
		Jws<Claims> jwsClaims = parseClaims(token, JwtTokenSettings.getTokenSigningKey());
		String id = jwsClaims.getBody().getId();
		if (id != null) {
			tokenDetails.setId(Long.valueOf(id));
		}
		return tokenDetails;
	}

}
