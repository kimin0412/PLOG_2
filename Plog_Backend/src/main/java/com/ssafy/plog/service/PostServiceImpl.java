package com.ssafy.plog.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.plog.dao.CategoryDao;
import com.ssafy.plog.dao.HashTagDAO;
import com.ssafy.plog.dao.PostDao;
import com.ssafy.plog.dao.PostHashtagDAO;
import com.ssafy.plog.dao.ScheduleDAO;
import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Schedule;
import com.ssafy.plog.models.User;
import com.ssafy.plog.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	PostDao dao;
	
	@Autowired
	CategoryDao cdao;
	
	@Autowired
	HashTagDAO hdao;
	
	@Autowired
	PostHashtagDAO phdao;
	
	@Autowired
	ScheduleDAO sdao;
	
	@Autowired 
	UserRepository ur;
	
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
		dao.insertPost(post.getpId(), post.getpTitle(), post.getpContent(), post.getpUser(), post.getpSchedule(), post.getpCategory(), post.getpColor(), post.getpClub());
		
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

	@Override
	public List<Post> searchHashtag(int uid, String hName) {
		//해쉬 아이디 가져오기 -> ph에서 해쉬아이디로 검색 -> ph_post로 post가져오기
		int hid = hdao.getHId(hName);
		
		List<Integer> pIds = phdao.getPhByHash(uid, hid);
		List<Post> posts = new LinkedList<>();
		for (int i = 0, size = pIds.size(); i < size; i++) {
			posts.add(dao.findBypId(pIds.get(i)));
		}
		
		return posts;
	}

	@Override
	public List<Post> selectAllClub(int clid) {
		return dao.findBypClub(clid, Sort.by(Sort.Direction.DESC,"pBookmark","pDate"));
	}

	@Override
	public List<Post> countPosts(int uid) {
		List<Post> posts = dao.findBypUser(uid, Sort.by(Sort.Direction.DESC,"pBookmark","pDate"));
		int[] monthPosts = new int[12];
		
		for (int i = 0, size = posts.size(); i < size; i++) {
			LocalDateTime pDate = posts.get(i).getpDate();
			int year = pDate.getYear();
			if(year == 2020) {
				monthPosts[pDate.getMonthValue()-1]++;
			}
		}
		
		int[] monthSchedules = new int[12];
		List<Schedule> schedules = sdao.getScheduleBySUser(uid);
		for (int i = 0, size = schedules.size(); i < size; i++) {
			String start = schedules.get(i).getsStartdate();
			String end = schedules.get(i).getsEnddate();
			
			boolean isStart2020 = start.substring(0, 4).equals("2020");
			boolean isEnd2020 = end.substring(0, 4).equals("2020");
			
			int sMonth = Integer.parseInt(start.substring(5,7));
			int eMonth = Integer.parseInt(end.substring(5,7));
			
			if(isStart2020) {
				if(isEnd2020) {
					for (int j = eMonth-1; j > sMonth-2; j--) {
						monthSchedules[j]++;
					}
				}else {
					for (int j = 11; j > sMonth-2; j--) {
						monthSchedules[j]++;
					}
				}
			}else {
				if(isEnd2020) {
					for (int j = 0; j < eMonth; j++) {
						monthSchedules[j]++;
					}
				}else {
					for (int j = 0; j < 12; j++) {
						monthSchedules[j]++;
					}
				}
			}
		}
		
		List<Post> toSend = new LinkedList<>();
		for (int i = 1; i < 13; i++) {
			Post temp = new Post();
			temp.setpBookmark(monthPosts[i-1]);
			temp.setpCategory(monthSchedules[i-1]);
			temp.setpClub(i);
			toSend.add(temp);
		}
		return toSend;
	}

	@Override
	public String getUser(int pid) {
		int uid = dao.getPUser(pid);
		User user = ur.findbyClId(uid);
		return user.getEmail();
	}
}