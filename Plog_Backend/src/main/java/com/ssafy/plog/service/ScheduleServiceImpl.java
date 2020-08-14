package com.ssafy.plog.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.plog.dao.PostDao;
import com.ssafy.plog.dao.ScheduleDAO;
import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Schedule;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDAO sdao;
	@Autowired
	private PostDao pdao;

	@Override
	public void insertSchedule(Schedule schedule) {
		sdao.save(schedule);
	}

	@Override
	public List<Schedule> getMonthScheduleList(String date, int sId) {
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
		
		//내가 작성
		List<Schedule> sList = sdao.selectByMonth(monthAndYear, sId);
		
		//그룹이 작성
		
		return sList;
	}
	
	@Override
	public List<Integer> getMonthScheduleListPicker(String sDate, int sId) {
		//sDate는 2020-06 형태
		
		List<Schedule> sList = sdao.selectByMonth(sDate, sId);
		
		//몇월인지
		int curMonth = sDate.charAt(6) - '0';
		if(sDate.charAt(5) > '0') {
			curMonth += (sDate.charAt(5) - '0')*10;
		}
		
		int[] dayofMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		boolean[] isAnySchedule = new boolean[dayofMonth[curMonth-1]+1];
		int sMonth, eMonth, sDay, eDay;
		Schedule cur;
		
		
		for (int i = 0, size = sList.size(); i < size; i++) {
			//s_startDate가 curMonth인지
			cur = sList.get(i);
			String[] s_splitDate = cur.getsStartdate().split(" ");
			
			if(s_splitDate[0].charAt(5) == '0') {
				sMonth = s_splitDate[0].charAt(6) - '0';
			}else {
				sMonth = s_splitDate[0].charAt(6) - '0' + 10;
			}
			
			
			//s_endDate가 curMonth인지 아닌지
			String[] e_splitDate = cur.getsEnddate().split(" ");
			
			if(e_splitDate[0].charAt(5) == '0') {
				eMonth = e_splitDate[0].charAt(6) - '0';
			}else {
				eMonth = e_splitDate[0].charAt(6) - '0' + 10;
			}
			
			
			if(s_splitDate[0].charAt(8) == '0') {
				sDay = s_splitDate[0].charAt(9) - '0';
			}else {
				sDay = (s_splitDate[0].charAt(8) - '0') * 10 + s_splitDate[0].charAt(9) - '0';
			}
			
			if(e_splitDate[0].charAt(8) == '0') {
				eDay = e_splitDate[0].charAt(9) - '0';
			}else {
				eDay = (e_splitDate[0].charAt(8) - '0') * 10 + e_splitDate[0].charAt(9) - '0';
			}
			
			if(sMonth == curMonth) {
				if(eMonth == curMonth) {
					for (int j = sDay; j < eDay+1; j++) {
						isAnySchedule[j] = true;
					}
				}else {
					for (int j = sDay; j < dayofMonth[curMonth-1]+1; j++) {
						isAnySchedule[j] = true;
					}
				}
			}else {
				if(eMonth == curMonth) {
					for (int j = 1; j < eDay+1; j++) {
						isAnySchedule[j] = true;
					}
				}else {
					for (int j = 1; j < dayofMonth[curMonth-1]+1; j++) {
						isAnySchedule[j] = true;
					}
				}
			}
		}
		
		List<Integer> scheduleDayNumber = new LinkedList<>();
		
		for (int i = 1; i < dayofMonth[curMonth-1]+1; i++) {
			if(isAnySchedule[i]) {
				scheduleDayNumber.add(i);
			}
		}
		
		return scheduleDayNumber;
	}

	@Override
	public void updateSchedule(Schedule schedule) {
		sdao.update(schedule.getsId(), schedule.getsName(), schedule.getsContent(), schedule.getsStartdate(), schedule.getsEnddate(), schedule.getsUser());
	}

	@Modifying
	@Transactional
	@Override
	public void deleteSchedule(Schedule schedule) {
		sdao.delete(schedule);
	}

	@Override
	public List<Schedule> getDailyScheduleList(String sDate, int sId) {
		return sdao.getDailySchedule(sDate, sId);
	}

	@Override
	public List<Post> getPost(String sDate, int sId) {
		LocalDate date = LocalDate.parse(sDate, DateTimeFormatter.ISO_DATE);
		return pdao.findBypDate2(sId, date);
	}

	@Override
	public List<Integer> getMonthPostListPicker(String sDate, int sId) {
		List<Post> monthPost = pdao.findByPdateMonth(sId, sDate);
		
		//몇월인지
		int curMonth = sDate.charAt(6) - '0';
		if(sDate.charAt(5) > '0') {
			curMonth += (sDate.charAt(5) - '0')*10;
		}
				
		int[] dayofMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		boolean[] isAnySchedule = new boolean[dayofMonth[curMonth]+1];
		
		for (int i = 0, size = monthPost.size(); i < size; i++) {
			isAnySchedule[monthPost.get(i).getpDate().getDayOfMonth()] = true;
		}
		
		List<Integer> postDays = new LinkedList<>();
		
		for (int i = 1; i < dayofMonth[curMonth] + 1; i++) {
			if(isAnySchedule[i]) {
				postDays.add(i);
			}
		}
		
		return postDays;
	}

	@Override
	public Schedule selectById(String sId, int sUser) {
		return sdao.findBySId(sId, sUser);
	}

	@Override
	public List<Post> selectPostById(String sId) {
		return pdao.selectPostsbySchedule(sId);
	}

	@Override
	public List<Schedule> getDailyClubScheduleList(String sDate, int sClub) {
		return sdao.getDailyClubSchedule(sDate, sClub);
	}

	@Override
	public Schedule getScheduleByPost(int pId) {
		return sdao.findScheduleByPost(pId);
	}
}