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
@Data
@Getter
//@NoArgsConstructor
//@AllArgsConstructor
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Hashtag {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int hId;
	
	@Column
	private String hName;

	public Hashtag(int hid, String hName) {
		this.hId = hid;
		this.hName = hName;
	}

	public Hashtag() {
		// TODO Auto-generated constructor stub
	}

	public int gethId() {
		return hId;
	}

	public void sethId(int hId) {
		this.hId = hId;
	}

	public String gethName() {
		return hName;
	}

	public void sethName(String hName) {
		this.hName = hName;
	}

	@Override
	public String toString() {
		return "Hashtag [hId=" + hId + ", hName=" + hName + "]";
	}
	
	
}
