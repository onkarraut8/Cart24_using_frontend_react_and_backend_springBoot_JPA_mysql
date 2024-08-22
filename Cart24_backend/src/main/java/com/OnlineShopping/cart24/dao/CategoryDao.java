package com.OnlineShopping.cart24.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.OnlineShopping.cart24.model.Category;



@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
	
	@Query("SELECT c FROM  Category c WHERE CONCAT(c.title, c.description) LIKE %?1%")
	public List<Category> search(String keyword);
}
