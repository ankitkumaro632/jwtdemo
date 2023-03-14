package com.jwt.employee.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jwt.employee.model.ServiceMessage;
import com.jwt.employee.model.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * This handler is used for handling response for authentication failure.
 * 
 * @author Ankur Kumar
 *
 */
@Component
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {
	/**
	 * Instance of {@link ObjectMapper}.
	 */
	private final ObjectMapper mapper;
	/**
	 * Instance of {@link MessageSource}.
	 */
	@Autowired
	private MessageSource messageSource;

	@Autowired
	public JwtAuthenticationFailureHandler(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException e) throws IOException, ServletException {
		response.setStatus(HttpStatus.OK.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		if (e instanceof BadCredentialsException) {
			String msg = e.getMessage();
			if (msg != null && msg.equalsIgnoreCase("AbstractUserDetailsAuthenticationProvider.badCredentials")) {
				mapper.writeValue(response.getWriter(), new ServiceResult(ServiceResult.StatusCode.UNAUTHORIZED,
						new ServiceMessage(messageSource, "msg.bad.credentials"), null));
			} else {
				mapper.writeValue(response.getWriter(),
						new ServiceResult(ServiceResult.StatusCode.UNAUTHORIZED, new ServiceMessage(e.getMessage()), null));
			}
		} else if (e instanceof AuthenticationServiceException) {
			mapper.writeValue(response.getWriter(),
					new ServiceResult(ServiceResult.StatusCode.UNAUTHORIZED, new ServiceMessage(e.getMessage()), null));
		} else if (e instanceof SessionAuthenticationException) {
			mapper.writeValue(response.getWriter(), new ServiceResult(ServiceResult.StatusCode.UNAUTHORIZED,
					new ServiceMessage(messageSource, "msg.authentication.failed")));
		}
		mapper.writeValue(response.getWriter(), new ServiceResult(ServiceResult.StatusCode.UNAUTHORIZED,
				new ServiceMessage(messageSource, "msg.authentication.failed")));
	}
}
