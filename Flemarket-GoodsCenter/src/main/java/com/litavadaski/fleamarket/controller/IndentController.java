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
import com.litavadaski.fleamarket.Entity.Indent;
import com.litavadaski.fleamarket.Service.IndentService;

@RestController
@RequestMapping(path="/Indent")
public class IndentController {
	@Autowired
	IndentService service;
	
	private static final Logger log = LoggerFactory.getLogger(IndentController.class);

	@PostMapping
	public Response<Indent> createIndent(@RequestBody Indent indent,@RequestParam  String token){
		log.info("成功捕获请求");
		return service.createIndent(indent, token);
	}
	
	@DeleteMapping
	public Response<Boolean> deleteIndent(@RequestParam int id,@RequestParam String token){
		log.info("成功捕获请求");
		return service.deleteIndent(id, token);
	}
	
	@GetMapping
	public Response<Indent> findIndentById(@RequestParam int id,@RequestParam String token){
		log.info("成功捕获请求");
		return service.findIndentById(id, token);
	}
	
	@GetMapping(path="myIndents")
	public Response<List<Indent>> findMyIndentList(@RequestParam int bid,@RequestParam String token){
		log.info("成功捕获请求");
		return service.findMyIndentList(bid, token);
	}
	
	@PutMapping(path="position")
	public Response<Boolean> updatePosition(@RequestParam int id,@RequestParam String position){
		log.info("成功捕获请求");
		return service.updatePosition(id, position);
	}
	
	@PutMapping(path="status")
	public Response<Boolean> updateStatus(@RequestParam int id,@RequestParam String status){
		log.info("成功捕获请求");
		return service.updateStatus(id, status);
	}
	
	@GetMapping(path="status")
	public Response<String> checkStatus(@RequestParam int id,@RequestParam String token){
		log.info("成功捕获请求");
		return service.checkStatus(id, token);
	}
}
