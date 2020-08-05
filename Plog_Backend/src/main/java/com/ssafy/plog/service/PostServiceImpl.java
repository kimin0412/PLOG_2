package com.ssafy.plog.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
		return dao.findBypUser(uid, Sort.by(Sort.Direction.DESC,"pBookmark","pDate"));
	}

	@Override
	public List<Post> selectByBookmark(int uid) {
		return dao.findBypBookmark(uid);
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
		dao.insertPost(post.getpId(), post.getpTitle(), post.getpContent(), 1, post.getpSchedule(), post.getpCategory(), post.getpColor());
		
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
	public List<Post> selectByWord(int uid, String word, boolean[] checklist) {
		if(checklist[0] && checklist[1] && checklist[2]) {
			return dao.findBypUserAndpTitleOrpContentOrpTag(uid, word);
		} else if(checklist[0] && checklist[1] && !checklist[2]) {
			return dao.findBypUserAndpTitleOrpContent(uid, word);
		} else if(checklist[0] && !checklist[1] && checklist[2]) {
			return dao.findBypUserAndpTitleOrpTag(uid, word);
		} else if(!checklist[0] && checklist[1] && checklist[2]) {
			System.out.println("check");
			List<Post> list = dao.findBypUserAndpContentOrpTag(uid, word);
			System.out.println(list.size());
			return list;
			
		} else if(checklist[0] && !checklist[1] && !checklist[2]) {
			return dao.findBypUserAndpTitle(uid, word);
		} else if(!checklist[0] && checklist[1] && !checklist[2]) {
			return dao.findBypUserAndpContent(uid, word);
		} else if(!checklist[0] && !checklist[1] && checklist[2]){
			return dao.findBypUserAndpTag(uid, word);
		} else {
			return null;
		}
	}

	@Override
	public boolean bookmarkByPid(int pid) {
		Post p = dao.findBypId(pid);
		if(p.getpBookmark() == 0) {
			p.setpBookmark(1);
		} else {
			p.setpBookmark(0);
		}
		return dao.save(p) != null;
	}

}
