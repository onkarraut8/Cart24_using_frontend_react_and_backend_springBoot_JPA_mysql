package com.OnlineShopping.cart24.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;
import org.springframework.web.multipart.MultipartFile;

import com.OnlineShopping.cart24.model.Product;



public class UpdateImageRequest {
	private int id;
	private String display;
    private String title;
	private String description;
	private int quantity;
    private BigDecimal price;
    private int categoryId;
    private MultipartFile image;
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
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	@Override
	public String toString() {
		return "UpdateImageRequest [id=" + id + ", display=" + display + ", title=" + title + ", description="
				+ description + ", quantity=" + quantity + ", price=" + price + ", categoryId=" + categoryId
				+ ", image=" + image + "]";
	}
    
	public static Product toEntity(UpdateImageRequest dto) {
		Product entity=new Product();
		BeanUtils.copyProperties(dto, entity, "image", "categoryId");		
		return entity;
	}
    
    
    
    
}
