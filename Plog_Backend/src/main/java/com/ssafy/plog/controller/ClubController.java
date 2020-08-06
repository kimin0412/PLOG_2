package com.ssafy.plog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plog.dto.BasicResponse;
import com.ssafy.plog.dto.Club;
import com.ssafy.plog.dto.Schedule;
import com.ssafy.plog.service.ClubService;

@CrossOrigin("*")
@RestController
public class ClubController {
	
	@Autowired
	ClubService gService;
	
	static int groupID;
	
	@GetMapping("/club/list")
	public List<Club> clubList(@RequestParam int uId) { 
    	return gService.getClubList(uId);
	}
	
	@GetMapping("/club/monthList")
	public List<Schedule> monthScheduleListByClub(@RequestParam int clId, String sDate) { 
    	return gService.getMonthScheduleList(clId, sDate);
	}
	
	@PostMapping("/club/insert")
	public Object insertClub(@RequestBody Club club) { 
		final BasicResponse result = new BasicResponse();
    	gService.insertClub(club);
    	
    	result.status = true;
        result.data = "success";	
    		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/club")
	public Club getClub(@RequestParam int clId) { 
    	return gService.getClub(clId);
	}
	
	@GetMapping("/club/host")
	public String getHost(@RequestParam int clId) { 
    	return gService.getHost(clId);
	}
}