package com.ssafy.plog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.Club;

@Repository
public interface ClubDao extends JpaRepository<Club, Integer> {

	@Query(value = "select * from club where cl_id = ?1 ", nativeQuery=true)
	Club getClubByClId(int ucClub);

	@Query(value = "select max(cl_id) from club ", nativeQuery=true)
	int getLatelyClId();

	@Query(value = "select cl_color from club where cl_id = ?1 ", nativeQuery=true)
	String getColorbyClId(int clId);
	
	@Query(value = "select * from club where cl_name like %?1% and cl_id not in (select uc_club from user_club where uc_user = ?2)", nativeQuery=true)
	List<Club> findByclName(String searchword, int uId);

	@Query(value = "select cl_password from club where cl_id = ?1", nativeQuery=true)
	String selectclPasswordByclId(int clId);
	
	
}
