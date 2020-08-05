package com.ssafy.plog.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plog.dto.BasicResponse;
import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.service.CategoryService;

@CrossOrigin("*")
@RestController
public class CategoryController {
	
	@Autowired
	CategoryService cService;
	
    @RequestMapping("/category/insert")
	public Object insert(@RequestBody Category category) {
    	
    	final BasicResponse result = new BasicResponse();
    	
    	cService.insertCategory(category);
    	
    	result.status = true;
        result.data = "success";	
    		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @RequestMapping("/category/listAll")
	public List<Category> getAllList(@RequestParam int uid) {
    	return cService.getAllCategory(uid);
		
	}
    
    @RequestMapping("/category/update/post")
	public Object updatePost(@RequestBody Post post) {
    	
    	final BasicResponse result = new BasicResponse();
    	
    	cService.updatePostCategory(post);
    	
    	result.status = true;
        result.data = "success";	
    		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @DeleteMapping("/category/delete/all")
	public Object deleteAllCategory(@RequestParam int cid) {
    	
    	final BasicResponse result = new BasicResponse();
    	cService.deletePostInCategory(cid);
    	
    	result.status = true;
        result.data = "success";	
    		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @DeleteMapping("/category/delete/only")
	public Object deleteOnlyCategory(@RequestParam int cid) {
    	
    	final BasicResponse result = new BasicResponse();
    	cService.deleteOnlyCategory(cid);
    	
    	result.status = true;
        result.data = "success";	
    		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @PutMapping("/category/update")
	public Object updateCategory(@RequestBody Category category) {
    	
    	final BasicResponse result = new BasicResponse();
    	cService.updateCategory(category);
    	
    	result.status = true;
        result.data = "success";	
    		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}