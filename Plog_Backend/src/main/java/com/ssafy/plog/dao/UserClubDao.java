package com.ssafy.plog.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.User_club;
import com.ssafy.plog.models.User;

@Repository
public interface UserClubDao extends JpaRepository<User_club, Integer> {

	@Query(value = " select * from user_club where uc_user = ?1 ", nativeQuery=true)
	List<User_club> getClubByUcUser(int uId);

	@Query(value = " select * from user_club where uc_club = ?1 and uc_host = 1", nativeQuery=true)
	User_club getUCByUcClub(int clId);

	@Modifying
	@Transactional
	@Query(value = "delete from user_club where uc_user = ?1 and uc_club = ?2 ", nativeQuery=true)
	void deleteByUserAndClub(int uId, int groupId);

	@Modifying
	@Transactional
	@Query(value = "delete from user_club where uc_club = ?1 ", nativeQuery=true)
	void deleteByClub(int groupId);
	
	@Query(value = " select uc_user from user_club where uc_club = ?1 ", nativeQuery=true)
	List<Integer> getUsers(int clId);

	
	
	
}
