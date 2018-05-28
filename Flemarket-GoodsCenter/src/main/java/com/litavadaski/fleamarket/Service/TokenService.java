package com.litavadaski.fleamarket.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.Entity.Goods;
import com.litavadaski.fleamarket.repository.GoodsRepository;
import com.litavadaski.fleamarket.security.AccessToken;
import com.litavadaski.fleamarket.security.JwtHelper;

import io.jsonwebtoken.Claims;
@Service
public class TokenService {
	@Autowired
	private GoodsRepository repo;
	
	@Value("${client.clientId}")
	private String clientId;
	@Value("${client.base64Secret}")
	private String base64Secret;
	@Value("${client.name}")
	private String name;
	@Value("${client.expiresSecond}")
	private long expiresSecond;
	
	public Response<Boolean> checkAccessToken(String token) {
		Response<Boolean> response = new Response<>();
		try {
			Claims claims = JwtHelper.parseJWT(token, base64Secret);
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

