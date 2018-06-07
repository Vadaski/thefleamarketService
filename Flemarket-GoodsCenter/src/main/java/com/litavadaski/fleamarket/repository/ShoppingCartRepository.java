package com.litavadaski.fleamarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litavadaski.fleamarket.Entity.ShoppingCart;
@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>{
	public List<ShoppingCart> findByOwnerid(int Ownerid);
	
}
