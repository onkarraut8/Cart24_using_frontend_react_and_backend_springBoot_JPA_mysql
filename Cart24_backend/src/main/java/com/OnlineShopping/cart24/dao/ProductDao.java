package com.OnlineShopping.cart24.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.OnlineShopping.cart24.model.Product;




@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {
	
	List<Product> findByCategoryId(int category);
	
	@Query("SELECT p FROM Product p WHERE p.display='Show' and p.category.id=?1")
	List<Product> findByCategoryIdD(int category);
	
	@Query("SELECT p FROM Product p WHERE p.display='Show'")
	List<Product> findAllD();

	@Query("SELECT p FROM Product p WHERE CONCAT(p.title, p.description) LIKE %?1% or p.category.title LIKE %?1% or p.category.description LIKE %?1%")
    /*@Query("SELECT p FROM Product p , Category c WHERE p.title LIKE %?1% or p.description LIKE %?1% or c.title LIKE %?1% group by p.id")*/
	public List<Product> search(String keyword);
	
	@Query("SELECT p FROM Product p WHERE p.display='Show' and (CONCAT(p.title, p.description) LIKE %?1% or p.category.title LIKE %?1% or p.category.description LIKE %?1%)")
    /*@Query("SELECT p FROM Product p , Category c WHERE p.title LIKE %?1% or p.description LIKE %?1% or c.title LIKE %?1% group by p.id")*/
	public List<Product> searchD(String keyword);
	
	List<Product> findByid(int Id);

}
