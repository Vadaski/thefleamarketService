package com.litavadaski.fleamarket.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.Entity.Goods;
import com.litavadaski.fleamarket.repository.GoodsRepository;

@Service
public class GoodsService implements GoodsInterface{
	@Autowired 
	GoodsRepository repo;
	@Autowired
	TokenService service;
	
	private static final Logger log = LoggerFactory.getLogger(GoodsService.class);

	@Override
	public Response<Boolean> ReleaseGoods(Goods goods,String token) {
		Response<Boolean> response = service.checkAccessToken(token);
		if(!response.getValue()) {
			log.debug("无效的token");
			return response;
		}
		repo.save(goods);
		response.setStatus(true);
		response.setValue(true);
		log.info("发布成功");
		return response;
	}

	@Override
	public Response<Boolean> deleteGoods(int goodsId, String token) {
		Response<Boolean> response = service.checkAccessToken(token);
		if(!response.getValue()) {
			log.debug("无效的token");
			return response;
		}
		if (!repo.existsById(goodsId)) {
			log.debug("删除失败，无效的id");
			response.setStatus(false);
			response.setValue(false);
			response.setErrormessage("不存在此商品，请重新确认");
			return response;
		}
		repo.deleteById(goodsId);
		response.setStatus(true);
		response.setValue(true);
		log.info("删除成功");
		return response;
	}

	@Override
	public Response<Boolean> updateGoods(Goods goods,String token) {
		Response<Boolean> response = service.checkAccessToken(token);
		if(!response.getValue()) {
			log.debug("更新失败");
			return response;
		}
		repo.save(goods);
		response.setStatus(true);
		response.setValue(true);
		log.info("更新成功");
		return response;
	}

	@Override
	public Response<List<Goods>> findGoodsByName(String name) {
		List<Goods> list = repo.findByName(name);
		Response<List<Goods>> response = new Response<>();
		response.setValue(list);
		response.setStatus(true);
		log.info("查找成功");
		return response;
	}

	@Override
	public Response<List<Goods>> findGoodsByTag(String tag) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<String> getUrl() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response<List<Goods>> getGoodsList(int sellerId, String token) {
		Response<List<Goods>> response = new Response<>();
		Response<Boolean> boolR = service.checkAccessToken(token);
		if(!boolR.getStatus()) {
			response.setErrormessage(boolR.getErrormessage());
			log.debug("无效的token");
			return response;
		}
		response.setValue(repo.findBySellerId(sellerId));
		log.info("查询成功");
		return response;
	}
}
