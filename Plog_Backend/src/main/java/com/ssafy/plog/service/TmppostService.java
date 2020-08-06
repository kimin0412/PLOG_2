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

	List<Tmppost> selectByWord(int uid, String title, boolean[] checklist);

	List<Post> selectByTitle(String searchword);

	boolean joinClub(int uId, int clId, String password);
}
