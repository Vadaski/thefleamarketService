package com.litavadaski.fleamarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litavadaski.fleamarket.Entity.Goods;
@Repository
public interface GoodsRepository extends JpaRepository<Goods, Integer> {
	//找到自己发布的商品
	public List<Goods> findBySellerId(int SellerId); 
	//找到一种商品
	public List<Goods> findByName(String name);
	//获取某个标签的商品
//	public List<Goods> findByTag(String Tag);
	
}
