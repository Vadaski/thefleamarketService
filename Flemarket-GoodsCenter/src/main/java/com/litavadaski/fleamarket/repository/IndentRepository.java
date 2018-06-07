package com.litavadaski.fleamarket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litavadaski.fleamarket.Entity.Indent;

@Repository
public interface IndentRepository extends JpaRepository<Indent,	 Integer>{
	public List<Indent> findIndentByBid(int bid);
}
