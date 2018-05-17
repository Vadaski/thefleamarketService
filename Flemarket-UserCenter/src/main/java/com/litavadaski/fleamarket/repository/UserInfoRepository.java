package com.litavadaski.fleamarket.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.litavadaski.fleamarket.entity.UserInfo;

@Repository
public interface UserInfoRepository extends MongoRepository<UserInfo, Integer> {
	//返回用户名模糊查找
	Page<UserInfo> findAllByName(String name);
}
