package com.ssafy.plog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.User_club;

@Repository
public interface UserClubDao extends JpaRepository<User_club, Integer> {

	@Query(value = " select * from user_club where uc_user = ?1 ", nativeQuery=true)
	List<User_club> getClubByUcUser(int uId);

	@Query(value = " select * from user_club where uc_club = ?1 and uc_host = 1", nativeQuery=true)
	User_club getUCByUcClub(int clId);

	@Query(value = " select u_email from user where u_id in (select uc_user from user_club where uc_club = ?1 order by uc_user)", nativeQuery=true)
	List<String> findclMemberByclId(int clId);

	
	
	
}
