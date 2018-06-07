package com.litavadaski.fleamarket.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.Entity.Indent;
import com.litavadaski.fleamarket.repository.IndentRepository;

@Service
public class IndentService implements IndentInterface{

	@Autowired
	private IndentRepository repository;
	@Autowired
	TokenService service;
	
	private static final Logger log = LoggerFactory.getLogger(IndentService.class);

	
	@Override
	public Response<Indent> createIndent(Indent indent, String token) {
		Response<Boolean> response = service.checkAccessToken(token);
		Response<Indent> response2 = new Response<>();
		if(!response.getValue()) {
			log.debug("无效的token");
			response2.setErrormessage(response.getErrormessage());
			return response2;
		}
		repository.save(indent);
		response2.setValue(indent);
		response2.setStatus(true);
		log.info("成功创建订单,id: "+indent.getId());
		return response2;
	}

	@Override
	public Response<Boolean> deleteIndent(int id, String token) {
		Response<Boolean> response = service.checkAccessToken(token);
		if(!response.getValue()) {
			log.debug("无效的token");
			return response;
		}
		if(!repository.existsById(id)) {
			response.setErrormessage("不存在该订单");
			log.debug("不存在该订单");
			return response;
		}
		repository.deleteById(id);
		response.setStatus(true);
		response.setValue(true);
		log.info("成功删除该订单，id: "+id);
		return response;
	}

	@Override
	public Response<Indent> findIndentById(int id, String token) {
		Response<Boolean> response = service.checkAccessToken(token);
		Response<Indent> response2 = new Response<>();
		if(!response.getValue()) {
			log.debug("无效的token");
			response2.setErrormessage(response.getErrormessage());
			return response2;
		}
		if(!repository.existsById(id)) {
			response2.setErrormessage("不存在该订单");
			log.debug("不存在该订单");
			return response2;
		}
        Optional<Indent> indent =  repository.findById(id);
		response2.setValue(indent.get());
		response2.setStatus(true);
		log.info("成功找到订单，id："+id);
		return response2;
	}

	@Override
	public Response<List<Indent>> findMyIndentList(int bid, String token) {
		Response<Boolean> response = service.checkAccessToken(token);
		Response<List<Indent>> response2 = new Response<>();
		if(!response.getValue()) {
			log.debug("无效的token");
			response2.setErrormessage(response.getErrormessage());
			return response2;
		}
		response2.setValue(repository.findIndentByBid(bid));
		response2.setStatus(true);
		log.info("查询成功");
		return response2;
	}

	@Override
	public Response<Boolean> updatePosition(int id, String position) {
		Response<Boolean> response = new Response<>();
		if(!repository.existsById(id)) {
			response.setErrormessage("不存在该订单");
			return response;
		}
		Optional<Indent> indent = repository.findById(id);
		indent.get().setPosition(position);
		repository.save(indent.get());
		response.setStatus(true);
		response.setValue(true);
		log.info("位置信息更新成功，id："+id);
		return response;
	}

	@Override
	public Response<Boolean> updateStatus(int id, String status) {
		Response<Boolean> response = new Response<>();
		if(!repository.existsById(id)) {
			response.setErrormessage("不存在该订单");
			return response;
		}
		Optional<Indent> indent = repository.findById(id);
		indent.get().setStatus(status);
		repository.save(indent.get());
		response.setStatus(true);
		response.setValue(true);
		log.info("更新订单状态成功,id: "+id);
		return response;
	}

	@Override
	public Response<String> checkStatus(int id, String token) {
		Response<Boolean> response = service.checkAccessToken(token);
		Response<String> response2 = new Response<>();
		if(!response.getValue()) {
			log.debug("无效的token");
			response2.setErrormessage(response.getErrormessage());
			return response2;
		}
		if(!repository.existsById(id)) {
			response2.setErrormessage("不存在该订单");
			log.debug("不存在该订单");
			return response2;
		}
		Optional<Indent> indent = repository.findById(id);
		response2.setValue(indent.get().getStatus());
		response2.setStatus(true);
		log.info("查询订单状态成功,id："+id);
		return response2;
	}	
}
