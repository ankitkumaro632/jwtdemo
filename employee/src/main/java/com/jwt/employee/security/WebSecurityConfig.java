package com.jwt.employee.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * This class is used for handling the security of web application.
 * 
 * @author Ankur Kumar
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * Instance of {@link AuthenticationFailureHandler}
	 */
	@Autowired
	private AuthenticationFailureHandler failureHandler;
	/**
	 * Instance of {@link JwtAuthenticationProvider}
	 */
	@Autowired
	private JwtAuthenticationProvider jwtAuthenticationProvider;
	/**
	 * Instance of {@link UserAuthorization}
	 */
	@Autowired
	private TokenExtractor tokenExtractor;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().antMatcher("/**").requestMatchers().and().authorizeRequests().antMatchers("/**")
				.authenticated().and().addFilterBefore(new JwtTokenAuthenticationProcessingFilter("/**", failureHandler,
						tokenExtractor, authenticationManager()), UsernamePasswordAuthenticationFilter.class);
		http.cors();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**");

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(jwtAuthenticationProvider);
	}

}