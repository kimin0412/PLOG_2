package com.ssafy.plog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.plog.dao.CategoryDao;
import com.ssafy.plog.dao.HashTagDAO;
import com.ssafy.plog.dao.PostDao;
import com.ssafy.plog.dao.PostHashtagDAO;
import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao cdao;
	
	@Autowired
	PostDao pdao;
	
	@Autowired
	HashTagDAO hdao;

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

	@Override
	public void deletePostInCategory(int cid) {
		//cid에 맞는 post들 가져오기 -> 해당 post들에 맞는 ph 지우기 -> post들 지우기 -> 카테고리 지우기
		List<Post> posts = pdao.findPostByCategory(cid);
		int size = posts.size();
		
		for (int i = 0; i < size; i++) {
			hdao.deletePostHashtag(posts.get(i).getpId());
		}
		
		for (int i = 0; i < size; i++) {
			pdao.deleteById(posts.get(i).getpId());
		}
		
		cdao.deleteById(cid);
	}

	@Override
	public void deleteOnlyCategory(int cid) {
		//cid에 맞는 post들 가져오기 -> 해당 post들의 pCategory를 1로 update -> 카테고리 지우기
		List<Post> posts = pdao.findPostByCategory(cid);
		int size = posts.size();
		
		for (int i = 0; i < size; i++) {
			//update
//			pdao.deleteById(posts.get(i).getpId());
		}
	}
}
