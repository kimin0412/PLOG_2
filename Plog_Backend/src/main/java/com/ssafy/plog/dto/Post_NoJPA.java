package com.ssafy.plog.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Post_NoJPA {

	private int pId;
	private String pTitle;
	private String pContent;
	private LocalDateTime pDate;
	private int pUser;
	private int pSchedule;
	private int pCategory;
	private int pBookmark;
	private String pColor;
	private int pClub;
	private String pHashtag;


	@Override
	public String toString() {
		return "Post [pId=" + pId + ", pTitle=" + pTitle + ", pContent=" + pContent + ", pDate=" + pDate + ", pUser="
				+ pUser + ", pSchedule=" + pSchedule + ", pCategory=" + pCategory + ", pBookmark=" + pBookmark
				+ ", pColor=" + pColor + "]";
	}

	public int getpId() {
		return pId;
	}

	public void setpId(int pId) {
		this.pId = pId;
	}

	public String getpTitle() {
		return pTitle;
	}

	public void setpTitle(String pTitle) {
		this.pTitle = pTitle;
	}

	public String getpContent() {
		return pContent;
	}

	public void setpContent(String pContent) {
		this.pContent = pContent;
	}

	public LocalDateTime getpDate() {
		return pDate;
	}

	public void setpDate(LocalDateTime pDate) {
		this.pDate = pDate;
	}

	public int getpUser() {
		return pUser;
	}

	public void setpUser(int pUser) {
		this.pUser = pUser;
	}

	public int getpSchedule() {
		return pSchedule;
	}

	public void setpSchedule(int pSchedule) {
		this.pSchedule = pSchedule;
	}

	public int getpCategory() {
		return pCategory;
	}

	public void setpCategory(int pCategory) {
		this.pCategory = pCategory;
	}

	public int getpBookmark() {
		return pBookmark;
	}

	public void setpBookmark(int pBookmark) {
		this.pBookmark = pBookmark;
	}

	public String getpColor() {
		return pColor;
	}

	public void setpColor(String pColor) {
		this.pColor = pColor;
	}
	
	public int getpClub() {
		return pClub;
	}

	public void setpClub(int pClub) {
		this.pClub = pClub;
	}

	public String getpHashtag() {
		return pHashtag;
	}

	public void setpHashtag(String pHashtag) {
		this.pHashtag = pHashtag;
	}
	
	
}
