package com.OnlineShopping.cart24.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.OnlineShopping.cart24.model.Orders;
import com.OnlineShopping.cart24.model.User;


@Repository
public interface OrderDao extends JpaRepository<Orders, Integer> {
	
	List<Orders> findByUser_id(int userId);
	List<Orders> findByOrderId(String orderId);
	List<Orders> findByUser_idAndProduct_id(int userId, int productId);
	List<Orders> findByUser(User user);

	/*@Query("SELECT o FROM  Orders o WHERE o.deliveryPersonId =?1 AND o.deliveryStatus != 'Delivered'")*/
	List<Orders> findByDeliveryPersonId(int deliveryPersonId);
	
	@Query("SELECT o FROM  Orders o WHERE o.deliveryPersonId =?1 AND o.deliveryStatus != 'Delivered'")
	List<Orders> findByDeliveryPersonIdNot(int deliveryPersonId);
	
	@Query(value = "SELECT * FROM  Orders o WHERE o.delivery_person_id ='0'", nativeQuery = true)
	List<Orders> findAllNoDeliveryPerson();
	
	@Query(value = "select count(id) from orders where SUBSTRING(order_date, 1, 2) = DAY(now()) and SUBSTRING(order_date, 4, 2) = MONTH(now()) and SUBSTRING(order_date, 7, 4) = YEAR(now())", nativeQuery = true)
	int countTodayOrder();
	
	@Query(value = "select count(id) from orders where SUBSTRING(order_date, 4, 2) = MONTH(now()) and SUBSTRING(order_date, 7, 4) = YEAR(now())", nativeQuery = true)
	int countThisMonthOrder();
	 
}
