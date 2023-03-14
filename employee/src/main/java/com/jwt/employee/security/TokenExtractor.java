package com.jwt.employee.security;

/**
 * This interface is use for Token Extractor
 * 
 * @author Ankur Kumar
 *
 */
public interface TokenExtractor {
	public String extract(String payload);
}
