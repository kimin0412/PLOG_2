package com.ssafy.plog.dao;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.Post;

@Repository
public interface PostDao extends JpaRepository<Post, Integer> {
	
	List<Post> findBypUser(int pUser, Sort sort);

	@Query(value = "select * from post where pUser = ?1 and DATE(pDate) = ?2 order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findBypDate(int pUser, LocalDate pDate);	
	
	@Query(value = "select * from post where p_user = ?1 and DATE(p_date) = ?2 order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findBypDate2(int pUser, LocalDate pDate);	
	
	@Query(value = "select * from post where p_user = ?1 and p_title LIKE %?2% order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findBypUserAndpTitle(int pUser, String pTitle);
	
	@Query(value = "select * from post where p_user = ?1 and p_content LIKE %?2% order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findBypUserAndpContent(int uid, String pContent);	
	
	@Query(value = "select * from post where p_user = ?1 and (p_content LIKE %?2% or p_title LIKE %?2%) order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findBypUserAndpTitleOrpContent(int uid, String word);
	
	@Query(value = "select * from post where p_id in (select ph_post "
													+ "from post_hashtag "
													+ "where ph_user = ?1 and ph_hashtag in (select h_id "
																						+ "from hashtag "
																						+ "where h_name like %?2%)) order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findBypUserAndpTag(int uid, String word);
	
	
	@Query(value = "(select * from post p1 where p1.p_user = ?1 and p1.p_title LIKE %?2%) UNION (select * from post p2 where p2.p_id in (select ph_post from post_hashtag where ph_user = ?1 and ph_hashtag in (select h_id from hashtag where h_name like %?2%))) order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findBypUserAndpTitleOrpTag(int uid, String word);
	
	@Query(value = "select * from post p where (p.p_user = ?1 and p.p_content LIKE %?2%) or (p.p_id in (select ph_post from post_hashtag where ph_user = ?1 and ph_hashtag in (select h_id from hashtag where h_name like %?2%))) order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findBypUserAndpContentOrpTag(int uid, String word);

	@Query(value = "select * from post where (p_user = ?1 and (p_content LIKE %?2% or p_title LIKE %?2%)) or (p_id in (select ph_post from post_hashtag where ph_user = ?1 and ph_hashtag in (select h_id from hashtag where h_name like %?2%))) order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findBypUserAndpTitleOrpContentOrpTag(int uid, String word);
			
	
	@Query(value = "select * from post where pUser = ?1 and pSchedule = ?2 order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findBypUserAndpSchedule(int pUser, int pSchedule);

	@Transactional
	int deleteBypId(int pId);

	Post findBypId(int pId);

	@Query(value = "select * from post where pUser = ?1 and pCategory = ?2 order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findBypCategory(int pUser, int pCategory);

	@Query(value = "select * from post where p_user = ?1 and p_date like %?2% order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> findByPdateMonth(int sId, String sDate);

	@Query(value = "select * from post where p_schedule = ?1 order by p_bookmark desc, p_date desc", nativeQuery=true)
	List<Post> selectPostsbySchedule(String sId);

	@Transactional
	@Query(value = "select max(p_id) from post", nativeQuery=true)
	int getLatelyPId();

	@Modifying
	@Transactional
	@Query(value = "INSERT INTO post (p_id, p_title, p_content, p_user, p_schedule, p_category, p_color) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7) ", nativeQuery=true)
	int insertPost(int pid, String title, String content, int uid, int schedule, int category, String color);

	@Query(value = "select * from post where p_category = ?1 ", nativeQuery = true)
	List<Post> findPostByCategory(int cid);

	@Modifying
	@Transactional
	@Query(value = "update post set p_category = 1 where p_id = ?1 ", nativeQuery=true)
	void updatePCategory(int pId);

	@Query(value = "select * from post where p_user = ?1 and p_bookmark = 1 order by p_bookmark desc, p_date desc", nativeQuery=true)
    List<Post> findBypBookmark(int uid);


}
