package com.ssafy.plog.service;

import java.time.LocalDate;
import java.util.List;

import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;

public interface PostService {

	List<Post> selectAll(int uid);

	List<Post> selectByDay(int uid, LocalDate date);

	List<Post> selectBySchedule(int uid, int s_id);

	public Post selectByPid(int p_id);
	
	boolean deleteByPid(int p_id);

	boolean updatePost(Post post);

	int registPost(Post post);

	List<Post> selectByCategory(int uid, int c_id);

	List<Category> getCategory(int uid);

	List<Post> selectByTitle(int uid, String title);

}
