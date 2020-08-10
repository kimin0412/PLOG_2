package com.ssafy.plog.service;

import java.util.List;

import com.ssafy.plog.dto.Club;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Schedule;

public interface ClubService {

	List<Club> getClubList(int uId);

	List<Schedule> getMonthScheduleList(int clId, String sDate);

	void insertClub(Club club);

	Club getClub(int clId);

	String getHost(int clId);
	
	List<Club> selectByTitle(String searchword, int uId);

	boolean joinClub(int uId, int clId, String password);
}