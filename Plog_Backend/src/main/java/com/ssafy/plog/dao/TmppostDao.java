package com.ssafy.plog.dao;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Tmppost;

@Repository
public interface TmppostDao extends JpaRepository<Tmppost, Integer> {

	List<Tmppost> findBytpUser(int tpUser);

	@Query(value = "select * from tmppost where tp_user = ?1 and DATE(tp_date) = ?2", nativeQuery=true)
	List<Tmppost> findBytpDate(int tpUser, LocalDate date);

	@Query(value = "select * from tmppost where tp_user = ?1 and tp_title LIKE %?2%", nativeQuery=true)
	List<Tmppost> findBytpUserAndtpTitle(int tpUser, String tpTitle);
	
	@Query(value = "select * from tmppost where tp_user = ?1 and tp_content LIKE %?2%", nativeQuery=true)
	List<Tmppost> findBytpUserAndtpContent(int tpUser, String tpContent);
	
	@Query(value = "select * from tmppost where tp_user = ?1 and (tp_content LIKE %?2% or tp_title LIKE %?2%)", nativeQuery=true)
	List<Tmppost> findBytpUserAndtpTitleOrtpContent(int uid, String word);
	
	Tmppost findBytpId(int tpId);

	@Transactional
	int deleteBytpId(int tpId);

	
	
}
