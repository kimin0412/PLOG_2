package com.ssafy.plog.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.plog.dao.ClubDao;
import com.ssafy.plog.dao.UserClubDao;
import com.ssafy.plog.dto.Club;
import com.ssafy.plog.dto.User_club;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired
	ClubDao cdao;

	@Autowired
	UserClubDao ucdao;
	
	@Override
	public List<Club> getClubList(int uId) {
		List<User_club> ucs = ucdao.getClubByUcUser(uId);
		List<Club> clubs = new LinkedList<>();
		for (int i = 0, size = ucs.size(); i < size; i++) {
			clubs.add(cdao.getClubByClId(ucs.get(i).getUcClub()));
		}
		return clubs;
	}
	
	
}
