package com.OnlineShopping.cart24.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.OnlineShopping.cart24.model.Review;





@Repository
public interface ReviewDao extends JpaRepository<Review, Integer> {
	
	@Query(value = "SELECT * FROM  review u WHERE u.product_id =?1 AND u.user_id=?2", nativeQuery = true)
	Review findByProductIdAndUserId(int productId, int userId);
    
	@Query(value = "SELECT * FROM  review u WHERE u.product_id =?1", nativeQuery = true)
	List<Review> findAllByProductId(int id);

}
