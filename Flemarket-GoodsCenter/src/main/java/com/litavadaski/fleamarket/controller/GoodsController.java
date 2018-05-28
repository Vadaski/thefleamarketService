package com.litavadaski.fleamarket.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.Entity.Goods;
import com.litavadaski.fleamarket.Service.GoodsService;

@RestController
@RequestMapping(path="/Goods")
public class GoodsController {
	
	@Autowired
	GoodsService service;
	
	private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

	@PostMapping
	public Response<Boolean> ReleaseGoods(@RequestBody Goods goods,@RequestParam String token){
		return service.ReleaseGoods(goods, token);
	}
	
	@DeleteMapping
	public Response<Boolean> deleteGoods(@RequestParam int goodsId,@RequestParam String token){
		return service.deleteGoods(goodsId, token);
	}
	
	@PutMapping
	public Response<Boolean> updateGoods(@RequestBody Goods goods,@RequestParam String token){
		return service.updateGoods(goods, token);
	}
	
	@GetMapping
	public Response<List<Goods>> findGoodsByName(@RequestParam String name){
		return service.findGoodsByName(name);
	}
	
	@GetMapping(path="/myGoods")
	public Response<List<Goods>> getGoodsList(@RequestParam int sellerId,@RequestParam String token){
		return service.getGoodsList(sellerId, token);
	}
	
	
}
