package com.OnlineShopping.cart24.dao;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.OnlineShopping.cart24.model.User;




@Repository
public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByEmailIdAndPasswordAndRoleAndVarify(String emailId, String password, String role,String varify);
	User findByEmailIdAndPhoneNo(String emailId, String phoneNo);
	@Query("SELECT u FROM  User u WHERE u.emailId=?1 OR u.phoneNo=?2 ")
	List <User> findByEmailIdAndPhoneNo1(String emailId, String phoneNo);
	User findByEmailIdAndRoleAndPhoneNo(String emailId, String role, String phoneNo);
	List <User> findByRole(String role);
	User findByEmailId(String emailId);
	
	@Query("SELECT u FROM  User u WHERE u.emailId=?1 OR u.phoneNo=?2 OR u.id=?3 ")
	User findByEmailIdOrPhoneNoOrId(String emailId, String phoneNo,int id);
	
	@Query("SELECT c FROM  User c WHERE c.role !='admin' AND CONCAT(c.firstName, c.lastName) LIKE %?1%")
	public List<User> search(String keyword);

	@Query(value = "SELECT * FROM  User u WHERE u.role='Delivery' ", nativeQuery = true)
	List <User> varifyUser();
	
	@Query(value = "SELECT * FROM  User u WHERE u.role !='Admin'", nativeQuery = true)
	List <User> findAll();
	@Query(value = "SELECT * FROM  User u WHERE u.role =?1 AND u.varify='YES'", nativeQuery = true)
	List<User> findByRoleAndVarify(String string);
	
	@Query(value = "select count(id) from user where role='Customer'", nativeQuery = true)
	int countCustomer();
	
	@Query(value = "select count(id) from user where role='Delivery'", nativeQuery = true)
	int countDelivery();

}
