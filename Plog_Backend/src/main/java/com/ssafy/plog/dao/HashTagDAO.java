package com.ssafy.plog.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.Hashtag;

@Repository
public interface HashTagDAO extends JpaRepository<Hashtag, Integer>{

	@Query(value = "select * from hashtag where h_name = ?1 ", nativeQuery=true)
	Hashtag findByName(String tag);

	@Query(value = "select * from hashtag where h_id = ?1 ", nativeQuery=true)
	Hashtag findByHId(int phHashtag);

	@Modifying
	@Transactional
	@Query(value = "delete from post_hashtag where ph_post = ?1 ", nativeQuery=true)
	void deletePostHashtag(int pId);

}
