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
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Category {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private int cId;
	
	@Column
	private String cName;
	
	@Column
	private int cUser;
	private int cClub;

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public int getcUser() {
		return cUser;
	}

	public void setcUser(int cUser) {
		this.cUser = cUser;
	}

	public int getcClub() {
		return cClub;
	}

	public void setcClub(int cClub) {
		this.cClub = cClub;
	}
	
	
		
}
