package com.litavadaski.fleamarket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.Entity.Goods;
import com.litavadaski.fleamarket.Entity.ShoppingCart;
import com.litavadaski.fleamarket.Service.ShoppingCartService;


@RestController
@RequestMapping(path="/ShoppingCart")
public class ShoppingCartController {
	@Autowired
	ShoppingCartService service;
	
	private static final Logger log = LoggerFactory.getLogger(ShoppingCartController.class);
	
	@PostMapping
	public Response<ShoppingCart> createSC(@RequestParam Goods goods,@RequestParam int ownerid){
		log.info("成功捕获该请求");
		return service.createSC(goods, ownerid);
	}
	
	@DeleteMapping
	public Response<Boolean> deleteSC(int id){
		log.info("成功捕获该请求");
		return service.deleteSC(id);
	}
	
	@GetMapping
	public Response<List<ShoppingCart>> findMySC(int ownderId){
		log.info("成功捕获该请求");
		return service.findMySC(ownderId);
	}
	
	@DeleteMapping(path="/all")
	public Response<Boolean> deleteAll(int OwnerId){
		log.info("成功捕获该请求");
		return service.deleteAll(OwnerId);
	}
}
