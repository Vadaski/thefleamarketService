package com.litavadaski.fleamarket.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtHelper {
	
	private static final Logger log = LoggerFactory.getLogger(JwtHelper.class);

	//解析WebToken
	public static Claims parseJWT(String jsonWebToken,String base64Security) {
		try {
			Claims claims = Jwts.parser()
					.setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
					.parseClaimsJws(jsonWebToken).getBody();
			return claims;
		} catch (Exception e) {
			log.debug(e.getMessage());
			return null;
		}
	}
	
	public static String createJWT(String email, String userId,   
            String audience, String issuer, long TTLMillis, String base64Security)   
    {  
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;  
           
        long nowMillis = System.currentTimeMillis();  
        Date now = new Date(nowMillis);  
           
        //生成签名密钥  
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(base64Security);  
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());  
           
          //添加构成JWT的参数  
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")  
                                        .claim("unique_email", email)  
                                        .claim("userid", userId)  
                                        .setIssuer(issuer)  
                                        .setAudience(audience)  
                                        .signWith(signatureAlgorithm, signingKey);  
         //添加Token过期时间  
        if (TTLMillis >= 0) {  
            long expMillis = nowMillis + TTLMillis;  
            Date exp = new Date(expMillis);  
            builder.setExpiration(exp).setNotBefore(now);  
        }  
           
         //生成JWT  
        return builder.compact();  
    }   
}  

