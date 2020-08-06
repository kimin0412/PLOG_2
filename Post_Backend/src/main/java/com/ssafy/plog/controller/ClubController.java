package com.ssafy.plog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plog.dto.Club;
import com.ssafy.plog.service.ClubService;

@CrossOrigin("*")
@RestController
public class ClubController {
	
	@Autowired
	ClubService gService;
	
	@GetMapping("/club/list")
	public List<Club> clubList(@RequestParam int uId) { 
    	return gService.getClubList(uId);
	}
	
	@GetMapping("/club/monthList")
	public List<Club> monthScheduleListByClub(@RequestParam int uId) { 
    	return gService.getClubList(uId);
	}
}