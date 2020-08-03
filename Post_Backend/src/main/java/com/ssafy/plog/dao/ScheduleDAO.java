package com.ssafy.plog.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Schedule;

@Repository
public interface ScheduleDAO extends JpaRepository<Schedule, Integer>{

	@Query(value = "select * from schedule where s_Startdate like %?1% or s_Enddate like %?1%", nativeQuery=true)
	public List<Schedule> selectByMonth(String monthAndYear);

	@Query(value = "select * from schedule where date(?1) between date(s_Startdate) and date(s_EndDate)", nativeQuery=true)
	public List<Schedule> getDailySchedule(String sDate);

	@Query(value = "select * from post where p_schedule = ?1", nativeQuery=true)
	public List<Post> getPost(int s_Id);
}
