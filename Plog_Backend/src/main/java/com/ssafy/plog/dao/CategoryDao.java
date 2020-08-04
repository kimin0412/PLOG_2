package com.ssafy.plog.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {

	@Query(value = "select * from category where c_user = ?1 ", nativeQuery=true)
	List<Category> findAllByCUser(int uid);

	@Modifying
	@Transactional
	@Query(value = "update Post set p_category = ?2 where p_id = ?1 ", nativeQuery=true)
	void updatePost(int pId, int pCategory);
}
