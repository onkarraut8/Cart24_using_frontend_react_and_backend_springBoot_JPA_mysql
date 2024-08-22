package com.OnlineShopping.cart24.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.OnlineShopping.cart24.model.DeletedProduct;



@Repository
public interface DeletedProductDao extends JpaRepository<DeletedProduct, Integer>{

	List<DeletedProduct> findByCategoryId(int category);

	@Query("SELECT p FROM DeletedProduct p WHERE CONCAT(p.title, p.description) LIKE %?1% or p.category.title LIKE %?1% or p.category.description LIKE %?1%")
    /*@Query("SELECT p FROM Product p , Category c WHERE p.title LIKE %?1% or p.description LIKE %?1% or c.title LIKE %?1% group by p.id")*/
	public List<DeletedProduct> search(String keyword);
	List<DeletedProduct> findByid(int Id);
	
	
	
}
