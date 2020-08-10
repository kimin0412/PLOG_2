package com.ssafy.plog.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.Post_Hashtag;

@Repository
public interface PostHashtagDAO extends JpaRepository<Post_Hashtag, Integer>{

	@Query(value = "select * from post_hashtag where ph_user = ?1 and ph_post = ?2", nativeQuery=true)
	List<Post_Hashtag> getPhById(int uid, int pid);
	
	@Query(value = "select ph_post from post_hashtag where ph_user = ?1 and ph_hashtag = ?2", nativeQuery=true)
	List<Integer> getPhByHash(int uid, int hid);

	@Query(value = "select * from post_hashtag where ph_user = ?1 ", nativeQuery=true)
	List<Post_Hashtag> getPhbyUser(int uId);

	@Modifying
	@Transactional
	@Query(value = "delete from post_hashtag where ph_post = ?1 ", nativeQuery=true)
	void deleteAllInPost(int pid);

}
