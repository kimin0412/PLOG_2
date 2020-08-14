package com.ssafy.plog.service;

import java.util.List;

import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;

public interface CategoryService {

	void insertCategory(Category category);

	List<Category> getAllCategory(int uid);

	void updatePostCategory(Post post);

	void deletePostInCategory(int cid);

	void deleteOnlyCategory(int cid);

	void updateCategory(Category category);
	
	List<Category> getAllClubCategory(int cClub);

	Category getCategory(int pid);

}