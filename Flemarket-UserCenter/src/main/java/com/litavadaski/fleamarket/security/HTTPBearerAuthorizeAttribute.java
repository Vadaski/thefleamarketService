package com.litavadaski.fleamarket.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HTTPBearerAuthorizeAttribute implements Filter{  
	
	@Value("${client.clientId}")
	private String clientId;
	@Value("${client.base64Secret}")
	private String base64Secret;
	@Value("${client.name}")
	private String name;
	@Value("${client.expiresSecond}")
	private long expiresSecond;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,  
                filterConfig.getServletContext());
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		String auth = httpServletRequest.getHeader("Authorization");
		if ((auth != null) && (auth.length() > 7))  
        {  
            String HeadStr = auth.substring(0, 6).toLowerCase();  
            if (HeadStr.compareTo("bearer") == 0)  
            {  
                  
                auth = auth.substring(7, auth.length());   
                if (JwtHelper.parseJWT(auth,base64Secret) != null)  
                {  
                    chain.doFilter(request, response);  
                    return;  
                }  
            }  
        }
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;  
        httpResponse.setCharacterEncoding("UTF-8");    
        httpResponse.setContentType("application/json; charset=utf-8");   
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); 
        
        ObjectMapper mapper = new ObjectMapper();  
        
        httpResponse.getWriter().write(mapper.writeValueAsString("failed"));  
        return;  
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}  