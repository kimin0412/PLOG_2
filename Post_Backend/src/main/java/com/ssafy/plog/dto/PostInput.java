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
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostInput {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private int pId;
	
	@Column
	private String pTitle;
	
	@Column
	private String pContent;
	
	@Column(insertable = false, updatable = false)
	private LocalDateTime p_date;
	
	@Column
	private int pUser;
	@Column
	private int pSchedule;
	@Column
	private int pCategory;
	
	@Column
	private String[] phashtag; 
	
}
