package com.ssafy.plog.service;

import java.util.List;

import com.ssafy.plog.dto.Club;
import com.ssafy.plog.dto.Schedule;

public interface ClubService {

	List<Club> getClubList(int uId);

	List<Schedule> getMonthScheduleList(int uId, int clId, String sDate);

	

}