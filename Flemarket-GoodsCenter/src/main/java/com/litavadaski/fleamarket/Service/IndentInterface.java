package com.litavadaski.fleamarket.Service;

import java.util.List;

import com.litavadaski.fleamarket.Response;
import com.litavadaski.fleamarket.Entity.Indent;

public interface IndentInterface {
	
	//创建订单
	public Response<Indent> createIndent(Indent indent,String token);
	//删除订单
	public Response<Boolean> deleteIndent(int id,String token);
	//通过id找到一个订单
	public Response<Indent> findIndentById(int id,String token);
	//找到一个人的全部订单
	public Response<List<Indent>> findMyIndentList(int bid,String token);
	//更新物流信息
	public Response<Boolean> updatePosition(int id,String position);
	//更新订单信息
	public Response<Boolean> updateStatus(int id,String status);
	//检查一个订单状态
	public Response<String> checkStatus(int id,String token);
	
}
