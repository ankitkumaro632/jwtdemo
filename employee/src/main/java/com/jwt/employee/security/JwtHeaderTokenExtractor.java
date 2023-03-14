package com.jwt.employee.security;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * This class is used for extract the Authorization Token.
 * 
 * @author Ankur Kumar
 *
 */
@Component
public class JwtHeaderTokenExtractor implements TokenExtractor {
	/**
	 * Instance of {@link Logger}
//	 */
//	private static Logger logger = Logger.getLogger(JwtHeaderTokenExtractor.class);
	/**
	 * Instance of {@link String}
	 */
	public final String HEADER_PREFIX = "Bearer ";
	/**
	 * Instance of {@link MessageSource}
	 */
	@Autowired
	private MessageSource messageSource;

	@Override
	public String extract(String header) {
//		logger.debug("Inside JwtHeaderTokenExtractor in extract method :::::::");
		if (StringUtils.isBlank(header)) {
			throw new AuthenticationServiceException(
					messageSource.getMessage("msg.auth.header.not.found", null, Locale.US));
		}
		if (!header.startsWith(HEADER_PREFIX)) {
			throw new AuthenticationServiceException(
					messageSource.getMessage("msg.auth.header.not.bearer", null, Locale.US));
		}

		if (header.length() < HEADER_PREFIX.length()) {
			throw new AuthenticationServiceException(
					messageSource.getMessage("msg.auth.header.is.invalid", null, Locale.US));
		}
		return header.substring(HEADER_PREFIX.length(), header.length());
	}
}
