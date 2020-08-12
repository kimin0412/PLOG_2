package com.ssafy.plog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plog.dto.BasicResponse;
import com.ssafy.plog.dto.Hashtag;
import com.ssafy.plog.service.HashTagService;

@CrossOrigin("*")
@RestController
public class HashTagController {
	
	@Autowired
	HashTagService hService;
    
    @RequestMapping("/hashtag/select")
    @ResponseBody
	public Object selectByIds(@RequestParam int uid, int pid) {   	
    	List<String> tags = hService.selectByIds(uid, pid);
		return tags;
	}
    
    @RequestMapping("/hashtag/getnextPostId")
    @ResponseBody
	public Object selectNextPostId(@RequestParam int uId) { 
    	int no = hService.getNextPId(uId);
    	Hashtag ht = new Hashtag();
    	ht.sethId(no+1);
		return ht;
	}
    
    @DeleteMapping("/hashtag/delete")
    @ResponseBody
    public Object deleteHashtag(@RequestParam int pId) { 
    	hService.deletePostHashtag(pId);
    	
    	final BasicResponse result = new BasicResponse();
    	
    	result.status = true;
        result.data = "success";	
    		
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/hashtag/all")
	public List<Hashtag> getAllHashtag(@RequestParam int uid) { 
    	List<Hashtag> hList = hService.getAll(uid);
		return hList;
	}
    
    @RequestMapping("/hashtag/update")
   	public Object update(@RequestBody Hashtag hashtag) {
       	
       	final BasicResponse result = new BasicResponse();
       	if(!hashtag.gethName().equals("")) {
       		String[] tags = hashtag.gethName().split(" ");
            hService.updateHashTag(tags, hashtag.gethId()%1000, hashtag.gethId()/1000);
        }
       	
       	result.status = true;
        result.data = "success";	
       		
   		return new ResponseEntity<>(result, HttpStatus.OK);
   	}
}
