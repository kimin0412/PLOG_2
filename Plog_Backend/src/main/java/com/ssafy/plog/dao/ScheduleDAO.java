package com.ssafy.plog.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Schedule;

@Repository
public interface ScheduleDAO extends JpaRepository<Schedule, Integer>{

	@Query(value = "select * from schedule where (s_Startdate like %?1% or s_Enddate like %?1% ) and (s_user = ?2 or s_club in (select uc_club from user_club where uc_user = ?2 ) ) ", nativeQuery=true)
	public List<Schedule> selectByMonth(String monthAndYear, int sId);

	@Query(value = "select * from schedule where date(?1) between date(s_Startdate) and date(s_EndDate) and s_user = ?2 ", nativeQuery=true)
	public List<Schedule> getDailySchedule(String sDate, int sId);

	@Query(value = "select * from post where p_date like %?1% ", nativeQuery=true) //date로 찾아야해
	public List<Post> getPost(String sDate);

	@Query(value = "select * from schedule where s_id = ?1 and s_user = ?2 ", nativeQuery=true)
	public Schedule findBySId(String sId, int sUser);

//	@Query(value = "UPDATE schedule SET s_name = ?2 , s_content = ?3 , s_startdate = ?4 , s_enddate = ?5 WHERE s_id= ?1 ", nativeQuery=true)
//	public void update(Schedule schedule);

	@Modifying
	@Transactional
	@Query(value = "UPDATE schedule SET s_name = ?2 , s_content = ?3 , s_startdate = ?4 , s_enddate = ?5 WHERE s_user = ?6 and s_id = ?1 ", nativeQuery=true)
	public int update(int s_Id, String s_Name, String s_Content, String s_Startdate, String s_Enddate, int s_user);

	@Query(value = "select * from schedule where (s_Startdate like %?1% or s_Enddate like %?1% ) and s_club = ?2 ", nativeQuery=true)
	public List<Schedule> selectByGroupMonth(String monthAndYear, int clId);

	@Query(value = "select * from schedule where date(?1) between date(s_Startdate) and date(s_EndDate) and s_club = ?2 ", nativeQuery=true)
	public List<Schedule> getDailyClubSchedule(String sDate, int sClub);

	@Query(value = "select * from schedule where s_user = ?1 ", nativeQuery=true)
	public List<Schedule> getScheduleBySUser(int uid);

	@Modifying
	@Transactional
	@Query(value = "update schedule set s_user = ?2 where s_user = ?1 ", nativeQuery=true)
	public void updateSUser(int uId, int hostId);

	@Modifying
	@Transactional
	@Query(value = "delete from schedule where s_club = ?1 ", nativeQuery=true)
	public void deleteByClub(int groupId);

	@Query(value = "select * from schedule where s_id = (select p_schedule from post where p_id = ?1 ) ", nativeQuery=true)
	public Schedule findScheduleByPost(int pId);
}
