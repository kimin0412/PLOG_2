package com.ssafy.plog.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		return dao.deleteBypId(pId);
	}

	@Override
	public boolean updatePost(Post post) {
		return dao.save(post) != null;
	}

	@Override
	public void registPost(Post post) {
		dao.save(post);
	}

	@Override
	public List<Post> selectByCategory(int uid, int cId) {
		return dao.findBypCategory(uid, cId);
	}
	
	@Override
	public List<Category> getCategory(int uid) {
		return null;//cdao.selectAll(mem_id);
	}

}
