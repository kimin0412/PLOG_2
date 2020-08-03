package com.ssafy.plog.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private int s_Id;
	
	@Column
	private String s_Name;
	private String s_Content;
	private String s_Startdate;
	private String s_Enddate;
	private String s_User;
	public int getS_Id() {
		return s_Id;
	}
	public void setS_Id(int s_Id) {
		this.s_Id = s_Id;
	}
	public String getS_Name() {
		return s_Name;
	}
	public void setS_Name(String s_Name) {
		this.s_Name = s_Name;
	}
	public String getS_Content() {
		return s_Content;
	}
	public void setS_Content(String s_Content) {
		this.s_Content = s_Content;
	}
	public String getS_Startdate() {
		return s_Startdate;
	}
	public void setS_Startdate(String s_Startdate) {
		this.s_Startdate = s_Startdate;
	}
	public String getS_Enddate() {
		return s_Enddate;
	}
	public void setS_Enddate(String s_Enddate) {
		this.s_Enddate = s_Enddate;
	}
	public String getS_User() {
		return s_User;
	}
	public void setS_User(String s_User) {
		this.s_User = s_User;
	}
	@Override
	public String toString() {
		return "Schedule [s_Id=" + s_Id + ", s_Name=" + s_Name + ", s_Content=" + s_Content + ", s_Startdate="
				+ s_Startdate + ", s_Enddate=" + s_Enddate + ", s_User=" + s_User + "]";
	}
	
	
}
