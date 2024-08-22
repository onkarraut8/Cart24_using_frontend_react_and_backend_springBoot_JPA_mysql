package com.OnlineShopping.cart24.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShopping.cart24.model.Contact;



@Repository
public interface ContactDao extends JpaRepository<Contact, Integer> {

}
