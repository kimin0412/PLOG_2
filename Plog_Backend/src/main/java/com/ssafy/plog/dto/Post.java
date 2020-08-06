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

@Entity
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Post {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int pId;
	
	@Column
	private String pTitle;
	
	@Column
	private String pContent;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime pDate;
	
	@Column
	private int pUser;
	@Column
	private int pSchedule;
	@Column
	private int pCategory;
	
	@Column
	private int pBookmark;
	
	@Column
	private String pColor;
	
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
	
	
	
}
