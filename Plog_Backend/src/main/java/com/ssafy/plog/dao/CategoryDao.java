package com.ssafy.plog.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ssafy.plog.dto.Category;
import com.ssafy.plog.dto.Post;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
	
	
	//List<Category> selectAll(int c_member);
	
}
