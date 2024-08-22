package com.OnlineShopping.cart24.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import org.springframework.web.multipart.MultipartFile;

import com.OnlineShopping.cart24.model.Product;





public class ProductUpdateImageRequest {

	private int id;
	private String display;
    private String title;
	private String description;
	private int quantity;
    private BigDecimal price;
    private int categoryId;
    private String imageName;
    private String imageName1;
    private String imageName2;
    private String imageName3;
    private MultipartFile image;
    
    private MultipartFile image1;
    private MultipartFile image2;
    private MultipartFile image3;
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
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
	public MultipartFile getImage1() {
		return image1;
	}
	public void setImage1(MultipartFile image1) {
		this.image1 = image1;
	}
	public MultipartFile getImage2() {
		return image2;
	}
	public void setImage2(MultipartFile image2) {
		this.image2 = image2;
	}
	public MultipartFile getImage3() {
		return image3;
	}
	public void setImage3(MultipartFile image3) {
		this.image3 = image3;
	}
	
	public String getDisplay() {
		return display;
	}
	public void setDisplay(String display) {
		this.display = display;
	}
	@Override
	public String toString() {
		return "ProductUpdateImageRequest [id=" + id + ", display=" + display + ", title=" + title + ", description="
				+ description + ", quantity=" + quantity + ", price=" + price + ", categoryId=" + categoryId
				+ ", imageName=" + imageName + ", imageName1=" + imageName1 + ", imageName2=" + imageName2
				+ ", imageName3=" + imageName3 + ", image=" + image + ", image1=" + image1 + ", image2=" + image2
				+ ", image3=" + image3 + "]";
	}
    
    
	public static Product toEntity(ProductUpdateImageRequest dto) {
		Product entity=new Product();
		BeanUtils.copyProperties(dto, entity, "image", "image1", "image2", "image3", "categoryId");		
		return entity;
	}
	
	
	
}
