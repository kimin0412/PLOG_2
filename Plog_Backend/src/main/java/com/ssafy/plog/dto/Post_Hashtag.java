package com.ssafy.plog.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Post_Hashtag {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private int phId;
	
	@Column
	private int phPost;
	private int phHashtag;
	private int phUser;
	public int getPhId() {
		return phId;
	}
	public void setPhId(int phId) {
		this.phId = phId;
	}
	public int getPhPost() {
		return phPost;
	}
	public void setPhPost(int phPost) {
		this.phPost = phPost;
	}
	public int getPhHashtag() {
		return phHashtag;
	}
	public void setPhHashtag(int phHashtag) {
		this.phHashtag = phHashtag;
	}
	public int getPhUser() {
		return phUser;
	}
	public void setPhUser(int phUser) {
		this.phUser = phUser;
	}
	
}