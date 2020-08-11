package com.ssafy.plog.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.plog.dao.ClubDao;
import com.ssafy.plog.dao.ScheduleDAO;
import com.ssafy.plog.dao.UserClubDao;
import com.ssafy.plog.dto.Club;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Schedule;
import com.ssafy.plog.dto.User_club;
import com.ssafy.plog.models.User;
import com.ssafy.plog.repository.UserRepository;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired
	ClubDao cdao;

	@Autowired
	UserClubDao ucdao;
	
	@Autowired
	ScheduleDAO sdao;
	
	@Autowired 
	UserRepository ur;
	
	@Override
	public List<Club> getClubList(int uId) {
		List<User_club> ucs = ucdao.getClubByUcUser(uId);
		List<Club> clubs = new LinkedList<>();
		for (int i = 0, size = ucs.size(); i < size; i++) {
			clubs.add(cdao.getClubByClId(ucs.get(i).getUcClub()));
		}
		return clubs;
	}

	@Override
	public List<Schedule> getMonthScheduleList(int clId, String date) {
		//'7월 2020'과 같은 형태를 '2020-07과 같은 형태로 만든다.
		//'7월'과 '2020'으로 분해
		String[] monthDateSplit = date.split(" ");
				
		//'7월'을 '7'과 '월'으로 분해 후 int형으로 변형
		int month = Integer.parseInt(monthDateSplit[0].substring(0, monthDateSplit[0].length()-1));
				
		//두자리면 그대로 두고 아니면 앞에 0을 붙여야한다.
		String monthAndYear;
		if(month < 10) {
			monthAndYear = monthDateSplit[1] + "-0" + month;
		}else {
			monthAndYear = monthDateSplit[1] + "-" + month;
		}
				
		//select * from schedule where s_startdate 
		List<Schedule> sList = sdao.selectByGroupMonth(monthAndYear, clId);
				
		return sList;
	}

	@Override
	public void insertClub(Club club) {
		//지정해야할 그룹 아이디를 가져온다. -> club을 생성한다. -> uc를 생성한며 호스트 지정 -> userclub을 생성한다.
		int clId = cdao.getLatelyClId()+1;
		
		int uid = club.getClId();
		club.setClId(clId);
		cdao.save(club);
		
		User_club uc = new User_club();
		uc.setUcClub(clId);
		uc.setUcHost(true);
		uc.setUcUser(uid);
		
		ucdao.save(uc);
	}

	@Override
	public Club getClub(int clId) {
		return cdao.getClubByClId(clId);
	}

	@Override
	public User getHost(int clId) {
		User_club uc = ucdao.getUCByUcClub(clId);
		User user = ur.findbyClId(uc.getUcUser());
		return user;
	}
	
	@Override
	public List<Club> selectByTitle(String searchword,int uId) {
		return cdao.findByclName(searchword, uId);
	}

	@Override
	public boolean joinClub(int uId, int clId, String clPassword) {
		if(clPassword.equals(cdao.selectclPasswordByclId(clId))){
			ucdao.save(new User_club(uId, clId, false));
			return true;
		}
		return false;
	}

	@Override
	public List<String> selectMemberbyclId(int clId) {
		
		return ucdao.findclMemberByclId(clId);
	}
	
}
