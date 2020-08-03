package com.ssafy.plog.dto;

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
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Schedule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sId;
	
	@Column
	private String sName;
	private String sContent;
	private String sStartdate;
	private String sEnddate;
	private int sUser;
	
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getsContent() {
		return sContent;
	}
	public void setsContent(String sContent) {
		this.sContent = sContent;
	}
	public String getsStartdate() {
		return sStartdate;
	}
	public void setsStartdate(String sStartdate) {
		this.sStartdate = sStartdate;
	}
	public String getsEnddate() {
		return sEnddate;
	}
	public void setsEnddate(String sEnddate) {
		this.sEnddate = sEnddate;
	}
	public int getsUser() {
		return sUser;
	}
	public void setsUser(int sUser) {
		this.sUser = sUser;
	}
	
	
}
