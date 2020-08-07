package com.ssafy.plog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plog.dto.BasicResponse;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Schedule;
import com.ssafy.plog.service.ScheduleService;

@CrossOrigin("*")
@RestController
public class ScheduleController {
	
	@Autowired
	ScheduleService sService;
	
    @RequestMapping("/schedule/insert")
	public Object insert(@RequestBody Schedule schedule) {
    	
    	final BasicResponse result = new BasicResponse();
    	sService.insertSchedule(schedule);
    	result.status = true;
        result.data = "success";	
    		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    
    @GetMapping("/schedule/monthList")
	public List<Schedule> monthList(@RequestParam String sDate, int sId) { //date의 s_name에 year, month를 저장해서 온다.
    	List<Schedule> sList = sService.getMonthScheduleList(sDate, sId);
		return sList;
	}
    
    @GetMapping("/schedule/monthSchedulePicker")
	public List<Integer> monthListPicker(@RequestParam String sDate, int sId) { 
		return sService.getMonthScheduleListPicker(sDate, sId);
	}
    
    @GetMapping("/schedule/monthPostPicker")
	public List<Integer> monthPostPicker(@RequestParam String sDate, int sId) { 
		return sService.getMonthPostListPicker(sDate, sId);
	}
    
    @GetMapping("/schedule/dayList")
	public List<Schedule> dayList(@RequestParam String sDate, int sId) { 
    	List<Schedule> sList = sService.getDailyScheduleList(sDate, sId);
    	System.out.println(sList.size());
		return sList;
	}
    
    @GetMapping("/schedule/club/dayList")
	public List<Schedule> dayListInClub(@RequestParam int sClub, String sDate ) { 
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	List<Schedule> sList = sService.getDailyScheduleList(sDate, sClub);
    	
		return sList;
	}
    
    
    @RequestMapping("/schedule/update")
	public Object update(@RequestBody Schedule schedule) {
    	
    	final BasicResponse result = new BasicResponse();
    	
    	sService.updateSchedule(schedule);
    	result.status = true;
        result.data = "success";	
    		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @RequestMapping("/schedule/delete")
	public Object delete(@RequestBody Schedule schedule) {
    	
    	final BasicResponse result = new BasicResponse();
    	
    	sService.deleteSchedule(schedule);
    	result.status = true;
        result.data = "success";	
    		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @GetMapping("/schedule/dayPost")
	public List<Post> getPost(@RequestParam String sDate, int sId) {	
		return sService.getPost(sDate, sId);
	}
    
    @GetMapping("/schedule/select")
	public Schedule getScheduleById(@RequestParam String sId, int sUser) {	
		return sService.selectById(sId, sUser);
	}
    
    @GetMapping("/schedule/selectPost")
	public List<Post> getPostsById(@RequestParam String sId) {
		return sService.selectPostById(sId);
	}
}