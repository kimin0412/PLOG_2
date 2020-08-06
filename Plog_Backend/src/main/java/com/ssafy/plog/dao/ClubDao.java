package com.ssafy.plog.dao;

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
	
	
}
