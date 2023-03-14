package com.jwt.employee.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.employee.core.AavasConstants;
import com.jwt.employee.model.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;

@Component
public class UserAuthorization {

	/**
	 * Instance of {@link Logger}
	 */
//	private static Logger logger = Logger.getLogger(JwtAuthenticationProvider.class);
	/**
	 * Instance of {@link MessageSource}
	 */
	@Autowired
	private MessageSource messageSource;

	public Authentication validateTokenWithAuthServer(String token) {
//		logger.debug("Inside JwtAuthenticationProvider in validateTokenWithAuthServer method :::::::");
		try {
			System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,TLSv1");
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add(AavasConstants.AUTHORIZATION, token);
			HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//			String requestUrl = "http://localhost:8800/AuthServer/token";
			String requestUrl = "http://localhost:8800/authenticate";
//			String requestUrl = "http://localhost:7676/api/auth/signin";
			ResponseEntity<String> response = restTemplate.exchange(requestUrl, HttpMethod.GET, entity, String.class);
			System.out.println("response " + response);
			ObjectMapper mapper = new ObjectMapper();
			ServiceResult result = mapper.readValue(response.getBody(), ServiceResult.class);
			if (result.getCode() == ServiceResult.StatusCode.SUCCESS.getStatusCode()) {
				return mapper.convertValue(result.getResult(), AbstractAuthenticationToken.class);
			} else if (result.getCode() == ServiceResult.StatusCode.UNAUTHORIZED.getStatusCode()) {
				String msg = null;
				if (result.getResultmsg() != null && result.getResultmsg().size() > 0) {
					msg = result.getResultmsg().get(0).getMsgdescription();
				} else {
					msg = messageSource.getMessage("msg.token.invalid", null, Locale.US);
				}
				throw new BadCredentialsException(msg);
			}
			System.out.println("aaaaaaaaaaaaaaaankur abjwa");
		} catch (Exception e) {
			e.printStackTrace();
			if (e instanceof BadCredentialsException) {
				throw new BadCredentialsException(e.getMessage());
			} else {
				throw new BadCredentialsException(messageSource.getMessage("msg.token.invalid", null, Locale.US));
			}
		}
		throw new BadCredentialsException(messageSource.getMessage("msg.token.invalid", null, Locale.US));
	}
}
