package com.ssafy.plog.service;

import java.util.List;

public interface HashTagService {

	void insertHashTag(String[] tags, int pid);

	List<String> selectByIds(int uid, int pid);

	int getNextPId(int sId);
	
}
