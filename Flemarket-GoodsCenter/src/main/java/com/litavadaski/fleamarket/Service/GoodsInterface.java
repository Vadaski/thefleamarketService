package com.litavadaski.fleamarket.Service;

import java.util.List;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.Entity.Goods;

public interface GoodsInterface {
	
	//创建商品
	public Response<Boolean> ReleaseGoods(Goods goods,String token);
	//删除商品
	public Response<Boolean> deleteGoods (int goodsId,String token);
	//更新商品
	public Response<Boolean> updateGoods(Goods goods,String token);
	//通过商品名称查找商品
	public Response<List<Goods>> findGoodsByName(String name);
	//查找某个类型的商品
	public Response<List<Goods>> findGoodsByTag(String tag);
	//获取URL
	public Response<String> getUrl();
	//获取自己发布的商品列表
	public Response<List<Goods>> getGoodsList(int sellerId,String token);
}
