package com.ssafy.plog.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.plog.dao.CategoryDao;
import com.ssafy.plog.dao.PostDao;
import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostDao dao;
	
	@Autowired
	CategoryDao cdao;
	
	public List<Post> selectAll(int uid) {
		//return dao.findAll();
		return dao.findBypUser(uid);
	}

	@Override
	public List<Post> selectByDay(int uid, LocalDate date) {
		return dao.findBypDate(uid, date);
	}

	@Override
	public List<Post> selectBySchedule(int uid, int sId) {
		return dao.findBypUserAndpSchedule(uid, sId);
	}
	
	@Override
	public Post selectByPid(int pId) {
		return dao.findBypId(pId);
	}

	@Override
	public boolean deleteByPid(int pId) {
		return (dao.deleteBypId(pId) > 0) ? true : false;
	}

	@Override
	public boolean updatePost(Post post) {
		return dao.save(post) != null;
	}

	@Transactional
	@Override
	public int registPost(Post post) {
		dao.insertPost(post.getpTitle(), post.getpContent(), 1, post.getpSchedule(), post.getpCategory());
		
		return 0;
	}

	@Override
	public List<Post> selectByCategory(int uid, int cId) {
		return dao.findBypCategory(uid, cId);
	}
	
	@Override
	public List<Category> getCategory(int uid) {
		return null;//cdao.selectAll(mem_id);
	}

	@Override
	public List<Post> selectByTitle(int uid, String title) {
		return dao.findBypUserAndpTitle(uid, title);
	}

	@Override
	public boolean bookmarkByPid(int pid) {
		Post p = dao.findBypId(pid);
		if(p.getBookmark() == 0) {
			p.setBookmark(1);
		} else {
			p.setBookmark(0);
		}
		return dao.save(p) != null;
	}

}
