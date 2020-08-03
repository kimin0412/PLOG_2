package com.ssafy.plog.controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Tmppost;
import com.ssafy.plog.service.PostService;
import com.ssafy.plog.service.TmppostService;

@RestController
@CrossOrigin("*")
@RequestMapping("/tp")
public class TmppostController {
	
	@Autowired
	TmppostService service;
	
	@GetMapping("/")
	public Tmppost selectBypId(@RequestParam(required = false) final int tpId) {
		Tmppost tmppost = service.selectByTpid(tpId);
    	return tmppost;
    }
	
	@GetMapping("/list/all")
	public List<Tmppost> selectAll(@RequestParam(required = false) final int uId) {
		List<Tmppost> tposts = service.selectAll(uId);
    	return tposts;
    }
	
	@GetMapping("/list/search")
	public Object selectByTitle(@RequestParam(required = false) final int uid, @RequestParam final String searchword) {	//@RequestParam final String searchType,
		List<Tmppost> posts = service.selectByTitle(uid, searchword);
    	return new ResponseEntity<>(posts, HttpStatus.OK);
    }
	
	@PostMapping("/")
    public Object registPost(@RequestBody Tmppost tp) {
		System.out.println(tp.getTpTitle());
		System.out.println(tp.getTpContent());
   
		service.registPost(tp);
		
    	return new ResponseEntity<>("success", HttpStatus.OK);
    }
	
	 @PutMapping("/")
	 public Object updatePost(@RequestBody Tmppost tp) {
	    	ResponseEntity response = null;
	    	if(service.updatePost(tp)) {    	
	    	    response = new ResponseEntity<String>("success", HttpStatus.OK);
	    	} else {
	    		response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    	}    	
	    	return response;
	    }
	
	 
	 @DeleteMapping("/")
	 public Object delete(@RequestParam(required = false) final int tpId) {
	    	
	    	ResponseEntity response = null;
	    	if(service.deleteByTpid(tpId)) {
	    	    response = new ResponseEntity<String>("success", HttpStatus.OK);
	    	} else {
	    		response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    	}    	
	    	return response;
	    }
	 
	 
			 
}
