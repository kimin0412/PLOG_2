package com.ssafy.plog.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.Post;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {

	
	List<Post> findBypUser(int pUser);

	@Query(value = "select * from post where pUser = ?1 and DATE(pDate) = ?2", nativeQuery=true)
	List<Post> findBypDate(int pUser, LocalDate pDate);	
	
	@Query(value = "select * from post where pUser = ?1 and pSchedule = ?2", nativeQuery=true)
	List<Post> findBypUserAndpSchedule(int pUser, int pSchedule);

	boolean deleteBypId(int pId);

	Post findBypId(int pId);

	@Query(value = "select * from post where pUser = ?1 and pCategory = ?2", nativeQuery=true)
	List<Post> findBypCategory(int pUser, int pCategory);

	
}
