package com.jwt.employee.security;

/**
 * This class contains the JWT Token Settings.
 * 
 * @author Ankur Kumar
 *
 */
public class JwtTokenSettings {
	private static final long tokenExpirationTime = 864_000_000;
	//private static final long tokenExpirationTime = 60000;
	private static final String tokenIssuer = "Aavas";

//	private static final String TokenSigningKey = "Bellurbis-29-March-2017-Aavas-Java_Developer";

	private static final String TokenSigningKey = "bellurbis";

//	private static final String TokenSigningKey = "ankitkumar";

	private static final String bearerTokenType = "Bearer";

	public static long getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public static String getTokenIssuer() {
		return tokenIssuer;
	}

	public static String getTokenSigningKey() {
		return TokenSigningKey;
	}

	public static String getBearerTokenType() {
		return bearerTokenType;
	}
}
