package com.litavadaski.fleamarket.Service;

import java.util.List;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.Entity.Goods;
import com.litavadaski.fleamarket.Entity.ShoppingCart;

public interface ShoppingCartImterface {

	//向购物车中添加一个商品
	public Response<ShoppingCart> createSC(Goods goods,int Ownerid) ;
	
	//删除一个购物车商品
	public Response<Boolean> deleteSC(int id);
	
	//我的购物车
	public Response<List<ShoppingCart>> findMySC(int ownderId);
	
	//删除账户时删除我的所有购物车记录
	public Response<Boolean> deleteAll(int OwnerId);
}
