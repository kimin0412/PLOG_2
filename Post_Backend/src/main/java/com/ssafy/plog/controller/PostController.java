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
import com.ssafy.plog.service.PostService;

@RestController
@CrossOrigin("*")
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService service;
	
	@GetMapping("/")
	public Post selectBypId(@RequestParam(required = false) final int pId) {
		Post post = service.selectByPid(pId);
    	return post;
    }
	
	@GetMapping("/list/all")
	public List<Post> selectAll(@RequestParam(required = false) final int uid) {
		List<Post> posts = service.selectAll(uid);
//		for (Post p : posts) {
//			System.out.println(p);
//		}
    	return posts;
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
    	
		System.out.println(post);
		service.registPost(post);
		
    	return new ResponseEntity<>("success", HttpStatus.OK);
    }
	
	 @PutMapping("/")
	 public Object updatePost(@RequestBody Post post) {
	    	ResponseEntity response = null;
	    	if(service.updatePost(post)) {    	
	    	    response = new ResponseEntity<String>("success", HttpStatus.OK);
	    	} else {
	    		response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    	}    	
	    	return response;
	    }
	
	 
	 @DeleteMapping("/")
	 public Object delete(@RequestParam(required = false) final int p_id) {
	    	
	    	ResponseEntity response = null;
	    	if(service.deleteByPid(p_id)) {
	    	    response = new ResponseEntity<>("success", HttpStatus.OK);
	    	} else {
	    		response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	    	}    	
	    	return response;
	    }
	
}
