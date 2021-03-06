package com.ssafy.plog.service;

import java.util.List;

import com.ssafy.plog.dto.Hashtag;

public interface HashTagService {
	List<String> selectByIds(int uid, int pid);

	int getNextPId(int sId);

	void deletePostHashtag(int pId);

	List<Hashtag> getAll(int uId);

	List<Hashtag> getNotesTags(int uid, int clId);

	List<Hashtag> getNotesTagsInCategory(int uid, int clId, int cId);
	
}
