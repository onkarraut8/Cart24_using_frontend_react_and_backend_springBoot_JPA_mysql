package com.OnlineShopping.cart24.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.OnlineShopping.cart24.model.DeletedProduct;
import com.OnlineShopping.cart24.model.Product;




public class ProductUpdateRequest {

	private int id;
    private String title;
    private String display;
	private String description;
	private int quantity;
    private BigDecimal price;
    private int categoryId;
    private String imageName;
    private String imageName1;
    private String imageName2;
    private String imageName3;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	
	public String getImageName1() {
		return imageName1;
	}
	public void setImageName1(String imageName1) {
		this.imageName1 = imageName1;
	}
	public String getImageName2() {
		return imageName2;
	}
	public void setImageName2(String imageName2) {
		this.imageName2 = imageName2;
	}
	public String getImageName3() {
		return imageName3;
	}
	public void setImageName3(String imageName3) {
		this.imageName3 = imageName3;
	}
	
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	@Override
	public String toString() {
		return "ProductUpdateRequest [id=" + id + ", title=" + title + ", display=" + display + ", description="
				+ description + ", quantity=" + quantity + ", price=" + price + ", categoryId=" + categoryId
				+ ", imageName=" + imageName + ", imageName1=" + imageName1 + ", imageName2=" + imageName2
				+ ", imageName3=" + imageName3 + "]";
	}
    
	public static Product toEntity(ProductUpdateRequest dto) {
		Product entity=new Product();
		BeanUtils.copyProperties(dto, entity, "categoryId");		
		return entity;
	}
	
	public static DeletedProduct toEntityD(ProductUpdateRequest dto) {
		DeletedProduct entity=new DeletedProduct();
		BeanUtils.copyProperties(dto, entity, "categoryId");		
		return entity;
	}
	
	
	
}
