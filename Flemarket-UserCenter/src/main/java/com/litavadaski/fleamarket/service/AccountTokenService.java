package com.litavadaski.fleamarket.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.entity.Account;
import com.litavadaski.fleamarket.repository.AccountRepository;
import com.litavadaski.fleamarket.security.AccessToken;
import com.litavadaski.fleamarket.security.JwtHelper;

import io.jsonwebtoken.Claims;
@Service
public class AccountTokenService {
	@Autowired
	private AccountRepository repo;
	
	@Value("${client.clientId}")
	private String clientId;
	@Value("${client.base64Secret}")
	private String base64Secret;
	@Value("${client.name}")
	private String name;
	@Value("${client.expiresSecond}")
	private long expiresSecond;
	
	public AccessToken getAccessToken(String email,String audience)  
    {    
        try  
        {  
        	 List<Account> accountList = repo.findByEmail(email);
            //拼装accessToken  
            String accessToken = JwtHelper.createJWT(
            		accountList.get(0).getEmail(),
            		String.valueOf(accountList.get(0).getId()),   
                    audience, 
                    name,
                    expiresSecond*1000,
                    base64Secret);           
            //返回accessToken  
            AccessToken accessTokenEntity = new AccessToken();  
            accessTokenEntity.setAccess_token(accessToken);  
            accessTokenEntity.setExpires_in(expiresSecond);  
            accessTokenEntity.setToken_type("bearer");  
            return accessTokenEntity;
        }  
        catch(Exception ex)  
        {  
           return null;
        }		
    }  
	
	public Response<Boolean> checkAccessToken(String token) {
		Response<Boolean> response = new Response<>();
		try {
			Claims claims = JwtHelper.parseJWT(token, base64Secret);
			System.out.println("---------------------------------------");
			System.out.println("claims.toString:"+claims.toString());
			System.out.println("claims.getSubject:"+claims.getSubject());
			System.out.println("claims.getExpiration:"+claims.getExpiration());
			System.out.println("claims.getAudience:"+claims.getAudience());
			System.out.println("claims.getId:"+claims.getId());
			System.out.println("claims.getIssuer:"+claims.getIssuer());
			System.out.println("claims.getIssuedAt:"+claims.getIssuedAt());
			System.out.println("claims.getNotBefore:"+claims.getNotBefore());
			System.out.println("---------------------------------------");
//			检查数字签名颁发者
			if(!claims.getIssuer().equals("litavadaski")) {
				response.setErrormessage("错误的签名");
				response.setValue(false);
				return response;
			}
			//检查超时
			if(new Date().after(claims.getExpiration())) {
				response.setErrormessage("数字签名超时");
				response.setValue(false);
				return response;
			}
			response.setValue(true);
			response.setStatus(true);
		} catch (Exception e) {
			
		}
		return response;
	}
}  

