package com.ssafy.plog.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email); // 추가

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

	@Transactional
	Optional<User> deleteById(int id);
	
	@Query(value = " select * from user where u_id = ?1 ", nativeQuery=true)
	User findbyClId(int ucUser);
	
	@Query(value = " select * from user where u_id in (select uc_user from user_club where uc_club = ?1 order by uc_user)", nativeQuery=true)
	List<User> findclMemberByclId(int clId);
}