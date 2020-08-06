package com.ssafy.plog.service;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.plog.dao.HashTagDAO;
import com.ssafy.plog.dao.PostDao;
import com.ssafy.plog.dao.PostHashtagDAO;
import com.ssafy.plog.dto.Hashtag;
import com.ssafy.plog.dto.Post_Hashtag;

@Service
public class HashTagServiceImpl implements HashTagService {

	@Autowired
	HashTagDAO hdao;
	
	@Autowired
	PostDao pdao;
	
	@Autowired
	PostHashtagDAO phdao;
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void insertHashTag(String[] tags, int pid, int uid) {
		//post의 id를 찾는다.;
		
		int size = tags.length;
		//태그들이 존재하는지 확인하고 없으면 만들어준다.		
		Hashtag tmp;
		for (int i = 0; i < size; i++) {
			tmp = hdao.findByName(tags[i]);
			if(tmp == null) {
				Hashtag toCreate = new Hashtag();
				toCreate.sethName(tags[i]);
				hdao.save(toCreate);
			}
		}
		
		//tmppost에 넣어준다.
		for (int i = 0; i < size; i++) {
			tmp = hdao.findByName(tags[i]);
			Post_Hashtag ph = new Post_Hashtag();
			ph.setPhPost(pid);
			ph.setPhHashtag(tmp.gethId());
			ph.setPhUser(uid);
			phdao.save(ph);
		}
	}

	@Override
	public List<String> selectByIds(int uid, int pid) {
		List<Post_Hashtag> phs = phdao.getPhById(uid, pid);
		List<String> tags = new LinkedList<>();
		
		for (int i = 0, size = phs.size(); i < size; i++) {
			Hashtag tag = hdao.findByHId(phs.get(i).getPhHashtag());
			tags.add(tag.gethName());
		}
		
		return tags;
		
	}

	@Override
	public int getNextPId(int sId) {
		return pdao.getLatelyPId();
	}

	@Override
	public void deletePostHashtag(int pId) {
		hdao.deletePostHashtag(pId);
	}
}
