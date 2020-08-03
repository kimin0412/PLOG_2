package com.ssafy.plog.service;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.plog.dao.ScheduleDAO;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Schedule;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleDAO sdao;

	@Override
	public void insertSchedule(Schedule schedule) {
		System.out.println(schedule.toString());
		sdao.save(schedule);
	}

	@Override
	public List<Schedule> getMonthScheduleList(String date) {
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
		List<Schedule> sList = sdao.selectByMonth(monthAndYear);
		
		
		return sList;
	}

	@Override
	public void updateSchedule(Schedule schedule) {
		sdao.delete(schedule);
		sdao.save(schedule);
	}

	@Override
	public void deleteSchedule(Schedule schedule) {
		sdao.save(schedule);
	}

	@Override
	public List<Schedule> getDailyScheduleList(String sDate) {
		return sdao.getDailySchedule(sDate);
	}

	@Override
	public List<Post> getPost(Schedule schedule) {
		return sdao.getPost(schedule.getS_Id());
	}
}