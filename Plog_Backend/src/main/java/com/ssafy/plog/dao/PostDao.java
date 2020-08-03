package com.ssafy.plog.dao;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.Post;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
	
	List<Post> findBypUser(int pUser);

	@Query(value = "select * from post where pUser = ?1 and DATE(pDate) = ?2", nativeQuery=true)
	List<Post> findBypDate(int pUser, LocalDate pDate);	
	
	@Query(value = "select * from post where p_user = ?1 and DATE(p_date) = ?2 ", nativeQuery=true)
	List<Post> findBypDate2(int pUser, LocalDate pDate);	
	
	@Query(value = "select * from post where p_user = ?1 and p_title LIKE %?2%", nativeQuery=true)
	List<Post> findBypUserAndpTitle(int pUser, String pTitle);
	
	@Query(value = "select * from post where pUser = ?1 and pSchedule = ?2", nativeQuery=true)
	List<Post> findBypUserAndpSchedule(int pUser, int pSchedule);

	@Transactional
	int deleteBypId(int pId);

	Post findBypId(int pId);

	@Query(value = "select * from post where pUser = ?1 and pCategory = ?2", nativeQuery=true)
	List<Post> findBypCategory(int pUser, int pCategory);

	@Query(value = "select * from post where p_user = ?1 and p_date like %?2% ", nativeQuery=true)
	List<Post> findByPdateMonth(int sId, String sDate);

	@Query(value = "select * from post where p_schedule = ?1 ", nativeQuery=true)
	List<Post> selectPostsbySchedule(String sId);

	@Transactional
	@Query(value = "select max(p_id) from post where p_user = ?1 ", nativeQuery=true)
	int getLatelyPId(int sId);

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO post (p_title, p_content, p_user, p_schedule, p_category) VALUES (?1, ?2, ?3, ?4, ?5) ", nativeQuery=true)
	int insertPost(String title, String content, int uid, int schedule, int category);	

}
