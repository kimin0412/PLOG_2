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
public class Category {

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column
	private int cId;
	
	@Column
	private String c_name;
	
	@Column
	private String c_member;
		
}
