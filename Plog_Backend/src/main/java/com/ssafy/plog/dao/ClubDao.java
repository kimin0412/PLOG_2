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
	
	
}
