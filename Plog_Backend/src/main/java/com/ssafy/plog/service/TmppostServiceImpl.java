package com.ssafy.plog.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.plog.dao.CategoryDao;
import com.ssafy.plog.dao.PostDao;
import com.ssafy.plog.dao.TmppostDao;
import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Tmppost;

@Service
public class TmppostServiceImpl implements TmppostService {

	@Autowired
	TmppostDao dao;

	@Override
	public List<Tmppost> selectAll(int tpUser) {
		return dao.findBytpUser(tpUser);
	}

	@Override
	public List<Tmppost> selectByDay(int tpUser, LocalDate date) {
		return dao.findBytpDate(tpUser, date);
	}


	@Override
	public Tmppost selectByTpid(int tpId) {
		return dao.findBytpId(tpId);
	}

	@Override
	public boolean deleteByTpid(int tpId) {
		return (dao.deleteBytpId(tpId) > 0) ? true : false;
	}

	@Override
	public boolean updatePost(Tmppost tp) {
		return dao.save(tp) != null;
	}

	@Override
	public void registPost(Tmppost tp) {
		dao.save(tp);
	}

	@Override
	public List<Tmppost> selectByTitle(int uid, String title) {
		return dao.findBytpUserAndtpTitle(uid, title);
	}
}
