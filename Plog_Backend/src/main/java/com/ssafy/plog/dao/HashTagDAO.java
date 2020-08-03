package com.ssafy.plog.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.Hashtag;

@Repository
public interface HashTagDAO extends JpaRepository<Hashtag, Integer>{

	@Query(value = "select * from hashtag where h_name = ?1 ", nativeQuery=true)
	Hashtag findByName(String tag);

	@Query(value = "select * from hashtag where h_id = ?1 ", nativeQuery=true)
	Hashtag findByHId(int phHashtag);

}
