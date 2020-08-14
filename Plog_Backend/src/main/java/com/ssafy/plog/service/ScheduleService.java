package com.ssafy.plog.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Schedule;

public interface ScheduleService {

	void insertSchedule(Schedule schedule);
	
	List<Schedule> getMonthScheduleList(String date, int sId);

	void updateSchedule(Schedule schedule);

	void deleteSchedule(Schedule schedule);

	List<Schedule> getDailyScheduleList(String sDate, int sId);
	
	List<Post> getPost(String sDate, int sId);

	List<Integer> getMonthScheduleListPicker(String sDate, int sId);

	List<Integer> getMonthPostListPicker(String sDate, int sId);

	Schedule selectById(String sId, int sUser);

	List<Post> selectPostById(String sId);

	List<Schedule> getDailyClubScheduleList(String sDate, int sClub);

	Schedule getScheduleByPost(int pId);

}