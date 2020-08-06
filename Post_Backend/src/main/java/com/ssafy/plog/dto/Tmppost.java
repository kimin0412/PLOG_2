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
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tmppost {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private int tpId;
	
	@Column
	private String tpTitle;
	
	@Column
	private String tpContent;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime tpDate;
	
	@Column
	private int tpUser;

	public int getTpId() {
		return tpId;
	}

	public void setTpId(int tpId) {
		this.tpId = tpId;
	}

	public String getTpTitle() {
		return tpTitle;
	}

	public void setTpTitle(String tpTitle) {
		this.tpTitle = tpTitle;
	}

	public String getTpContent() {
		return tpContent;
	}

	public void setTpContent(String tpContent) {
		this.tpContent = tpContent;
	}

	public LocalDateTime getTpDate() {
		return tpDate;
	}

	public void setTpDate(LocalDateTime tpDate) {
		this.tpDate = tpDate;
	}

	public int getTpUser() {
		return tpUser;
	}

	public void setTpUser(int tpUser) {
		this.tpUser = tpUser;
	}
	
	
}
