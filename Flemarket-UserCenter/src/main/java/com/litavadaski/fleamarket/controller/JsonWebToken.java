package com.litavadaski.fleamarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.entity.Account;
import com.litavadaski.fleamarket.repository.AccountRepository;
import com.litavadaski.fleamarket.security.AccessToken;
import com.litavadaski.fleamarket.security.JwtHelper;

@RestController
@RequestMapping("oauth/token")
public class JsonWebToken {
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

	@PostMapping
    public Object getAccessToken(@RequestParam String email,@RequestParam String password,@RequestParam String audience)  
    {  
        Response<AccessToken> response= new Response<>();  
        try  
        {  
            List<Account> accountList = repo.findByEmail(email);
           
            if(accountList.isEmpty())        
            {  
               response.setErrormessage("该账号不存在，请重新输入");
                return response;  
            }  
              
            //验证用户名密码  
         
            if (!accountList.get(0).getPassword().equals(password))  
            {  
                response.setErrormessage("输入的密码有误");
                return response;
            }  
              
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
            response.setValue(accessTokenEntity);
            response.setStatus(true);
            return response;
        }  
        catch(Exception ex)  
        {  
            response.setErrormessage("登陆失败");
        }
		return response;  
    }  
}  