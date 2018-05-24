package com.litavadaski.fleamarket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.litavadaski.fleamarket.security.HTTPBearerAuthorizeAttribute;

@EnableJpaRepositories
@SpringBootApplication

public class FlemarketUserCenterApplication {
	
//	@Bean  
//    public FilterRegistrationBean  basicFilterRegistrationBean() {  
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
//        HTTPBasicAuthorizeAttribute httpBasicFilter = new HTTPBasicAuthorizeAttribute();  
//        registrationBean.setFilter(httpBasicFilter);  
//        List<String> urlPatterns = new ArrayList<String>();  
//        urlPatterns.add("/user/getuser");  
//        registrationBean.setUrlPatterns(urlPatterns);  
//        return registrationBean;  
//    }  
      
    @Bean  
    public FilterRegistrationBean jwtFilterRegistrationBean(){  
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();  
        HTTPBearerAuthorizeAttribute httpBearerFilter = new HTTPBearerAuthorizeAttribute();  
        registrationBean.setFilter(httpBearerFilter);  
        List<String> urlPatterns = new ArrayList<String>();  
        urlPatterns.add("/user/getusers");  
        registrationBean.setUrlPatterns(urlPatterns);  
        return registrationBean;  
    }  
	public static void main(String[] args) {
		SpringApplication.run(FlemarketUserCenterApplication.class, args);
	}
}
