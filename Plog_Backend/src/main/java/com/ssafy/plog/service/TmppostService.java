package com.ssafy.plog.service;

import java.time.LocalDate;
import java.util.List;

import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Tmppost;

public interface TmppostService {

	List<Tmppost> selectAll(int uId);

	List<Tmppost> selectByDay(int uId, LocalDate date);

	Tmppost selectByTpid(int tpId);
	
	boolean deleteByTpid(int tpId);

	boolean updatePost(Tmppost tp);

	void registPost(Tmppost tp);

	List<Tmppost> selectByTitle(int uid, String title);
}
