package com.OnlineShopping.cart24.dto;

public class Count {
    
	int countCustomer ;
	int countDelivery ;
	int countTodayOrder ;
	int countThisMonthOrder;
	public int getCountCustomer() {
		return countCustomer;
	}
	public void setCountCustomer(int countCustomer) {
		this.countCustomer = countCustomer;
	}
	public int getCountDelivery() {
		return countDelivery;
	}
	public void setCountDelivery(int countDelivery) {
		this.countDelivery = countDelivery;
	}
	public int getCountTodayOrder() {
		return countTodayOrder;
	}
	public void setCountTodayOrder(int countTodayOrder) {
		this.countTodayOrder = countTodayOrder;
	}
	public int getCountThisMonthOrder() {
		return countThisMonthOrder;
	}
	public void setCountThisMonthOrder(int countThisMonthOrder) {
		this.countThisMonthOrder = countThisMonthOrder;
	}
	@Override
	public String toString() {
		return "Count [countCustomer=" + countCustomer + ", countDelivery=" + countDelivery + ", countTodayOrder="
				+ countTodayOrder + ", countThisMonthOrder=" + countThisMonthOrder + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
