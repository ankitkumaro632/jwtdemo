package com.example.jwtdemo.service;

import com.example.jwtdemo.entity.UserNew;
import com.example.jwtdemo.model.ServiceResult;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String TokenSigningKey = "bellurbis";

    private static final String tokenIssuer = "Aavas";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(TokenSigningKey).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

//    public String generateToken(String username, String phoneNumber, String dob, Integer roleId, String login_source, String email ) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("username", username);
//        claims.put("phonenumber", phoneNumber);
//        claims.put("dob", dob);
//        claims.put("roleId", roleId);
//        claims.put("login_source", login_source);
//        claims.put("email",email);
//
//        return createToken(claims, username, phoneNumber, dob, roleId, login_source, email);
//    }
public String generateToken(UserNew user, Integer roleId, String login_source ){
    Claims claims = Jwts.claims().setId(String.valueOf(user.getId()));
    claims.put("email", user.getEmail());
    claims.put("phoneNumber", user.getPhoneNumber());
    claims.put("dob", user.getDob());
    claims.put("scope", ServiceResult.UserTokenScope.AUTH.getValue());
    claims.put("login_source", login_source);
    claims.put("role", roleId);

    return createToken(claims, user, roleId, login_source);
}
      private String createToken(Map<String, Object> claims,UserNew user, Integer roleId, String login_source){
          return Jwts.builder().setClaims(claims).setIssuer(tokenIssuer).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 ))
                .signWith(SignatureAlgorithm.HS512, TokenSigningKey).compact();
      }

//    private String createToken(Map<String, Object> claims, String subject, String username, String phoneNumber, Integer roleId, String login_source, String email) {
//
//        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//                .signWith(SignatureAlgorithm.HS256, TokenSigningKey).compact();
//    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
