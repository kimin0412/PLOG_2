package com.ssafy.plog.service;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.imageio.ImageIO;

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
import com.ssafy.plog.dto.Hashtag;
import com.ssafy.plog.dto.Post;
import com.ssafy.plog.dto.Post_Hashtag;
import com.ssafy.plog.dto.Post_NoJPA;
import com.ssafy.plog.dto.Schedule;
import com.sun.xml.messaging.saaj.packaging.mime.util.BASE64DecoderStream;
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
	public boolean updatePost(Post_NoJPA temp) { 
		Post post = new Post();
		
		post.setpId(temp.getpId());
		post.setpTitle(temp.getpTitle());
		post.setpUser(temp.getpUser());
		post.setpContent(temp.getpContent());
		post.setpColor(temp.getpColor());
		
		if(temp.getpSchedule() == 0) {
			post.setpSchedule(1);
		}else post.setpSchedule(temp.getpSchedule());
		
		if(temp.getpCategory() == 0) {
			post.setpCategory(1);
		}else post.setpCategory(temp.getpCategory());
		
		if(temp.getpClub() == 0) {
			post.setpClub(1);
		}else post.setpClub(temp.getpClub());
		
		
		String content = post.getpContent();
		if(content.contains(";base64,")) {
			int start = content.indexOf("base64,");
			start = content.indexOf(",",start) + 1;
			int end = content.indexOf("&quot;", start);
			
			int estart = content.indexOf("data:image");
			estart = content.indexOf("/", estart) + 1;
			int eend = content.indexOf(";",estart);
			String extend = content.substring(estart, eend);
			
			File file = new File("src/main/resources/static/upload/" + post.getpId() + "." + extend);
									
			try {			
				String image = content.substring(start, end);
								
				byte[] bImage = image.getBytes("UTF-8");
				Decoder decoder = Base64.getDecoder();
				
				byte[] dImage = decoder.decode(bImage);
				BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(dImage));
				
				//////////////////////////////////
				int imageWidth = bufImage.getWidth(null);
				int imageHeight = bufImage.getHeight(null);
				
				double ratio = (double)400/(double)imageWidth;
                int w = (int)(imageWidth * ratio);
                int h = (int)(imageHeight * ratio);
				
                // 이미지 리사이즈
                // Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
                // Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
                // Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
                // Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
                // Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
                Image resizeImage = bufImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
     
                // 새 이미지  저장하기
                BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                Graphics g = newImage.getGraphics();
                g.drawImage(resizeImage, 0, 0, null);
                g.dispose();
				
				ImageIO.write(newImage, extend, file);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String path = "http://localhost:8080/api/upload/" + post.getpId() + "." + extend;
			int cstart = content.indexOf("img src=&quot");
			cstart = content.indexOf(";",cstart) + 1;
			String con = content.substring(0, cstart);
			con = con + path + content.substring(end);
			post.setpContent(con);
			
		}
		
		boolean ok = dao.save(post) != null;
		
		if(!temp.getpHashtag().equals("")) {
       		String[] tags = temp.getpHashtag().split(" ");
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
    		
    		//이미 있던 태그들을 삭제해준다.
    		phdao.deleteAllInPost(post.getpId());
    		
    		for (int i = 0; i < size; i++) {
    			tmp = hdao.findByName(tags[i]);
    			Post_Hashtag ph = new Post_Hashtag();
    			ph.setPhPost(post.getpId());
    			ph.setPhHashtag(tmp.gethId());
    			ph.setPhUser(post.getpUser());
    			phdao.save(ph);
    		}
        }
		
		return ok;
	}

	@Transactional
	@Override
	public int registPost(Post_NoJPA temp) {
		Post post = new Post();
		
		post.setpTitle(temp.getpTitle());
		post.setpUser(temp.getpUser());
		post.setpContent(temp.getpContent());
		post.setpColor(temp.getpColor());
		
		if(temp.getpSchedule() == 0) {
			post.setpSchedule(1);
		}else post.setpSchedule(temp.getpSchedule());
		
		if(temp.getpCategory() == 0) {
			post.setpCategory(1);
		}else post.setpCategory(temp.getpCategory());
		
		if(temp.getpClub() == 0) {
			post.setpClub(1);
		}else post.setpClub(temp.getpClub());
		
		String content = post.getpContent();
		if(content.contains(";base64,")) {
			int start = content.indexOf("base64,");
			start = content.indexOf(",",start) + 1;
			int end = content.indexOf("&quot;", start);
			
			int estart = content.indexOf("data:image");
			estart = content.indexOf("/", estart) + 1;
			int eend = content.indexOf(";",estart);
			String extend = content.substring(estart, eend);
			
			File file = new File("src/main/resources/static/upload/" + post.getpId() + "." + extend);
									
			try {			
				String image = content.substring(start, end);
								
				byte[] bImage = image.getBytes("UTF-8");
				Decoder decoder = Base64.getDecoder();
				
				byte[] dImage = decoder.decode(bImage);
				BufferedImage bufImage = ImageIO.read(new ByteArrayInputStream(dImage));
				
				//////////////////////////////////
				int imageWidth = bufImage.getWidth(null);
				int imageHeight = bufImage.getHeight(null);
				
				double ratio = (double)400/(double)imageWidth;
				int w = (int)(imageWidth * ratio);
				int h = (int)(imageHeight * ratio);
				
				// 이미지 리사이즈
				// Image.SCALE_DEFAULT : 기본 이미지 스케일링 알고리즘 사용
				// Image.SCALE_FAST    : 이미지 부드러움보다 속도 우선
				// Image.SCALE_REPLICATE : ReplicateScaleFilter 클래스로 구체화 된 이미지 크기 조절 알고리즘
				// Image.SCALE_SMOOTH  : 속도보다 이미지 부드러움을 우선
				// Image.SCALE_AREA_AVERAGING  : 평균 알고리즘 사용
				Image resizeImage = bufImage.getScaledInstance(w, h, Image.SCALE_SMOOTH);
				
				// 새 이미지  저장하기
				BufferedImage newImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				Graphics g = newImage.getGraphics();
				g.drawImage(resizeImage, 0, 0, null);
				g.dispose();
				
				ImageIO.write(newImage, extend, file);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String path = "http://localhost:8080/api/upload/" + post.getpId() + "." + extend;
			int cstart = content.indexOf("img src=&quot");
			cstart = content.indexOf(";",cstart) + 1;
			String con = content.substring(0, cstart);
			con = con + path + content.substring(end);
			post.setpContent(con);
			
		}
		
		dao.saveAndFlush(post);
		
		int pid = dao.getLatelyPId();
		
		if(!temp.getpHashtag().equals("")) {
            String[] tags = temp.getpHashtag().split(" ");
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
    			ph.setPhUser(post.getpUser());
    			phdao.save(ph);
    		}
         }
		
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
		return user.getUsername();
	}
}