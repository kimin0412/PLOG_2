package com.ssafy.plog.service;

import java.util.List;

import com.ssafy.plog.dto.Club;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Schedule;
import com.ssafy.plog.models.User;

public interface ClubService {

	List<Club> getClubList(int uId);

	List<Schedule> getMonthScheduleList(int clId, String sDate);

	void insertClub(Club club);

	Club getClub(int clId);

	User getHost(int clId);
	
	List<Club> selectByTitle(String searchword, int uId);

	boolean joinClub(int uId, int clId, String password);
	
	List<User> selectMemberbyclId(int clId);

	void updateClub(Club club);

	void deleteMember(int uId, int hostId, int groupId);

	void deleteClub(int groupId);
}