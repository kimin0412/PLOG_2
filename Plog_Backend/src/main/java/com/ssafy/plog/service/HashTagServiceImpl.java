package com.ssafy.plog.service;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
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

	@Override
	public List<Hashtag> getAll(int uId) {
		List<Post_Hashtag> phs = phdao.getPhbyUser(uId);
		
		List<Hashtag> hList = new LinkedList<>();
		for (int i = 0, size = phs.size(); i < size; i++) {
			int hid = phs.get(i).getPhHashtag();
			Hashtag hash = hdao.findByHId(hid);
			boolean findSame = false;
			for (int j = 0, size2 = hList.size(); j < size2; j++) {
				if(hList.get(j).gethName().equals(hash.gethName())) {
					Hashtag tempHash = hList.get(j);
					hList.remove(j);
					int cnt = tempHash.gethId();
					tempHash.sethId(cnt+1);
					hList.add(tempHash);
					findSame = true;
					break;
				}
			}
			
			if(!findSame) {
				hList.add(new Hashtag(1, hash.gethName()));
			}
		}
		return hList;
	}
}
