package com.ssafy.plog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.plog.dao.CategoryDao;
import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao cdao;

	@Override
	public void insertCategory(Category category) {
		cdao.save(category);
		
	}

	@Override
	public List<Category> getAllCategory(int uid) {
		return cdao.findAllByCUser(uid);
	}

	@Override
	public void updatePostCategory(Post post) {
		cdao.updatePost(post.getpId(), post.getpCategory());
		
	}
}
