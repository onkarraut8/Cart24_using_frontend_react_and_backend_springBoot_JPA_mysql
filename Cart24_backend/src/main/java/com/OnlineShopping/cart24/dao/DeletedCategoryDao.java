package com.OnlineShopping.cart24.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.OnlineShopping.cart24.model.DeletedCategory;



@Repository
public interface DeletedCategoryDao extends JpaRepository<DeletedCategory, Integer> {
	
	@Query("SELECT c FROM  DeletedCategory c WHERE CONCAT(c.title, c.description) LIKE %?1%")
	public List<DeletedCategory> search(String keyword);

}
