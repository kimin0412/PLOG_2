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
//@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class User_club {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ucId;
	
	@Column
	private int ucUser;
	private int ucClub;
	private boolean ucHost;
	
	public User_club() {}
	
	public User_club(int ucUser, int ucClub, boolean ucHost) {
		this.ucUser = ucUser;
		this.ucClub = ucClub;
		this.ucHost = ucHost;
	}
	public int getUcId() {
		return ucId;
	}
	public void setUcId(int ucId) {
		this.ucId = ucId;
	}
	public int getUcUser() {
		return ucUser;
	}
	public void setUcUser(int ucUser) {
		this.ucUser = ucUser;
	}
	public int getUcClub() {
		return ucClub;
	}
	public void setUcClub(int ucClub) {
		this.ucClub = ucClub;
	}
	public boolean isUcHost() {
		return ucHost;
	}
	public void setUcHost(boolean ucHost) {
		this.ucHost = ucHost;
	}
	
	
	
}
