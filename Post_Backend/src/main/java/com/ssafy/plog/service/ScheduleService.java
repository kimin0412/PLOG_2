package com.ssafy.plog.service;

import java.util.List;

import javax.persistence.EntityManager;

import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Schedule;

public interface ScheduleService {

	void insertSchedule(Schedule schedule);
	
	List<Schedule> getMonthScheduleList(String date);

	void updateSchedule(Schedule schedule);

	void deleteSchedule(Schedule schedule);

	List<Schedule> getDailyScheduleList(String sDate);
	
	List<Post> getPost(Schedule schedule);

}