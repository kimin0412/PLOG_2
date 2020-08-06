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

	//@Query(value = " Select uuc.u_email from (Select u_id, u_email from user_club as uc left Join user as u on uc.uc_user = u.u_id) as uuc where u_id = ?1 ", nativeQuery=true)
	//String getHost(int clId);
	
	
}
