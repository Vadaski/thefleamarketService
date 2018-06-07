package com.litavadaski.fleamarket.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.Entity.Goods;
import com.litavadaski.fleamarket.Entity.ShoppingCart;
import com.litavadaski.fleamarket.repository.ShoppingCartRepository;

@Service
public class ShoppingCartService implements ShoppingCartImterface {
	@Autowired
	private ShoppingCartRepository repo;

	private static final Logger log = LoggerFactory.getLogger(ShoppingCartService.class);

	
	
	@Override
	public Response<ShoppingCart> createSC(Goods goods, int ownerid) {
		Response<ShoppingCart> response = new Response<>();
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setGoodsId(goods.getId());
		shoppingCart.setName(goods.getName());
		shoppingCart.setOwnerid(ownerid);
		shoppingCart.setPrice(goods.getPrice());
		response.setValue(shoppingCart);
		response.setStatus(true);
		repo.save(shoppingCart);
		log.debug("成功添加购物车");
		return response;
	}

	@Override
	public Response<Boolean> deleteSC(int id) {
		Optional<ShoppingCart> shoppingCart = repo.findById(id);
		Response<Boolean> response = new Response<>();
		if(shoppingCart.get()==null) {
			log.info("删除失败，不存该该购物车");
			response.setErrormessage("删除失败，不存在该购物车");
			response.setValue(false);
			return response;
		}
		repo.deleteById(id);
		log.debug("删除成功");
		response.setStatus(true);
		response.setValue(true);
		return response;
	}

	@Override
	public Response<List<ShoppingCart>> findMySC(int ownderId) {
		Response<List<ShoppingCart>> response = new Response<>();
		response.setValue(repo.findByOwnerid(ownderId));
		response.setStatus(true);
		return response;
	}

	@Override
	public Response<Boolean> deleteAll(int OwnerId) {
		Response<Boolean> response = new Response<>();
		Response<List<ShoppingCart>> Rlist = findMySC(OwnerId);
		for(int i = 0;i<Rlist.getValue().size();i++) {
			repo.deleteById(Rlist.getValue().get(i).getId());
			log.info("成功删除");
		}
		response.setStatus(true);
		response.setValue(true);
		return response;
	}

}
