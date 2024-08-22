package com.OnlineShopping.cart24.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.OnlineShopping.cart24.model.Address;


@Repository
public interface AddressDao extends JpaRepository<Address, Integer> {

}
