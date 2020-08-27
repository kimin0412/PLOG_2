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
	@Query(value = "update post set p_category = ?2 where p_id = ?1 ", nativeQuery=true)
	void updatePost(int pId, int pCategory);

	@Modifying
	@Transactional
	@Query(value = "update category set c_name = ?2 where c_id = ?1 ", nativeQuery=true)
	void updateCategory(int cId, String cName);

	@Query(value = "select * from category where c_club = ?1 ", nativeQuery=true)
	List<Category> findAllByCClub(int cClub);

	@Modifying
	@Transactional
	@Query(value = "delete from category where c_club = ?1 ", nativeQuery=true)
	void deleteByClub(int groupId);

	@Modifying
	@Transactional
	@Query(value = "update category set c_user = ?2 where c_user = ?1 and c_club = ?3 ", nativeQuery=true)
	void updateByCUser(int uId, int hostId, int groupId);

	@Query(value = "select * from category where c_id = ( select p_category from post where p_id = ?1 ) ", nativeQuery=true)
	Category findCategoryByPost(int pid);
}
