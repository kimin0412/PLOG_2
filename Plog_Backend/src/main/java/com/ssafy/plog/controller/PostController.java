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

import com.ssafy.plog.dto.BasicResponse;
import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.service.PostService;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService service;
	
	@GetMapping("/")
	public Post selectBypId(@RequestParam(required = false) final int pId) {
		System.out.println();
		Post post = service.selectByPid(pId);
    	return post;
    }
	
	@GetMapping("/list/all")
	public List<Post> selectAll(@RequestParam(required = false) final int uid) {
		List<Post> posts = service.selectAll(uid);
    	return posts;
    }
	
	@GetMapping("/list/search")
	public Object selectByTitle(@RequestParam(required = false) final int uid, @RequestParam final String searchword,
			@RequestParam final Boolean c1, @RequestParam final Boolean c2, @RequestParam final Boolean c3) {
		boolean[] checklist = new boolean[3];
		checklist[0] = c1;
		checklist[1] = c2;
		checklist[2] = c3;
		List<Post> posts = service.selectByWord(uid, searchword, checklist);
    	return new ResponseEntity<>(posts, HttpStatus.OK);
    }
	
	@GetMapping("category")
	public Object getCategory(@RequestParam(required = false) final int uid) {

			List<Category> categorys = service.getCategory(uid);
			return new ResponseEntity<>(categorys, HttpStatus.OK);
	}
	
	@GetMapping("/list/category")
	public Object selectByCategory(@RequestParam(required = false) final int uid, 
							  @RequestParam(required = false) final int cId) {
    	
    	List<Post> posts = service.selectByCategory(uid, cId);
    	return new ResponseEntity<>(posts, HttpStatus.OK);
    }
	
	@GetMapping("/list/day")
	public Object selectByDay(@RequestParam(required = false) final int uid, 
							  @RequestParam(required = false) final LocalDate date) {
    	
    	List<Post> posts = service.selectByDay(uid, date);
    	return new ResponseEntity<>(posts, HttpStatus.OK);
    }
	
	@GetMapping("/list/shedule")
	public Object selectBySchedule(@RequestParam(required = false) final int uid,
								   @RequestParam(required = false) final int sId) {
    	
    	List<Post> posts = service.selectBySchedule(uid, sId);  	
    	return new ResponseEntity<>(posts, HttpStatus.OK);
    }
	
	@PostMapping("/")
    public Object registPost(@RequestBody Post post) {
		
		final BasicResponse result = new BasicResponse();
		//System.out.println(post.getpSchedule());
		
		if(post.getpSchedule() == 0) {
			post.setpSchedule(1);
		}
		
		if(post.getpCategory() == 0) {
			post.setpCategory(1);
		}
		result.temp = service.registPost(post);
		result.data = "success";
		
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	 @PutMapping("/")
	 public Object updatePost(@RequestBody Post post) {
		 	if(post.getpSchedule() == 0) {
				post.setpSchedule(1);
			}
	    	ResponseEntity response = null;
	    	if(service.updatePost(post)) {    	
	    	    response = new ResponseEntity<String>("success", HttpStatus.OK);
	    	} else {
	    		response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    	}    	
	    	return response;
	    }
	
	 
	 @DeleteMapping("/")
	 public Object delete(@RequestParam(required = false) final int pId) {
	    	
	    	ResponseEntity response = null;
	    	if(service.deleteByPid(pId)) {
	    	    response = new ResponseEntity<String>("success", HttpStatus.OK);
	    	} else {
	    		response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    	}    	
	    	return response;
	    }
	 
	 
	 @GetMapping("/bookmark")
	 public Object bookmark(@RequestParam final int uid, @RequestParam final int pid) {
		 ResponseEntity response = null;
	    	if(service.bookmarkByPid(pid)) {
	    	    response = new ResponseEntity<String>("success", HttpStatus.OK);
	    	} else {
	    		response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    	}    	
	    	return response;
	 }
			 
}
