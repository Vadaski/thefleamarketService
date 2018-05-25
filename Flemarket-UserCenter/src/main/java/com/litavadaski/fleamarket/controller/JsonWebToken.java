package com.litavadaski.fleamarket.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litavadaski.fleamarket.service.JsonWebTokenService;

@RestController
@RequestMapping("oauth/token")
public class JsonWebToken {
	@Autowired
	private JsonWebTokenService service;

	@PostMapping
    public Object getAccessToken(@RequestParam String email,@RequestParam String password,@RequestParam String audience)  
    {
		return service.getAccessToken(email, password, audience);
    }
}  