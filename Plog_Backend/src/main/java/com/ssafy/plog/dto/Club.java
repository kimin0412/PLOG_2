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
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
public class Club {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int clId;
	
	@Column
	private String clName;
	private String clInfo;
	@Column(insertable = false, updatable = false)
	private LocalDateTime clRegdate;
	private String clColor;
	private String clPassword;
	
	public int getClId() {
		return clId;
	}
	public void setClId(int clId) {
		this.clId = clId;
	}
	public String getClName() {
		return clName;
	}
	public void setClName(String clName) {
		this.clName = clName;
	}
	public String getClInfo() {
		return clInfo;
	}
	public void setClInfo(String clInfo) {
		this.clInfo = clInfo;
	}
	public LocalDateTime getClRegdate() {
		return clRegdate;
	}
	public void setClRegdate(LocalDateTime clRegdate) {
		this.clRegdate = clRegdate;
	}
	public String getClColor() {
		return clColor;
	}
	public void setClColor(String clColor) {
		this.clColor = clColor;
	}
	public String getClPassword() {
		return clPassword;
	}
	public void setClPassword(String clPassword) {
		this.clPassword = clPassword;
	}
	
	
}
